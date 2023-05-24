package api.model;

import api.enums.TipoLancamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LancamentoModel {

    private Long id;

    @NotBlank(message = "Campo ID Produto é obrigatório")
    private Long idProduto;

    @NotBlank(message = "Campo Tipo Lançamento é obrigatório")
    private TipoLancamento tipoLancamento;

    @NotBlank(message = "Campo Valor é obrigatório")
    private BigDecimal valor;

    @NotBlank(message = "Campo Data Lançamento é obrigatório")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataLancamento;

    @CreationTimestamp
    @Temporal( TemporalType.TIMESTAMP )
    private Date createdAt;

    @UpdateTimestamp
    @Temporal( TemporalType.TIMESTAMP )
    private Date updatedAt;

}