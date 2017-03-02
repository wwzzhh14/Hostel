package com.springmvc.dataservice;

import com.springmvc.entities.AccountEntity;
import com.springmvc.model.AccountModel;

import java.util.List;

/**
 * Created by wzh on 21/01/2017.
 */
public interface AccountDataService {
    public boolean updateAccountById(String accountId, String[] keys, Object[] value);
    public boolean addAccount(AccountEntity entity);
    public AccountEntity getAccountById(String id);
    public List<AccountModel> getAllAccountInfo();

}
