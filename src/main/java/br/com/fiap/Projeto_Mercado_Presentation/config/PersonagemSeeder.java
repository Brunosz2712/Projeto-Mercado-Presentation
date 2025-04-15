package br.com.fiap.Projeto_Mercado_Presentation.config;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.Projeto_Mercado_Presentation.model.Personagem;
import br.com.fiap.Projeto_Mercado_Presentation.repository.PersonagemRepository;
import jakarta.annotation.PostConstruct;

@Component
public class PersonagemSeeder {

    @Autowired
    private PersonagemRepository repository;

    @PostConstruct
    public void init() {
        if (repository.count() == 0) {
            var nomes = List.of("Arthas", "Jaina", "Legolas", "Gimli", "Merlin", "Diana", "Lancelot");
            var classes = List.of("guerreiro", "mago", "arqueiro");

            for (int i = 0; i < 10; i++) {
                Personagem personagem = Personagem.builder()
                        .name(nomes.get(new Random().nextInt(nomes.size())))
                        .classe(classes.get(new Random().nextInt(classes.size())))
                        .nivel(new Random().nextInt(99) + 1)
                        .coins(BigDecimal.valueOf(new Random().nextDouble() * 1000))
                        .build();

                repository.save(personagem);
            }
        }
    }
}
