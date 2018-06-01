package in.pune.amanora.idemia.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.pune.amanora.idemia.exception.NotFoundException;
import in.pune.amanora.idemia.model.Book;
import in.pune.amanora.idemia.util.ErrorMessage;

@RestController
public class BookController {

	/*
	 * Get books in a particular shelf
	 * 
	 * @Path variable 'id' : for shelf id
	 * 
	 * @Author : Swarshri
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/shelves/{id}/books")
	public List<Book> getBooks(@PathVariable("id") long id) throws NotFoundException {
		for (int i = 0; i < RackController.rack.getShelves().size(); i++) {
			if (RackController.rack.getShelves().get(i).getId() == id) {
				return RackController.rack.getShelves().get(i).getBooks();
			}
		}
		throw new NotFoundException(ErrorMessage.SHELF_NOT_FOUND);
	}

	/*
	 * Get a particular book details in a particular shelf.
	 * 
	 * @ Two Path variable needed for shelfid 'id' & bookid 'bookid'
	 * 
	 * @Author : Swarshri
	 */

	@RequestMapping(method = RequestMethod.GET, value = "/shelves/{id}/books/{bookid}")
	public Book getBookDetails(@PathVariable("id") long id, @PathVariable("bookid") long bookid)
			throws NotFoundException {
		for (int i = 0; i < RackController.rack.getShelves().size(); i++) {

			if (RackController.rack.getShelves().get(i).getId() == id) {
				for (int j = 0; j < RackController.rack.getShelves().get(i)
						.getBooks().size(); j++) {

					if (RackController.rack.getShelves().get(i).getBooks()
							.get(j).getId() == bookid) {
						return RackController.rack.getShelves().get(i)
								.getBooks().get(j);
					}

					//

				}
				throw new NotFoundException(ErrorMessage.BOOK_NOT_FOUND);

			}

		}
		throw new NotFoundException(ErrorMessage.SHELF_NOT_FOUND);

	}

	/*
	 * Delete a particular book in a shelf
	 * 
	 * @ Two Path variable needed for shelfid 'id' & bookid 'bookid'
	 * 
	 * @Author : Swarshri
	 */

	@RequestMapping(method = RequestMethod.DELETE, value = "/shelves/{id}/books/{bookid}")
	public Book deleteBook(@PathVariable("id") long id, @PathVariable("bookid") long bookid) throws NotFoundException {
		for (int i = 0; i < RackController.rack.getShelves().size(); i++) {

			if (RackController.rack.getShelves().get(i).getId() == id) {
				for (int j = 0; j < RackController.rack.getShelves().get(i).getBooks().size(); j++) {

					if (RackController.rack.getShelves().get(i).getBooks().get(j).getId() == bookid) {
						return RackController.rack.getShelves().get(i).getBooks().remove(j);
					} else {
						throw new NotFoundException(ErrorMessage.BOOK_NOT_FOUND);

					}
				}
			}

		}
		throw new NotFoundException(ErrorMessage.SHELF_NOT_FOUND);

	}

	/*
	 * Below API is for adding new Book in a shelf
	 * 
	 * @RequestBody Book object
	 * 
	 * @author Riya
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/shelves/{id}/books")
	public Book addBook(@PathVariable("id") long id, @RequestBody Book book) throws NotFoundException{
		for (int i = 0; i < RackController.rack.getShelves().size(); i++) {
			if (RackController.rack.getShelves().get(i).getId() == id) {
				RackController.rack.getShelves().get(i).getBooks().add(book);
				return book;
			}
		}
		throw new NotFoundException(ErrorMessage.SHELF_NOT_FOUND);
	}

	/*
	 * below API is for updating book properties
	 * 
	 * two @PathVariable is given 1st(id) is for shelf and 2nd(bookid) is for Book
	 * 
	 * @author Shraddha
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/shelves/{id}/books/{bookid}")
	public Book updateBook(@PathVariable("id") long id, @RequestBody Book book, @PathVariable("bookid") long bookid)
			throws NotFoundException {
		for (int i = 0; i < RackController.rack.getShelves().size(); i++) {

			if (RackController.rack.getShelves().get(i).getId() == id) {
				for (int j = 0; j < RackController.rack.getShelves().get(i).getBooks().size(); j++) {

					if (RackController.rack.getShelves().get(i).getBooks().get(j).getId() == bookid) {
						if ((!book.getAuthor().equals(null))) {
							RackController.rack.getShelves().get(i).getBooks().get(j).setAuthor(book.getAuthor());
						}
						if ((!book.getName().equals(null))) {
							RackController.rack.getShelves().get(i).getBooks().get(j).setName(book.getName());
						}
						if ((book.getId() != 0)) {
							RackController.rack.getShelves().get(i).getBooks().get(j).setId(book.getId());
						}
						if ((book.getNoOfPages() != 0)) {
							RackController.rack.getShelves().get(i).getBooks().get(j).setNoOfPages(book.getNoOfPages());
						}
					}
				}
				throw new NotFoundException(ErrorMessage.BOOK_NOT_FOUND);
			} 
		}
		throw new NotFoundException(ErrorMessage.SHELF_NOT_FOUND);

	}
}
