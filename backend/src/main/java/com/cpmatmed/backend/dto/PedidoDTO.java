package com.cpmatmed.backend.dto;

import java.math.BigDecimal;
import java.util.List;

public class PedidoDTO {

    private Long id;
    private Long compradorId;
    private Long fornecedorId;
    private String nomeComprador;
    private String nomeFornecedor;
    private List<ProdutoDTO> produtos;
    private Integer totalProdutos;
    private BigDecimal valorTotal;
    private String descricao;

    public PedidoDTO() {}

    public PedidoDTO(Long id, Long compradorId, Long fornecedorId, String nomeComprador, String nomeFornecedor,
                     List<ProdutoDTO> produtos, Integer totalProdutos, BigDecimal valorTotal) {
        this.id = id;
        this.compradorId = compradorId;
        this.fornecedorId = fornecedorId;
        this.nomeComprador = nomeComprador;
        this.nomeFornecedor = nomeFornecedor;
        this.produtos = produtos;
        this.totalProdutos = totalProdutos;
        this.valorTotal = valorTotal;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCompradorId() { return compradorId; }
    public void setCompradorId(Long compradorId) { this.compradorId = compradorId; }

    public Long getFornecedorId() { return fornecedorId; }
    public void setFornecedorId(Long fornecedorId) { this.fornecedorId = fornecedorId; }

    public String getNomeComprador() { return nomeComprador; }
    public void setNomeComprador(String nomeComprador) { this.nomeComprador = nomeComprador; }

    public String getNomeFornecedor() { return nomeFornecedor; }
    public void setNomeFornecedor(String nomeFornecedor) { this.nomeFornecedor = nomeFornecedor; }

    public List<ProdutoDTO> getProdutos() { return produtos; }
    public void setProdutos(List<ProdutoDTO> produtos) { this.produtos = produtos; }

    public Integer getTotalProdutos() { return totalProdutos; }
    public void setTotalProdutos(Integer totalProdutos) { this.totalProdutos = totalProdutos; }

    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
