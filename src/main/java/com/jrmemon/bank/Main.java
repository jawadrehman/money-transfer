package com.jrmemon.bank;

import com.jrmemon.bank.routes.EstablishRoutes;

import static spark.Spark.*;


public class Main {
    public static void main(String[] args){

        port(8081);

        EstablishRoutes routes = new EstablishRoutes();
        routes.setupRoutes();


        get("/hello", (req, res) -> "Hello World");

    }
}
