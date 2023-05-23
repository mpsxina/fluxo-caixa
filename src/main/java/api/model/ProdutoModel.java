package api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoModel {

    private Long id;

    @NotBlank(message = "Campo Nome é obrigatório")
    private String nome;

    private String descricao;

}
