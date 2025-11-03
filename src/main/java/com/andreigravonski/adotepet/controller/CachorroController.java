package com.andreigravonski.adotepet.controller;

import com.andreigravonski.adotepet.model.Cachorro;
import com.andreigravonski.adotepet.model.Denuncia;
import com.andreigravonski.adotepet.model.ONG;
import com.andreigravonski.adotepet.model.StatusDenuncia;
import com.andreigravonski.adotepet.service.CachorroService;
import com.andreigravonski.adotepet.service.DenunciaService;
import com.andreigravonski.adotepet.service.ONGService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;

import java.util.List;

@Controller
@RequestMapping("/caes")
public class CachorroController {

    private final DenunciaService denunciaService;
    private final CachorroService cachorroService;
    private final ONGService ongService;

    public CachorroController(DenunciaService denunciaService, CachorroService cachorroService, ONGService ongService) {
        this.denunciaService = denunciaService;
        this.cachorroService = cachorroService;
        this.ongService = ongService;
    }

    @GetMapping("/listar")
    public String listarCaes(Model model, Authentication authentication,
                             @RequestParam(value = "raca",required = false) String raca) { // <-- PARÂMETRO NOVO
        ONG ongLogada = (ONG) authentication.getPrincipal();
        List<Cachorro> caesDaOng = cachorroService.buscarCaesFiltrado(ongLogada, raca);
        model.addAttribute("caes", caesDaOng);
        model.addAttribute("racaBusca", raca);
        return "caes/listar";
    }

    @GetMapping("/novo")
    public String mostrarFormularioDeCadastro(Model model) {
        model.addAttribute("cao", new Cachorro());
        model.addAttribute("listaOngs", ongService.buscarONG());
        return "caes/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editarCaes(@PathVariable Long id, Model model) {
        Cachorro cachorro = cachorroService.buscarPorId(id);
        model.addAttribute("cao", cachorro);
        model.addAttribute("listaOngs", ongService.buscarONG());
        return "caes/formulario";
    }

    @PostMapping("/salvar")
    public String salvarCao(@ModelAttribute Cachorro cachorro,
                            @RequestParam("imagemFile") MultipartFile imagemFile, // <-- NOVO PARÂMETRO
                            RedirectAttributes redirectAttributes) {
        try {
            // Agora passamos o cachorro E o arquivo para o serviço
            cachorroService.salvar(cachorro, imagemFile);
            redirectAttributes.addFlashAttribute("mensagem", "Cão salvo com sucesso!");
            return "redirect:/caes/listar";
        } catch (Exception e) {
            System.err.println("### ERRO AO SALVAR O CÃO ###");
            e.printStackTrace(); // <-- A CONFISSÃO DO ERRO
            redirectAttributes.addFlashAttribute("mensagemErro", "Ocorreu um erro inesperado ao salvar.");
            return "redirect:/caes/formulario";
        }
    }

    @GetMapping("/deletar/{id}")
    public String deletarCao(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        cachorroService.deletarPorId(id);
        redirectAttributes.addFlashAttribute("mensagem", "Cão deletado com sucesso!");
        return "redirect:/caes/listar";
    }

    @GetMapping("/registrar-por-denuncia/{id}")
    public String mostrarFormularioRegistroPorDenuncia(@PathVariable Long id, Model model, Authentication authentication) {
        Denuncia denuncia = denunciaService.buscarPorId(id);
        ONG ongLogada = (ONG) authentication.getPrincipal();
        denuncia.setOng(ongLogada);
        denuncia.setStatus(StatusDenuncia.EM_ANALISE);
        denunciaService.salvar(denuncia);
        Cachorro novoCachorro = new Cachorro();
        novoCachorro.setDescricao(denuncia.getDescricao());
        model.addAttribute("cao", novoCachorro);
        model.addAttribute("listaOngs", ongService.buscarONG());
        return "caes/formulario";
    }
}

