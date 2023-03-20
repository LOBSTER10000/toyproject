package com.actionproject.springboot.entity.cs;

import com.actionproject.springboot.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class View2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qnaNumber;


    @Column(length = 500)
    private String bbsHead;

    @Column(length = 2500)
    private String bbsContent;
    @Temporal(TemporalType.TIMESTAMP)
    private Date bbsDate = new Date();
    private int bbsView;
    private String bbsImages;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
