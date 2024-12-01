package com.example.Sequence.service;

import com.example.Sequence.entity.User;
import com.example.Sequence.entity.Certification;
import com.example.Sequence.entity.Career;
import com.example.Sequence.entity.Activity;
import com.example.Sequence.dto.UserSignupRequest;
import com.example.Sequence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SignupService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String signup(UserSignupRequest request) {
        // 중복 확인
        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email already exists";
        }
        if (userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            return "Phone number already exists";
        }

        // Certification 매핑
        var certifications = request.getCertifications().stream()
                .map(cert -> Certification.builder()
                        .name(cert.getName())
                        .date(cert.getDate())
                        .build())
                .collect(Collectors.toList());

        // Career 매핑
        var careers = request.getCareers().stream()
                .map(career -> Career.builder()
                        .name(career.getName())
                        .type(career.getType())
                        .startDate(career.getStartDate())
                        .endDate(career.getEndDate())
                        .build())
                .collect(Collectors.toList());

        // Activity 매핑
        var activities = request.getActivities().stream()
                .map(activity -> Activity.builder()
                        .name(activity.getName())
                        .type(activity.getType())
                        .startDate(activity.getStartDate())
                        .endDate(activity.getEndDate())
                        .build())
                .collect(Collectors.toList());

        // User 엔티티 생성 및 저장
        User user = User.builder()
                .id(request.getId())
                .name(request.getName())
                .birthDate(request.getBirthDate())
                .gender(request.getGender())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .schoolName(request.getSchoolName())
                .majorName(request.getMajorName())
                .entranceYear(request.getEntranceYear())
                .graduationYear(request.getGraduationYear())
                .academicStatus(request.getAcademicStatus())
                .skills(request.getSkills())
                .desiredPositions(request.getDesiredPositions())
                .portfolioUrl(request.getPortfolioUrl())
                .portfolioFile(request.getPortfolioFile())
                .introduction(request.getIntroduction())
                .certifications(certifications)
                .careers(careers)
                .activities(activities)
                .build();

        userRepository.save(user);
        return "Signup successful";
    }
}
