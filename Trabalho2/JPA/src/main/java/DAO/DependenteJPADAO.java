package DAO;

import Model.Dependente;
import utils.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class DependenteJPADAO implements DependenteDAO{


    @Override
    public void adicionar(Dependente dependente) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        try {
            JPAUtil.beginTransaction();
            entityManager.persist(dependente);
            JPAUtil.commit();
            System.out.println("Adicionado");

        } catch (Exception ex) {
            JPAUtil.rollback();
            ex.printStackTrace();
        } finally {
            JPAUtil.closeEntityManager();
        }
    }

    @Override
    public List<Dependente> getDependancyForInitialLetter(String initial) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        List<Dependente> dependenteList = null;
        Query query = entityManager
                .createQuery("SELECT d FROM Dependente d WHERE d.nome LIKE :nome");
        dependenteList = (List<Dependente>) query
                .setParameter("nome", initial+"%")
                .getResultList();
        return dependenteList;
    }
}
