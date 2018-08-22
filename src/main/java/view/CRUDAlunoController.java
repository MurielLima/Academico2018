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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Cidade;
import org.springframework.data.domain.Sort;

/**
 *
 * @author Muriel
 */
public class CRUDAlunoController implements Initializable{
        /**
     * Initializes the controller class.
     */
    private AlunoController controllerPai;


    @FXML
    private TextField txtFldCodigo;

    @FXML
    private TextField txtFldNome;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button btnConfirma;

    @FXML
    private ComboBox cmbCidade;

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
                or(txtFldNome.textProperty().isEmpty()));
        
    }

    public void setCadastroController(AlunoController controllerPai) {
        this.controllerPai = controllerPai;
        txtFldCodigo.setText(controllerPai.aluno.getCpf());
        txtFldNome.setText(controllerPai.aluno.getNome());

        cmbCidade.setItems(FXCollections.observableList(
                cidadeRepository.findAll(new Sort(new Sort.Order("nome")))));

        if (controllerPai.acao != INCLUIR) {
            cmbCidade.getSelectionModel().select(controllerPai.aluno.getCidade());
        }

        txtFldCodigo.setDisable(controllerPai.acao == EXCLUIR);
        txtFldNome.setDisable(controllerPai.acao == EXCLUIR);

    }
}
