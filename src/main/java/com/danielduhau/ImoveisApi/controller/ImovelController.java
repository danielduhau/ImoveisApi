package com.danielduhau.ImoveisApi.controller;

import com.danielduhau.ImoveisApi.model.Imovel;
import com.danielduhau.ImoveisApi.service.ImovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/imovel")
public class ImovelController {

    private final ImovelService imovelService;

    @Autowired
    public ImovelController(ImovelService imovelService) {
        this.imovelService = imovelService;
    }

    @GetMapping
    public List<Imovel> getImovel(){
        return imovelService.getImoveis();
    }

    /*@GetMapping("pesquisar/{id}")
    public Optional<Imovel> get(@PathVariable Long id) {
        return imovelService.findById(id);

    }*/

    @GetMapping("pesquisar/{id}")
	public ResponseEntity<Optional<Imovel>> detalhar(@PathVariable Long id) {
		Optional<Imovel> imovel = imovelService.findById(id);

		if (imovel.isPresent()) {
			return ResponseEntity.ok(imovel);

		}

		return ResponseEntity.notFound().build();
	}

    @GetMapping("/buscaProprietario/{proprietario}")
    public List<Imovel> buscaPorProprietario(@PathVariable String proprietario){
        return imovelService.findByProprietario(proprietario);
    }

    @PostMapping
    public void create(@RequestBody Imovel imovel){
        imovelService.addNovoImovel(imovel);
    }
}
