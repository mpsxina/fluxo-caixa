package api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaldoModel {

    private LocalDate data;

    private BigDecimal saldoCreditos;

    private BigDecimal saldoDebitos;

    private BigDecimal saldoTotal;


}
