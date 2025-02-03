package com.danielduhau.ImoveisApi.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Imovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String matricula;

    @Column(nullable = false)
    private String nomeProprietario;

    @Column(nullable = false)
    private String endereco;

    public Imovel(String matricula, String endereco, String nomeProprietario) {
        this.matricula = matricula;
        this.endereco = endereco;
        this.nomeProprietario = nomeProprietario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Imovel imovel = (Imovel) o;
        return id != null && Objects.equals(id, imovel.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
