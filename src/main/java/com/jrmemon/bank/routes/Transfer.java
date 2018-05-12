package com.jrmemon.bank.routes;

import java.math.BigDecimal;

import static spark.Spark.*;

public class Transfer {

    public static void configure() {
        path("/transfer", () -> {
            before("/*", (q, a) -> System.out.println("Received api call"));
            get("/:id", (request, response) -> false);
            post("/:fromId/:toId/:amount", (request, response) -> {
                boolean transaction = com.jrmemon.bank.models.Transfer.transferTransaction(request.params("fromId"), request.params("toId"), new BigDecimal(request.params("amount")));
               if(transaction){
                   return "successful transfer";
               }else{
                   response.status(400);
                   return "an error occured";
               }
            });
        });
    }
}
