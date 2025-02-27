package com.Tavin.IventoryControl.infra.repositories;

import com.Tavin.IventoryControl.domain.movimentations.Outbound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OutboundRepository extends JpaRepository<Outbound, UUID> {
}
