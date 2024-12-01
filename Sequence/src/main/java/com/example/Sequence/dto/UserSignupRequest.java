package com.example.Sequence.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserSignupRequest {
    private String id;
    private String name;
    private LocalDate birthDate;
    private String gender;
    private String address;
    private String phoneNumber;
    private String email;
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

    private List<CertificationRequest> certifications;
    private List<CareerRequest> careers;
    private List<ActivityRequest> activities;

    @Data
    public static class CertificationRequest {
        private String name;
        private LocalDate date;
    }

    @Data
    public static class CareerRequest {
        private String name;
        private String type;
        private LocalDate startDate;
        private LocalDate endDate;
    }

    @Data
    public static class ActivityRequest {
        private String name;
        private String type;
        private LocalDate startDate;
        private LocalDate endDate;
    }
}
