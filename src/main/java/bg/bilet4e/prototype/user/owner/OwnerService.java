package bg.bilet4e.prototype.user.owner;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public interface OwnerService {

    List<Owner> fetchAll();

    Owner create(String username, String password);

    Optional<Owner> fetchById(int ownerId);

    void update(Owner owner);
}

