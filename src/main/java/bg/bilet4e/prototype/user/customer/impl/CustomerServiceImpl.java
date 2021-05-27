package bg.bilet4e.prototype.user.customer.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bg.bilet4e.prototype.user.customer.Customer;
import bg.bilet4e.prototype.user.customer.CustomerRepository;
import bg.bilet4e.prototype.user.customer.CustomerService;

@Component
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;

    }

    @Override
    public List<Customer> fetchAll() {
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);

        return customers;
    }

    @Override
    public Customer create(String username, String password) {
        Customer customer = new Customer(username, password);
        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> fetchById(int customerId) {
        return customerRepository.findById(customerId);
    }

    @Override
    public Optional<Customer> fetchByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

}
