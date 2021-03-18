package DAO;

import Model.Dependente;
import Model.Funcionario;
import com.sun.xml.fastinfoset.sax.SystemIdResolver;
import utils.JPAUtil;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.AbstractQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioJPADAO implements FuncionarioDAO {
    public boolean verificaMatriculaExiste(String matricula) {
        boolean result = false;
        EntityManager entityManager = JPAUtil.getEntityManager();
        try {
            JPAUtil.beginTransaction();
            Funcionario funcionario = entityManager.createQuery("SELECT f FROM Funcionario f WHERE f.matricula=?1", Funcionario.class)
                    .setParameter(1, matricula)
                    .getSingleResult();
            System.out.println(funcionario);
            if (funcionario != null)
                result = true;

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
            Funcionario funcionarios = entityManager.createQuery("SELECT f FROM Funcionario f WHERE f.cpf=?1", Funcionario.class)
                    .setParameter(1, cpf)
                    .getSingleResult();
            if (funcionarios != null)
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
    public void adicionar(Funcionario funcionario) {

        if(verificaCpfExiste(funcionario.getCpf()) || verificaMatriculaExiste(funcionario.getMatricula())) {
            System.out.println("Funcionário já existe, impossível adicioná-lo, assim como seus dependentes");
            return;
        }

        EntityManager entityManager = JPAUtil.getEntityManager();
        try {
            JPAUtil.beginTransaction();
            entityManager.persist(funcionario);
            JPAUtil.commit();
            System.out.println("Adicionado");

        } catch (Exception ex) {
            JPAUtil.rollback();
            System.out.println("Um rollback foi dado");
            throw ex;
        } finally {
            JPAUtil.closeEntityManager();
        }
    }

    @Override
    public List<Funcionario> getAllEmployeesCriteria() {
        EntityManager entityManager = JPAUtil.getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Funcionario> cq = cb.createQuery(Funcionario.class);
        Root<Funcionario> rootEntry = cq.from(Funcionario.class);
        CriteriaQuery<Funcionario> all = cq.select(rootEntry);
        TypedQuery<Funcionario> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();


    }

    @Override
    public List<Funcionario> getAllEmployeesJPQL() {
        EntityManager entityManager = JPAUtil.getEntityManager();
        List<Funcionario> funcionarios = null;
        try {
            JPAUtil.beginTransaction();
            Query query = entityManager.createQuery("SELECT f FROM Funcionario f");

            funcionarios = (List<Funcionario>) query.getResultList();
        } catch (Exception ex) {
            JPAUtil.rollback();
        } finally {
            JPAUtil.closeEntityManager();
        }

        return funcionarios;
    }

    @Override
    public List<Funcionario> getAllEmployeesNamedQuery() {
        EntityManager entityManager = JPAUtil.getEntityManager();
        List<Funcionario> funcionarios = null;

        try {
            JPAUtil.beginTransaction();
            funcionarios = entityManager
                    .createNamedQuery("Funcionario.getAllEmployees", Funcionario.class)
                    .getResultList();

        } catch (Exception ex) {
            JPAUtil.rollback();
        } finally {
            JPAUtil.closeEntityManager();
        }

        return funcionarios;
    }

    @Override
    public void getAllEmployeesNativeQuery() {
        List<Funcionario> funcionarios = null;
        EntityManager entityManager = JPAUtil.getEntityManager();

        try {
            JPAUtil.beginTransaction();
            List<Object[]> employeesObject = entityManager.createNativeQuery("SELECT f.* FROM funcionario f")
                    .getResultList();

            for(Object[] e: employeesObject)
                System.out.print("Funcinario={" + e[0] +", "+ e[1] + ", "+e[2]+", "+e[3]+ ", "+e[4] +", "+e[5]+"}\n");

        }catch (Exception ex) {
            JPAUtil.rollback();
            ex.printStackTrace();
        } finally {
            JPAUtil.closeEntityManager();
        }
    }

    @Override
    public void createTransaction() {
        // 1° Transação
        Funcionario funcionario = new Funcionario("878791", "2332", "Funcionario 1", "joao@gmail.com", "09980979");
        List<Dependente> dependentes = new ArrayList<>();
        Dependente dependente1 = new Dependente("65522", "Dependente 1", funcionario);
        Dependente dependente2 = new Dependente("2432", "Dependente 2", funcionario);
        dependentes.add(dependente1);
        dependentes.add(dependente2);

        funcionario.setDependenteList(dependentes);
        this.adicionar(funcionario);

        // 2° Transação
        Funcionario funcionario1 = new Funcionario("87879143", "2232332", "Funcionario 1", "joao@gmail.com", "09980979");
        List<Dependente> dependentes1 = new ArrayList<>();
        Dependente dependente3 = new Dependente("65532422", "Dependente 3", funcionario1);
        Dependente dependente4 = new Dependente("243432", "Dependente 4", funcionario1);
        dependentes1.add(dependente3);
        dependentes1.add(dependente4);

        funcionario1.setDependenteList(dependentes1);
        this.adicionar(funcionario1);

        // 3° Transação: ERRO, criar um dependente sem funcionário vinculado deve resultar em um rollback
        Funcionario funcionario2 = new Funcionario();
        Dependente dependente5 = new Dependente("6553242222", "Dependente 5", funcionario2);
        Dependente dependente6 = new Dependente("243432342", "Dependente 6", funcionario2);
        List<Dependente> dependenteList = new ArrayList<>();
        dependenteList.add(dependente5);
        dependenteList.add(dependente6);

        funcionario.setDependenteList(dependenteList);
        this.adicionar(funcionario2);

    }
}