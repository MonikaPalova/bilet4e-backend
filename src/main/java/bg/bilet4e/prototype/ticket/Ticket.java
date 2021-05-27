package bg.bilet4e.prototype.ticket;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank
    private String customerId;

    @NotBlank
    private TicketType type;

    // TODO find a way to save the qr code and include its creation time

    public Ticket() {
        // used by Spring
    }
}
