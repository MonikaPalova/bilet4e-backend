package bg.bilet4e.prototype.user.customer.rest;

class CustomerDTO {

    private int id;

    private String username;

    public CustomerDTO(int id, String username) {
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
