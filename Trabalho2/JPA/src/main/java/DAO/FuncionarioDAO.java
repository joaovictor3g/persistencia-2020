package DAO;

import Model.Funcionario;

import java.util.List;

public interface FuncionarioDAO {
    public boolean verificaMatriculaExiste(String matricula);

    public boolean verificaCpfExiste(String matricula);

    void adicionar(Funcionario funcionario);

    List<Funcionario> getAllEmployeesCriteria();

    List<Funcionario> getAllEmployeesJPQL();

    List<Funcionario> getAllEmployeesNamedQuery();

    void getAllEmployeesNativeQuery();

    void createTransaction();

}