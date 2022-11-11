package br.univille.novostalentos.controller;



import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.univille.novostalentos.entity.Produto;
import br.univille.novostalentos.service.CidadeService;
import br.univille.novostalentos.service.ProdutoService;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private CidadeService cidadeService;

    @Autowired
    //  CONTROLLER CHAMA O SERVICE
    // Ligaçao do controller com o service
    private ProdutoService service;
    @GetMapping
    public ModelAndView index(){
        var listaProdutos = service.getAll();
        return new ModelAndView("/produto/index", "listaProdutos", listaProdutos);
    }

    
    // o botao novo chama o formulario 
    @GetMapping("/novo")
    public ModelAndView novo(){
        var produto = new Produto();
        var listaCidades = cidadeService.getAll();
        HashMap<String,Object> dados = new HashMap<>();
        dados.put("listaCidades", listaCidades);
        dados.put("produto", produto);

        // ModelAndView(linka para onde esta a pasta do arquivo,variavel q eu criei no Form, novo produto );
        
        return new ModelAndView("/produto/form",dados);
        
    }
    
    // codigo para o botao enviar dps de cadastrar um novo produto funcionar
    @PostMapping(params = "form")
    public ModelAndView save(Produto produto){
    service.save(produto);
    return new ModelAndView("redirect:/produtos");
    }
    

    // o botao alterar tem q ter o Id para achar qual produto devevmos alterar
    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") long id){
        var produtos = service.findById(id);
        var listaCidades = cidadeService.getAll();
        HashMap<String,Object> dados = new HashMap<>();
        dados.put("listaCidades", listaCidades);
        dados.put("produtos", produtos);
        return new ModelAndView("produto/form",dados);

    }



}
