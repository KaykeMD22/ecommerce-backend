package br.edu.unifio.ecommerce.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Cliente {

    private String nome;
    private String email;
    private String telefone;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}