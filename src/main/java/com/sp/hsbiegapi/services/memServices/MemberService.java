package com.sp.hsbiegapi.services.memServices;

import com.sp.hsbiegapi.daos.RequestDaos.memberRequestsDao.MemberRequestDao;
import com.sp.hsbiegapi.daos.ResponseDaos.memberResponseDaos.MemberResponseDao;

import java.util.List;

public interface MemberService {

    // Get all the Members
    List<MemberResponseDao> getAllMembers();

    // Get a Single Member based on the Passed in memberId
    MemberResponseDao getSingleMember(long memberId);

    // Get the amount of all the active Members in the Database
    String getAllActiveMembers();

    // Create and Save a new Member into the Database
    MemberResponseDao addMember(MemberRequestDao memberRequestDao);
}
