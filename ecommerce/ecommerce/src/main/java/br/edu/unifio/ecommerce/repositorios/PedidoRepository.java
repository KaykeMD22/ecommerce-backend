package br.edu.unifio.ecommerce.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unifio.ecommerce.entidades.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
