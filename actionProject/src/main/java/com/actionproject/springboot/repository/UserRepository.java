package com.actionproject.springboot.repository;

import com.actionproject.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Long > {
    @Modifying
    @Transactional
    @Query(value = "insert into user(user_id, user_email, user_pass, user_zip, user_add) " +
            "values (:userId, :userEmail, :userPass, :userZip, :userAdd)",  nativeQuery = true)
    public void insertJoin(@Param("userId") String userId, @Param("userEmail") String userEmail, @Param("userPass") String userPass,
                           @Param("userZip") String userZip, @Param("userAdd") String userAdd);

    @Transactional
    @Query(value = "select * from user where user_id = :userId and user_pass = :userPass", nativeQuery = true)
    public User selLogin(@Param("userId") String userId, @Param("userPass") String userPass);
}
