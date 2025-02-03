package com.danielduhau.ImoveisApi.mapper;
import com.danielduhau.ImoveisApi.dto.ImovelDTO;
import com.danielduhau.ImoveisApi.model.Imovel;

public class ImovelMapper {

    public Imovel toEntity(ImovelDTO dto){
        Imovel imovel = new Imovel();
        imovel.setMatricula(dto.getMatricula());
        imovel.setNomeProprietario(dto.getNomeProprietario());
        imovel.setEndereco(dto.getEndereco());
        return imovel;
    }

    public ImovelDTO toDTO(Imovel imovel){
        ImovelDTO dto = new ImovelDTO();
        dto.setMatricula(imovel.getMatricula());
        dto.setNomeProprietario(imovel.getNomeProprietario());
        dto.setEndereco(imovel.getEndereco());
        return dto;
    }

}
