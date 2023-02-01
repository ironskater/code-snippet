package codesnippet.spring.feign;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.StreamUtils;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

@EnableFeignClients
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
    WireMockConfig.class,
    FeignAutoConfiguration.class,
    HttpMessageConvertersAutoConfiguration.class
}, initializers = ConfigDataApplicationContextInitializer.class)
@TestPropertySource("/application-test.properties")
public class BooksClientTest {

    @Autowired
    private WireMockServer wireMockServer;

    @Autowired
    private BooksClient booksClient;

    @Test
    public void testGetBooks() throws IOException{
        // Arrange
        wireMockServer.stubFor(
            WireMock.get(WireMock.urlEqualTo("/books"))
                .willReturn(
                    WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(
                            StreamUtils.copyToString(
                                BooksClientTest.class.getClassLoader()
                                    .getResourceAsStream("payload/get-books-response.json"),
                                    Charset.defaultCharset())))
        );

        // Act
        List<BookImmutable> books = booksClient.getBooks().stream()
            .map(e -> (BookImmutable)e).collect(Collectors.toList());

        // Assert
        Assertions.assertThat(books)
            .extracting(
                BookImmutable::getTitle,
                BookImmutable::getAuthor,
                BookImmutable::getIsbn
            )
            .containsExactlyInAnyOrder(
                Assertions.tuple("Dune", "Frank Herbert", "1447264533"),
                Assertions.tuple("Foundation",  "Isaac Asimov", "0451524934")
            );
    }

    @Test
    public void testGetBookByIsbn() throws IOException{
        // Arrange
        String body = StreamUtils.copyToString(
            BooksClientTest.class.getClassLoader().getResourceAsStream("payload/get-book-by-isbn-response.json"),
            Charset.defaultCharset());

        wireMockServer.stubFor(
            WireMock.get(WireMock.urlEqualTo("/books/1447264533"))
                .willReturn(
                    WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(body)
                )
        );

        // Act
        BookImmutable book = (BookImmutable) booksClient.getBookByIsbn("1447264533");

        // Assert
        Assertions.assertThat(book).usingRecursiveComparison()
            .isEqualTo(new Book("Dune", "Frank Herbert", "1447264533"));
    }
}
