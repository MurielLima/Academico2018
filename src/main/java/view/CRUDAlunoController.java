/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import static config.Config.ALTERAR;
import static config.Config.EXCLUIR;
import static config.Config.INCLUIR;
import static config.DAO.cidadeRepository;
import static config.DAO.alunoRepository;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.net.URL;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Cidade;
import org.controlsfx.control.PopOver;
import org.springframework.data.domain.Sort;
import utility.XPopOver;

/**
 *
 * @author Muriel
 */
public class CRUDAlunoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private AlunoController controllerPai;

    @FXML
    private TextField txtFldCodigo;

    @FXML
    private TextField txtFldNome;
    @FXML
    private TextField txtFldEmail;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button btnConfirma;

    @FXML
    public ComboBox cmbCidade;

    public char acao;
    public Cidade cidade;
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
    private final char separadorDecimal
            = new DecimalFormatSymbols(Locale.getDefault(Locale.Category.FORMAT)).getDecimalSeparator();

    @FXML
    private void btnCancelaClick() {
        anchorPane.getScene().getWindow().hide();
        controllerPai.tblView.requestFocus();
    }

    @FXML
    private void btnConfirmaClick() {
        controllerPai.aluno.setCpf(txtFldCodigo.getText());
        controllerPai.aluno.setNome(txtFldNome.getText());
        controllerPai.aluno.setCidade((Cidade) cmbCidade.getSelectionModel().getSelectedItem());
        controllerPai.aluno.setEmail(txtFldEmail.getText());
        try {
            switch (controllerPai.acao) {
                case INCLUIR:
                    alunoRepository.insert(controllerPai.aluno);
                    break;
                case ALTERAR:
                    alunoRepository.save(controllerPai.aluno);
                    break;
                case EXCLUIR:
                    alunoRepository.delete(controllerPai.aluno);
                    break;
            }
            controllerPai.tblView.setItems(
                    FXCollections.observableList(alunoRepository.findAll(
                            new Sort(new Sort.Order("nome")))));
            controllerPai.tblView.refresh();
            controllerPai.tblView.getSelectionModel().clearSelection();
            controllerPai.tblView.getSelectionModel().select(controllerPai.aluno);
            anchorPane.getScene().getWindow().hide();
//
        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Cadastro de Disciplina");
            if (e.getMessage().contains("duplicate key")) {
                alert.setContentText("Código já cadastrado");
            } else {
                alert.setContentText(e.getMessage());
            }
            alert.showAndWait();

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnConfirma.disableProperty().bind(txtFldCodigo.textProperty().isEmpty().
                or(txtFldNome.textProperty().isEmpty()).or(txtFldEmail.textProperty().isEmpty()).or(cmbCidade.getSelectionModel().selectedItemProperty().isNull()));
//        txtFldCodigo.textProperty().addListener(listenerCpf);
        btnAlterar.visibleProperty().bind(cmbCidade.getSelectionModel().selectedItemProperty().isNotNull());
    }

    public void setCadastroController(AlunoController controllerPai) {
        this.controllerPai = controllerPai;
        txtFldCodigo.setText(controllerPai.aluno.getCpf());
        txtFldNome.setText(controllerPai.aluno.getNome());
        txtFldEmail.setText(controllerPai.aluno.getEmail());

        cmbCidade.setItems(FXCollections.observableList(
                cidadeRepository.findAll(new Sort(new Sort.Order("nome")))));

        if (controllerPai.acao != INCLUIR) {
            cmbCidade.getSelectionModel().select(controllerPai.aluno.getCidade());
        }

        txtFldCodigo.setDisable(controllerPai.acao == EXCLUIR);
        txtFldNome.setDisable(controllerPai.acao == EXCLUIR);
        txtFldEmail.setDisable(controllerPai.acao == EXCLUIR);

    }
    private final ChangeListener<? super String> listenerCpf
            = (observable, oldValue, newValue) -> {
                if (!newValue.matches("([0-9])*")
                && !newValue.isEmpty()) {
                    txtFldCodigo.setText(oldValue);
                } else {
                    txtFldCodigo.setText(newValue);
                }
            };

    /**
     * CRUD CIDADE
     */
    @FXML
    private void acIncluir() {
        acao = INCLUIR;
        cidade = new Cidade();
        showCRUD();

    }

    @FXML
    private void acAlterar() {
        acao = ALTERAR;
        cidade = (Cidade) cmbCidade.getSelectionModel().getSelectedItem();
        showCRUD();

    }

    private void showCRUD() {
        String cena = "/fxml/CRUDCidadeA.fxml";
        XPopOver popOver = null;

        switch (acao) {
            case INCLUIR:
                popOver = new XPopOver(cena, "Inclusão de Cidade", btnIncluir, PopOver.ArrowLocation.TOP_RIGHT);
                break;
            case ALTERAR:
                popOver = new XPopOver(cena, "Alteração de Cidade", btnAlterar, PopOver.ArrowLocation.TOP_RIGHT);
                break;
        }
        CRUDCidadeAlunoController controllerFilho = popOver.getLoader().getController();
        controllerFilho.setCadastroController(this);
    }

}
