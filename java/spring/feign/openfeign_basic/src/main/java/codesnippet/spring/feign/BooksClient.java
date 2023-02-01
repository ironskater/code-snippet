package codesnippet.spring.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// value can be arbitrary client name
@FeignClient(
    value = "bookClient",
    url = "${book.service.url}"
)
public interface BooksClient {

    // @RequestLine("GET /books")
    // @Headers({
    //     "Content-Type: application/json",
    //     "Accept: application/json"
    // })
    @GetMapping(value = "/books", consumes = {"application/json"}, produces = {"application/json"})
    List<Book> getBooks();

    // @RequestLine("GET /books/{id}")
    // @Headers({
    //     "Content-Type: application/json",
    //     "Accept: application/json"
    // })
    @GetMapping(value = "/books/{id}", consumes = {"application/json"}, produces = {"application/json"})
    Book getBookByIsbn(@PathVariable("id") String id);
}
