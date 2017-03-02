package com.springmvc.dataservice;

import com.springmvc.entities.MemberEntity;

/**
 * Created by wzh on 17/01/2017.
 */
public interface MemberDataService {

    public boolean addMember(MemberEntity entity);
    public MemberEntity getMemberById(String memberId);
    public boolean updateMemberById(String memberId, String[] keys, Object[] values);
    public boolean updateMemberEntity(MemberEntity entity);

}
