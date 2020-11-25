package com.demo.controler;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.pojo.Employee;
import com.demo.pojo.Greeting;
import com.demo.repository.EmployeeRepository;

@RestController
public class Controller {

	@GetMapping("/greeting")
	public String gretting() {
		return "hello world";

	}

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	// test with http://localhost:8080/greetingName?name=User
	// $ curl localhost:8080/greeting
	@GetMapping("/greetingName")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	private final EmployeeRepository repository;

	Controller(EmployeeRepository repository) {
	    this.repository = repository;
	  }

	@GetMapping("/employees")
	List<Employee> all() {
		return repository.findAll();
	}

}
