package com.BookClub.BookClub.Repositories.Util;

import com.BookClub.BookClub.Entities.Util.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO location(area_code, name, address, phone) VALUES (?1, ?2, ?3, ?4)", nativeQuery = true)
    int insertLocation(long area_code, String name, String address, String phone);

    /*@Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO location(area_code, phone) VALUES (?1, ?2)", nativeQuery = true)
    int insertLocation(int area_code, String phone);*/

    @Query(value = "SELECT * FROM location ORDER BY area_code", nativeQuery = true)
    List<Location> getLocations();

    @Query(value = "SELECT * FROM \"location\" WHERE area_code = ?1", nativeQuery = true)
    Optional<Location> findByAreaCode(long area_code);
}
