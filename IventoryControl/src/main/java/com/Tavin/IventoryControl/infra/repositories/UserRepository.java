package com.Tavin.IventoryControl.infra.repositories;

import com.Tavin.IventoryControl.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("select u from User u where upper(u.username) like upper(:username) ")
    Page<User> findByUsername(
    @Param("username") String username, Pageable pageable);
}
