package com.BookClub.BookClub.Entities.User;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "authorities", schema = "public")
@Data
@NoArgsConstructor
public class Authorities {

    @Id
    @JoinColumn(name = "username")
    private String username;

    @Column(name = "authority", unique = true, columnDefinition = "text", nullable = false)
    private String authority;
}
