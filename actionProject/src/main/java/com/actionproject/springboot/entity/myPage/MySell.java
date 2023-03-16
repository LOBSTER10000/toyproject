package com.actionproject.springboot.entity.myPage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MySell {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sellNum;
    private String proNumber;
    private String proImages;

    @Column(length = 500)
    private String proHead;
    private int sellMoney;

    @Column(columnDefinition = "DATETIME")
    private String sellDate;

    private String sellerId;
    private String depositYN;
}
