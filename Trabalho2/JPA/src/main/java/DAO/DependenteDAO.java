package DAO;

import Model.Dependente;

import java.util.List;


public interface DependenteDAO {
    public boolean verificaCpfExiste(String matricula);

    void adicionar(Dependente dependente);
    public List<Dependente> getDependancyForInitialLetterCriteria(String initial);
    public List<Dependente> getDependancyForInitialLetterJPQL(String initial);
    public List<Dependente> getDependancyForInitialLetterNamedQuery(String initial);
    public void getDependancyForInitialLetterNativeQuery(String initial);
}
