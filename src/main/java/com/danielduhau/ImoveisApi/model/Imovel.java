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
public class Imovel {

    @Id
    @SequenceGenerator(
            name = "imovel_seq",
            sequenceName = "imovel_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "imovel_seq"
    )
    private Long id;
    private String matricula;
    private String endereco;
    private String proprietario;

    public Imovel(String matricula, String endereco, String proprietario) {
        this.matricula = matricula;
        this.endereco = endereco;
        this.proprietario = proprietario;
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
