package br.univille.novostalentos.controller;



import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.univille.novostalentos.entity.Cliente;
import br.univille.novostalentos.service.CidadeService;
import br.univille.novostalentos.service.Clienteservice;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
    

    // //esse codigo será removido - controlador nao deve chamar repositorio
    // @Autowired
    // private ClienteRepository repositorio;

    @Autowired
    private Clienteservice service;

    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public ModelAndView index(){

        /*ArrayList<Cliente> listaClientes = 
            new ArrayList<>();
        
        var cliente1 = new Cliente();
        cliente1.setId(1);
        cliente1.setNome("Zezinho");
        cliente1.setEndereco("Rua lalalala 100");
        cliente1.setSexo("Masculino");
        listaClientes.add(cliente1);
        var cliente2 = new Cliente();
        cliente2.setId(2);
        cliente2.setNome("Luizinho");
        cliente2.setEndereco("Rua lelelelel 200");
        cliente2.setSexo("Masculino");
        listaClientes.add(cliente2);*/
        
        var listaClientes = service.getAll();

        return new ModelAndView("cliente/index","listaClientes",listaClientes);
    
    }
    // olha o verbo e a rota
    @GetMapping("/novo")
    public ModelAndView novo(){
        var cliente = new Cliente(); 
        var listaCidades = cidadeService.getAll();
        HashMap<String,Object> dados = new HashMap<>();

        dados.put("cliente", cliente);
        dados.put("listaCidades", listaCidades);


        return new ModelAndView("cliente/form", dados);
    }
    

    @PostMapping(params = "form")
    public ModelAndView save(@Valid Cliente cliente, BindingResult bindResult){
        // System.out.print("Nome: ");6
        // System.out.printl''''''''''''''''''''''''''''''''n(cliente.getNome());
        // System.out.print("\nEndereço: ");
        // System.out.println(cliente.getEndereco());
        // System.out.print("\nSexo: ");
        // System.out.println(cliente.getSexo());

        if(bindResult.hasErrors()){
            var listaCidades = cidadeService.getAll();
            HashMap<String,Object> dados = new HashMap<>();
    
            dados.put("cliente", cliente);
            dados.put("listaCidades", listaCidades);
    


            return new ModelAndView("cliente/form",dados);
        }


        service.save(cliente);
        

        return new ModelAndView("redirect:/clientes");
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") long id){
        var umCliente = service.findById(id);
        var listaCidades = cidadeService.getAll();
        HashMap<String,Object> dados = new HashMap<>();

        dados.put("cliente", umCliente);
        dados.put("listaCidades", listaCidades);

        return new ModelAndView("cliente/form",dados);
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") long id){
        service.delete(id);

        return new ModelAndView("redirect:/clientes");
    }



}
