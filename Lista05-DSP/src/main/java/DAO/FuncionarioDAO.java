package DAO;

import Model.Funcionario;

import java.util.List;

public interface FuncionarioDAO extends GenericDAO<Funcionario> {

    boolean verificaMatriculaExiste(String matricula);

    public boolean verificaCpfExiste(String matricula);

    void adicionar(Funcionario funcionario);

    void deletar(int id);

    void alterar(Funcionario funcionario, int id);

    Funcionario consultar(int id);

    void adicionaListaFuncionarios(List<Funcionario> funcionarioList);

    List<Funcionario> listaPaginada(int pagina, int tamanhoPagina);
}
