package Model;
import javax.persistence.*;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name="Funcionario.getAllEmployees", query="SELECT f FROM Funcionario f")
})
@Table(name="funcionario")
public class Funcionario {
    @Id
    @Column(name="id_funcionario")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String cpf;
    @Column(nullable = false)
    private String matricula;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String telefone;

    public Funcionario() {}

    public Funcionario(String cpf, String matricula, String nome, String email, String telefone) {
        this.cpf = cpf;
        this.matricula = matricula;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    @OneToMany(cascade=CascadeType.ALL, mappedBy="funcionario")
    List<Dependente> dependenteList;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Dependente> getDependenteList() {
        return dependenteList;
    }

    public void setDependenteList(List<Dependente> dependenteList) {
        this.dependenteList = dependenteList;
    }

    public List<Dependente> dependentes() {
        return dependenteList;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "id='"+id+'\''+
                "cpf='" + cpf + '\'' +
                ", matricula='" + matricula + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}