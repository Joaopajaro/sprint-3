package com.exemplo.assessoria.controller;

import com.exemplo.assessoria.dto.InvestidorDto;
import com.exemplo.assessoria.model.Investidor;
import com.exemplo.assessoria.service.InvestidorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para Investidor.
 */
@RestController
@RequestMapping("/api/investidores")
public class InvestidorController {

    private final InvestidorService investidorService;

    public InvestidorController(InvestidorService investidorService) {
        this.investidorService = investidorService;
    }

    @GetMapping
    public List<Investidor> listar() {
        return investidorService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Investidor> obter(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(investidorService.obterPorId(id));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Investidor> criar(@Valid @RequestBody InvestidorDto dto) {
        Investidor investidor = new Investidor();
        investidor.setNome(dto.getNome());
        investidor.setEmail(dto.getEmail());
        Investidor salvo = investidorService.salvar(investidor);
        return new ResponseEntity<>(salvo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Investidor> atualizar(@PathVariable Long id, @Valid @RequestBody InvestidorDto dto) {
        try {
            Investidor investidor = new Investidor();
            investidor.setNome(dto.getNome());
            investidor.setEmail(dto.getEmail());
            return ResponseEntity.ok(investidorService.atualizar(id, investidor));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        try {
            investidorService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
