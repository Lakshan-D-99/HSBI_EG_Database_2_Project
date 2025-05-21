package com.sp.hsbiegapi.repositories;

import com.sp.hsbiegapi.models.memModels.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
}
