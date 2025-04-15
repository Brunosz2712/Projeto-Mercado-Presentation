package br.com.fiap.Projeto_Mercado_Presentation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fiap.Projeto_Mercado_Presentation.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
