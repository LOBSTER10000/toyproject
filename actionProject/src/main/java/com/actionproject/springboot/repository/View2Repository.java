package com.actionproject.springboot.repository;

import com.actionproject.springboot.entity.cs.View2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface View2Repository extends JpaRepository<View2, Long> {

    @Modifying
    @Transactional
    @Query(value = "insert into view(user_id ,bbs_head, bbs_content, bbs_images, bbs_view, bbs_date) " +
            "values(:#{#view2.userId}, :#{#view2.bbsHead}, :#{#view2.bbsContent}, :#{#view2.bbsImages}, :#{#view2.bbsView}, :#{#view2.bbsDate})", nativeQuery = true)
    public void writeNo2(@Param("view2") View2 view2);

}
