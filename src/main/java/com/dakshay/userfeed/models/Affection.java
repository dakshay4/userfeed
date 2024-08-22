package com.dakshay.userfeed.models;

import com.dakshay.userfeed.enums.AffectionContextType;
import com.dakshay.userfeed.enums.AffectionType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "affections",
        uniqueConstraints = @UniqueConstraint(columnNames = {"contextId", "contextType", "userId"})
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Affection {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long contextId;
    @Enumerated(EnumType.STRING)
    private AffectionContextType contextType;
    private Long userId;
    @Enumerated(EnumType.STRING)
    private AffectionType affectionType;
    private boolean deleted;

    public Affection(Long postId, Long userId, AffectionType affectionType) {
        this.userId = userId;
        this.affectionType = affectionType;
    }
}
