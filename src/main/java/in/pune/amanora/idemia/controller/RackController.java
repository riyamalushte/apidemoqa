package in.pune.amanora.idemia.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.pune.amanora.idemia.exception.NotFoundException;
import in.pune.amanora.idemia.model.Rack;
import in.pune.amanora.idemia.util.ErrorMessage;

@RestController
public class RackController {

	public static Rack rack = new Rack();

	/*
	 * Get Rack details
	 * 
	 * @author Shraddha
	 */
	@RequestMapping(value = "/rack")
	public Rack getRack() {

		return rack;
	}

	/*
	 * Add new Rack
	 * 
	 * @RequestBody Rack object
	 * 
	 * @author Shraddha
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/rack")
	public Rack addRack(@RequestBody Rack rack) {
		if (RackController.rack.getId() == 0) {
			RackController.rack.setId(rack.getId());
			RackController.rack.setNoOfShelves(rack.getNoOfShelves());
			RackController.rack.setShelves(rack.getShelves());
		}
		return RackController.rack;
	}

	/*
	 * below API is for updating rack properties
	 * 
	 * @PathVariable id rackid
	 * 
	 * @RequestBody rack object
	 * 
	 * @author Shraddha
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/rack/{id}")
	public Rack updateRack(@PathVariable("id") long id, @RequestBody Rack rack)
			throws NotFoundException {
		if (RackController.rack != null) {
			if (RackController.rack.getId() == id) {
				if (rack.getId() != 0)
					RackController.rack.setId(rack.getId());
				if (rack.getNoOfShelves() != 0)
					RackController.rack.setNoOfShelves(rack.getNoOfShelves());
				if (!rack.getShelves().isEmpty())
					RackController.rack.setShelves(rack.getShelves());

				return rack;
			}

		}
		throw new NotFoundException(ErrorMessage.RACK_ID_NOT_FOUND);

	}

	/*
	 * below API is for deleting rack properties
	 * 
	 * @PathVariable id rackid
	 * 
	 * @RequestBody rack object
	 * 
	 * @author Riya
	 */

	@RequestMapping(method = RequestMethod.DELETE, value = "/rack/{id}")
	public Rack removerack(@PathVariable("id") long id)
			throws NotFoundException {
		if (RackController.rack != null) {
			if (RackController.rack.getId() == id) {
				RackController.rack = new Rack();

				return RackController.rack;
			}

		}
		throw new NotFoundException(ErrorMessage.RACK_ID_NOT_FOUND);
	}
}