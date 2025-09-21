package com.exemplo.assessoria.controller;

import com.exemplo.assessoria.dto.AtivoDto;
import com.exemplo.assessoria.model.Ativo;
import com.exemplo.assessoria.service.AtivoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para operações com ativos de investimento.
 *
 * Este controlador expõe endpoints para criar, listar, atualizar e excluir
 * ativos. Os métodos retornam ResponseEntity para padronizar o status HTTP
 * e o corpo da resposta. As validações básicas são aplicadas a partir
 * do DTO, e exceções são tratadas pelo GlobalExceptionHandler.
 */
@RestController
@RequestMapping("/api/ativos")
public class AtivoController {

    private final AtivoService ativoService;

    public AtivoController(AtivoService ativoService) {
        this.ativoService = ativoService;
    }

    /**
     * Lista todos os ativos cadastrados.
     *
     * @return lista de ativos
     */
    @GetMapping
    public List<Ativo> listarAtivos() {
        return ativoService.listarTodos();
    }

    /**
     * Obtém um ativo específico pelo id.
     *
     * @param id identificador do ativo
     * @return ResponseEntity contendo o ativo ou 404 se não existir
     */
    @GetMapping("/{id}")
    public ResponseEntity<Ativo> obterAtivo(@PathVariable Long id) {
        try {
            Ativo ativo = ativoService.obterPorId(id);
            return ResponseEntity.ok(ativo);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Cadastra um novo ativo.
     *
     * @param dto dados do ativo a cadastrar
     * @return ResponseEntity com o ativo criado e status 201
     */
    @PostMapping
    public ResponseEntity<Ativo> criarAtivo(@Valid @RequestBody AtivoDto dto) {
        Ativo ativo = new Ativo();
        ativo.setNome(dto.getNome());
        ativo.setValor(dto.getValor());
        ativo.setCategoria(dto.getCategoria());
        Ativo salvo = ativoService.salvar(ativo);
        return new ResponseEntity<>(salvo, HttpStatus.CREATED);
    }

    /**
     * Atualiza um ativo existente.
     *
     * @param id  identificador do ativo
     * @param dto dados atualizados
     * @return ResponseEntity com o ativo atualizado ou 404 se não existir
     */
    @PutMapping("/{id}")
    public ResponseEntity<Ativo> atualizarAtivo(@PathVariable Long id, @Valid @RequestBody AtivoDto dto) {
        try {
            Ativo ativo = new Ativo();
            ativo.setNome(dto.getNome());
            ativo.setValor(dto.getValor());
            ativo.setCategoria(dto.getCategoria());
            Ativo atualizado = ativoService.atualizar(id, ativo);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Exclui um ativo pelo id.
     *
     * @param id identificador do ativo
     * @return ResponseEntity sem corpo e status 204 ou 404 se não existir
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAtivo(@PathVariable Long id) {
        try {
            ativoService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}