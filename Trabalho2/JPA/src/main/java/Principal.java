import DAO.*;
import Model.Dependente;
import Model.Funcionario;

import java.util.List;

public class Principal {
    public static void main(String[] args) {
        DependenteDAO dependenteDAO = new DependenteJPADAO();
        FuncionarioDAO funcionarioDAO = new FuncionarioJPADAO();

        try {
//            List<Dependente> dependenteList = dependenteDAO.getDependancyForInitialLetter("D");
//            dependenteList.forEach(System.out::println);

            List<Funcionario> funcionarioList = funcionarioDAO.getAllEmployees();
            funcionarioList.forEach(System.out::println);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}