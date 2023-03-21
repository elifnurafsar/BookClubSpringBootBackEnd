package com.BookClub.BookClub.Entities.User;

import com.BookClub.BookClub.Entities.Util.Location;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "localadmin", schema = "public")
@Data
public class LocalAdmin {

    @Id
    @Column(name = "id", unique = true, columnDefinition = "uuid")
    @org.hibernate.annotations.Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID ID;

    @NotBlank(message = "E-mail cannot be blank")
    @NotEmpty(message = "E-mail cannot be empty")
    @NotNull
    @Column(name = "e_mail", columnDefinition = "text", nullable = false)
    private String username;

    @NotBlank(message = "Phone cannot be blank")
    @NotEmpty(message = "Phone cannot be empty")
    @NotNull
    @Column(name = "phone", columnDefinition = "text", nullable = false)
    private String phone;

    //data needed for local admin
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "area_code")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    Location location;

}
