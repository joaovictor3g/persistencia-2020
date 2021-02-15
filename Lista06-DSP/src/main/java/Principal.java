import DAO.GenericDAO;
import DAO.GenericJPADAO;
import Model.Funcionario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static int menu() {
        Scanner input = new Scanner(System.in);
        int escolha = 0;
        // Menu
        System.out.println("-----------------------------");
        System.out.println("1 - Adicionar um Funcionário");
        System.out.println("2 - Deletar um Funcionário");
        System.out.println("3 - Consultar um Funcionário");
        System.out.println("4 - Atualizar um Funcionário");
        System.out.println("5 - Adicionar mais de um Funcionário");
        System.out.println("6 - Exibir lista de funcionários");

        escolha = input.nextInt();

        return escolha;
    }

    public static void main(String[] args) {
        GenericDAO<Funcionario> funcionarioDAO = new GenericJPADAO<Funcionario>(Funcionario.class);
        int escolha = menu();
        int pagina = 1, tamanhoPagina = 2, paginacao = 0;

        while(true) {
            Scanner input = new Scanner(System.in);
            switch (escolha) {
                case 1: {
                    String nome, cpf, email, telefone, matricula;
                    Funcionario funcionario = new Funcionario();

                    System.out.println("Nome");
                    nome = input.nextLine();
                    funcionario.setNome(nome);
                    System.out.println("CPF");
                    cpf = input.next();
//                    while(funcionarioDAO.verificaCpfExiste(cpf)) {
//                        System.out.println("Digite um CPF válido");
//                        cpf = input.next();
//                    }
                    funcionario.setCpf(cpf);
                    System.out.println("E-mail");
                    email = input.next();
                    funcionario.setEmail(email);
                    System.out.println("Matrícula");
                    matricula = input.next();

//                    while(funcionarioDAO.verificaMatriculaExiste(matricula)) {
//                        System.out.println("Digite uma Matrícula válida");
//                        matricula = input.next();
//                    }
                    funcionario.setMatricula(matricula);
                    System.out.println("Telefone");
                    telefone = input.next();
                    funcionario.setTelefone(telefone);

                    try {
                        funcionarioDAO.beginTransaction();
                        funcionarioDAO.adicionar(funcionario);
                        funcionarioDAO.commit();
                    } catch(Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        funcionarioDAO.close();
                    }
                    escolha = menu();
                } break;


                case 2: {
                    try {

                        System.out.println("Digite o id do Funcioário a ser apagado");
                        int id = input.nextInt();

                        funcionarioDAO.beginTransaction();
                        Funcionario funcionario = funcionarioDAO.consultar(id);
                        funcionarioDAO.deletar(funcionario);
                        funcionarioDAO.commit();


                    } catch (Exception ex) {
                        ex.printStackTrace();
                        funcionarioDAO.rollback();

                    } finally {
                        funcionarioDAO.close();
                        escolha = menu();
                    }
                }
                break;

                case 3: {
                    try {

                        int id;
                        System.out.println("Digite o id do Funcioário a ser consultado");
                        id = input.nextInt();

                        funcionarioDAO.beginTransaction();
                        Funcionario funcionario = funcionarioDAO.consultar(id);
                        System.out.println(funcionario);
                        funcionarioDAO.commit();

                    } catch(Exception ex) {
                        ex.printStackTrace();
                        funcionarioDAO.rollback();
                    } finally {
                        funcionarioDAO.close();
                        escolha = menu();
                    }
                }
                break;

                case 4: {
                    try {
                        String nome, cpf, email, telefone, matricula;
                        int id;
                        System.out.println("Digite o id do Funcionário a ser atualizado");
                        id = input.nextInt();

                        Funcionario funcionario = funcionarioDAO.consultar(id);
                        System.out.println(funcionario);

                        System.out.println("Nome");
                        nome = input.next();
                        funcionario.setNome(nome);


                        System.out.println("E-mail");
                        email = input.next();
                        funcionario.setEmail(email);

                        System.out.println("Telefone");
                        telefone = input.next();
                        funcionario.setTelefone(telefone);

                        funcionarioDAO.beginTransaction();
                        funcionarioDAO.alterar(funcionario);
                        funcionarioDAO.commit();

                    } catch(Exception ex) {
                        ex.printStackTrace();
                        funcionarioDAO.rollback();
                    } finally {
                        funcionarioDAO.close();
                        escolha = menu();
                    }
                }
                break;

                case 5: {
                    try {
                        int quantFuncionarios = 0;
                        System.out.print("Quantos funcionários adicionará?");
                        quantFuncionarios = input.nextInt();

                        List<Funcionario> funcionarios = new ArrayList<>();
                        String nome, cpf, email, telefone, matricula;

                        while (quantFuncionarios > 0) {
                            Funcionario funcionario = new Funcionario();
                            System.out.println("Nome");
                            nome = input.next();
                            funcionario.setNome(nome);
                            System.out.println("CPF");
                            cpf = input.next();
//                        while(funcionarioDAO.verificaCpfExiste(cpf)) {
//                            System.out.println("Digite um CPF válido");
//                            cpf = input.next();
//                        }
                            funcionario.setCpf(cpf);
                            System.out.println("E-mail");
                            email = input.next();
                            funcionario.setEmail(email);
                            System.out.println("Matrícula");
                            matricula = input.next();
//                        while(funcionarioDAO.verificaCpfExiste(matricula)) {
//                            System.out.println("Digite uma matrícula válida");
//                            matricula = input.next();
//                        }
                            funcionario.setMatricula(matricula);
                            System.out.println("Telefone");
                            telefone = input.next();
                            funcionario.setTelefone(telefone);
                            funcionarios.add(funcionario);
                            quantFuncionarios--;
                        }
                        funcionarioDAO.beginTransaction();
                        funcionarioDAO.adicionaListaFuncionarios(funcionarios);
                        funcionarioDAO.commit();

                    } catch(Exception ex) {
                        ex.printStackTrace();
                        funcionarioDAO.rollback();
                    } finally {
                        funcionarioDAO.close();
                        escolha = menu();
                    }

                } break;

                case 6: {
                    try {

                        List<Funcionario> funcionarios = null;
                        if (paginacao == 0) {
                            funcionarios = funcionarioDAO.listaPaginada(pagina, tamanhoPagina);
                            for (Funcionario f : funcionarios) {
                                System.out.println(f);
                            }
                        }

                        System.out.print("1 - Paginar a esquerda    ");
                        System.out.println("2 - Paginar a direita");
                        System.out.println("3 - Sair");
                        paginacao = input.nextInt();
                        funcionarioDAO.beginTransaction();
                        if (paginacao == 2) {
                            funcionarios = funcionarioDAO.listaPaginada(pagina+1, tamanhoPagina);

                            if (funcionarios.size() == 0) {
                                System.out.println("Página inexistente");
                            } else {
                                for (Funcionario f : funcionarios) {
                                    System.out.println(f);
                                }
                            }
                            pagina++;
                        } else if (paginacao == 1) {
                            if (pagina - 1 <= 0) {
                                System.out.println("Pagina Inexistente");
                            } else {
                                funcionarios = funcionarioDAO.listaPaginada(pagina - 1, tamanhoPagina);

                                for (Funcionario f : funcionarios) {
                                    System.out.println(f);
                                }
                            }
                            pagina--;
                        } else  escolha = menu();

                    } catch(Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        funcionarioDAO.close();

                    }
                } break;

                default:
                    System.out.println("Valor inválido");
                    escolha = menu();
                    break;
            }
        }
    }
}
