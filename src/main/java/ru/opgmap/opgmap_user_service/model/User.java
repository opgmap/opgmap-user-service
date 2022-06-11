package ru.opgmap.opgmap_user_service.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Model {

    @Id
    @GenericGenerator(name = "settableGenerator", strategy = "ru.opgmap.opgmap_user_service.hibernate.SettableSequenceGenerator")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "settableGenerator")
    private UUID id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Embedded
    private Address address;

    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @Column(columnDefinition = "boolean default false")
    private boolean isVip;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

}
