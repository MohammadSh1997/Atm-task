package com.progressoft.induction.atm;

import com.progressoft.induction.atm.exceptions.AccountNotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AccountDoaImpl implements AccountDao {
    private  final List<Account> accounts = new ArrayList<>();

    public AccountDoaImpl() {
        initAccounts();
    }

    @Override
    public Account getAccountByAccountId(String accountId) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountId)) {
                return account;
            }
        }
        throw new AccountNotFoundException();
    }

    private void initAccounts() {
        accounts.add(new Account("123456789", new BigDecimal(1000)));
        accounts.add(new Account("111111111", new BigDecimal(1000)));
        accounts.add(new Account("222222222", new BigDecimal(1000)));
        accounts.add(new Account("333333333", new BigDecimal(1000)));
        accounts.add(new Account("444444444", new BigDecimal(1000)));
    }
}
