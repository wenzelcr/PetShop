package br.com.tt.petshop.controller;

import br.com.tt.petshop.model.Unidade;
import br.com.tt.petshop.service.UnidadeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UnidadeController {
    UnidadeService unidadeService;

    public UnidadeController(UnidadeService unidadeService) {
        this.unidadeService = unidadeService;
    }

    @RequestMapping("/admin/unidade/cadastrar")
    public String cadastrar(){
        return "/unidade/cadastrar";
    }

    @RequestMapping("/admin/unidade/salvar")
    public String salvar(Unidade unidade, Model model){
        this.unidadeService.criar(unidade);
        return "redirect:/admin";
    }

    @RequestMapping("/admin/unidade/listar")
    public String listar(Model model){
        List<Unidade> lista = this.unidadeService.listar();
        model.addAttribute("unidades",lista);
        return "/unidade/listar";
    }

    @RequestMapping("/admin/unidade/limpar")
    public String limpar(Unidade unidade, Model model){
        this.unidadeService.limpar(unidade);
        return "redirect:/admin";
    }

}
