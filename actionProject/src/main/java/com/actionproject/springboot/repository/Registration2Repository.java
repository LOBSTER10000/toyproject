package com.actionproject.springboot.repository;

import com.actionproject.springboot.entity.Registration2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface Registration2Repository extends JpaRepository<Registration2, Long> {


    @Modifying
    @Transactional
    @Query(value = "insert into registration2(file_name, file_path, numbers) " +
            "values(?, ? ,?)", nativeQuery = true)
    public int saveRe(String uuid, String realPath, Long numbers);

}
