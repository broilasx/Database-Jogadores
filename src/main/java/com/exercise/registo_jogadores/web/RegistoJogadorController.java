package com.exercise.registo_jogadores.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exercise.registo_jogadores.model.GrupoCodinome;
import com.exercise.registo_jogadores.model.Jogador;
import com.exercise.registo_jogadores.service.JogadorService;

@Controller
@RequestMapping("registo-jogador")
public class RegistoJogadorController {
    private final JogadorService jogadorService;

    public RegistoJogadorController(JogadorService jogadorService) {
        this.jogadorService = jogadorService;
    }

    @GetMapping
    public String paginaRegistoJogador(Model model) {
        model.addAttribute("jogador", new Jogador(null, null, null, null, null));
        model.addAttribute("gruposCodinomes", GrupoCodinome.values());
        return "registo-jogador";
    }   

    @PostMapping
    public String cadastrarJogador(@ModelAttribute Jogador jogador) {
        try {
            jogadorService.registarJogador(jogador);
            return "redirect:/registo-jogador";
        } catch (Exception e) {
            return "redirect:/registo-jogador";
        }
        
    }

}
