package bg.bilet4e.prototype.shop.rest;

import java.util.EnumMap;

import bg.bilet4e.prototype.ticket.TicketType;

public class StockRequest {

    private EnumMap<TicketType, Integer> stock;

    public StockRequest() {
        
    }
    
    public StockRequest(EnumMap<TicketType, Integer> stock) {
        this.stock = stock;
    }

    public EnumMap<TicketType, Integer> getStock() {
        return stock;
    }

    public void setStock(EnumMap<TicketType, Integer> stock) {
        this.stock = stock;
    }
}
