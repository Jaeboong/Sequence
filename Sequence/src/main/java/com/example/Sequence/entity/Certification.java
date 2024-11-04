package com.example.Sequence.entity;

import java.time.LocalDate;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Embeddable
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Certification {
    private String name;            // 활동명
    private LocalDate date;         // 취득/수상일
} 