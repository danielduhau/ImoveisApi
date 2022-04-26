package com.danielduhau.ImoveisApi.service;

import com.danielduhau.ImoveisApi.model.Imovel;
import com.danielduhau.ImoveisApi.repository.ImovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImovelService {

    @Autowired
    private ImovelRepository imovelRepository;

    public List<Imovel> getImoveis(){
        return imovelRepository.findAll();
    }
}
