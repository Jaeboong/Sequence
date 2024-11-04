package com.example.Sequence.config;

import com.example.Sequence.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import com.example.Sequence.entity.Activity;

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
                User user = User.builder()
                    .id("cbkjh0225")
                    // 기본 정보
                    .name("김재환")
                    .birthDate(LocalDate.of(1999, 2, 25))
                    .gender("남자")
                    .address("서울특별시 강남구 xxx아파트 xxx호")
                    .phoneNumber("010-1111-2222")
                    .email("undergod@fighting.com")
                    .password(passwordEncoder.encode("1234"))  // 비밀번호 암호화
                    
                    // 학력 정보
                    .schoolName("고려대학교 세종캠퍼스")
                    .majorName("컴퓨터융합소프트웨어학과")
                    .entranceYear("2019")
                    .graduationYear("2025")
                    .academicStatus("재학")
                    
                    // 스킬 (쉼표로 구분된 문자열로 저장)
                    .skills("Javascript,Java,Python,C++")
                    
                    // 희망 직무 (쉼표로 구분된 문자열로 저장)
                    .desiredPositions("Back-end")
                    
                    // 활동 이력
                    .activities(Arrays.asList(
                        Activity.builder()
                            .name("오픈소스 프로젝트 기여")
                            .type("프로젝트")
                            .startDate(LocalDate.of(2023, 3, 1))
                            .endDate(LocalDate.of(2023, 12, 31))
                            .build(),
                        Activity.builder()
                            .name("알고리즘 스터디 운영")
                            .type("스터디")
                            .startDate(LocalDate.of(2022, 9, 1))
                            .endDate(LocalDate.of(2023, 2, 28))
                            .build()
                    ))
                    
                    // 경력
                    .careers(Arrays.asList(
                        Career.builder()
                            .name("네이버 클라우드 인턴")
                            .type("인턴십")
                            .startDate(LocalDate.of(2023, 7, 1))
                            .endDate(LocalDate.of(2023, 8, 31))
                            .build()
                    ))
                    
                    // 자격증
                    .certifications(Arrays.asList(
                        Certification.builder()
                            .name("정보처리기사")
                            .date(LocalDate.of(2023, 8, 15))
                            .build(),
                        Certification.builder()
                            .name("SQLD")
                            .date(LocalDate.of(2023, 6, 20))
                            .build()
                    ))
                    
                    // 포트폴리오
                    .portfolioUrl("https://github.com/undergod")
                    .portfolioFile("portfolio_김재환.pdf")
                    
                    // 자기소개
                    .introduction(
                        "안녕하세요, 백엔드 개발자를 꿈꾸는 김재환입니다. " +
                        "새로운 기술을 배우는 것을 좋아하며, 클린 코드와 시스템 설계에 관심이 많습니다. " +
                        "대학 생활 동안 다양한 프로젝트와 활동을 통해 실무 경험을 쌓았으며, " +
                        "특히 오픈소스 프로젝트 기여를 통해 협업 능력을 키웠습니다."
                    )
                    
                    .build();
                    
                em.persist(user);
                return null;
            });
        };
    }
} 