import DAO.*;
import Model.Dependente;
import Model.Funcionario;

import java.util.List;

public class Principal {
    public static void main(String[] args) {
        DependenteDAO dependenteDAO = new DependenteJPADAO();
        FuncionarioDAO funcionarioDAO = new FuncionarioJPADAO();

        try {
            List<Funcionario> funcionarioList = funcionarioDAO.getAllEmployeesCriteria();
            System.out.println("Todas as informações de funcionário - CriteriaQuery");
            funcionarioList.forEach(System.out::println);

            List<Funcionario> funcionarioList1 = funcionarioDAO.getAllEmployeesJPQL();
            System.out.println("Todas as informações de funcionário - JPQL");
            funcionarioList1.forEach(System.out::println);

            List<Funcionario> funcionarioList2 = funcionarioDAO.getAllEmployeesNamedQuery();
            System.out.println("Todas as informações de funcionário - NamedQuery");
            funcionarioList2.forEach(System.out::println);

            System.out.println("Todas as informações de funcionário - NativeQuery");
            funcionarioDAO.getAllEmployeesNativeQuery();

            List<Dependente> dependenteList = dependenteDAO.getDependancyForInitialLetterCriteria("Depen");
            System.out.println("Todas as informações de dependente - CriteriaQuery");
            dependenteList.forEach(System.out::println);

            List<Dependente> dependenteList1 = dependenteDAO.getDependancyForInitialLetterJPQL("D");
            System.out.println("Todas as informações de dependente - JPQL");
            dependenteList1.forEach(System.out::println);

            List<Dependente> dependenteList2 = dependenteDAO.getDependancyForInitialLetterNamedQuery("D");
            System.out.println("Todas as informações de dependente - NamedQuery");
            dependenteList2.forEach(System.out::println);

            System.out.println("Todas as informações de dependente - NativeQuery");
            dependenteDAO.getDependancyForInitialLetterNativeQuery("D");

//        funcionarioDAO.createTransaction();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}