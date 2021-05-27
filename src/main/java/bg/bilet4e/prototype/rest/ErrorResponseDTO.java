package bg.bilet4e.prototype.rest;

public class ErrorResponseDTO {

    public ErrorResponseDTO(String message) {
        this.message = message;
    }
 
    private String message;

    public String getMessage() {
        return message;
    }
}
