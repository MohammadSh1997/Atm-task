package com.progressoft.induction.atm;

import java.util.List;
import java.util.Map;

public interface BanknoteDao {
    Map<Banknote, Integer> getBanknotes();
}
