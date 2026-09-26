package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import br.edu.unifio.ecommerce.entidades.Categoria;
import br.edu.unifio.ecommerce.entidades.Produto;

@DataJpaTest
public class ProdutoRepositoryTest {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    private Categoria criarCategoria() {
        Categoria categoria = new Categoria();
        categoria.setNome("Categoria Produto Teste");
        categoria.setDescricao("Categoria usada nos testes de Produto");
        return categoriaRepository.save(categoria);
    }

    private Produto criarProduto(String nome, BigDecimal preco) {
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setDescricao("Produto criado para teste");
        produto.setPreco(preco);
        produto.setEstoque(10);
        produto.setCategoria(criarCategoria());
        return repository.save(produto);
    }

    @Test
    public void buscarPorId() {
        Produto salvo = criarProduto("Notebook Teste", BigDecimal.valueOf(3500.00));

        Produto encontrado = repository.findById(salvo.getId()).orElse(null);

        assertNotNull(encontrado);
        assertEquals("Notebook Teste", encontrado.getNome());
        assertEquals(BigDecimal.valueOf(3500.00), encontrado.getPreco());
        assertNotNull(encontrado.getCategoria());
        assertEquals("Categoria Produto Teste", encontrado.getCategoria().getNome());
    }

    @Test
    public void listar() {
        criarProduto("Mouse Teste", BigDecimal.valueOf(120.00));
        criarProduto("Teclado Teste", BigDecimal.valueOf(220.00));

        List<Produto> produtos = repository.findAll();

        assertTrue(produtos.size() >= 2);
        assertTrue(produtos.stream().anyMatch(p -> "Mouse Teste".equals(p.getNome())));
        assertTrue(produtos.stream().anyMatch(p -> "Teclado Teste".equals(p.getNome())));
    }
}
