package com.sp.hsbiegapi.services.joinTableServices;

import com.sp.hsbiegapi.daos.ResponseDaos.energyResponseDaos.EnergySourceResponseDao;
import com.sp.hsbiegapi.daos.ResponseDaos.memberResponseDaos.MemberResponseDao;

import java.util.Set;

public interface MemberEnergySourceService {

    // Add a Member to an Energy Source using Member and Energy Source ids
    void addEnergySourceToMember(long memberId,long energySourceId);

    // Remove an Energy Source from a Member based on the passed in ids
    void removeEnergySourceFromMember(long memberId,long energySourceId);

    // Get all the Energy Sources where a specific Member has joined, based on the passed in Member id
    Set<EnergySourceResponseDao> getAllEnergySourcesMemberJoined(long memberId);

    // Get all the Members of a specific Energy Source based on the passed in Energy Source id
    Set<MemberResponseDao> getAllMembersOfEnergySource(long energySourceId);
}
