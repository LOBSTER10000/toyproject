package com.actionproject.springboot.repository;

import com.actionproject.springboot.entity.cs.View;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository

public interface ViewRepository extends JpaRepository<View, Long> {


}
