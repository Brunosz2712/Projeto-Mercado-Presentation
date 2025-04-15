package br.com.fiap.Projeto_Mercado_Presentation.config;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.Projeto_Mercado_Presentation.model.Item;
import br.com.fiap.Projeto_Mercado_Presentation.model.Personagem;
import br.com.fiap.Projeto_Mercado_Presentation.repository.ItemRepository;
import br.com.fiap.Projeto_Mercado_Presentation.repository.PersonagemRepository;
import jakarta.annotation.PostConstruct;

@Component
public class ItemSeeder {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PersonagemRepository personagemRepository;

    @PostConstruct
    public void init() {
        if (itemRepository.count() == 0) {
            var nomes = List.of(
                    "Espada Flamejante", "Armadura de Aço", "Poção de Cura", "Anel do Poder",
                    "Cajado de Gelo", "Elmo do Trovão", "Botas de Velocidade", "Poção de Mana",
                    "Adaga Sombria", "Amuleto da Sorte"
            );

            var tipos = List.of("arma", "armadura", "poção", "acessório");
            var raridades = List.of("comum", "raro", "épico", "lendário");
            var personagens = personagemRepository.findAll();

            if (personagens.isEmpty()) return;

            Random random = new Random();

            for (int i = 0; i < 20; i++) {
                Item item = Item.builder()
                        .nome(nomes.get(random.nextInt(nomes.size())))
                        .tipo(tipos.get(random.nextInt(tipos.size())))
                        .raridade(raridades.get(random.nextInt(raridades.size())))
                        .preco(BigDecimal.valueOf(10 + (500 * random.nextDouble())))
                        .dono(personagens.get(random.nextInt(personagens.size())))
                        .build();

                itemRepository.save(item);
            }
        }
    }
}
