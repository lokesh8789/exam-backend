package com.exam.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 5000)
    private String content;
    private String image;
    @Column(length = 1000)
    private String option1;
    @Column(length = 1000)
    private String option2;
    @Column(length = 1000)
    private String option3;
    @Column(length = 1000)
    private String option4;
    @Column(length = 1000)
    private String answer;
    @Transient
    private String givenAnswer;
    @ManyToOne(fetch = FetchType.EAGER)
    private Quiz quiz;
}
