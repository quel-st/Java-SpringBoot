package br.univille.novostalentos.service;

import java.util.List;

import br.univille.novostalentos.entity.Produto;

public interface ProdutoService {
    List<Produto> getAll();
    Produto save(Produto produto);
    Produto findById(long id);
}
