package com.danielduhau.ImoveisApi.repository;

import com.danielduhau.ImoveisApi.model.Imovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ImovelRepository extends JpaRepository<Imovel, Long> {

    List<Imovel> findByProprietarioIgnoreCaseContaining(@Param("proprietario") String proprietario);

    Optional<Imovel> findByMatricula(String matricula);

}
