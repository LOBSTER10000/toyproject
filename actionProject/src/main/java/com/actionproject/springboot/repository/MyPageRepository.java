package com.actionproject.springboot.repository;

import com.actionproject.springboot.entity.myPage.MyPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyPageRepository extends JpaRepository<MyPage, Long> {
}
