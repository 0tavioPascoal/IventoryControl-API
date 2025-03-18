package com.Tavin.IventoryControl.infra.repositories;

import com.Tavin.IventoryControl.domain.movimentations.Outbound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OutboundRepository extends JpaRepository<Outbound, String> {

    @Query("select o from Outbound o where upper(o.location) like upper(:location) and upper(o.product.name) like upper(:productName) and upper(o.user.username) like upper(:userName) ")
    Page<Outbound> findByLocationAndUserNameAndProductName(
            @Param("userName") String userName,
            @Param("productName") String productName,
            @Param("location") String location,
            Pageable pageable);
}
