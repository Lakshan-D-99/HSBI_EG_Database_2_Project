package com.sp.hsbiegapi.services.serviceimpl;

import com.sp.hsbiegapi.daos.RequestDaos.MemberRequestDao;
import com.sp.hsbiegapi.daos.ResponseDaos.MemberResponseDao;
import com.sp.hsbiegapi.models.Member;
import com.sp.hsbiegapi.repositories.MemberRepository;
import com.sp.hsbiegapi.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //-------------------- Helper Methods --------------------//

    // Convert an Entity into a Response Dao
    private MemberResponseDao conEntityToDao(Member member){
        MemberResponseDao md = new MemberResponseDao();
        md.setId(member.getId());
        md.setMemberName(member.getMemberName());
        md.setMemMatNumber(member.getMemberMatNumber());
        md.setMemEmail(member.getMemberEmail());
        md.setMemberContribution(member.getMemberContribution());
        md.setMemberStats(member.getMemberStatus());
        md.setMemberJoinDate(member.getMemberJoinDate());
        md.setMemberPaymentType(member.getMemberPaymentType());

        return md;

    }

    // Convert a Request Dao into an Entity
    private Member conDaoToEntity(MemberRequestDao memberRequestDao){
        Member member = new Member();
        member.setMemberName(memberRequestDao.getMemberName());
        member.setMemberMatNumber(memberRequestDao.getMemMatNumber());
        member.setMemberEmail(memberRequestDao.getMemEmail());
        member.setMemberContribution(memberRequestDao.getMemberContribution());
        member.setMemberStatus(memberRequestDao.getMemberStats());
        member.setMemberJoinDate(LocalDate.now());
        member.setMemberPaymentType(memberRequestDao.getMemberPaymentType());
        return member;
    }


    //-------------------- Model based Methods --------------------//

    // Get all the Members from the Database
    @Override
    public List<MemberResponseDao> getAllMembers() {
       try {
           List<MemberResponseDao> memberResponseDaoList = memberRepository.findAll()
                   .stream()
                   .map(member -> conEntityToDao(member))
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
                return conEntityToDao(member.get());
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
            memberRepository.save(conDaoToEntity(memberRequestDao));
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
