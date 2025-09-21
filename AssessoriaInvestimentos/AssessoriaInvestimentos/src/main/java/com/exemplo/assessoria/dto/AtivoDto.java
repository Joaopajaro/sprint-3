package com.exemplo.assessoria.dto;

import com.exemplo.assessoria.model.CategoriaAtivo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * DTO para ativo de investimento.
 */
public class AtivoDto {
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotNull(message = "Valor é obrigatório")
    private BigDecimal valor;

    @NotNull(message = "Categoria é obrigatória")
    private CategoriaAtivo categoria;

    // getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
    public CategoriaAtivo getCategoria() { return categoria; }
    public void setCategoria(CategoriaAtivo categoria) { this.categoria = categoria; }
}
