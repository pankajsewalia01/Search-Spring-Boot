package com.search.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.search.dao.entity.UserMeta;

@Repository
public interface UserMetaRepository extends JpaRepository<UserMeta, Long> {
	
	public List<UserMeta> findAll(); 
	
	public UserMeta findByToken(String token);

}
