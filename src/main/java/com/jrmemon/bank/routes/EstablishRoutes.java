package com.jrmemon.bank.routes;

public class EstablishRoutes {
    public void setupRoutes(){
        Transfer.configure();
        Accounts.configure();
    }
}

