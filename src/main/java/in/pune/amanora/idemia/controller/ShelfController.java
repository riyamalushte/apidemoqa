package in.pune.amanora.idemia.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.pune.amanora.idemia.exception.CommonException;
import in.pune.amanora.idemia.exception.NotFoundException;
import in.pune.amanora.idemia.model.Shelf;
import in.pune.amanora.idemia.util.ErrorMessage;

@RestController
public class ShelfController {

	/*
	 * Get all shelves in a rack
	 * 
	 * @author Shraddha
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/shelves")
	public List<Shelf> getShelves() throws NotFoundException {
		if (RackController.rack.getShelves() != null && !RackController.rack.getShelves().isEmpty()) {
			return RackController.rack.getShelves();
		} else {
			throw new NotFoundException(ErrorMessage.NO_SHELF);
		}
	}

	/*
	 * Add new shelf in a rack
	 * 
	 * @RequestBody Shelf object
	 * 
	 * @author Shraddha
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/shelves")
	public Shelf addShelf(@RequestBody Shelf shelf) throws CommonException {
		for(int i=0; i< RackController.rack.getShelves().size() ; i++)
			if(RackController.rack.getShelves().get(i).getId() == shelf.getId())
				throw new CommonException(ErrorMessage.Dublicate_Shelf);
			
		
		if (RackController.rack.getShelves().size() >= RackController.rack.getNoOfShelves()) {
			throw new CommonException(ErrorMessage.SHELF_NOT_FOUND);
		} else {
			RackController.rack.getShelves().add(shelf);
			return shelf;
		}
		}

	

	/*
	 * Get shelf details for requested shelf Id
	 * 
	 * @author Riya
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/shelves/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Shelf getShelfDetails(@PathVariable("id") long id) throws NotFoundException {
		if (RackController.rack.getShelves() != null && !RackController.rack.getShelves().isEmpty()) {
			for (int i = 0; i < RackController.rack.getShelves().size(); i++) {
				if (RackController.rack.getShelves().get(i).getId() == id) {
					return RackController.rack.getShelves().get(i);
				}
			}
		}
		throw new NotFoundException(ErrorMessage.SHELF_NOT_FOUND);
	}

	/*
	 * Delete a particular shelf from a rack
	 * 
	 * @Path variable needed for shelfid 'id'
	 * 
	 * @Author : Shraddha
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/shelves/{id}")
	public Shelf removeShelfDetails(@PathVariable("id") long id) throws NotFoundException {
		if (RackController.rack.getShelves() != null && !RackController.rack.getShelves().isEmpty()) {
			for (int i = 0; i < RackController.rack.getShelves().size(); i++) {
				if (RackController.rack.getShelves().get(i).getId() == id) {
					return RackController.rack.getShelves().remove(i);
				}
			}
		}
		throw new NotFoundException(ErrorMessage.SHELF_NOT_FOUND);
	}

	/*
	 * Updated Shelf details as per Id provided Params : @PathVariable id : Shelf Id
	 * 
	 * @RequestBody : Shelf object
	 * 
	 * @author Riya
	 */

	@RequestMapping(method = RequestMethod.PUT, value = "/shelves/{id}")
	public Shelf updateShelf(@PathVariable("id") long id, @RequestBody Shelf shelf) throws NotFoundException {
		if (RackController.rack.getShelves() != null && !RackController.rack.getShelves().isEmpty()) {
			for (int i = 0; i < RackController.rack.getShelves().size(); i++) {
				if (RackController.rack.getShelves().get(i).getId() == id) {
					if (shelf.getId() != 0)
						RackController.rack.getShelves().get(i).setId(shelf.getId());
					if (shelf.getCapacity() != 0)
						RackController.rack.getShelves().get(i).setCapacity(shelf.getCapacity());
					if (!shelf.getBooks().isEmpty())
						RackController.rack.getShelves().get(i).setBooks(shelf.getBooks());

					return shelf;
				}
			}
		}
		throw new NotFoundException(ErrorMessage.SHELF_NOT_FOUND);

	}

}
