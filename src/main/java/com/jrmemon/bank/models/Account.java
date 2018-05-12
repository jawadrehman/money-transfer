package com.jrmemon.bank.models;

import java.math.BigDecimal;

public class Account {
    private String accountName;
    private BigDecimal amount;

    public Account(String accountName){
        this.accountName = accountName;
        this.amount = new BigDecimal(0);
    }

    public void setAccountName(String accountName){
        this.accountName = accountName;
    }

    public String getAccountName(){
        return this.accountName;
    }


    public BigDecimal depositAmmount(BigDecimal amount){
        this.amount = this.amount.add(amount);
        return this.amount;
    }

    public BigDecimal creditAmmount(BigDecimal amount) throws Exception{
        if(amount.compareTo(this.amount) > 0){
            throw new Exception("Insufficient amount in account");
        }
        this.amount =  this.amount.subtract(amount);
        return this.amount;
    }

    public BigDecimal getAmount(){
        return this.amount;
    }

    public String toString(){
        return "Account " + this.accountName +  " with amount " + this.amount;
    }

    public static Account create(String uuid, String name){
        Account account = new Account(name);
        Store.accountStore.put(uuid, account );
        return account;

    }

}
