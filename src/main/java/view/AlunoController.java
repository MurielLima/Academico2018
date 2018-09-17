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
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;
import model.Aluno;
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
    
    public char acao;
    public Aluno aluno;
    @FXML
    public TableView<Aluno> tblViewAlunos;
    @FXML MenuItem mnVerBoletim;
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
    private void tblVwAlunosClick(Event event) {
        MouseEvent me = null;
        if (event.getEventType() == MOUSE_CLICKED) {
            me = (MouseEvent) event;
            if (me.getClickCount() == 2 && tblViewAlunos.getSelectionModel().getSelectedItem() != null) {
                aluno=tblViewAlunos.getSelectionModel().getSelectedItem();
                mostraDisciplinasAluno();
            }
        }
    }
    @FXML
    private void mostraDisciplinasAluno() {
        aluno = tblViewAlunos.getSelectionModel().getSelectedItem();
        String cena = "/fxml/Boletim.fxml";
        XPopOver popOver = null;
        popOver = new XPopOver(cena, "Boletim Acadêmico", null);
        BoletimController controllerFilho = popOver.getLoader().getController();
        controllerFilho.setCadastroController(this);
    }

    @FXML
    private void acIncluir() {
        acao = INCLUIR;
        aluno = new Aluno();
        showCRUD();
    }

    @FXML
    private void acAlterar() {
        acao = ALTERAR;
        aluno = tblViewAlunos.getSelectionModel().getSelectedItem();
        showCRUD();
    }

    @FXML
    private void acExcluir() {
        acao = EXCLUIR;
        aluno = tblViewAlunos.getSelectionModel().getSelectedItem();
        showCRUD();
    }

    @FXML
    private void acPesquisar() {
        tblViewAlunos.setItems(FXCollections.observableList(
                alunoRepository.findByNomeLikeIgnoreCaseOrEmailLikeIgnoreCase(txtFldPesquisar.getText(), txtFldPesquisar.getText())));
    }

    @FXML
    private void acLimpar() {
        txtFldPesquisar.setText("");
        tblViewAlunos.setItems(
                FXCollections.observableList(alunoRepository.findAll(new Sort(new Sort.Order("nome")))));
    }

    private void showCRUD() {
        String cena = "/fxml/CRUDAluno.fxml";
        XPopOver popOver = null;

        switch (acao) {
            case INCLUIR:
                popOver = new XPopOver(cena, "Inclusão de Aluno", null);
                break;
            case ALTERAR:
                popOver = new XPopOver(cena, "Alteração de Aluno", null);
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
        tblViewAlunos.setItems(
                FXCollections.observableList(alunoRepository.findAll(new Sort(new Sort.Order("nome")))));
        btnAlterar.visibleProperty().bind(
                Bindings.isEmpty((tblViewAlunos.getSelectionModel().getSelectedItems())).not());
        btnExcluir.visibleProperty().bind(btnAlterar.visibleProperty());
        mnAlterar.visibleProperty().bind(btnAlterar.visibleProperty());
        mnExcluir.visibleProperty().bind(btnAlterar.visibleProperty());
        mnVerBoletim.visibleProperty().bind(btnAlterar.visibleProperty());
        btnPesquisar.disableProperty().bind(txtFldPesquisar.textProperty().isEmpty());
    }

}
