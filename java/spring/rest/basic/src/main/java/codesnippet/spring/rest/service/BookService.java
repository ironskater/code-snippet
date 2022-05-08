package codesnippet.spring.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codesnippet.spring.rest.jpa.Book;
import codesnippet.spring.rest.jpa.BookDao;

@Service
public class BookService
{
	@Autowired
	private BookDao bookDao;

	public List<Book>
		getAll()
	{
		return bookDao.findAll();
	}
}
