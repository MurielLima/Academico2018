package view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static config.Config.ALTERAR;
import static config.Config.EXCLUIR;
import static config.Config.INCLUIR;
import static config.DAO.alunoRepository;
import static config.DAO.alunoRepository;
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
import model.Aluno;
import model.Professor;
import org.springframework.data.domain.Sort;
import utility.XPopOver;

/**
 * FXML Controller class
 *
 * @author Muriel
 */
public class AlunoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    public TableView<Aluno> tblView;
    public char acao;
    public Aluno aluno;
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
    private MenuItem mnAlterar;
    @FXML
    private MenuItem mnExcluir;

    @FXML
    private void acIncluir() {
        acao = INCLUIR;
        aluno = new Aluno();
        showCRUD();

    }

    @FXML
    private void acAlterar() {
        acao = ALTERAR;
        aluno = tblView.getSelectionModel().getSelectedItem();
        showCRUD();

    }

    @FXML
    private void acExcluir() {
        acao = EXCLUIR;

        aluno = tblView.getSelectionModel().getSelectedItem();
        showCRUD();

    }

    @FXML
    private void acPesquisar() {

        tblView.setItems(FXCollections.observableList(
                alunoRepository.findByNomeLikeIgnoreCase(txtFldPesquisar.getText())));
    }

    @FXML
    private void acLimpar() {
        txtFldPesquisar.setText("");
        tblView.setItems(
                FXCollections.observableList(alunoRepository.findAll(new Sort(new Sort.Order("nome")))));
    }

    private void showCRUD() {
        String cena = "/fxml/CRUDAluno.fxml";
        XPopOver popOver = null;

        switch (acao) {
            case INCLUIR:
                popOver = new XPopOver(cena, "Inclusão de Aluno", btnIncluir);
                break;
            case ALTERAR:
                popOver = new XPopOver(cena, "Alteração de Aluno", btnAlterar);
                break;
            case EXCLUIR:
                popOver = new XPopOver(cena, "Exclusão de Aluno", btnExcluir);
                break;
        }
        CRUDAlunoController controllerFilho = popOver.getLoader().getController();
        controllerFilho.setCadastroController(this);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblView.setItems(
                FXCollections.observableList(alunoRepository.findAll(new Sort(new Sort.Order("nome")))));
        btnAlterar.visibleProperty().bind(
                Bindings.isEmpty((tblView.getSelectionModel().getSelectedItems())).not());
        btnExcluir.visibleProperty().bind(btnAlterar.visibleProperty());
        mnAlterar.visibleProperty().bind(btnAlterar.visibleProperty());
        mnExcluir.visibleProperty().bind(btnAlterar.visibleProperty());
        btnPesquisar.disableProperty().bind(txtFldPesquisar.textProperty().isEmpty());
    }

}
