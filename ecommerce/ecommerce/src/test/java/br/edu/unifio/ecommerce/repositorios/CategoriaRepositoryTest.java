package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import br.edu.unifio.ecommerce.entidades.Categoria;

@DataJpaTest
public class CategoriaRepositoryTest {

    @Autowired
    private CategoriaRepository repository;

    @Test
    public void buscarPorId() {
        Categoria categoria = new Categoria();
        categoria.setNome("Eletrônicos Teste");
        categoria.setDescricao("Categoria criada para teste");

        Categoria salvo = repository.save(categoria);

        Categoria encontrado = repository.findById(salvo.getId()).orElse(null);

        assertNotNull(encontrado);
        assertEquals("Eletrônicos Teste", encontrado.getNome());
        assertEquals("Categoria criada para teste", encontrado.getDescricao());
    }

    @Test
    public void listar() {
        Categoria categoria1 = new Categoria();
        categoria1.setNome("Livros Teste");
        categoria1.setDescricao("Livros para teste");
        repository.save(categoria1);

        Categoria categoria2 = new Categoria();
        categoria2.setNome("Escritório Teste");
        categoria2.setDescricao("Materiais para teste");
        repository.save(categoria2);

        List<Categoria> categorias = repository.findAll();

        assertTrue(categorias.size() >= 2);
        assertTrue(categorias.stream().anyMatch(c -> "Livros Teste".equals(c.getNome())));
        assertTrue(categorias.stream().anyMatch(c -> "Escritório Teste".equals(c.getNome())));
    }
}
