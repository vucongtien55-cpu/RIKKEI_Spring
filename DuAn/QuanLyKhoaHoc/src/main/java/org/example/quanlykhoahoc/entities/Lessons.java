package org.example.quanlykhoahoc.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lessons {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_id")
    private Integer lessonId;

    // Liên kết với khóa học
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    @JsonIgnore
    private Courses course;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(name = "content_url", length = 500)
    private String contentUrl; // Link video YouTube, Vimeo hoặc S3

    @Column(name = "text_content", columnDefinition = "TEXT")
    private String textContent; // Nội dung bài viết nếu không có video

    @Column(name = "order_index", nullable = false)
    private Integer orderIndex; // Thứ tự bài học: 1, 2, 3...

    @Column(name = "is_published", nullable = false)
    private Boolean isPublished = false;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
