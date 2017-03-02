package com.springmvc.dao;

import com.springmvc.dataservice.AccountDataService;
import com.springmvc.entities.AccountEntity;
import com.springmvc.model.AccountModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wzh on 21/01/2017.
 */
@Service
public class AccountDao implements AccountDataService {
    @Resource
    BaseDao baseDao;
    @Override
    public boolean updateAccountById(String accountId, String[] keys, Object[] value) {
        String[] keys2 = {"accountId"};
        Object[] values2 = {accountId};
        return baseDao.updateByProperties(AccountEntity.class,keys2,values2,keys,value);
    }

    @Override
    public boolean addAccount(AccountEntity entity) {
        return baseDao.save(entity);
    }

    @Override
    public AccountEntity getAccountById(String id) {
        return (AccountEntity) baseDao.findById(AccountEntity.class,id);
    }

    @Override
    public List<AccountModel> getAllAccountInfo() {
        Object[] values = {};
        String sql = "select new com.springmvc.model.AccountModel(h.name,a.balance) from AccountEntity a,HotelEntity h where a.hotelId = h.hotelId";
        baseDao.execQuery(sql,values);
        return null;
    }
}
