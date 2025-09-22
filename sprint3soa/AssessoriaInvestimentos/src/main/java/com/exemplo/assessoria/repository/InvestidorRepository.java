package com.exemplo.assessoria.repository;

import com.exemplo.assessoria.model.Investidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório para a entidade Investidor.
 */
@Repository
public interface InvestidorRepository extends JpaRepository<Investidor, Long> {
}
