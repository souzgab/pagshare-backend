package br.com.payshare.model;

import br.com.payshare.interfaces.Taxes;
/*** @Autor vinicius Alves ***/
public class Lobby implements Taxes {

    private String lobbyDescription;
    private OrderService orderService;

    public Lobby(String lobbyDescription, OrderService orderService) {
        this.lobbyDescription = lobbyDescription;
        this.orderService = orderService;
    }

    public String getLobbyDescription() {
        return lobbyDescription;
    }

    public void setLobbyDescription(String lobbyDescription) {
        this.lobbyDescription = lobbyDescription;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String toString() {
        return "Lobby{" +
                "lobbyDescription='" + lobbyDescription + '\'' +
                ", orderService=" + orderService +
                '}';
    }
}
