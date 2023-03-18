package com.actionproject.springboot.repository;

import com.actionproject.springboot.entity.cs.View;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository

public interface ViewRepository extends JpaRepository<View, Long> {


    @Modifying
    @Transactional
    @Query(value = "insert into view(user_id ,bbs_head, bbs_content, bbs_images, bbs_view, bbs_date) " +
            "values(:#{#view.userId}, :#{#view.bbsHead}, :#{#view.bbsContent}, :#{#view.bbsImages}, :#{#view.bbsView}, :#{#view.bbsDate})", nativeQuery = true)
    public void writeNo(@Param("view") View view);


    @Transactional
    @Query(value = "select * from view order by notice_number desc limit 0,10", nativeQuery = true)
    public List<View> selectAll();

    @Transactional
    @Query(value = "select * from view where notice_number = :noticeNumber", nativeQuery = true)
    public View selectOne(@Param("noticeNumber") Long noticeNumber);

    @Modifying
    @Transactional
    @Query(value = "update view set bbs_head = :#{#view.bbsHead}, bbs_content = :#{#view.bbsContent} where notice_number = :#{#noticeNumber}", nativeQuery = true)
    public int updateNo(@Param("view") View view, @Param("noticeNumber") Long noticeNumber);

    @Modifying
    @Transactional
    @Query(value = "delete from view where notice_number = :noticeNumber", nativeQuery = true)
    public int deleteNo(@Param("noticeNumber") Long noticeNumber);
}
