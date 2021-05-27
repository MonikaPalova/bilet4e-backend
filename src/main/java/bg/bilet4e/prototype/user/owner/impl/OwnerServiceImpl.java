package bg.bilet4e.prototype.user.owner.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bg.bilet4e.prototype.user.owner.Owner;
import bg.bilet4e.prototype.user.owner.OwnerRepository;
import bg.bilet4e.prototype.user.owner.OwnerService;

@Component
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    @Autowired
    OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;

    }

    @Override
    public List<Owner> fetchAll() {
        List<Owner> owners = new ArrayList<>();
        ownerRepository.findAll().forEach(owners::add);

        return owners;
    }

    @Override
    public Owner create(String username, String password) {
        Owner owner = new Owner(username, password);
        return ownerRepository.save(owner);
    }

    @Override
    public Optional<Owner> fetchById(int ownerId) {
        return ownerRepository.findById(ownerId);
    }

    @Override
    public void update(Owner owner) {
        ownerRepository.save(owner);
    }

}
