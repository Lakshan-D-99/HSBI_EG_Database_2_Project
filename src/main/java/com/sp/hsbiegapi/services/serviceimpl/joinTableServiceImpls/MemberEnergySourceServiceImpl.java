package com.sp.hsbiegapi.services.serviceimpl.joinTableServiceImpls;

import com.sp.hsbiegapi.daos.ResponseDaos.energyResponseDaos.EnergySourceResponseDao;
import com.sp.hsbiegapi.daos.ResponseDaos.memberResponseDaos.MemberResponseDao;
import com.sp.hsbiegapi.models.energyModels.EnergySource;
import com.sp.hsbiegapi.models.memModels.Member;
import com.sp.hsbiegapi.repositories.energyRepositories.EnergySourceRepository;
import com.sp.hsbiegapi.repositories.membRepositories.MemberRepository;
import com.sp.hsbiegapi.services.joinTableServices.MemberEnergySourceService;
import com.sp.hsbiegapi.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class MemberEnergySourceServiceImpl implements MemberEnergySourceService {

    private final MemberRepository memberRepository;
    private final EnergySourceRepository energySourceRepository;

    @Autowired
    public MemberEnergySourceServiceImpl(MemberRepository memberRepository, EnergySourceRepository energySourceRepository) {
        this.memberRepository = memberRepository;
        this.energySourceRepository = energySourceRepository;
    }

    @Override
    @Transactional
    public void addEnergySourceToMember(long memberId, long energySourceId) {

        try {
            // Get the Member from the Database through the passed in Member id
            Member member = memberRepository.findById(memberId).orElseThrow();

            // Get the Energy Source from the Database through the passed in Energy Source id
            EnergySource energySource = energySourceRepository.findById(energySourceId).orElseThrow();

            // Assign Energy Source to the Member
            member.addEnergySource(energySource);

            // Save the Member Object to the Database
            memberRepository.save(member);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void removeEnergySourceFromMember(long memberId, long energySourceId) {

        try {
            // Get the Member from the Database through the passed in Member id
            Member member = memberRepository.findById(memberId).orElseThrow();

            // Get the Energy Source from the Database through the passed in Energy Source id
            EnergySource energySource = energySourceRepository.findById(energySourceId).orElseThrow();

            // Remove the Energy Source from the Member
            member.removeEnergySource(energySource);

            // Save the Member Object to the Database
            memberRepository.save(member);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Set<EnergySourceResponseDao> getAllEnergySourcesMemberJoined(long memberId) {

        try {
            // Get the Member through the passed in Member id
            Member member = memberRepository.findById(memberId).orElseThrow();

            // Create a Set to store all the Energy Sources of a Member
            Set<EnergySourceResponseDao> energySourceResponseDaoSet = new HashSet<>();

            // Add all the Energy Source of a Member into the Response DAOs set
            member.getEnergySourceSet()
                    .forEach(energySource -> energySourceResponseDaoSet.add(Mapper.conEntityToDao(energySource)));

            // Return the Response Dao Set
            return energySourceResponseDaoSet;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Set<MemberResponseDao> getAllMembersOfEnergySource(long energySourceId) {

        try {
            // Get the Energy Source through the passed in Energy Source id
            EnergySource energySource = energySourceRepository.findById(energySourceId).orElseThrow();

            // Create a Set to store all the Members of that specific Energy Source
            Set<MemberResponseDao> memberResponseDaoSet = new HashSet<>();

            // Add all the Members of an Energy Source into the Response DAOs set
            energySource.getMemberSet()
                    .forEach(member -> memberResponseDaoSet.add(Mapper.conEntityToDao(member)));

            // Return the Member Set
            return memberResponseDaoSet;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
