package com.sp.hsbiegapi.services.serviceimpl;

import com.sp.hsbiegapi.daos.RequestDaos.memberRequestsDao.MemberRequestDao;
import com.sp.hsbiegapi.daos.ResponseDaos.memberResponseDaos.MemberResponseDao;
import com.sp.hsbiegapi.models.memModels.Member;
import com.sp.hsbiegapi.repositories.membRepositories.MemberRepository;
import com.sp.hsbiegapi.services.memServices.MemberService;
import com.sp.hsbiegapi.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // Get all the Members from the Database
    @Override
    public List<MemberResponseDao> getAllMembers() {
       try {
           List<MemberResponseDao> memberResponseDaoList = memberRepository.findAll()
                   .stream()
                   .map(member -> Mapper.conEntityToDao(member))
                   .toList();

           return memberResponseDaoList;
       } catch (Exception e) {
           throw new RuntimeException(e.getMessage());
       }

    }

    // Get a Single Member from the Database if the Member exists in the Database
    @Override
    public MemberResponseDao getSingleMember(long memberId) {
        try {
            Optional<Member> member = memberRepository.findById(memberId);

            if (member.isPresent()){
                return Mapper.conEntityToDao(member.get());
            }
            return new MemberResponseDao();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // Save a new Member into the Database
    @Override
    public void addMember(MemberRequestDao memberRequestDao) {
        try {
            memberRepository.save(Mapper.conDaoToEntity(memberRequestDao));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    // Update an existing Member in the Database
    @Override
    public void updateMember(long memberId, MemberRequestDao memberRequestDao) {
        //TODO: Update will be implemented later
    }

    // Delete an existing Member from the Database
    @Override
    public void deleteMember(long memberId) {
        try {
            Optional<Member> existsMember = memberRepository.findById(memberId);

            existsMember.ifPresent(member -> memberRepository.delete(member));

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
