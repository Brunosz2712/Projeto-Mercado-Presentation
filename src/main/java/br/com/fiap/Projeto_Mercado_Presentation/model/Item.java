package br.com.fiap.Projeto_Mercado_Presentation.model;

import java.math.BigDecimal;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Tipo é obrigatório")
    @Pattern(regexp = "^(arma|armadura|poção|acessório)$", message = "Tipo deve ser arma, armadura, poção ou acessório")
    private String tipo;

    @NotBlank(message = "Raridade é obrigatória")
    @Pattern(regexp = "^(comum|raro|épico|lendário)$", message = "Raridade deve ser comum, raro, épico ou lendário")
    private String raridade;

    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value = "0.0", inclusive = true, message = "Preço deve ser um valor positivo")
    private BigDecimal preco;

    @ManyToOne
    @JoinColumn(name = "personagem_id")
    private Personagem dono;
}
