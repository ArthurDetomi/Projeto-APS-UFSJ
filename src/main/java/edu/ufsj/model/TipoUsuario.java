package edu.ufsj.model;

/**
 *
 * @author arthurd
 */
public enum TipoUsuario {
    ADMIN("ADMIN"),
    MEDICO("Medico"),
    ATENDENTE("Atendente");
    
    private final String tipo;

    TipoUsuario(String tipo) {
        this.tipo = tipo;
    }
    
    public static TipoUsuario getFromTipo(String tipo) {
        for (TipoUsuario tipoUsuario : values()) {
            if (tipoUsuario.tipo.equalsIgnoreCase(tipo)) {
                return tipoUsuario;
            }
        }
        return null;
    }

    public String getTipo() {
        return tipo;
    }
      
}
