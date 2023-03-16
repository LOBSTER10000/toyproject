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
public class MyPurchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purNum;

    private String sellerId;
    private String proImages;
    @Column(length = 500)
    private String proHead;
    private int purMoney;
    @Column(columnDefinition = "DATETIME")
    private String purDate;
    private String DepositYN;
}
