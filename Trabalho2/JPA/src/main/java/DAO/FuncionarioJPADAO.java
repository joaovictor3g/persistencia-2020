package DAO;

import Model.Funcionario;
import utils.JPAUtil;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class FuncionarioJPADAO implements FuncionarioDAO {
    public boolean verificaMatriculaExiste(String matricula) {
        boolean result = false;
        EntityManager entityManager = JPAUtil.getEntityManager();
        try {
            JPAUtil.beginTransaction();
            List<Funcionario> funcionarios = entityManager.createQuery("select f.matricula from Funcionario f where f.matricula=?1", Funcionario.class)
                    .setParameter(1, matricula)
                    .getResultList();
            if (funcionarios.size() != 0)
                result = true;
            JPAUtil.commit();
        } catch (Exception ex) {
            JPAUtil.rollback();
            ex.printStackTrace();
        } finally {
            JPAUtil.closeEntityManager();
        }

        return result;
    }

    public boolean verificaCpfExiste(String cpf) {
        boolean result = false;
        EntityManager entityManager = JPAUtil.getEntityManager();
        try {
            JPAUtil.beginTransaction();
            List<Funcionario> funcionarios = entityManager.createQuery("select f.matricula from Funcionario f where f.cpf=?1", Funcionario.class)
                    .setParameter(1, cpf)
                    .getResultList();
            if (funcionarios.size() != 0)
                result = true;
            JPAUtil.commit();

        } catch (Exception ex) {
            JPAUtil.rollback();
            ex.printStackTrace();
        } finally {
            JPAUtil.closeEntityManager();
        }

        return result;
    }

    @Override
    public void adicionar(Funcionario funcionario) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        try {
            JPAUtil.beginTransaction();
            entityManager.persist(funcionario);
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
    public List<Funcionario> getAllEmployees() {
        EntityManager entityManager = JPAUtil.getEntityManager();
        List<Funcionario> funcionarios=null;
        Query query = entityManager.createQuery("SELECT f FROM Funcionario f");

        funcionarios = (List<Funcionario>) query.getResultList();
        return funcionarios;
    }
}