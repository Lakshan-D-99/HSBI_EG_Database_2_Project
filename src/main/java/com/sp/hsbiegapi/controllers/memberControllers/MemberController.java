package com.sp.hsbiegapi.controllers.memberControllers;

import com.sp.hsbiegapi.daos.ResponseDaos.memberResponseDaos.MemberResponseDao;
import com.sp.hsbiegapi.services.memServices.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/members")
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


}
