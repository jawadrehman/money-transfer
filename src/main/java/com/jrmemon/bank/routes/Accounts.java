package com.jrmemon.bank.routes;

import com.jrmemon.bank.models.Account;
import com.jrmemon.bank.models.Store;

import java.math.BigDecimal;
import java.util.*;

import static spark.Spark.*;


public class Accounts{

    public static void configure() {

        HashMap<String, Account> accounts = (HashMap<String, Account>) Store.accountStore;

        path("/accounts", () -> {

            get("", (req, res)->{
               return accounts;
            });

            get("/:id", (request, response) ->
                accounts.get(request.params("id"))
            );

            put("", ((request, response)->{
               String uuid = UUID.randomUUID().toString();
               Account account =  Account.create(uuid, request.queryParams("accountName"));
               return uuid + " " +  account;
            }));

            post("/:id/credit/:amount", (request, response) -> {

                Account account = accounts.get(request.params("id"));
                try{
                    account.creditAmmount(new BigDecimal(request.params("amount")));
                    return account;
                }catch(Exception e){
                    response.status(400);
                    return e;
                }


            });

            post("/:id/deposit/:amount", (request, response) -> {
                Account account = accounts.get(request.params("id"));
                account.depositAmmount(new BigDecimal(request.params("amount")));
                return account;
            });


        });

    }

}
