package codesnippet.spring.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import codesnippet.spring.rest.jpa.Book;
import codesnippet.spring.rest.service.BookService;

@RestController
@RequestMapping(value = "/api")
public class BookController
{
	@Autowired
	private BookService bookService;

	@GetMapping(value = "/v1/book",
				produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Book>>
		getAllBook()
	{
		return new ResponseEntity<>(bookService.getAll(),
									HttpStatus.OK);
	}

	@GetMapping(value = "/v1/book/delete",
				produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void>
		deleteAllBook()
	{
		throw new RuntimeException("Not Impl");
	}
}
