package com.actionproject.springboot.entity.myPage;

import com.actionproject.springboot.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MyPage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userEmoney;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
