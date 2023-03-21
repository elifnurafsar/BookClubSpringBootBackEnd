package com.BookClub.BookClub.Entities.Util;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Location", schema = "public")
@Data
@NoArgsConstructor
public class Location {
    @Id
    @Column(name = "area_code", unique = true, nullable = false)
    private long area_code;

    @Column(name = "name", columnDefinition = "text", nullable = true)
    private String name;

    @Column(name = "address", columnDefinition = "text", nullable = true)
    private String address;

    @Column(name = "phone", columnDefinition = "text", nullable = false)
    private String phone;

}
