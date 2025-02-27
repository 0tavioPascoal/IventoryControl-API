package com.Tavin.IventoryControl.infra.repositories;

import com.Tavin.IventoryControl.domain.movimentations.Inbound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InboundRepository extends JpaRepository<Inbound, UUID> {
}
