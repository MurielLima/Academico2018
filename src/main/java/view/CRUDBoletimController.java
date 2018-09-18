package view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static config.DAO.alunoRepository;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Aluno;
import model.Matricula;

/**
 * FXML Controller class
 *
 * @author Muriel
 */
public class CRUDBoletimController implements Initializable {

    private Aluno aluno;
    private BoletimController controllerPai;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField txtFld1Sem;
    @FXML
    private TextField txtFld2Sem;
    @FXML
    private TextField txtFldExam;
    @FXML
    private TextField txtFldFalt;
    @FXML
    private Label lblNomeDisciplina;
    @FXML
    private Button btnConfirma;

    public void setCadastroController(BoletimController controllerPai) {
        this.controllerPai = controllerPai;
        aluno = controllerPai.aluno;
        lblNomeDisciplina.setText(controllerPai.matricula.getDisciplina().getNome());
        txtFld1Sem.setText(String.valueOf(controllerPai.matricula.getNota1Sem()));
        txtFld2Sem.setText(String.valueOf(controllerPai.matricula.getNota2Sem()));
        txtFldExam.setText(String.valueOf(controllerPai.matricula.getNotaExam()));
        txtFldFalt.setText(String.valueOf(controllerPai.matricula.getFaltas()));
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnConfirma.disableProperty().bind(txtFld1Sem.textProperty().isEmpty().or(txtFld2Sem.textProperty().isEmpty()).or(txtFldExam.textProperty().isEmpty()).or(txtFldFalt.textProperty().isEmpty()));
    }

    @FXML
    private void btnCancelaClick() {
        controllerPai.tblViewBoletim.requestFocus();
        controllerPai.tblViewBoletim.refresh();
        controllerPai.tblViewBoletim.getSelectionModel().clearSelection();
        anchorPane.getScene().getWindow().hide();
    }

    @FXML
    private void btnConfirmaClick() {
        controllerPai.matricula.setNota1Sem(Integer.parseInt(txtFld1Sem.getText()));
        controllerPai.matricula.setNota2Sem(Integer.parseInt(txtFld2Sem.getText()));
        controllerPai.matricula.setNotaExam(Integer.parseInt(txtFldExam.getText()));
        controllerPai.matricula.setFaltas(Integer.parseInt(txtFldFalt.getText()));
        List<Matricula> lstTemp = new ArrayList<>();
//        lstTemp.add(controllerPai.matricula);
        for (Matricula m : controllerPai.aluno.getMatriculas()) {
            lstTemp.add(m);
        }
        controllerPai.aluno.setMatriculas(lstTemp);
        try {
            alunoRepository.save(controllerPai.aluno);
            controllerPai.tblViewBoletim.setItems(FXCollections.observableList(controllerPai.aluno.getMatriculas()));
            controllerPai.tblViewBoletim.refresh();
            controllerPai.tblViewBoletim.getSelectionModel().clearSelection();
            controllerPai.tblViewBoletim.requestFocus();
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
}
