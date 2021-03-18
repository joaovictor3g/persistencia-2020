package Model;

import javax.persistence.*;

@Entity
@NamedQuery(name="Dependente.getDependancyForInitialLetter",
        query="SELECT d FROM Dependente d WHERE d.nome LIKE :nome")
@Table(name="dependente")
public class Dependente {
    @Id
    @Column(name="id_dependente")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String cpf;
    private String nome;

    @ManyToOne
    private Funcionario funcionario;

    public Dependente(String cpf, String nome, Funcionario funcionario) {
        this.cpf = cpf;
        this.nome = nome;
        this.funcionario = funcionario;
    }

    public Dependente() {
        super();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    @Override
    public String toString() {
        return "Dependente{" +
                "id=" + id +
                ", cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", funcionario=" + funcionario.getNome() +
                '}';
    }
}
