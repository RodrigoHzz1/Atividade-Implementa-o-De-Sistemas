package com.example.Helpdesk.repository;

import com.example.Helpdesk.model.ChamadoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChamadoRepository extends JpaRepository<ChamadoModel, Long> {
}
