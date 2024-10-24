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

    public String getTipo() {
        return tipo;
    }
      
}
