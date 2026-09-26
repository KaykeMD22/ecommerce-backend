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

import br.edu.unifio.ecommerce.entidades.Categoria;
import br.edu.unifio.ecommerce.entidades.Cliente;
import br.edu.unifio.ecommerce.entidades.ItemPedido;
import br.edu.unifio.ecommerce.entidades.Pedido;
import br.edu.unifio.ecommerce.entidades.Produto;

@DataJpaTest
public class ItemPedidoRepositoryTest {

    @Autowired
    private ItemPedidoRepository repository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    private Cliente criarCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("Cliente Item Teste");
        cliente.setEmail("item" + System.nanoTime() + "@email.com");
        cliente.setTelefone("14999990000");
        return clienteRepository.save(cliente);
    }

    private Produto criarProduto(String nome) {
        Categoria categoria = new Categoria();
        categoria.setNome("Categoria Item Teste");
        categoria.setDescricao("Categoria usada no teste de ItemPedido");
        categoria = categoriaRepository.save(categoria);

        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setDescricao("Produto do teste de ItemPedido");
        produto.setPreco(BigDecimal.valueOf(100.00));
        produto.setEstoque(20);
        produto.setCategoria(categoria);

        return produtoRepository.save(produto);
    }

    private Pedido criarPedido() {
        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.now());
        pedido.setStatus("PAGO");
        pedido.setValorTotal(BigDecimal.valueOf(200.00));
        pedido.setCliente(criarCliente());

        return pedidoRepository.save(pedido);
    }

    private ItemPedido criarItem(String nomeProduto, int quantidade) {
        ItemPedido item = new ItemPedido();
        item.setQuantidade(quantidade);
        item.setValorUnitario(BigDecimal.valueOf(100.00));
        item.setPedido(criarPedido());
        item.setProduto(criarProduto(nomeProduto));

        return repository.save(item);
    }

    @Test
    public void buscarPorId() {
        ItemPedido salvo = criarItem("Produto Item Teste", 2);

        ItemPedido encontrado = repository.findById(salvo.getId()).orElse(null);

        assertNotNull(encontrado);
        assertEquals(2, encontrado.getQuantidade());
        assertEquals(
                BigDecimal.valueOf(100.00),
                encontrado.getValorUnitario());

        assertNotNull(encontrado.getProduto());
        assertEquals(
                "Produto Item Teste",
                encontrado.getProduto().getNome());
    }

    @Test
    public void listar() {
        criarItem("Produto Lista Um", 1);
        criarItem("Produto Lista Dois", 3);

        List<ItemPedido> itens = repository.findAll();

        assertTrue(itens.size() >= 2);
        assertTrue(itens.stream().anyMatch(
                i -> "Produto Lista Um".equals(i.getProduto().getNome())));
        assertTrue(itens.stream().anyMatch(
                i -> "Produto Lista Dois".equals(i.getProduto().getNome())));
    }
}
