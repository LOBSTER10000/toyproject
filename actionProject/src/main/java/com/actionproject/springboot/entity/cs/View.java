package com.actionproject.springboot.entity.cs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class View {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bbsNumber;

    @Column(length = 500)
    private String bbsHead;

    @Column(length = 2500)
    private String bbsContent;
    @Column(columnDefinition = "DATETIME")
    private String bbsDate;
    private int bbsView;
    private String bbsImages;
}
