package com.actionproject.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long proNumber;
    private String proHead;
    private String proFrom;
    private int proStartMoney; // 시작가
    private int proPresentMoney; //현재가
    private int proUnitMoney; // 가격 단위
    private int proDirectMoney; // 즉구가
    private String proContent; // 게시판 내용

    @Temporal(TemporalType.TIMESTAMP)
    private Date proDate = new Date();

    private String imagesExist; // 이미지 존재 개수

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
