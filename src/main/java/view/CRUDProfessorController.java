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
import static config.DAO.cidadeRepository;
import static config.DAO.disciplinaRepository;
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
 * FXML Controller class
 *
 * @author Muriel
 */
public class CRUDProfessorController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private ProfessorController controllerPai;
    public Cidade cidade;
    public char acao;
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
    private ComboBox cmbCidade;
    
    @FXML
    private void btnCancelaClick() {
        anchorPane.getScene().getWindow().hide();
        controllerPai.tblView.requestFocus();
    }

    @FXML
    private void btnConfirmaClick() {
        controllerPai.professor.setCpf(txtFldCodigo.getText());
        controllerPai.professor.setNome(txtFldNome.getText());
        controllerPai.professor.setCidade((Cidade) cmbCidade.getSelectionModel().getSelectedItem());
          controllerPai.professor.setEmail(txtFldEmail.getText());
        try {
            switch (controllerPai.acao) {
                case INCLUIR:
                    professorRepository.insert(controllerPai.professor);
                    break;
                case ALTERAR:
                    professorRepository.save(controllerPai.professor);
                    break;
                case EXCLUIR:
                    if (disciplinaRepository.countByProfessor(controllerPai.professor) > 0) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erro");
                        alert.setHeaderText("Exclusão de Professor");
                        alert.setContentText("Não é possivel excluir!\n"
                                + "Existem disciplinas cadastradas para este "
                                + "professor. Primeiro remova as disciplinas que estão vinculadas ");
                        alert.showAndWait();

                        break;
                    }
                    professorRepository.delete(controllerPai.professor);
                    break;
            }
            controllerPai.tblView.setItems(
                    FXCollections.observableList(professorRepository.findAll(
                            new Sort(new Sort.Order("nome")))));
            controllerPai.tblView.refresh();
            controllerPai.tblView.getSelectionModel().clearSelection();
            controllerPai.tblView.getSelectionModel().select(controllerPai.professor);
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

    public void setCadastroController(ProfessorController controllerPai) {
        this.controllerPai = controllerPai;
        txtFldCodigo.setText(controllerPai.professor.getCpf());
        txtFldNome.setText(controllerPai.professor.getNome());
        txtFldEmail.setText(controllerPai.professor.getEmail());
        cmbCidade.setItems(FXCollections.observableList(
                cidadeRepository.findAll(new Sort(new Sort.Order("nome")))));

        if (controllerPai.acao != INCLUIR) {
            cmbCidade.getSelectionModel().select(controllerPai.professor.getCidade());
        }

        txtFldCodigo.setDisable(controllerPai.acao == EXCLUIR);
        txtFldNome.setDisable(controllerPai.acao == EXCLUIR);

    }

}
