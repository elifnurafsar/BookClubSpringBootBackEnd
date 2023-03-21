package com.BookClub.BookClub.Repositories.User;

import com.BookClub.BookClub.Entities.User.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthorityRepository extends JpaRepository<Authorities, UUID> {

    @Query(value = "SELECT authority FROM authorities WHERE username = ?1", nativeQuery = true)
    String getRoleByUserEMail(String e_mail);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO authorities (username, authority) \n" +
            "VALUES(?1, ?2)", nativeQuery = true)
    int insertUserRole(String e_mail, String role);

    @Query(value = "SELECT * FROM authorities WHERE username = ?1 ", nativeQuery = true)
    Optional<Authorities> getAuthority(String e_mail);

    /*@Query(value = "SELECT role FROM authority WHERE user_id IN " +
            "(SELECT id FROM users WHERE e_mail = ?1) ", nativeQuery = true)
    String getRoleByUserEMail(String e_mail);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO authority (user_id, role ) \n" +
            "VALUES( (SELECT id FROM users WHERE e_mail = ?1), ?2)", nativeQuery = true)
    int insertUserRole(String e_mail, String role);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM authority WHERE user_id IN " +
            "(SELECT id FROM users WHERE e_mail = ?1) ", nativeQuery = true)
    int deleteUserRoleByEMail(String e_mail);
    */
}
