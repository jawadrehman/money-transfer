package com.jrmemon.bank.models;

import java.math.BigDecimal;

public class Transfer {
    public static BigDecimal zeroBigDecimal = new BigDecimal(0);

    public static boolean transferTransaction(String fromId, String toId, BigDecimal amount){
        Account fromAccount = Store.accountStore.get(fromId);
        Account toAccount = Store.accountStore.get(toId);


        if((fromAccount == null || (fromAccount.getAmount().compareTo(zeroBigDecimal) <=0) || fromAccount.getAmount().compareTo(amount) < 0)|| toAccount == null || amount.compareTo(zeroBigDecimal) <= 0 ){
            return false;
        }else {
            try {

                //check if successful credit transaction
                fromAccount.creditAmmount(amount);

                toAccount.depositAmmount(amount);
                return true;

            }catch(Exception e) {
                e.printStackTrace();

                return false;
            }
        }
    }
}
