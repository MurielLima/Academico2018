package model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Disciplina {

    @Id
    private String id;

    @DBRef
    private Professor professor;
    @Indexed(unique = true)
    private String codigo;

    private String nome;
    private int aulas;
    private String observacao;
    private boolean semestral;
    @DBRef
    private Turno turno;
    private int maxAlunos;

    public Disciplina() {
    }

    public Disciplina(Professor professor, String codigo, String nome, int aulas, String observacao, boolean semestral) {
        this.professor = professor;
        this.codigo = codigo;
        this.nome = nome;
        this.aulas = aulas;
        this.observacao = observacao;
        setSemestral(semestral);
    }

    public Disciplina(String codigo, String nome, int aulas, String observacao) {
        this.codigo = codigo;
        this.nome = nome;
        this.aulas = aulas;
        this.observacao = observacao;
        setSemestral(semestral);
    }

    public Disciplina(Professor professor, String codigo, String nome, int aulas, String observacao, boolean semestral, Turno turno) {
        this.professor = professor;
        this.codigo = codigo;
        this.nome = nome;
        this.aulas = aulas;
        this.observacao = observacao;
        this.semestral = semestral;
        this.turno = turno;
    }

    public int getMaxAlunos() {
       return maxAlunos;
        
    }

    public void setMaxAlunos(int maxAlunos) {
        if (maxAlunos > 0) {
            this.maxAlunos = maxAlunos;
        } else {
            this.maxAlunos = 0;
        }
    }

    public boolean isSemestral() {

        if (semestral) {
            return semestral;
        } else {
            return false;
        }
    }

    public void setSemestral(boolean semestral) {
        this.semestral = semestral;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String getPrimeiroNome() {
        if (professor != null) {
            String[] array = professor.getNome().split(" ");
            return array[0];
        } else {
            return "";
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAulas() {
        return aulas;
    }

    public void setAulas(int aulas) {
        this.aulas = aulas;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Disciplina other = (Disciplina) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

}
