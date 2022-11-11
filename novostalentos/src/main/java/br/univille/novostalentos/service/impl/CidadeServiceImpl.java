package br.univille.novostalentos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.novostalentos.entity.Cidade;
import br.univille.novostalentos.repository.CidadeRepository;
import br.univille.novostalentos.service.CidadeService;

// pede o serviço
@Service
public class CidadeServiceImpl implements CidadeService{

    // acha o objeto q eu preciso
    @Autowired
    private CidadeRepository repositorio;

    // anotaçao opcional, tornar a sobreescrita visual
    // a classe concreta 
    //
    @Override
    public List<Cidade> getAll() {
        
        return repositorio.findAll();
    }

    @Override
    public Cidade save(Cidade cidade) {
        
        return repositorio.save(cidade);
    }

    @Override
    public Cidade findById(long id) {
        
        var resultado = repositorio.findById(id);
        if(resultado.isPresent()){
            return resultado.get();
        }
        return new Cidade();
    }

    
}
