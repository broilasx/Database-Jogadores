package com.exercise.registo_jogadores.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exercise.registo_jogadores.model.GrupoCodinome;
import com.exercise.registo_jogadores.model.Jogador;
import com.exercise.registo_jogadores.service.JogadorService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("registo-jogador")
public class RegistoJogadorController {
    private final JogadorService jogadorService;

    public RegistoJogadorController(JogadorService jogadorService) {
        this.jogadorService = jogadorService;
    }

    @GetMapping
    public String paginaRegistoJogador(Model model) {
        return getViewAndModel(model, new Jogador(null, null, null, null, null));
    }

    @PostMapping
    public String cadastrarJogador(@ModelAttribute @Valid Jogador jogador, BindingResult result, Model model) throws Exception {
        if (result.hasErrors()) {
            return getViewAndModel(model, jogador);
        }
        
        jogadorService.registarJogador(jogador);
        return "redirect:/registo-jogador";
        
        
    }

    private String getViewAndModel(Model model, Jogador jogador) {
        model.addAttribute("jogador", jogador);
        model.addAttribute("gruposCodinomes", GrupoCodinome.values());
        return "registo-jogador";
    }   

}
