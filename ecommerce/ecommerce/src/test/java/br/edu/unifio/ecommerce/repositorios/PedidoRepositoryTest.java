package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import br.edu.unifio.ecommerce.entidades.Cliente;
import br.edu.unifio.ecommerce.entidades.Pedido;

@DataJpaTest
public class PedidoRepositoryTest {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    private Cliente criarCliente(String nome, String email) {
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setTelefone("14999990000");
        return clienteRepository.save(cliente);
    }

    private Pedido criarPedido(String status, BigDecimal valorTotal) {
        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.now());
        pedido.setStatus(status);
        pedido.setValorTotal(valorTotal);
        pedido.setCliente(criarCliente(
                "Cliente Pedido Teste",
                "pedido" + System.nanoTime() + "@email.com"));
        return repository.save(pedido);
    }

    @Test
    public void buscarPorId() {
        Pedido salvo = criarPedido("PAGO", BigDecimal.valueOf(500.00));

        Pedido encontrado = repository.findById(salvo.getId()).orElse(null);

        assertNotNull(encontrado);
        assertEquals("PAGO", encontrado.getStatus());
        assertEquals(BigDecimal.valueOf(500.00), encontrado.getValorTotal());
        assertNotNull(encontrado.getCliente());
        assertEquals("Cliente Pedido Teste", encontrado.getCliente().getNome());
    }

    @Test
    public void listar() {
        criarPedido("AGUARDANDO PAGAMENTO", BigDecimal.valueOf(100.00));
        criarPedido("ENVIADO", BigDecimal.valueOf(250.00));

        List<Pedido> pedidos = repository.findAll();

        assertTrue(pedidos.size() >= 2);
        assertTrue(pedidos.stream().anyMatch(
                p -> "AGUARDANDO PAGAMENTO".equals(p.getStatus())));
        assertTrue(pedidos.stream().anyMatch(
                p -> "ENVIADO".equals(p.getStatus())));
    }
}
