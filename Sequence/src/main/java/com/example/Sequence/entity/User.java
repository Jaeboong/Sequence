package com.example.Sequence.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @Column(length = 50)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String schoolName;
    private String majorName;
    private String entranceYear;
    private String graduationYear;
    private String academicStatus;
    private String skills;
    private String desiredPositions;
    private String portfolioUrl;
    private String portfolioFile;
    private String introduction;

    // 연관관계 설정
    @ElementCollection
    @CollectionTable(name = "user_certifications", joinColumns = @JoinColumn(name = "user_id"))
    private List<Certification> certifications = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "user_careers", joinColumns = @JoinColumn(name = "user_id"))
    private List<Career> careers = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "user_activities", joinColumns = @JoinColumn(name = "user_id"))
    private List<Activity> activities = new ArrayList<>();
}
