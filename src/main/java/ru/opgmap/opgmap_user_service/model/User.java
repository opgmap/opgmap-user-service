package ru.opgmap.opgmap_user_service.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

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

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime modifiedAt;

    @Column(columnDefinition = "boolean default false")
    private boolean isVip;

}
