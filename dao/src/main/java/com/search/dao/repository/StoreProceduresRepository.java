package com.search.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.search.dao.entity.StoreProcedures;

@Repository
public interface StoreProceduresRepository extends JpaRepository<StoreProcedures, Long> {

    @Procedure(name = "build_user_meta")
    void buildUserMeta(@Param("userId") Long userId, @Param("token") String token);
    
    
    @Procedure(name = "proc_get_user_search")
    void getUserSearch(
    		@Param("userId") Long userId, 
    		@Param("token") String token,
    		@Param("name") String name, 
    		@Param("profile") String profile
    	);
}
