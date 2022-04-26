package com.danielduhau.ImoveisApi.repository;

import com.danielduhau.ImoveisApi.model.Imovel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImovelRepository extends JpaRepository<Imovel, Long> {


}
