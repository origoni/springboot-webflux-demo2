package com.millky.demo.post;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.PATCH;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;
import static org.springframework.web.reactive.function.server.RequestPredicates.method;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class PostRouter {

    @Bean
    public RouterFunction<ServerResponse> routingFunction(PostHandler handler) {

        // https://en.wikipedia.org/wiki/Representational_state_transfer#Relationship_between_URL_and_HTTP_methods

        //@formatter:off
		RouterFunction<ServerResponse> personRoute =
				nest(path("/api/v1/function/posts"),
							nest(accept(APPLICATION_JSON),
									route(GET("/{id}"), handler::read)
								.andRoute(method(HttpMethod.GET), handler::list))
						.andNest(accept(APPLICATION_JSON).and(contentType(APPLICATION_JSON)),
									route(POST(""), handler::create)
								.andRoute(PATCH("/{id}"), handler::update))
						.andRoute(DELETE("/{id}"), handler::delete));
		//@formatter:on

        return personRoute;
    }

}
