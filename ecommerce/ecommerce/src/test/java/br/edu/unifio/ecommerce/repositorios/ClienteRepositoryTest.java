package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import br.edu.unifio.ecommerce.entidades.Cliente;

@DataJpaTest
public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository repository;

    @Test
    public void buscarPorId() {
        Cliente cliente = new Cliente();
        cliente.setNome("Cliente Teste");
        cliente.setEmail("cliente.teste@email.com");
        cliente.setTelefone("14999990000");

        Cliente salvo = repository.save(cliente);

        Cliente encontrado = repository.findById(salvo.getId()).orElse(null);

        assertNotNull(encontrado);
        assertEquals("Cliente Teste", encontrado.getNome());
        assertEquals("cliente.teste@email.com", encontrado.getEmail());
    }

    @Test
    public void listar() {
        Cliente cliente1 = new Cliente();
        cliente1.setNome("Cliente Um");
        cliente1.setEmail("cliente1@email.com");
        cliente1.setTelefone("14999990001");
        repository.save(cliente1);

        Cliente cliente2 = new Cliente();
        cliente2.setNome("Cliente Dois");
        cliente2.setEmail("cliente2@email.com");
        cliente2.setTelefone("14999990002");
        repository.save(cliente2);

        List<Cliente> clientes = repository.findAll();

        assertTrue(clientes.size() >= 2);
        assertTrue(clientes.stream().anyMatch(c -> "cliente1@email.com".equals(c.getEmail())));
        assertTrue(clientes.stream().anyMatch(c -> "cliente2@email.com".equals(c.getEmail())));
    }
}
