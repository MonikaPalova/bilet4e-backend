package bg.bilet4e.prototype.shop;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public interface ShopService {

    Shop create(int ownerId, String name, Coordinates coordinates);

    Optional<Shop> fetchById(int shopId);

    List<Shop> fetchByOwnerId(int ownerId);

    List<Shop> fetchAll();

    void deleteById(int shopId);

    void update(Shop shop);

}
