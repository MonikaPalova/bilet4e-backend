package bg.bilet4e.prototype.user.customer.rest;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bg.bilet4e.prototype.shop.Shop;
import bg.bilet4e.prototype.shop.rest.ShopDTO;
import bg.bilet4e.prototype.shop.rest.ShopDTOConverter;
import bg.bilet4e.prototype.user.customer.Customer;

@Component
class CustomerDTOConverter {

    private final ShopDTOConverter shopConverter;
    
    @Autowired
    CustomerDTOConverter(ShopDTOConverter shopConverter) {
        this.shopConverter = shopConverter;
    }

    CustomerDTO toDTO(Customer customer) {
        int id = customer.getId();
        String username = customer.getUsername();
        List<Shop> favoriteShops = customer.getFavoriteShops();
        List<ShopDTO> favoriteShopDtos = shopConverter.toDTOs(favoriteShops);

        return new CustomerDTO(id, username,favoriteShopDtos);
    }

    public List<CustomerDTO> toDTOs(Collection<Customer> customers) {
        return customers.stream() //
                .map(this::toDTO)//
                .collect(Collectors.toList());
    }
}
