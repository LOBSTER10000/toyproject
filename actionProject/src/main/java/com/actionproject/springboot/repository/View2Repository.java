package com.actionproject.springboot.repository;

import com.actionproject.springboot.entity.cs.View;
import com.actionproject.springboot.entity.cs.View2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface View2Repository extends JpaRepository<View2, Long> {

    @Modifying
    @Transactional
    @Query(value = "insert into view2(user_id ,bbs_head, bbs_content, bbs_images, bbs_view, bbs_date) " +
            "values(:#{#view2.userId}, :#{#view2.bbsHead}, :#{#view2.bbsContent}, :#{#view2.bbsImages}, :#{#view2.bbsView}, :#{#view2.bbsDate})", nativeQuery = true)
    public void writeNo2(@Param("view2") View2 view2);

    @Transactional
    @Query(value = "select * from view2 order by qna_number desc limit 0,10", nativeQuery = true)
    public List<View2> selectAll();

    @Transactional
    @Query(value = "select * from view2 where qna_number = :qnaNumber", nativeQuery = true)
    public View2 selectOne(@Param("qnaNumber") Long qnaNumber);

    @Modifying
    @Transactional
    @Query(value = "update view2 set bbs_head = :bbsHead, bbs_content = :bbsContent where qna_number = :qnaNumber", nativeQuery = true)
    public int updateNo(@Param("bbsHead") String bbsHead, @Param("bbsContent") String bbsContent, @Param("qnaNumber") Long qnaNumber);

    @Modifying
    @Transactional
    @Query(value = "delete from view2 where qna_number = :qnaNumber", nativeQuery = true)
    public int deleteNo(@Param("qnaNumber") Long qnaNumber);
}
