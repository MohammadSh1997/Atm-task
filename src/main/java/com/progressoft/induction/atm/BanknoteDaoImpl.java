package com.progressoft.induction.atm;

import java.util.HashMap;
import java.util.Map;

public class BanknoteDaoImpl implements BanknoteDao {

    private final Map<Banknote, Integer> bankNotes = new HashMap<>();

    public BanknoteDaoImpl() {
        bankNotes.put(Banknote.FIFTY_JOD, 10);
        bankNotes.put(Banknote.TWENTY_JOD, 20);
        bankNotes.put(Banknote.TEN_JOD, 100);
        bankNotes.put(Banknote.FIVE_JOD, 100);
    }

    @Override
    public Map<Banknote, Integer> getBanknotes() {
        return bankNotes;
    }
}
