package br.edu.unifio.ecommerce.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unifio.ecommerce.entidades.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Short> {

}
