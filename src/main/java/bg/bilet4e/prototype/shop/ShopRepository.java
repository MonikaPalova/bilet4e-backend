package bg.bilet4e.prototype.shop;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface ShopRepository extends CrudRepository<Shop, Integer> {

    List<Shop> findByOwnerId(int ownerId);

}
