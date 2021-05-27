package bg.bilet4e.prototype.shop.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import bg.bilet4e.prototype.shop.Coordinates;
import bg.bilet4e.prototype.shop.Shop;
import bg.bilet4e.prototype.shop.ShopRepository;
import bg.bilet4e.prototype.shop.ShopService;
import bg.bilet4e.prototype.user.owner.Owner;
import bg.bilet4e.prototype.user.owner.OwnerService;

@Component
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final OwnerService ownerService;

    @Autowired
    public ShopServiceImpl(ShopRepository shopRepository, OwnerService ownerService) {
        this.shopRepository = shopRepository;
        this.ownerService = ownerService;
    }

    @Override
    public Shop create(int ownerId, String name, Coordinates coordinates) {
        Owner owner = getOwner(ownerId);
        checkIfShopLimitReached(ownerId);

        Shop shop = new Shop(name, owner, coordinates);
        Shop createdShop = shopRepository.save(shop);

        addShopToOwner(createdShop, owner);

        return createdShop;
    }

    private Owner getOwner(int ownerId) {
        return ownerService.fetchById(ownerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Owner with id [" + ownerId + "] doesnt exist"));
    }

    private void addShopToOwner(Shop createdShop, Owner owner) {
        owner.addShop(createdShop);
        ownerService.update(owner);
    }

    private void checkIfShopLimitReached(int ownerId) { // TODO : move to owner service
        List<Shop> ownerShops = fetchByOwnerId(ownerId);
        if (ownerShops.size() >= 6) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Owner with id [" + ownerId + "] has reached the limit of 6 shops.");
        }
    }

    @Override
    public Optional<Shop> fetchById(int shopId) {
        return shopRepository.findById(shopId);
    }

    @Override
    public List<Shop> fetchByOwnerId(int ownerId) {
        return shopRepository.findByOwnerId(ownerId);
    }

    @Override
    public List<Shop> fetchAll() {
        List<Shop> shops = new ArrayList<>();
        shopRepository.findAll().forEach(shops::add);

        return shops;
    }

    @Override
    public void deleteById(int shopId) {
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Shop with id [" + shopId + "] doesn't exist"));

        Owner owner = shop.getOwner();
        owner.removeShop(shopId);
        ownerService.update(owner);

        shopRepository.deleteById(shopId);
    }

}
