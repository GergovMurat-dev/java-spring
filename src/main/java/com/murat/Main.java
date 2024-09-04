package com.murat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SpringBootApplication
@RequestMapping("/api/v1/customers")
public class Main {

    private final CustomerRepository customerRepository;

    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping
    public List<Customer> customers() {
        return this.customerRepository.findAll();
    }

    @PostMapping
    public Customer addCustomer(@RequestBody NewCustomerRequest newCustomer) {
        Customer customer = new Customer();
        customer.setName(newCustomer.name);
        customer.setAge(newCustomer.age);
        customer.setEmail(newCustomer.email);
        this.customerRepository.save(customer);
        return customer;
    }

    @DeleteMapping("{id}")
    public void deleteCustomer(@PathVariable Integer id) {
        this.customerRepository.deleteById(id);
    }

    record NewCustomerRequest(
            String name,
            String email,
            int age
    ) {
    }
}
