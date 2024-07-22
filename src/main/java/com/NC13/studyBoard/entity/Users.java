package com.NC13.studyBoard.entity;
import com.NC13.studyBoard.converter.RoleConverter;
import com.NC13.studyBoard.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Entity
@Table(name="users")
@Data
@Builder
@Transactional
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    private String name;

    @Convert(converter = RoleConverter.class)
    private Role role;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;

//    @JsonIgnore
//    @OneToMany(mappedBy = "users")
//    private List<Board> boards= new ArrayList<>();
}