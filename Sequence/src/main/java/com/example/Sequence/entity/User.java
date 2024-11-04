package com.example.Sequence.entity;

import lombok.*;
import jakarta.persistence.*;
import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User {

    @Id
    @Column(length = 50)
    private String id;

    @Column(nullable = false)
    private String name;            // 이름

    @Column(nullable = false)
    private LocalDate birthDate;       // 생년월일

    @Column(nullable = false)
    private String gender;          // 성별

    @Column(nullable = false)
    private String address;         // 주소지

    @Column(nullable = false, unique = true)
    private String phoneNumber;     // 휴대전화 번호

    @Column(nullable = false, unique = true)
    private String email;           // 이메일

    @Column(nullable = false)
    private String password;        // 비밀번호

    // 학력 관련
    private String schoolName;      // 학교명
    private String majorName;       // 전공명
    private String entranceYear;  // 입학연도
    private String graduationYear;  // 졸업연도
    private String academicStatus;  // 학적상태

    // 스킬
    @Column(length = 500)  // 여러 스킬을 저장할 수 있도록 길이 설정
    private String skills;     // Set<String>에서 String으로 변경

    // 희망직무
    @Column(length = 500)  // 여러 직무를 저장할 수 있도록 길이 설정
    private String desiredPositions;  // Set<String>에서 String으로 변경

    // 포트폴리오 URL
    private String portfolioUrl;
    private String portfolioFile;

    // 자기소개
    @Column(length = 500)
    private String introduction;

    // 경험 및 활동이력
    @ElementCollection
    @CollectionTable(name = "user_activities")
    @Builder.Default
    private List<Activity> activities = new ArrayList<>();

    // 경력
    @ElementCollection
    @CollectionTable(name = "user_careers")
    @Builder.Default
    private List<Career> careers = new ArrayList<>();

    // 자격 및 수상
    @ElementCollection
    @CollectionTable(name = "user_certifications")
    @Builder.Default
    private List<Certification> certifications = new ArrayList<>();
}

