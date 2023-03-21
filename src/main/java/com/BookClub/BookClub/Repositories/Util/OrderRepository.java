package com.BookClub.BookClub.Repositories.Util;

import com.BookClub.BookClub.DTO.Util.OrderDTO;
import com.BookClub.BookClub.Entities.Util.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO \"orders\" (username, areacode, isbn, count, address, phone, created_at) " +
            "VALUES (:#{#orderDTO.username}, :#{#orderDTO.areacode}, :#{#orderDTO.isbn}, :#{#orderDTO.count}, " +
            ":#{#orderDTO.address}, :#{#orderDTO.phone}, NOW())", nativeQuery = true)
    int insertOrder(@Param("orderDTO") OrderDTO orderDTO);

    /*
    already provided from JPA
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM \"orders\" WHERE \"id\" = ?1", nativeQuery = true)
    int deleteOrder(UUID id);
    */

    //getRecentOrders
    @Query(value = "SELECT * FROM \"orders\" ", nativeQuery = true)
    List<Order> getOrdersByTime();

    //getRecentOrders
    @Query(value = "SELECT * FROM \"orders\" WHERE areacode = ?1 ORDER BY created_at DESC", nativeQuery = true)
    List<Order> getOrdersAtSpecifiedLocationByTime(long code);

    @Query(value = "SELECT * FROM \"orders\" WHERE username = ?1 ORDER BY created_at DESC", nativeQuery = true)
    List<Order> getOrderByCustomerName(String username);

}
