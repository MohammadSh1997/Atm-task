package com.progressoft.induction.atm;

import com.progressoft.induction.atm.exceptions.InsufficientFundsException;
import com.progressoft.induction.atm.exceptions.NotEnoughMoneyInATMException;

import java.math.BigDecimal;
import java.util.*;

public class ATMMachine implements ATM {

    private BigDecimal totalAmount;
    private final Map<Banknote, Integer> bankNotesMap;
    private final AccountDao accountRepo;

    public ATMMachine(Map<Banknote, Integer> bankNotes, AccountDao accountRepo) {
        this.bankNotesMap = bankNotes;
        this.accountRepo = accountRepo;
        fillAtmAmount();
    }

    @Override
    public List<Banknote> withdraw(String accountNumber, BigDecimal amount) {
        Account userAccount = accountRepo.getAccountByAccountId(accountNumber);
        if (hasInsufficientFunds(amount.doubleValue(), userAccount.getAccountBalance(accountNumber).doubleValue())) {
            throw new InsufficientFundsException();
        }

        if (isEnoughMoneyInATM(amount)) {
            this.totalAmount = BigDecimal.valueOf(this.totalAmount.doubleValue() - amount.doubleValue());
            userAccount.debitAccount(accountNumber, amount);
        }
        return receivedBanknotes(amount);
    }

    private boolean hasInsufficientFunds(double requestedAmount, double userBalance) {
        return requestedAmount > userBalance;
    }

    private void fillAtmAmount() {
        BigDecimal tempAmount = BigDecimal.ZERO;
        Set<Map.Entry<Banknote, Integer>> entries = bankNotesMap.entrySet();
        for (Map.Entry<Banknote, Integer> entry : entries) {
            BigDecimal multiply = entry.getKey().getValue().multiply(BigDecimal.valueOf(entry.getValue()));
            tempAmount = tempAmount.add(multiply);
        }
        this.totalAmount = tempAmount;
    }

    private List<Banknote> receivedBanknotes(BigDecimal amount) {
        List<Banknote> banknotes = new ArrayList<>();
        List<Banknote> atmNotes = Arrays.asList(Banknote.values());
        Collections.sort(atmNotes , Collections.reverseOrder());

        for (Banknote atmNote : atmNotes) {
            while (amount.compareTo(BigDecimal.ZERO) > 0 && bankNotesMap.get(atmNote) > 0) {
                if (amount.compareTo(atmNote.getValue()) >= 0) {
                    amount = amount.subtract(atmNote.getValue());
                    banknotes.add(atmNote);
                    bankNotesMap.put(atmNote, bankNotesMap.get(atmNote) - 1);
                } else {
                    break;
                }
            }
        }
        return banknotes;
    }

    private boolean isEnoughMoneyInATM(BigDecimal amount) {
        if (this.totalAmount.compareTo(amount) > 0) {
            return true;
        }
        throw new NotEnoughMoneyInATMException();
    }
}