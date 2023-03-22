package com.actionproject.springboot.repository;

import com.actionproject.springboot.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    @Transactional
    @Modifying
    @Query(value = "insert into registration(pro_head, pro_from, pro_start_money, pro_present_money, pro_unit_money, pro_direct_money, pro_content, pro_date, images_exist, user_id) " +
            "values(:proHead, :proFrom, :proStartMoney, :proPresentMoney, :proUnitMoney, :proDirectMoney, :proContent, :proDate, :imagesExist, :userId)", nativeQuery = true)
    public int insertRe(@Param("proHead") String proHead, @Param("proFrom") String proFrom, @Param("proStartMoney") int proStartMoney, @Param("proPresentMoney") int proPresentMoney, @Param("proUnitMoney") int proUnitMoney, @Param("proDirectMoney") int proDirectMoney, @Param("proContent") String proContent, @Param("proDate") Date proDate, @Param("imagesExist") String ImagesExist, @Param("userId") String userId);

    @Transactional
    @Query(value = "select * from registration order by pro_number desc limit 0,9", nativeQuery = true)
    public List<Registration> selectRe();

    @Transactional
    @Query(value = "select * from registration where pro_number = :proNumber", nativeQuery = true)
    public Registration selectReOne(@Param("proNumber") Long proNumber);
}
