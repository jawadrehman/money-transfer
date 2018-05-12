package com.jrmemon.bank.models;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TransferTest {

    Account from, to;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        from = Account.create("a","from");
        to = Account.create("b","to");

    }


    @Test
    void transferMoney(){
        BigDecimal initialDeposit = new BigDecimal(90);

        from.depositAmmount(initialDeposit);

        //transfering more than whats present.
        Transfer.transferTransaction("a", "b", new BigDecimal(100));


        assertEquals(initialDeposit, from.getAmount());

        Transfer.transferTransaction("a", "b", new BigDecimal(45));

        assertEquals(new BigDecimal(45), from.getAmount());
        assertEquals(new BigDecimal(45), to.getAmount());



    }
}