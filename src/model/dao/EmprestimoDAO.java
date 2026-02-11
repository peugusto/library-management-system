package model.dao;

import java.util.List;

import model.entities.Emprestimo;

public interface EmprestimoDAO {
    void salvar(Emprestimo emprestimo);
    Emprestimo buscarPorId(Integer id);
    void atualizar(Emprestimo emprestimo);
    void cancelarEmprestimo(Emprestimo emprestimo);
    Boolean existeHistorico(Integer id);
    
}
