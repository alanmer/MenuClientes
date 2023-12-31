package com.bolsadeideas.springbootjpa.controllers;

import com.bolsadeideas.springbootjpa.models.dao.IClienteDao;
import com.bolsadeideas.springbootjpa.models.entity.Cliente;
import com.bolsadeideas.springbootjpa.models.service.IClienteService;
import com.bolsadeideas.springbootjpa.util.paginator.PageRender;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Map;


@Controller
@SessionAttributes("cliente")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;
    @GetMapping (value = "/listar")
    public String listar(@RequestParam(name = "page",defaultValue = "0") int page, Model model){
        Pageable pageRequest =  PageRequest.of(page,4);
        Page<Cliente> clientes=clienteService.findAll(pageRequest);

        PageRender<Cliente>pageRender=new PageRender<>("/listar",clientes);
        model.addAttribute("page",pageRender);
        model.addAttribute("titulo","Listado de clientes");
        model.addAttribute("clientes",clientes);
        return "listar";

    }
    @GetMapping(value = "/form")
    public  String crear (Map<String,Object> model){

        Cliente cliente =new Cliente();
        model.put("cliente",cliente);
        model.put("titulo","Formulario de Cliente");
        return "form";
    }
    @GetMapping (value = "/form/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String,Object> model){
        Cliente cliente =null;
        if(id>0){
            cliente=clienteService.findOne(id);
        }else{
            return "redirect:/listar";
        }
        model.put("cliente",cliente);
        model.put("titulo","Editar cliente");
        return "form";
    }


    @PostMapping(value = "/form")
    public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status){
        if(result.hasErrors()){
            model.addAttribute("titulo","Formulario de cliente");
            return "form";
        }

        clienteService.save(cliente);
        status.setComplete();
        return "redirect:listar";
    }

    @GetMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id")Long id){
        if(id >0){
            clienteService.delete(id);

        }
        return "redirect:/listar";
    }
}
