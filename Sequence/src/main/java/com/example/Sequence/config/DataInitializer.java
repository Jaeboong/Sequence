package com.example.Sequence.config;

import com.example.Sequence.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import com.example.Sequence.entity.Activity;
import com.example.Sequence.entity.User;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class DataInitializer {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
            
            transactionTemplate.execute(status -> {
                // 첫 번째 User 생성 (연관관계 없이)
                User user = User.builder()
                    .id("cbkjh0225")
                    .name("김재환")
                    .birthDate(LocalDate.of(1999, 2, 25))
                    .gender("남자")
                    .address("서울특별시 강남구 xxx아파트 xxx호")
                    .phoneNumber("010-1111-2222")
                    .email("undergod@fighting.com")
                    .password(passwordEncoder.encode("1234"))
                    .schoolName("고려대학교 세종캠퍼스")
                    .majorName("컴퓨터융합소프트웨어학과")
                    .entranceYear("2019")
                    .graduationYear("2025")
                    .academicStatus("재학")
                    .skills("Javascript,Java,Python,C++")
                    .desiredPositions("Back-end")
                    .portfolioUrl("https://github.com/undergod")
                    .portfolioFile("portfolio_김재환.pdf")
                    .introduction("안녕하세요, 백엔드 개발자를 꿈꾸는 김재환입니다...")
                    .build();

                em.persist(user);  // 먼저 User 저장

                // Activity 생성 및 연관관계 설정
                Activity activity1 = Activity.builder()
                    .name("오픈소스 프로젝트 기여")
                    .type("프로젝트")
                    .startDate(LocalDate.of(2023, 3, 1))
                    .endDate(LocalDate.of(2023, 12, 31))
                    .user(user)
                    .build();

                Activity activity2 = Activity.builder()
                    .name("알고리즘 스터디 운영")
                    .type("스터디")
                    .startDate(LocalDate.of(2022, 9, 1))
                    .endDate(LocalDate.of(2023, 2, 28))
                    .user(user)
                    .build();

                // Career 생성
                Career career1 = Career.builder()
                    .name("네이버 클라우드 인턴")
                    .type("인턴십")
                    .startDate(LocalDate.of(2023, 7, 1))
                    .endDate(LocalDate.of(2023, 8, 31))
                    .user(user)
                    .build();

                // Certification 생성
                Certification cert1 = Certification.builder()
                    .name("정보처리기사")
                    .date(LocalDate.of(2023, 8, 15))
                    .user(user)
                    .build();

                Certification cert2 = Certification.builder()
                    .name("SQLD")
                    .date(LocalDate.of(2023, 6, 20))
                    .user(user)
                    .build();

                // 연관관계 설정
                user.getActivities().addAll(Arrays.asList(activity1, activity2));
                user.getCareers().add(career1);
                user.getCertifications().addAll(Arrays.asList(cert1, cert2));

                // 두 번째 User도 같은 방식으로...
                User user2 = User.builder()
                    .id("hong1234")
                    .name("홍길동")
                    .birthDate(LocalDate.of(1998, 5, 15))
                    .gender("남자")
                    .address("경기도 성남시 분당구 xxx동 xxx호")
                    .phoneNumber("010-3333-4444")
                    .email("hong@example.com")
                    .password(passwordEncoder.encode("5678"))
                    .schoolName("서울대학교")
                    .majorName("컴퓨터공학부")
                    .entranceYear("2017")
                    .graduationYear("2023")
                    .academicStatus("졸업")
                    .skills("Python,React,Node.js,AWS")
                    .desiredPositions("Full-stack,DevOps")
                    .portfolioUrl("https://github.com/honggildong")
                    .portfolioFile("portfolio_홍길동.pdf")
                    .introduction("안녕하세요, 풀스택 개발자를 꿈꾸는 홍길동입니다...")
                    .build();

                em.persist(user2);

                // user2의 연관 엔티티들 생성 및 설정
                Activity activity3 = Activity.builder()
                    .name("SW 마에스트로")
                    .type("교육프로그램")
                    .startDate(LocalDate.of(2022, 4, 1))
                    .endDate(LocalDate.of(2022, 12, 31))
                    .user(user2)
                    .build();

                Activity activity4 = Activity.builder()
                    .name("쿠팡 인턴십")
                    .type("인턴십")
                    .startDate(LocalDate.of(2022, 7, 1))
                    .endDate(LocalDate.of(2022, 8, 31))
                    .user(user2)
                    .build();

                Career career2 = Career.builder()
                    .name("쿠팡 백엔드 개발자")
                    .type("정규직")
                    .startDate(LocalDate.of(2023, 1, 1))
                    .endDate(LocalDate.of(2023, 12, 31))
                    .user(user2)
                    .build();

                Certification cert3 = Certification.builder()
                    .name("AWS Solutions Architect")
                    .date(LocalDate.of(2023, 3, 15))
                    .user(user2)
                    .build();

                // 연관관계 설정
                user2.getActivities().addAll(Arrays.asList(activity3, activity4));
                user2.getCareers().add(career2);
                user2.getCertifications().add(cert3);

                // 엔티티들 저장
                em.persist(activity3);
                em.persist(activity4);
                em.persist(career2);
                em.persist(cert3);

                return null;
            });
        };
    }
} 