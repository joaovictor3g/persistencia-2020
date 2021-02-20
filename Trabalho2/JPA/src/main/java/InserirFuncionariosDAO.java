import DAO.*;
import Model.Dependente;
import Model.Funcionario;
import java.util.ArrayList;
import java.util.List;

// 4.Crie uma classe para adicionar via DAO pelo menos 6 funcionários
// com seus dependentes no banco de dados. Pelo menos 50% dos funcionários devem ter dependentes.

public class InserirFuncionariosDAO {
    public static void main(String[] args) {
        FuncionarioDAO funcionarioDao = new FuncionarioJPADAO();

        Funcionario funcionario1 =
                new Funcionario("9879879", "79878", "J.Victor", "joao@gmail.com", "8676");

        Funcionario funcionario2 =
                new Funcionario("76", "123", "J.Victor", "joao@gmail.com", "8676");

        Funcionario funcionario3 =
                new Funcionario("09897", "76576", "J.Victor", "joao@gmail.com", "8676");

        Funcionario funcionario4 =
                new Funcionario("0-8078", "432", "J.Victor", "joao@gmail.com", "8676");

        Funcionario funcionario5 =
                new Funcionario("09897", "09088", "J.Victor", "joao@gmail.com", "8676");

        Funcionario funcionario6 =
                new Funcionario("8767645", "796443", "J.Victor", "joao@gmail.com", "8676");

        Dependente dependente1 = new Dependente();
        dependente1.setCpf("9768698");
        dependente1.setNome("Victor");
        dependente1.setFuncionario(funcionario1);

        Dependente dependente2 = new Dependente();
        dependente2.setCpf("765678");
        dependente2.setNome("K.Victor");
        dependente2.setFuncionario(funcionario1);

        List<Dependente> dependenteList = new ArrayList<>();
        dependenteList.add(dependente1);
        dependenteList.add(dependente2);

        funcionario1.setDependenteList(dependenteList);
        funcionarioDao.adicionar(funcionario1);

        Dependente dependente3 = new Dependente();
        dependente3.setCpf("9768698");
        dependente3.setNome("Victor");
        dependente3.setFuncionario(funcionario2);

        Dependente dependente4 = new Dependente();
        dependente4.setCpf("765678");
        dependente4.setNome("K.Victor");
        dependente4.setFuncionario(funcionario2);

        List<Dependente> dependenteList1 = new ArrayList<>();
        dependenteList1.add(dependente3);
        dependenteList1.add(dependente4);

        funcionario2.setDependenteList(dependenteList1);
        funcionarioDao.adicionar(funcionario2);

        Dependente dependente5 = new Dependente();
        dependente5.setCpf("9768698");
        dependente5.setNome("Victor");
        dependente5.setFuncionario(funcionario3);

        Dependente dependente6 = new Dependente();
        dependente6.setCpf("765678");
        dependente6.setNome("K.Victor");
        dependente6.setFuncionario(funcionario3);

        List<Dependente> dependenteList2 = new ArrayList<>();
        dependenteList2.add(dependente5);
        dependenteList2.add(dependente6);

        funcionario3.setDependenteList(dependenteList2);
        funcionarioDao.adicionar(funcionario3);

        funcionarioDao.adicionar(funcionario4);
        funcionarioDao.adicionar(funcionario5);

        Dependente dependente7 = new Dependente();
        dependente7.setNome("Dias");
        dependente7.setCpf("87867567");
        dependente7.setFuncionario(funcionario6);

        List<Dependente> dependenteList3 = new ArrayList<>();
        dependenteList3.add(dependente7);
        funcionario6.setDependenteList(dependenteList3);
        funcionarioDao.adicionar(funcionario6);
    }
}