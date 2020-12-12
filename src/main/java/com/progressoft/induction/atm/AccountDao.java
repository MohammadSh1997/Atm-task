package com.progressoft.induction.atm;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDao {
    Account getAccountByAccountId(String accountId);
}
