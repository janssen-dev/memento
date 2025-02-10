package dev.janssensoftware.memento.domain.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "notes")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Note {

    @Id
    private UUID id = UUID.randomUUID();

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private int width;

    @Column(nullable = false)
    private int height;

    @Column(nullable = false)
    private int x;

    @Column(nullable = false)
    private int y;

    @Embedded
    private RgbaColor color;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}