package com.example.demo;

import java.util.Optional;
import java.util.function.Function;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@Bean
	public Function<Flux<Object>, Object> uppercase() {
		return objectFlux -> objectFlux
			.map(o -> Optional
				.of(o)
				.filter(o1 -> o1 instanceof Tuple2)
				.map(o1 -> (Object) ((Tuple2<?, ?>) o1).toArray())
				.orElse(o))
			.map(o -> Optional
				.of(o)
				.filter(o1 -> o1 instanceof String)
				.map(o1 -> (Object) (((String) o1).toUpperCase()))
				.orElse(o));
	}

	@Bean
	public Function<Flux<String>, Flux<Object>> exclaimer() {
		return objectFlux -> objectFlux.map(s -> Tuples.of("wow " + s + " !!!", s.length() * 2));
	}

}
