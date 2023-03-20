package com.actionproject.springboot.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationVO {
    private int proNumber;
    private String userId;
    private String proHead;
    private String proFrom;
    private int proStartMoney; // 시작가
    private int proPresentMoney; //현재가
    private int proUnitMoney; // 가격 단위
    private int proDirectMoney; // 즉구가
    private String proContent; // 게시판 내용

    private Date proDate = new Date();

    private String imagesExist; // 이미지 존재 개수
}
