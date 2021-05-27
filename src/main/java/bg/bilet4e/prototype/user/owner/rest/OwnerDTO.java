package bg.bilet4e.prototype.user.owner.rest;

public class OwnerDTO {

    private int id;

    private String username;

    public OwnerDTO(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

}
