package br.com.fiap.Projeto_Mercado_Presentation.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Classe é obrigatória")
    @Pattern(regexp = "^(guerreiro|mago|arqueiro)$", message = "Classe deve ser guerreiro, mago ou arqueiro")
    private String classe;

    @Min(value = 1, message = "Nível mínimo é 1")
    @Max(value = 99, message = "Nível máximo é 99")
    private int nivel;

    @NotNull(message = "Saldo de moedas é obrigatório")
    @DecimalMin(value = "0.0", inclusive = true, message = "Moedas deve ser um valor positivo")
    private BigDecimal moeda;
}
