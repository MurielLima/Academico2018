/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import static config.Config.ALTERAR;
import static config.Config.EXCLUIR;
import static config.Config.INCLUIR;
import static config.DAO.disciplinaRepository;
import static config.DAO.professorRepository;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import model.Professor;
import org.springframework.data.domain.Sort;

/**
 * FXML Controller class
 *
 * @author idomar
 */
public class CRUDDisciplinaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private DisciplinaController controllerPai;
    private final char separadorDecimal
            = new DecimalFormatSymbols(Locale.getDefault(Locale.Category.FORMAT)).getDecimalSeparator();
    @FXML
    private TextField txtFldCodigo;
    @FXML
    private TextField txtFldNome;
    @FXML
    private TextField txtFldObservacao;
    @FXML
    private TextField txtFldHoras;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button btnConfirma;
    @FXML
    private ComboBox cmbProfessor;
    @FXML
    private CheckBox chckBxSemestral;
    @FXML
    private ToggleGroup rdioGroupSemestral;
    @FXML
    private TextField txtFldMaxAlunos;
    

    @FXML
    private void btnCancelaClick() {
        anchorPane.getScene().getWindow().hide();
        controllerPai.tblView.requestFocus();
    }

    @FXML
    private void btnConfirmaClick() {
        controllerPai.disciplina.setCodigo(txtFldCodigo.getText());
        controllerPai.disciplina.setNome(txtFldNome.getText());
        controllerPai.disciplina.setObservacao(txtFldObservacao.getText());
        controllerPai.disciplina.setAulas(Integer.parseInt(txtFldHoras.getText()));
        controllerPai.disciplina.setProfessor((Professor) cmbProfessor.getSelectionModel().getSelectedItem());
        
        controllerPai.disciplina.setMaxAlunos(Integer.parseInt(txtFldMaxAlunos.getText()));
        try {
            switch (controllerPai.acao) {
                case INCLUIR:
                    disciplinaRepository.insert(controllerPai.disciplina);
                    break;
                case ALTERAR:
                    disciplinaRepository.save(controllerPai.disciplina);
                    break;
                case EXCLUIR:
                    disciplinaRepository.delete(controllerPai.disciplina);
                    break;
            }
            controllerPai.tblView.setItems(
                    FXCollections.observableList(disciplinaRepository.findAll(
                            new Sort(new Sort.Order("nome")))));
            controllerPai.tblView.refresh();
            controllerPai.tblView.getSelectionModel().clearSelection();
            controllerPai.tblView.getSelectionModel().select(controllerPai.disciplina);
            anchorPane.getScene().getWindow().hide();
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
                or(txtFldNome.textProperty().isEmpty()).or(txtFldHoras.textProperty().isEmpty()));
//        txtFldHoras.textProperty().addListener(listenerHoras);
//        txtFldCodigo.textProperty().addListener(listenerCodigo);    
    }

    public void setCadastroController(DisciplinaController controllerPai) {
        this.controllerPai = controllerPai;
        txtFldCodigo.setText(controllerPai.disciplina.getCodigo());
        txtFldNome.setText(controllerPai.disciplina.getNome());
        txtFldHoras.setText(String.valueOf(controllerPai.disciplina.getAulas()));
        txtFldMaxAlunos.setText(String.valueOf(controllerPai.disciplina.getMaxAlunos()));
//        chckBxSemestral.selectedProperty().set(controllerPai.disciplina.isSemestral());
        
        
        
        cmbProfessor.setItems(FXCollections.observableList(
                professorRepository.findAll(new Sort(new Sort.Order("nome")))));
        if (controllerPai.acao != INCLUIR) {
            cmbProfessor.getSelectionModel().select(controllerPai.disciplina.getProfessor());
        }
        txtFldCodigo.setDisable(controllerPai.acao == EXCLUIR);
        txtFldNome.setDisable(controllerPai.acao == EXCLUIR);
    }
    private final ChangeListener<? super String> listenerHoras
            = (observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*?")
                && !newValue.isEmpty()) {
                    txtFldHoras.setText(oldValue);
                } else {
                    txtFldHoras.setText(newValue);
                }
            };
    private final ChangeListener<? super String> listenerCodigo
            = (observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*?")
                && !newValue.isEmpty()) {
                    txtFldCodigo.setText(oldValue);
                } else {
                    txtFldCodigo.setText(newValue);
                }
            };
}
