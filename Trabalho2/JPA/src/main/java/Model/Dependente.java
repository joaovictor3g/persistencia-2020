package Model;

import javax.persistence.*;

@Entity
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
