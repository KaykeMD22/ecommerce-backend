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
import br.edu.unifio.ecommerce.entidades.Pagamento;
import br.edu.unifio.ecommerce.entidades.Pedido;

@DataJpaTest
public class PagamentoRepositoryTest {

    @Autowired
    private PagamentoRepository repository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    private Pedido criarPedido(String email) {
        Cliente cliente = new Cliente();
        cliente.setNome("Cliente Pagamento Teste");
        cliente.setEmail(email);
        cliente.setTelefone("14999990000");

        cliente = clienteRepository.save(cliente);

        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.now());
        pedido.setStatus("AGUARDANDO PAGAMENTO");
        pedido.setValorTotal(BigDecimal.valueOf(300.00));
        pedido.setCliente(cliente);

        return pedidoRepository.save(pedido);
    }

    private Pagamento criarPagamento(
            BigDecimal valor,
            String status,
            String tipo,
            String email) {

        Pagamento pagamento = new Pagamento();
        pagamento.setValor(valor);
        pagamento.setData(LocalDateTime.now());
        pagamento.setStatus(status);
        pagamento.setTipo(tipo);
        pagamento.setPedido(criarPedido(email));

        return repository.save(pagamento);
    }

    @Test
    public void buscarPorId() {
        Pagamento salvo = criarPagamento(
                BigDecimal.valueOf(300.00),
                "APROVADO",
                "PIX",
                "pagamento1" + System.nanoTime() + "@email.com");

        Pagamento encontrado = repository.findById(salvo.getId()).orElse(null);

        assertNotNull(encontrado);
        assertEquals(BigDecimal.valueOf(300.00), encontrado.getValor());
        assertEquals("APROVADO", encontrado.getStatus());
        assertEquals("PIX", encontrado.getTipo());
        assertNotNull(encontrado.getPedido());
    }

    @Test
    public void listar() {
        criarPagamento(
                BigDecimal.valueOf(150.00),
                "APROVADO",
                "PIX",
                "pagamento2" + System.nanoTime() + "@email.com");

        criarPagamento(
                BigDecimal.valueOf(200.00),
                "PENDENTE",
                "CARTAO",
                "pagamento3" + System.nanoTime() + "@email.com");

        List<Pagamento> pagamentos = repository.findAll();

        assertTrue(pagamentos.size() >= 2);
        assertTrue(pagamentos.stream().anyMatch(
                p -> "PIX".equals(p.getTipo())));
        assertTrue(pagamentos.stream().anyMatch(
                p -> "CARTAO".equals(p.getTipo())));
    }
}
