package com.example.Sequence.Controller;

import com.example.Sequence.dto.UserSignupRequest;
import com.example.Sequence.service.SignupService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class SignupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean // MockBean 사용으로 실제 Bean 대체
    private SignupService signupService;

    @Test
    public void testSignupSuccess() throws Exception {
        // Given
        UserSignupRequest request = createValidSignupRequest();

        // Mock Service: 성공적인 회원가입
        when(signupService.signup(Mockito.any(UserSignupRequest.class))).thenReturn("Signup successful");

        // When & Then
        mockMvc.perform(post("/api/users/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("Signup successful"));
    }

    @Test
    public void testSignupEmailExists() throws Exception {
        // Given
        UserSignupRequest request = createValidSignupRequest();

        // Mock Service: 이메일 중복 에러
        when(signupService.signup(Mockito.any(UserSignupRequest.class))).thenThrow(new IllegalArgumentException("Email already exists"));

        // When & Then
        mockMvc.perform(post("/api/users/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Email already exists"));
    }

    // Helper 메서드: Valid한 회원가입 요청 생성
    private UserSignupRequest createValidSignupRequest() {
        UserSignupRequest request = new UserSignupRequest();
        request.setId("pullzee6");
        request.setName("김민지");
        request.setBirthDate(java.time.LocalDate.of(2000, 6, 19));
        request.setGender("여자");
        request.setAddress("서울시");
        request.setPhoneNumber("010-1234-5678");
        request.setEmail("pullzee6@gmail.com");
        request.setPassword("1234");
        request.setSchoolName("고려대학교 세종캠퍼스");
        request.setMajorName("컴퓨터융합소프트웨어학과");
        request.setEntranceYear("2023");
        request.setGraduationYear("2025");
        request.setAcademicStatus("재학중");
        request.setSkills("Java, Spring, React");
        request.setDesiredPositions("Backend Developer");
        request.setPortfolioUrl("https://github.com/Minji6");
        request.setIntroduction("안녕하세요.");

        // Mock Certifications
        var certification = new UserSignupRequest.CertificationRequest();
        certification.setName("AWS Certified Developer");
        certification.setDate(java.time.LocalDate.of(2022, 8, 1));
        request.setCertifications(java.util.List.of(certification));

        // Mock Careers
        var career = new UserSignupRequest.CareerRequest();
        career.setName("(주)다돌 인턴");
        career.setType("Full-time");
        career.setStartDate(java.time.LocalDate.of(2024, 10, 1));
        career.setEndDate(java.time.LocalDate.of(2024, 12, 27));
        request.setCareers(java.util.List.of(career));

        // Mock Activities
        var activity = new UserSignupRequest.ActivityRequest();
        activity.setName("Hackathon");
        activity.setType("Competition");
        activity.setStartDate(java.time.LocalDate.of(2023, 8, 1));
        activity.setEndDate(java.time.LocalDate.of(2023, 8, 3));
        request.setActivities(java.util.List.of(activity));

        return request;
    }
}
