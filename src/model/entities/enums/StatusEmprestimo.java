package model.entities.enums;

public enum StatusEmprestimo {
    ATIVO {
        public boolean podeEncerrar() { return true; }
        public boolean podeRenovar() { return true; }
    },
    ENCERRADO {
        public boolean podeEncerrar() { return false; }
        public boolean podeRenovar() { return false; }
    },
    ATRASADO {
        public boolean podeEncerrar() { return true; }
        public boolean podeRenovar() { return false; }
    };

    public abstract boolean podeEncerrar();
    public abstract boolean podeRenovar();
}
