package controller;

import model.Client;

public class ClientController {
    Client client;
    public ClientController(Client client){
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
