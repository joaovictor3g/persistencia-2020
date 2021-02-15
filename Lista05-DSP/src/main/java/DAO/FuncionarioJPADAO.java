package DAO;

import Model.Funcionario;
import utils.JPAUtil;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class FuncionarioJPADAO extends GenericJPADAO<Funcionario> implements FuncionarioDAO{
    public FuncionarioJPADAO() {
        this.persistentClass = Funcionario.class;
    }

    public boolean verificaMatriculaExiste(String matricula) {
        boolean result = false;
        EntityManager entityManager = JPAUtil.getEntityManager();
        try {
            JPAUtil.beginTransaction();
            List<Funcionario> funcionarios = entityManager.createQuery("select f.matricula from Funcionario f where f.matricula=?1", Funcionario.class)
                    .setParameter(1, matricula)
                    .getResultList();
            if(funcionarios.size() != 0)
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
            if(funcionarios.size() != 0)
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

        } catch(Exception ex) {
            JPAUtil.rollback();
            ex.printStackTrace();
        } finally {
            JPAUtil.closeEntityManager();
        }
    }

    @Override
    public void deletar(int id) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        try {
            JPAUtil.beginTransaction();
            Query query = entityManager.createQuery(
                    "DELETE FROM Funcionario f WHERE f.id = ?1");
            query.setParameter(1, id).executeUpdate();

            JPAUtil.commit();
            System.out.println("Deletado");

        } catch (Exception ex) {
            JPAUtil.rollback();
            ex.printStackTrace();
        } finally {
            JPAUtil.closeEntityManager();
        }
    }

    @Override
    public void alterar(Funcionario funcionario, int id) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        try {
            JPAUtil.beginTransaction();
            Query query = entityManager.createQuery(
                    "update Funcionario set nome=?1, email=?2, telefone=?3"+
                    "where id=?4");

            query.setParameter(1, funcionario.getNome());
            query.setParameter(2, funcionario.getEmail());
            query.setParameter(3, funcionario.getTelefone());
            query.setParameter(4, id);
            query.executeUpdate();
            JPAUtil.commit();
        } catch (Exception ex) {
            JPAUtil.rollback();
            ex.printStackTrace();
        } finally {
            JPAUtil.closeEntityManager();
        }
    }

    @Override
    public Funcionario consultar(int id) {
        Funcionario funcionario = null;
        EntityManager entityManager = JPAUtil.getEntityManager();

        try {
            JPAUtil.beginTransaction();
            funcionario = entityManager.createQuery("select f from Funcionario f where f.id=?1", Funcionario.class)
                    .setParameter(1, id)
                    .getSingleResult();
            JPAUtil.commit();

        } catch (Exception ex) {
            JPAUtil.rollback();
            ex.printStackTrace();
        } finally {
            JPAUtil.closeEntityManager();
        }
        return funcionario;
    }

    @Override
    public void adicionaListaFuncionarios(List<Funcionario> funcionarioList) {
        EntityManager entityManager = JPAUtil.getEntityManager();

        try {
            JPAUtil.beginTransaction();
            for(Funcionario f: funcionarioList)
                entityManager.persist(f);
            JPAUtil.commit();
        } catch (Exception ex) {
            JPAUtil.rollback();
            ex.printStackTrace();
        }
        finally {
            JPAUtil.closeEntityManager();
        }
    }

    @Override
    public List<Funcionario> listaPaginada(int pagina, int tamanhoPagina) {
        List<Funcionario> funcionarioList = null;
        EntityManager entityManager = JPAUtil.getEntityManager();

        try {
            JPAUtil.beginTransaction();
            funcionarioList = entityManager.createQuery("select f from Funcionario f ", Funcionario.class)
                    .setFirstResult(pagina*tamanhoPagina-tamanhoPagina)
                    .setMaxResults(tamanhoPagina)
                    .getResultList();
            JPAUtil.commit();
        } catch(Exception ex) {
            JPAUtil.rollback();
            ex.printStackTrace();
        } finally {
            JPAUtil.closeEntityManager();
        }

        return funcionarioList;
    }
}
