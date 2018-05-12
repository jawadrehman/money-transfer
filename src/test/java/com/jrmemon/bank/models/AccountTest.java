package com.jrmemon.bank.models;

import javafx.scene.AmbientLight;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    Account test;
    String accountName;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        accountName = "testAcc";
         test = new Account(accountName);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @Test
    void createAccount(){


        assertNotNull(test);
        assertEquals(accountName, test.getAccountName());

    }

    @Test
    void depositAmount(){

        BigDecimal amount = new BigDecimal(1000);
        assertNotEquals(test.getAmount(), amount);
        test.depositAmmount(amount);

        assertEquals(test.getAmount(), amount);
    }

    @Test
    void creditAmount(){
        BigDecimal amount = test.getAmount();
        BigDecimal depositAmount = new BigDecimal(100);
        test.depositAmmount(depositAmount);
        //amount should be initial value
        try{
            test.creditAmmount(depositAmount );
            assertEquals(test.getAmount(), amount);
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            test.creditAmmount(amount);

        }catch (Exception e){
            //EXPECTING EXCEPTION
            assertEquals(amount, test.getAmount());
            e.printStackTrace();
        }







    }
}