package model.entities.enums;

public enum StatusUsuario {
    ATIVO {
        public boolean podeEncerrar() { return true; }
        public boolean podeRenovar() { return true; }
    },
    INATIVO {
        public boolean podeEncerrar() { return false; }
        public boolean podeRenovar() { return false; }
    };

    public abstract boolean podeEncerrar();
    public abstract boolean podeRenovar();
}
