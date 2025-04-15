package br.com.fiap.Projeto_Mercado_Presentation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fiap.Projeto_Mercado_Presentation.model.Personagem;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {
}
