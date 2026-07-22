package org.example.baitapss03.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Enrollment {
    private Long id;
    private String studentName;
    private Long courseId;
}
