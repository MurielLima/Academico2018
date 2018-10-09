package model;

import static config.Config.df;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Professor {

    @Id
    private String id;

    private String nome;
    private String email;

    @Indexed(unique = true)
    private String cpf;

    @DBRef
    private Cidade cidade;
    @DBRef
    private Departamento departamento;
    private LocalDate dataCadastro;
    private boolean ativo;

    public Professor() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Professor(String nome, String email, String cpf, Cidade cidade, Departamento departamento, boolean ativo) {
        setNome( nome);
        setEmail(email);
        setCpf(cpf);
        setCidade(cidade);
        setDepartamento(departamento);
        setAtivo(ativo);
    }

    public Professor(String nome, String email, String cpf, Cidade cidade, Departamento departamento) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.cidade = cidade;
        this.departamento = departamento;
        setDataCadastro(LocalDate.now());
    }

    public Professor(String nome, String email, String cpf, Cidade cidade,String id) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.cidade = cidade;
        setDataCadastro(LocalDate.now());
        setId(id);
    }

    /**
     * EXERCICIO 1 DA PROVA
     *
     * @return
     */
    public String getDataCadastroFormat() {
        if (dataCadastro != null) {
            return dataCadastro.format(df);
        } else {
            return "";
        }
    }

    /**
     * EXERCICIO 1 DA PROVA
     *
     * @return
     */
    public LocalDate getDataCadastro() {
        if (dataCadastro != null) {
            return dataCadastro;
        } else {
            return null;
        }
    }

    /**
     * EXERCICIO 1 DA PROVA
     *
     * @return
     */
    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    /**
     * EXERCICIO 2 DA PROVA
     *
     * @return
     */
    public boolean isAtivo() {
       if(ativo==false){
           return false;
       }else{
           return true;
       }
    }
        /**
         * EXERCICIO 2 DA PROVA
         *
         * @return
         */
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Departamento getDepartamento() {
        if (departamento != null) {
            return departamento;
        }
        return null;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Professor other = (Professor) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome;
    }

}
