package xyzbank.interview.assignment.entity;

import jakarta.persistence.*;
import lombok.*;
import xyzbank.interview.assignment.enums.DocumentStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "documents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // uploaded file name
    private String fileName;

    // file type like pdf/png/jpg
    private String fileType;

    // saved file path
    private String filePath;

    @Enumerated(EnumType.STRING)
    private DocumentStatus documentStatus;

    // reason if upload fails
    private String failureReason;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private LocalDateTime uploadedAt;

    @PrePersist
    public void prePersist() {
        this.uploadedAt = LocalDateTime.now();
    }
}