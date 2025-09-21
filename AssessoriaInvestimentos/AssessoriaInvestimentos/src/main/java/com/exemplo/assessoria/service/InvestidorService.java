package com.exemplo.assessoria.service;

import com.exemplo.assessoria.model.Investidor;
import com.exemplo.assessoria.repository.InvestidorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Serviço com regras de negócio para Investidor.
 */
@Service
public class InvestidorService {
    private final InvestidorRepository investidorRepository;

    public InvestidorService(InvestidorRepository investidorRepository) {
        this.investidorRepository = investidorRepository;
    }

    public List<Investidor> listarTodos() {
        return investidorRepository.findAll();
    }

    public Investidor obterPorId(Long id) {
        return investidorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Investidor não encontrado"));
    }

    public Investidor salvar(Investidor investidor) {
        return investidorRepository.save(investidor);
    }

    public Investidor atualizar(Long id, Investidor novo) {
        Investidor existente = obterPorId(id);
        existente.setNome(novo.getNome());
        existente.setEmail(novo.getEmail());
        return investidorRepository.save(existente);
    }

    public void excluir(Long id) {
        investidorRepository.deleteById(id);
    }
}
