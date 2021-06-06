package bg.bilet4e.prototype.shop.rest;

import java.util.Map;

import bg.bilet4e.prototype.ticket.TicketType;

public class StockRequest {

    private Map<TicketType, Integer> stock;

    public StockRequest() {
        
    }
    
    public StockRequest(Map<TicketType, Integer> stock) {
        this.stock = stock;
    }

    public Map<TicketType, Integer> getStock() {
        return stock;
    }

    public void setStock(Map<TicketType, Integer> stock) {
        this.stock = stock;
    }
}
