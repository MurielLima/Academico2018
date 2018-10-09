/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import static config.Config.INCLUIR;
import static config.Config.ALTERAR;
import static config.Config.EXCLUIR;
import static config.DAO.disciplinaRepository;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Disciplina;
import org.controlsfx.control.PopOver;
import org.springframework.data.domain.Sort;
import utility.XPopOver;

/**
 * FXML Controller class
 *
 * @author Muriel
 */
public class DisciplinaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public Disciplina disciplina;
    public char acao;
    @FXML
    public TableView<Disciplina> tblView;
    @FXML
    private MaterialDesignIconView btnIncluir;
    @FXML
    private MaterialDesignIconView btnAlterar;
    @FXML
    private MaterialDesignIconView btnExcluir;
    @FXML
    private TextField txtFldPesquisar;
    @FXML
    private MaterialDesignIconView btnPesquisar;
    @FXML
    private MenuItem mnIncluir;
    @FXML
    private MenuItem mnAlterar;
    @FXML
    private MenuItem mnExcluir;

    @FXML
    private void acIncluir() {
        acao = INCLUIR;
        disciplina = new Disciplina();
        showCRUD();
    }

    @FXML
    private void acAlterar() {
        acao = ALTERAR;
        disciplina = tblView.getSelectionModel().getSelectedItem();
        showCRUD();
    }

    @FXML
    private void acExcluir() {
        acao = EXCLUIR;
        disciplina = tblView.getSelectionModel().getSelectedItem();
        showCRUD();
    }

    @FXML
    private void acPesquisar() {
        tblView.setItems(FXCollections.observableList(
                disciplinaRepository.findByNomeLikeIgnoreCase(txtFldPesquisar.getText())));
    }

    @FXML
    private void btnMostraAlunosClick() {
        String cena = "/fxml/DisciplinaAlunos.fxml";
        XPopOver popOver = null;
        disciplina = tblView.getSelectionModel().getSelectedItem();
         popOver = new XPopOver(cena, "Alunos", null);
        DisciplinaAlunosController controllerFilho = popOver.getLoader().getController();
        controllerFilho.setCadastroController(this);
    }
    @FXML
    private void acLimpar() {
        txtFldPesquisar.setText("");
        tblView.setItems(
                FXCollections.observableList(disciplinaRepository.findAll(new Sort(new Sort.Order("nome")))));
    }

    private void showCRUD() {
        String cena = "/fxml/CRUDDisciplina.fxml";
        XPopOver popOver = null;

        switch (acao) {
            case INCLUIR:
                popOver = new XPopOver(cena, "Inclusão de Disciplina", null);
                break;
            case ALTERAR:
                popOver = new XPopOver(cena, "Alteração de Disciplina", null, PopOver.ArrowLocation.TOP_CENTER);
                break;
            case EXCLUIR:
                popOver = new XPopOver(cena, "Exclusão de Disciplina", btnExcluir);
                break;
        }
        CRUDDisciplinaController controllerFilho = popOver.getLoader().getController();
        controllerFilho.setCadastroController(this);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblView.setItems(
                FXCollections.observableList(disciplinaRepository.findAll(new Sort(new Sort.Order("nome")))));
        btnAlterar.visibleProperty().bind(
                Bindings.isEmpty((tblView.getSelectionModel().getSelectedItems())).not());
        btnExcluir.visibleProperty().bind(btnAlterar.visibleProperty());
        mnAlterar.visibleProperty().bind(btnAlterar.visibleProperty());
        mnExcluir.visibleProperty().bind(btnAlterar.visibleProperty());
        btnPesquisar.disableProperty().bind(txtFldPesquisar.textProperty().isEmpty());
        

    }

}
