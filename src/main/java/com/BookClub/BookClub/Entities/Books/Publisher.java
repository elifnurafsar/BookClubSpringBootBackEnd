package com.BookClub.BookClub.Entities.Books;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Publisher", schema = "public")
@Data
@NoArgsConstructor
public class Publisher{

    @Id
    @Column(name = "id", updatable = false, columnDefinition = "BINARY(16)")
    @org.hibernate.annotations.Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID ID;
    @Column(name = "name", columnDefinition = "text")
    private String name;
    @Column(name = "website", columnDefinition = "text", nullable = false)
    private String website;
    @Column(name = "phone", columnDefinition = "text", nullable = false)
    private String phone;
    @Column(name = "address" ,columnDefinition = "text", nullable = false)
    private String address;

    public Publisher(String name, String website, String phone, String address){
        this.name=name;
        this.address=address;
        this.website=website;
        this.phone=phone;
    }

    public String getWebsite() {
        return this.website;
    }

    public String getName() {
        return this.name;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getAddress() {
        return this.address;
    }

    public UUID getID() {
        return this.ID;
    }

}
