package com.exemplo.assessoria.repository;

import com.exemplo.assessoria.model.Ativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório para a entidade Ativo.
 */
@Repository
public interface AtivoRepository extends JpaRepository<Ativo, Long> {
}
