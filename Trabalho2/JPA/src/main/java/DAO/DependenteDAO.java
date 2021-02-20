package DAO;

import Model.Dependente;

import java.util.List;


public interface DependenteDAO {
//    public boolean verificaCpfExiste(String matricula);

    void adicionar(Dependente dependente);
    public List<Dependente> getDependancyForInitialLetter(String initial);
}
