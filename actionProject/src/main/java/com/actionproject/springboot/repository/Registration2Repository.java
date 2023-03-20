package com.actionproject.springboot.repository;

import com.actionproject.springboot.entity.Registration2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Registration2Repository extends JpaRepository<Registration2, Long> {
}
