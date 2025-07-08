package com.sp.hsbiegapi.controllers.memberControllers;

import com.sp.hsbiegapi.daos.RequestDaos.memberRequestsDao.MemberRequestDao;
import com.sp.hsbiegapi.daos.ResponseDaos.memberResponseDaos.MemberResponseDao;
import com.sp.hsbiegapi.services.memServices.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api_v1/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // Get all the Members
    @GetMapping("/all-members")
    public List<MemberResponseDao> getAllMembers(){
        return memberService.getAllMembers();
    }

    // Add a new Member
    @PostMapping("/new-member")
    public MemberResponseDao saveMember(@RequestBody MemberRequestDao memberRequestDao){
        return memberService.addMember(memberRequestDao);
    }


}
