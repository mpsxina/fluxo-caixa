package api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum TipoLancamento {

    DEBITO( "DEBITO" ),
    CREDITO( "CREDITO" );

    private String value;

    public static TipoLancamento fromValue(String value ) {
        return Arrays.stream( values() )
                .filter( v -> v.getValue().equalsIgnoreCase( value ) )
                .findFirst()
                .orElse( null );
    }

}
