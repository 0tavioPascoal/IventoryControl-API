package com.Tavin.IventoryControl.infra.repositories;

import com.Tavin.IventoryControl.domain.movimentations.Inbound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InboundRepository extends JpaRepository<Inbound, String> {


    @Query("select i from Inbound i where upper(i.product.name) like upper(:productName) and upper(i.user.username) like upper(:userName)")
    Page<Inbound> findByProductName(@Param("productName") String productName,
                                    @Param("userName") String userName,
                                    Pageable pageable);
}
