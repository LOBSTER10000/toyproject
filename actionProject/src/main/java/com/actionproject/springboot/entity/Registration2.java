package com.actionproject.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Registration2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imgNumber;

    @ManyToOne
    @JoinColumn(name = "proNumber") // Registration 클래스의 proNumber를 참조하는 외래키
    private Registration registration;

    private String fileName;

    private String filePath;

    private Long numbers;

}
