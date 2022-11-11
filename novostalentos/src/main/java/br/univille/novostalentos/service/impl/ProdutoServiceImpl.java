package br.univille.novostalentos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.novostalentos.entity.Produto;
import br.univille.novostalentos.repository.ProdutoRepository;
import br.univille.novostalentos.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    // conecta o repositorio
    @Autowired
    private ProdutoRepository repositorio;
    @Override
    public List<Produto> getAll() {
        //  findAll(); procura por tudo
        return repositorio.findAll();
    }
    @Override
    public Produto save(Produto produto) {
        
        return repositorio.save(produto);
    }
    @Override
    public Produto findById(long id) {
        var resultado = repositorio.findById(id);
        // esta vendo se tem o id q eu mencionei na tabela
        if(resultado.isPresent()){
            return resultado.get();
        }
        return new Produto();
    }
    
    

}
