package view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static config.Config.ALTERAR;
import static config.Config.EXCLUIR;
import static config.Config.INCLUIR;
import static config.DAO.professorRepository;
import static config.DAO.professorRepository;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import model.Disciplina;
import model.Professor;
import org.springframework.data.domain.Sort;
import utility.XPopOver;

/**
 * FXML Controller class
 *
 * @author Muriel
 */
public class ProfessorController implements Initializable {

  /**
     * Initializes the controller class.
     */
    @FXML
    public TableView<Professor> tblView;
    public char acao;
    public Professor professor;
    @FXML
    private MaterialDesignIconView btnIncluir;
    @FXML
    private MaterialDesignIconView btnAlterar;
    @FXML
    private MaterialDesignIconView btnExcluir;

    @FXML
    private void acIncluir() {
        acao = INCLUIR;
        professor = new Professor();

        showCRUD();

    }

    @FXML
    private void acAlterar() {
        acao = ALTERAR;
        professor = tblView.getSelectionModel().getSelectedItem();
        showCRUD();
    }
    @FXML
    private void acExcluir() {
        acao = EXCLUIR;
        professor = tblView.getSelectionModel().getSelectedItem();
        showCRUD();
    }

    private void showCRUD() {
        String cena = "/fxml/CRUDProfessor.fxml";
        XPopOver popOver = null;

        switch (acao) {
            case INCLUIR:
                popOver = new XPopOver(cena, "Inclusão de Professor", btnIncluir);
                break;
            case ALTERAR:
                popOver = new XPopOver(cena, "Inclusão de Professor", btnAlterar);
                break;
            case EXCLUIR:
                popOver = new XPopOver(cena, "Inclusão de Professor", btnExcluir);
                break;
        }
        CRUDProfessorController controllerFilho = popOver.getLoader().getController();
        controllerFilho.setCadastroController(this);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblView.setItems(
                FXCollections.observableList(professorRepository.findAll(new Sort(new Sort.Order("nome")))));
    }

}
