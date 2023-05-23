package api.model.errors;

import org.springframework.http.HttpStatus;

public class LancamentoErrors {

    public static final ErrorModel NOT_FOUND = new ErrorModel(HttpStatus.NOT_FOUND.value(), "404001", "Nenhum lançamento encontrado.");
    public static final ErrorModel ERROR_CREATING = new ErrorModel(HttpStatus.BAD_REQUEST.value(), "400001", "Erro ao criar novo lançamento. Tente novamente.");

}