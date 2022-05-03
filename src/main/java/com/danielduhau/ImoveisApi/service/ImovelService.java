package com.danielduhau.ImoveisApi.service;

import com.danielduhau.ImoveisApi.model.Imovel;
import com.danielduhau.ImoveisApi.repository.ImovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImovelService {

    @Autowired
    private ImovelRepository imovelRepository;

    public List<Imovel> getImoveis(){
        return imovelRepository.findAll();
    }

    public List<Imovel> findByProprietario(String nome){
        return imovelRepository.findByProprietarioIgnoreCaseContaining(nome);
    }

    public void addNovoImovel(Imovel imovel) {
        Optional<Imovel> imovelOptional =
                imovelRepository.findByMatricula(imovel.getMatricula());

        if (imovelOptional.isPresent()){
            throw new IllegalStateException("A matrícula informada já está associada a outro imóvel");
        }
        imovelRepository.save(imovel);
    }

    public Optional<Imovel> findById(Long id) {
        return imovelRepository.findById(id);
    }
}
