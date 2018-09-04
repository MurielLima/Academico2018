/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Objects;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Muriel
 */
@Document
public class Matricula {

    @DBRef
    private Disciplina disciplina;
    private int nota1Sem;
    private int nota2Sem;
    private int notaExam;
    private int faltas;

    public Matricula() {
    }

    public Matricula(Disciplina disciplina, int nota1Sem, int nota2Sem, int notaExam, int faltas) {
        this.disciplina = disciplina;
        setNota1Sem(nota1Sem);
        setNota2Sem(nota2Sem);
        setNotaExam(notaExam);
        setFaltas(faltas);
        
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public int getNota1Sem() {
        return nota1Sem;
    }

    public void setNota1Sem(int nota1Sem) {
        if (nota1Sem >= 0 && nota1Sem <= 100) {
            this.nota1Sem = nota1Sem;
        } else {
            this.nota1Sem = 0;
        }
    }

    public int getNota2Sem() {
        return nota2Sem;
    }

    public void setNota2Sem(int nota2Sem) {
        if (nota2Sem >= 0 && nota2Sem <= 100) {
            this.nota2Sem = nota2Sem;
        } else {
            this.nota2Sem = 0;
        }
    }

    public int getNotaExam() {
        return notaExam;
    }

    public void setNotaExam(int notaExam) {
        if (notaExam >= 0 && notaExam <= 100) {
            this.notaExam = notaExam;
        } else {
            this.notaExam = 0;
        }
    }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

    public Double getPercFrequencia() {
        if (disciplina != null && disciplina.getAulas() > 0) {
            return 100 - (faltas / disciplina.getAulas() * 100.0);
        }
        return 0.0;

    }

    public Double getMedia() {
        Double media;
        media = (nota1Sem + nota2Sem) / 2.0;
        if (media >= 70) {
            return media;
        } else {
            media = (nota1Sem + nota2Sem + notaExam) / 3.0;
            return media;
        }

    }

    public String getStatus() {
        if (getPercFrequencia() < 75.0) {
            return "Reprovado por falta";
        } else if (getMedia() >= 70) {
            return "Aprovado";
        } else if (getMedia() >= 50) {
            return "Aprovado";
        } else {
            return "Reprovado";
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.disciplina);
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
        final Matricula other = (Matricula) obj;
        if (!Objects.equals(this.disciplina, other.disciplina)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Matricula{" + "disciplina=" + disciplina + ", nota1Sem=" + nota1Sem + ", nota2Sem=" + nota2Sem + ", notaExam=" + notaExam + ", faltas=" + faltas + '}';
    }

}
