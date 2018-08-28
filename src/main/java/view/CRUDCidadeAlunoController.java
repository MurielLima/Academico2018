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
import static config.DAO.cidadeRepository;
import static config.DAO.disciplinaRepository;
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
import model.Cidade;
import model.Uf;
import org.springframework.data.domain.Sort;

/**
 * FXML Controller class
 *
 * @author Muriel
 */
public class CRUDCidadeAlunoController implements Initializable {

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
    private CRUDAlunoController controllerPai;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     btnConfirma.disableProperty().bind(txtFldNome.textProperty().isEmpty());
    }

    public void setCadastroController(CRUDAlunoController controllerPai) {
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
    }

    @FXML
    private void btnConfirmaClick() {
        controllerPai.cidade.setNome(txtFldNome.getText());
        controllerPai.cidade.setUf((Uf) cmbUf.getSelectionModel().getSelectedItem());
        try {
            switch (controllerPai.acao) {
                case INCLUIR:
                    cidadeRepository.insert(controllerPai.cidade);
                    break;
                case ALTERAR:
                    cidadeRepository.save(controllerPai.cidade);
                    break;
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sucesso");
            alert.setHeaderText("Cadastro de Cidade");
            alert.setContentText("Feito com sucesso!!");
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Cadastro de Cidade");
            alert.setContentText(e.getMessage());
            alert.showAndWait();

        }
        
    }

}
