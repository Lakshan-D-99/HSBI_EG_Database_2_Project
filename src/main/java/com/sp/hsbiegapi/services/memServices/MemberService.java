package com.sp.hsbiegapi.services.memServices;

import com.sp.hsbiegapi.daos.RequestDaos.memberRequestsDao.MemberRequestDao;
import com.sp.hsbiegapi.daos.ResponseDaos.memberResponseDaos.MemberResponseDao;

import java.util.List;

public interface MemberService {

    // Get all the Members
    List<MemberResponseDao> getAllMembers();

    // Get a Single Member based on the Passed in memberId
    MemberResponseDao getSingleMember(long memberId);

    // Create and Save a new Member into the Database
    void addMember(MemberRequestDao memberRequestDao);

    // Update an existing Member in the Database
    void updateMember(long memberId, MemberRequestDao memberRequestDao);

    // Delete an existing Member from the Database
    void deleteMember(long memberId);
}
