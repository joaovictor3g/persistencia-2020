package DAO;

import Model.Dependente;
import Model.Funcionario;
import utils.JPAUtil;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.AbstractQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class DependenteJPADAO implements DependenteDAO{
    public boolean verificaCpfExiste(String cpf) {
        boolean result = false;
        EntityManager entityManager = JPAUtil.getEntityManager();
        try {
            JPAUtil.beginTransaction();
            Dependente dependente = entityManager.createQuery("SELECT d FROM Dependente d WHERE d.cpf=?1", Dependente.class)
                    .setParameter(1, cpf)
                    .getSingleResult();
            if (dependente != null)
                result = true;


        } catch (Exception ex) {
            JPAUtil.rollback();
            ex.printStackTrace();
        } finally {
            JPAUtil.closeEntityManager();
        }

        return result;
    }

    @Override
    public void adicionar(Dependente dependente) {
        if(verificaCpfExiste(dependente.getCpf())) {
            System.out.println("Dependente já existe");
            return;
        }

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
    public List<Dependente> getDependancyForInitialLetterCriteria(String initial) {
        List<Dependente> dependenteList = null;
        EntityManager entityManager = JPAUtil.getEntityManager();
        try {
            JPAUtil.beginTransaction();
            CriteriaBuilder cb=entityManager.getCriteriaBuilder();
            AbstractQuery<Dependente> cq1=cb.createQuery(Dependente.class);
            Root<Dependente> dependant=cq1.from(Dependente.class);
            cq1.where(cb.like(dependant.get("nome"), initial+"%"));
            CriteriaQuery<Dependente> select1 = ((CriteriaQuery<Dependente>) cq1).select(dependant);
            TypedQuery<Dependente> tq1 = entityManager.createQuery(select1);
            dependenteList = tq1.getResultList();
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            JPAUtil.closeEntityManager();
        }

        return dependenteList;
    }

    @Override
    public List<Dependente> getDependancyForInitialLetterJPQL(String initial) {
        List<Dependente> dependenteList= null;
        EntityManager entityManager = JPAUtil.getEntityManager();

        try {
            JPAUtil.beginTransaction();
            Query query = entityManager
                    .createQuery("SELECT d FROM Dependente d WHERE d.nome LIKE :nome");
            dependenteList = (List<Dependente>) query
                    .setParameter("nome", initial+"%")
                    .getResultList();

        } catch(Exception ex) {
            JPAUtil.rollback();
        } finally {
            JPAUtil.commit();
            JPAUtil.closeEntityManager();
        }

        return dependenteList;
    }

    @Override
    public List<Dependente> getDependancyForInitialLetterNamedQuery(String initial) {
        List<Dependente> dependenteList= null;
        EntityManager entityManager = JPAUtil.getEntityManager();

        try {
            JPAUtil.beginTransaction();
            Query query = entityManager
                    .createNamedQuery("Dependente.getDependancyForInitialLetter");
            dependenteList = (List<Dependente>) query
                    .setParameter("nome", initial+"%")
                    .getResultList();
        } catch(Exception ex) {
            JPAUtil.rollback();
        } finally {
            JPAUtil.closeEntityManager();
        }

        return dependenteList;
    }

    @Override
    public void getDependancyForInitialLetterNativeQuery(String initial) {
        List<Dependente> dependenteList= null;
        EntityManager entityManager = JPAUtil.getEntityManager();

        try {
            JPAUtil.beginTransaction();
            Query query = entityManager
                    .createNativeQuery("SELECT d.* FROM Dependente AS d WHERE nome LIKE ?");
//            dependenteList = (List<Dependente>) query
//                    .setParameter(1, initial.concat("%"))
//                    .getResultList();
            List<Object[]> dependentsObject = query
                    .setParameter(1, initial+"%")
                    .getResultList();
            for(Object[] d: dependentsObject) {
                System.out.print("Dependente={" + d[0] +", "+ d[1] + ", "+d[2]+", "+d[3]+"}\n");
            }
        } catch(Exception ex) {
            JPAUtil.rollback();
            System.out.println("Um rollback foi dado");
        } finally {
            JPAUtil.closeEntityManager();
        }
    }
}
