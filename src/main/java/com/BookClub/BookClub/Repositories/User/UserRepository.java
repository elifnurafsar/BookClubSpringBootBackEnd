package com.BookClub.BookClub.Repositories.User;

import com.BookClub.BookClub.DTO.User.UserDTO;
import com.BookClub.BookClub.Entities.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "BEGIN TRANSACTION;  \n" +
            "       INSERT INTO users (username, password, enabled) VALUES (:#{#user.username}, :#{#user.password}, :#{#user.enabled});  \n" +
            "       INSERT INTO authorities (username, authority) VALUES( :#{#user.username}, :#{#user.authority});  \n" +
            " END; ", nativeQuery = true)
    int insertUser(@Param("user") UserDTO user);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "BEGIN TRANSACTION; " +
            "DELETE FROM authorities WHERE username = ?1;" +
            "DELETE FROM users WHERE username = ?1; " +
            "END; ", nativeQuery = true)
    int deleteSelf(String e_mail);

    @Query(value = "SELECT * FROM \"users\" WHERE username = ?1", nativeQuery = true)
    Optional<User> getByEmail(String e_mail);

    @Query(value = "SELECT * FROM \"users\"", nativeQuery = true)
    List<User> getAll();

    @Query(value = "SELECT * FROM \"users\" WHERE username IN " +
            "SELECT username FROM authorities WHERE authority = ?1", nativeQuery = true)
    List<User> getAllByRole(String role);

    /*@Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "BEGIN TRANSACTION;  \n" +
            "       INSERT INTO users(name, e_mail, password, location) VALUES (:#{#localAdminDTO.name}, :#{#localAdminDTO.e_mail}, :#{#localAdminDTO.password}, :#{#localAdminDTO.location})  \n" +
            "       INSERT INTO authority (user_id, role ) VALUES( (SELECT id FROM users WHERE e_mail = :#{#localAdminDTO.e_mail}), :#{#localAdminDTO.user_type} );  \n" +
            " END; ", nativeQuery = true)
    int insertLocalAdmin(@Param("localAdminDTO") LocalAdminDTO localAdminDTO);


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "BEGIN TRANSACTION;  \n" +
            "       INSERT INTO users(name, e_mail, password, location) VALUES (:#{#localAdminDTO.name}, :#{#localAdminDTO.e_mail}, :#{#localAdminDTO.password}, :#{#localAdminDTO.location})  \n" +
            "       INSERT INTO authority (user_id, role ) VALUES( (SELECT id FROM users WHERE e_mail = :#{#localAdminDTO.e_mail}), :#{#localAdminDTO.user_type} );  \n" +
            " END; ", nativeQuery = true)
    int insertLocalAdmin(@Param("localAdminDTO") LocalAdminDTO localAdminDTO);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "BEGIN TRANSACTION;  \n" +
            "       INSERT INTO users(name, e_mail, password, phone) VALUES (:#{#customerDTO.name}, :#{#customerDTO.e_mail}, :#{#customerDTO.password}, :#{#customerDTO.phone});  \n" +
            "       INSERT INTO authority (user_id, role ) VALUES( (SELECT id FROM users WHERE e_mail = :#{#customerDTO.e_mail}), :#{#customerDTO.user_type} );  \n" +
            " END; ", nativeQuery = true)
    int insertCustomer(@Param("customerDTO") CustomerDTO customerDTO);


    @Query(value = "WITH res\n" +
            "AS (SELECT user_id AS id FROM authority WHERE role = ?1)\n" +
            "SELECT * FROM users WHERE id IN (SELECT id FROM res);", nativeQuery = true)
    List<User> getByRole(String role);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "BEGIN TRANSACTION; " +
            "DELETE FROM authority WHERE user_id IN " +
            "(SELECT id FROM users WHERE e_mail = ?1; " +
            "DELETE FROM users WHERE e_mail = ?1; " +
            "END; ", nativeQuery = true)
    int deleteSelf(String e_mail);

    @Query(value = "SELECT * FROM \"users\" WHERE e_mail = ?1", nativeQuery = true)
    Optional<User> getByEmail(String e_mail);

    @Query(value = "SELECT * FROM \"users\" WHERE e_mail = ?1", nativeQuery = true)
    Optional<String> getRoleByEmail(String e_mail);*/

}
