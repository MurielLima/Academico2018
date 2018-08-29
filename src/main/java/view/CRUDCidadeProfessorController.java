package view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static config.Config.ALTERAR;
import static config.Config.INCLUIR;
import static config.DAO.cidadeRepository;
import static config.DAO.professorRepository;
import static config.DAO.ufRepository;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
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
import model.Uf;
import org.springframework.data.domain.Sort;
import utility.XPopOver;

/**
 * FXML Controller class
 *
 * @author Muriel
 */
public class CRUDCidadeProfessorController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private MaterialDesignIconView btnIncluir;
    @FXML
    private MaterialDesignIconView btnAlterar;
    @FXML
    private TextField txtFldNome;
    @FXML
    private ComboBox<Uf> cmbUf;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button btnConfirma;
    private CRUDProfessorController controllerPai;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnConfirma.disableProperty().bind(txtFldNome.textProperty().isEmpty().or(cmbUf.getSelectionModel().selectedItemProperty().isNull()));
    }

    public void setCadastroController(CRUDProfessorController controllerPai) {
        this.controllerPai = controllerPai;
        txtFldNome.setText(controllerPai.cidade.getNome());
        cmbUf.setItems(FXCollections.observableList(
                ufRepository.findAll(new Sort(new Sort.Order("sigla")))));
        if (controllerPai.acao != INCLUIR) {
            cmbUf.getSelectionModel().select(controllerPai.cidade.getUf());
        }
    }

    @FXML
    private void btnCancelaClick() {
        controllerPai.cmbCidade.requestFocus();
        anchorPane.getScene().getWindow().hide();
    }

    @FXML
    private void btnConfirmaClick() {
        controllerPai.cidade.setNome(txtFldNome.getText().toUpperCase());
        controllerPai.cidade.setUf((Uf) cmbUf.getSelectionModel().getSelectedItem());
        try {
            if (cidadeRepository.countByNomeAndUf(controllerPai.cidade.getNome(), controllerPai.cidade.getUf()) == 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Cadastro de Cidade");
            alert.setContentText("Já exite uma cidade cadastrada com esse nome nesse estado.");
            alert.showAndWait();
            } else {
                switch (controllerPai.acao) {
                    case INCLUIR:
                        cidadeRepository.insert(controllerPai.cidade);
                        break;
                    case ALTERAR:
                        cidadeRepository.save(controllerPai.cidade);
                        break;
                }

                controllerPai.cmbCidade.setItems(FXCollections.observableList(
                        ufRepository.findAll(new Sort(new Sort.Order("sigla")))));
                controllerPai.cmbCidade.getSelectionModel().clearSelection();
                controllerPai.cmbCidade.getSelectionModel().select(controllerPai.cidade);
                anchorPane.getScene().getWindow().hide();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Cadastro de Cidade");
            alert.setContentText(e.getMessage());
            alert.showAndWait();

        }

    }

}
