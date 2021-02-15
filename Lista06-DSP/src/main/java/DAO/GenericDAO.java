package DAO;

import Model.Funcionario;

import java.util.List;

public interface GenericDAO<T> {
    public void adicionar(T entity);
    public void deletar(T entity);
    void alterar(T entity);
    T consultar(int id);
    void adicionaListaFuncionarios(List<T> entityList);
    List<T> listaPaginada(int pagina, int tamanhoPagina);
    public void beginTransaction();
    public void commit();
    public void rollback();
    public void close();

}
