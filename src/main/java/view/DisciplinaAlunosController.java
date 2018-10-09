package view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static config.DAO.alunoRepository;
import static config.DAO.disciplinaRepository;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import model.Aluno;
import model.Disciplina;
import model.Matricula;

/**
 * FXML Controller class
 *
 * @author Muriel
 */
public class DisciplinaAlunosController implements Initializable {

    /**
     * Initializes the controller class.
     */
        /**
     * Initializes the controller class.
     */
    private DisciplinaController controllerPai;
    @FXML
    public TableView<Aluno> tblViewAlunos;
    @FXML
    private Label lblNomeDisciplina;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setCadastroController(DisciplinaController controllerPai) {
        this.controllerPai = controllerPai;
        List<Aluno> lstTemp = new ArrayList<Aluno>();
        List<Aluno> lstAlu = new ArrayList<Aluno>();
        List<Matricula> lstMat = new ArrayList<Matricula>();
        
        lstAlu=alunoRepository.findAll();
        
        for(Aluno a:lstAlu){
            
            lstMat=a.getMatriculas();
            
            for(Matricula m: lstMat){
                
                if(m.getDisciplina().equals(disciplinaRepository.findByNome(controllerPai.disciplina.getNome()))){
                    lstTemp.add(a);
                }
                
                
            }                
        }
        tblViewAlunos.setItems(FXCollections.observableList(lstTemp));
        lblNomeDisciplina.setText(controllerPai.disciplina.getNome());
    }

}

