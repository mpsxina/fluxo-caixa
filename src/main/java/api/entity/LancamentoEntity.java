package api.entity;

import api.enums.TipoLancamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "lancamento")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LancamentoEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id", unique = true, nullable = false )
    private Long id;

    @NotBlank(message = "Tipo do lançamento é obrigatório")
    @Column(name = "tipo")
    private TipoLancamento tipoLancamento;

    @NotBlank(message = "Nome do produto é obrigatória")
    @Column(name = "produto")
    private String produto;

    @NotNull(message = "Valor do lançamento é obrigatório")
    @DecimalMin(value = "0.01", message = "Valor do lançamento deve ser maior que zero")
    @Column(name = "valor")
    private BigDecimal valor;

    @NotNull(message = "Data do lançamento é obrigatória")
    @Column(name = "data")
    private LocalDate data;

    @CreationTimestamp
    @Temporal( TemporalType.TIMESTAMP )
    private Date createdAt;

    @UpdateTimestamp
    @Temporal( TemporalType.TIMESTAMP )
    private Date updatedAt;

}