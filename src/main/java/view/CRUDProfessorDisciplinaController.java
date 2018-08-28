/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import static config.DAO.disciplinaRepository;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import model.Disciplina;
import utility.XPopOver;

/**
 * FXML Controller class
 *
 * @author Muriel
 */
public class CRUDProfessorDisciplinaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    public TableView<Disciplina> tblViewDisciplinas;
    private ProfessorController controllerPai;
    @FXML
    private Label lblNomeProfessor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setCadastroController(ProfessorController controllerPai) {
        this.controllerPai = controllerPai;
        tblViewDisciplinas.setItems(FXCollections.observableList(
                disciplinaRepository.findByProfessor(controllerPai.professor)));
        lblNomeProfessor.setText(controllerPai.professor.getNome());
    }

}
