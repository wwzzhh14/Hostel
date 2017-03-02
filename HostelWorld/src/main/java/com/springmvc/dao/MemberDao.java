package com.springmvc.dao;

import com.springmvc.dataservice.MemberDataService;
import com.springmvc.entities.MemberEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by wzh on 17/01/2017.
 */

@Service
public class MemberDao implements MemberDataService {

    @Resource
    BaseDao baseDao;
    public boolean addMember(MemberEntity entity) {
        return baseDao.save(entity);
    }

    public MemberEntity getMemberById(String memberId) {
        return (MemberEntity) baseDao.findById(MemberEntity.class,memberId);
    }

    public boolean updateMemberById(String memberId, String[] keys, Object[] values) {
        String[] keys2 = {"memberId"};
        Object[] values2 = {memberId};
        return baseDao.updateByProperties(MemberEntity.class,keys2,values2,keys,values);
    }

    @Override
    public boolean updateMemberEntity(MemberEntity entity) {
        return baseDao.update(entity);
    }
}
