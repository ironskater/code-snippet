package codesnippet.webflux.simple_webflux;

import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class GreetingHandler {

  public Mono<ServerResponse> hello(ServerRequest request) {

    BodyInserter<?, ? super ServerHttpResponse> inserter =
        BodyInserters.fromValue(new Greeting("=============== Hello, Spring! ==============="));

    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(inserter);
  }
}