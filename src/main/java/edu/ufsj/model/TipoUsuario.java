package edu.ufsj.model;

/**
 *
 * @author arthurd
 */
public enum TipoUsuario {
    ADMIN("Admin"),
    MEDICO("Medico"),
    ATENDENTE("Atendente");
    
    private final String tipo;

    TipoUsuario(String tipo) {
        this.tipo = tipo;
    }
    
    public static TipoUsuario getFromTipo(String tipo) {
        for (TipoUsuario tipoUsuario : values()) {
            if (tipoUsuario.tipo.equals(tipo)) {
                return tipoUsuario;
            }
        }
        return null;
    }

    public String getTipo() {
        return tipo;
    }
      
}
