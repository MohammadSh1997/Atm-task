package com.progressoft.induction.atm;

import java.math.BigDecimal;

public class Account implements BankingSystem {
    String accountNumber;
    BigDecimal amount;

    public Account(String accountNumber, BigDecimal amount) {
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public BigDecimal getAccountBalance(String accountNumber) {
        return amount;
    }

    @Override
    public void debitAccount(String accountNumber, BigDecimal amount) {
        this.amount = this.amount.subtract(amount);
    }
}
