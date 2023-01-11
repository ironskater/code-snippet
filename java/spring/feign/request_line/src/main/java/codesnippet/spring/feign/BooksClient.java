package codesnippet.spring.feign;

import java.util.List;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface BooksClient {

    @RequestLine("GET /books")
    @Headers({
        "Content-Type: application/json",
        "Accept: application/json"
    })
    List<Book> getBooks();

    @RequestLine("GET /books/{id}")
    @Headers({
        "Content-Type: application/json",
        "Accept: application/json"
    })
    Book getBookByIsbn(@Param("id") String id);
}
