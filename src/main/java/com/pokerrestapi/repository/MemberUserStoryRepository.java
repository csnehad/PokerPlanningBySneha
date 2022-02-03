package com.pokerrestapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pokerrestapi.entity.MemberUserStory;

public interface MemberUserStoryRepository extends JpaRepository<MemberUserStory, String> {

	@Query(value = "select mu from MemberUserStory mu JOIN mu.userStory us where us.uStoryId=?1")
	List<MemberUserStory> findByUserStoryId(String uStoryId);

}
