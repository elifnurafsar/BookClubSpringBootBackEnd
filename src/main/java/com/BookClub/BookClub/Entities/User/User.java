package com.BookClub.BookClub.Entities.User;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "users", schema = "public")
@Data
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "id", unique = true, columnDefinition = "uuid")
    @org.hibernate.annotations.Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID ID;

    @NotBlank(message = "E-mail cannot be blank")
    @NotEmpty(message = "E-mail cannot be empty")
    @NotNull
    @Column(name = "username", columnDefinition = "text", nullable = false)
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @NotEmpty(message = "Password cannot be empty")
    @NotNull
    @Column( name = "password", columnDefinition = "text", nullable = false)
    private String password;

    @Column(name = "enabled", columnDefinition = "boolean")
    private boolean enabled;

}
