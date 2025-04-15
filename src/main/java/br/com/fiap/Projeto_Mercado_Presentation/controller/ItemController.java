package br.com.fiap.Projeto_Mercado_Presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.Projeto_Mercado_Presentation.model.Item;
import br.com.fiap.Projeto_Mercado_Presentation.repository.ItemRepository;
import br.com.fiap.Projeto_Mercado_Presentation.repository.PersonagemRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/itens")
@Slf4j
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PersonagemRepository personagemRepository;

    @GetMapping
    @Cacheable("items")
    @Operation(description = "Listar todos os itens", tags = "items", summary = "Lista de itens")
    public List<Item> index() {
        log.info("Buscando todos os itens");
        return itemRepository.findAll();
    }

    @PostMapping
    @CacheEvict(value = "items", allEntries = true)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(responses = {
            @ApiResponse(responseCode = "400", description = "Falha na validação")
    })
    public Item create(@RequestBody @Valid Item item) {
        log.info("Cadastrando item " + item.getNome());

        if (item.getDono() != null && item.getDono().getId() != null) {
            var personagem = personagemRepository.findById(item.getDono().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Personagem não encontrado"));
            item.setDono(personagem);
        }

        return itemRepository.save(item);
    }

    @GetMapping("{id}")
    public Item show(@PathVariable Long id) {
        log.info("Buscando item com id " + id);
        return getItem(id);
    }

    @PutMapping("{id}")
    public Item update(@PathVariable Long id, @RequestBody @Valid Item item) {
        log.info("Atualizando item " + id);

        getItem(id); // Garante que o item existe

        if (item.getDono() != null && item.getDono().getId() != null) {
            var personagem = personagemRepository.findById(item.getDono().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Personagem não encontrado"));
            item.setDono(personagem);
        }

        item.setId(id);
        return itemRepository.save(item);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Deletando item " + id);
        itemRepository.delete(getItem(id));
    }

    private Item getItem(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado"));
    }
}
