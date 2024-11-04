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
public class Career {
    private String name;            // 활동명
    private String type;            // 활동유형
    private LocalDate startDate;    // 시작일
    private LocalDate endDate;      // 종료일
} 