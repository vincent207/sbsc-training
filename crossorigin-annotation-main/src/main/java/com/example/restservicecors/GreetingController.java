package com.example.restservicecors;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";

	private final AtomicLong counter = new AtomicLong();

	//@CrossOrigin //DO NOT USE THIS FOR PRODUCTION!!
	//@CrossOrigin() //DO NOT USE THIS FOR PRODUCTION!!
	@CrossOrigin("*")  //DO NOT USE THIS FOR PRODUCTION!!
	//@CrossOrigin(origins = "http://192.168.1.15:8080")
	//@CrossOrigin(origins = "http://spring-cors.127.0.0.1.nip.io:8080")
	//@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(required = false, defaultValue = "World") String name) {
		System.out.println("==== get greeting ====");
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	@GetMapping("/greeting-javaconfig")
	public Greeting greetingWithJavaconfig(@RequestParam(required = false, defaultValue = "World") String name) {
		System.out.println("==== in greeting ====");
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	@GetMapping("/listheaders")
	public ResponseEntity<String> listAllHeaders(
			@RequestHeader Map<String, String> headers) {
		headers.forEach((key, value) -> {
			System.out.println(String.format("Header '%s' = %s", key, value));
		});

		return new ResponseEntity<String>(
				String.format("Listed %d headers", headers.size()), HttpStatus.OK);
	}
}
