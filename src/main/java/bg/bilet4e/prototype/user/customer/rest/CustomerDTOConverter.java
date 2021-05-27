package bg.bilet4e.prototype.user.customer.rest;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bg.bilet4e.prototype.user.customer.Customer;

@Component
class CustomerDTOConverter {

    @Autowired
    CustomerDTOConverter() {

    }

    CustomerDTO toDTO(Customer customer) {
        int id = customer.getId();
        String username = customer.getUsername();

        return new CustomerDTO(id, username);
    }

    public List<CustomerDTO> toDTOs(Collection<Customer> customers) {
        return customers.stream() //
                .map(this::toDTO)//
                .collect(Collectors.toList());
    }
}
