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
    @JoinColumn(name = "pro_number")
    private Registration Registration;

    private String fileName;

    private String filePath;
}
