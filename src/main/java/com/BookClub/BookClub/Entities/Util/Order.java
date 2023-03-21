package com.BookClub.BookClub.Entities.Util;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "orders", schema = "public")
@Data
@NoArgsConstructor
public class Order {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @org.hibernate.annotations.Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID id;

    @JoinColumn(name = "username", nullable = false)
    private String username;

    @JoinColumn(name = "areacode", nullable = false)
    private long areacode;

    @JoinColumn(name = "isbn", nullable = false)
    private String isbn;

    @Column(name = "count", nullable = false)
    private int count;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "checkval", nullable = false)
    private boolean checkval;

    @Column(name = "created_at",  nullable = false)
    private Timestamp created_at;

}
