package com.exemplo.assessoria.service;

import com.exemplo.assessoria.model.Ativo;
import com.exemplo.assessoria.repository.AtivoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Serviço com regras de negócio para Ativo.
 */
@Service
public class AtivoService {
    private final AtivoRepository ativoRepository;

    public AtivoService(AtivoRepository ativoRepository) {
        this.ativoRepository = ativoRepository;
    }

    public List<Ativo> listarTodos() {
        return ativoRepository.findAll();
    }

    public Ativo obterPorId(Long id) {
        return ativoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Ativo não encontrado"));
    }

    public Ativo salvar(Ativo ativo) {
        return ativoRepository.save(ativo);
    }

    public Ativo atualizar(Long id, Ativo novo) {
        Ativo existente = obterPorId(id);
        existente.setNome(novo.getNome());
        existente.setValor(novo.getValor());
        existente.setCategoria(novo.getCategoria());
        return ativoRepository.save(existente);
    }

    public void excluir(Long id) {
        ativoRepository.deleteById(id);
    }
}
