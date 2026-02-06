package model.entities;
import java.time.LocalDate;
import model.entities.enums.StatusEmprestimo;

public class Emprestimo {
	private Integer id;
	private Usuario usuario;
	private Livro livro;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private StatusEmprestimo status;
	
	
    public Emprestimo(Livro livro, Usuario usuario) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataInicio = LocalDate.now();
        this.dataFim = dataInicio.plusDays(7);
        this.status = StatusEmprestimo.ATIVO;
    }
	
	
    public void encerrar() {
        if (!status.podeEncerrar()) {
            throw new IllegalStateException("Empréstimo não pode ser encerrado");
        }
        this.status = StatusEmprestimo.ENCERRADO;
    }

    public void renovar() {
        if (!status.podeRenovar()) {
            throw new IllegalStateException("Empréstimo não pode ser renovado");
        }
        this.dataFim = this.dataFim.plusDays(7);
    }

    public void marcarAtraso() {
        if (status == StatusEmprestimo.ATIVO) {
            this.status = StatusEmprestimo.ATRASADO;
        }
    }
}

