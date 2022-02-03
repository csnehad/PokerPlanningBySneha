package com.pokerrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pokerrestapi.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

}
