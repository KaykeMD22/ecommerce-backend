package br.edu.unifio.ecommerce.entidades;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Pedido {

    private LocalDateTime data;

    private String status;

    private BigDecimal valorTotal;

    @ManyToOne
    private Cliente cliente;

    @OneToOne
    private Pagamento pagamento;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}