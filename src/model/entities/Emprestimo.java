package model.entities;
import java.time.LocalDate;
import model.entities.enums.StatusEmprestimo;

public class Emprestimo {
	private Integer id;
	private Integer id_usuario;
	private Integer id_livro;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private StatusEmprestimo status;
	
	
	public Emprestimo() {
		
	}
	
    public Emprestimo(Integer id_livro, Integer id_usuario) {
        this.id_livro = id_livro;
        this.id_usuario = id_usuario;
        this.dataInicio = LocalDate.now();
        this.dataFim = dataInicio.plusDays(7);
        this.status = StatusEmprestimo.ATIVO;
    }
	
    
	
    public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getId_usuario() {
		return id_usuario;
	}



	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}



	public Integer getId_livro() {
		return id_livro;
	}



	public void setId_livro(Integer id_livro) {
		this.id_livro = id_livro;
	}



	public LocalDate getDataInicio() {
		return dataInicio;
	}



	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}



	public LocalDate getDataFim() {
		return dataFim;
	}



	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}



	public StatusEmprestimo getStatus() {
		return status;
	}



	public void setStatus(StatusEmprestimo status) {
		this.status = status;
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

