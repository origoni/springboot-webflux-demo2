package com.millky.demo.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Validator;
import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.ServerResponse.noContent;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;


@Service
@RequiredArgsConstructor
public class PostHandler {

    private final PostRepository repository;

    // https://stackoverflow.com/questions/46508618/spring-5-webflux-functional-endpoints-how-to-perform-input-validation/46540102#46540102
    private final Validator validator;

    public Mono<ServerResponse> create(ServerRequest request) {
        return request
                .bodyToMono(Post.class)
                .flatMap(body -> validator.validate(body).isEmpty()
                        ?
                        repository.insert(body)
                                .flatMap(p -> ServerResponse
                                        .created(URI.create("/api/v1/function/posts/" + p.getId()))
                                        .body(fromObject(p)))
                        :
                        ServerResponse.unprocessableEntity().build() // badRequest()
                );
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        String id = request.pathVariable("id");
        return request
                .bodyToMono(Post.class)
                .flatMap(body -> validator.validate(body).isEmpty()
                        ?
                        repository.findById(id)
                                .flatMap(foundPost -> {
                                    foundPost.setSubject(body.getSubject());
                                    foundPost.setContent(body.getContent());
                                    return repository.save(foundPost);
                                })
                                .flatMap(p -> ServerResponse
                                        .ok()
                                        .body(fromObject(p)))
                                .switchIfEmpty(notFound().build())
                        :
                        ServerResponse.unprocessableEntity().build()
                );
    }

    public Mono<ServerResponse> list(ServerRequest request) {
        Flux<Post> people = this.repository.findAll();
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(people, Post.class);
    }

    public Mono<ServerResponse> read(ServerRequest request) {
        String id = request.pathVariable("id");
        return repository
                .findById(id)
                .flatMap(p -> ServerResponse.ok().contentType(APPLICATION_JSON).body(fromObject(p)))
                .switchIfEmpty(notFound().build());
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        String id = request.pathVariable("id");
        return repository
                .findById(id)
                .flatMap(p -> noContent().build(repository.delete(p)))
                .switchIfEmpty(notFound().build());
    }
}
