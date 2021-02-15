package DAO;

import Model.Funcionario;

import java.util.List;

public interface GenericDAO<T> {
    public void adicionar(T entity);
    public void deletar(T entity);
    void alterar(T entity);
    T consultar(Object id);
    void adicionaListaFuncionarios(List<T> entityList);
    List<T> listaPaginada(int pagina, int tamanhoPagina);

}
