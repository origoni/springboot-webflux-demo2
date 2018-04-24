package com.millky.demo.post;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/annotation/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostRepository repository;

    @PostMapping
    public Mono<ResponseEntity<Post>> create(@Valid @RequestBody Post post) {
        return repository.insert(post).map(p -> new ResponseEntity<>(p, HttpStatus.CREATED));
    }

    // https://en.wikipedia.org/wiki/Representational_state_transfer#Relationship_between_URL_and_HTTP_methods
    @PatchMapping("/{id}")
    public Mono<ResponseEntity<Post>> update(@PathVariable String id, @Valid @RequestBody Post post) {
        return repository.findById(id)
                .flatMap(foundPost -> {
                    foundPost.setSubject(post.getSubject());
                    foundPost.setContent(post.getContent());
                    return repository.save(foundPost);
                })
                .map(p -> new ResponseEntity<>(p, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public Flux<Post> list() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Post> read(@PathVariable String id) {
        return repository.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Mono<Void> deleteCustomer(@PathVariable String id) {
        return repository.deleteById(id);
    }

}
