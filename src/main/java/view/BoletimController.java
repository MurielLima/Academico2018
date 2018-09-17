package view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;
import javafx.scene.layout.AnchorPane;
import model.Aluno;
import model.Matricula;
import utility.XPopOver;

/**
 * FXML Controller class
 *
 * @author Muriel
 */
public class BoletimController implements Initializable {

    private AlunoController controllerPai;
    public Matricula matricula;
    public Aluno aluno;
    @FXML
    public TableView<Matricula> tblViewBoletim;
    @FXML
    private Label lblNomeAluno;
    @FXML
    private Button btnConfirma;
    @FXML
    private AnchorPane anchorPane;
    

    public void setCadastroController(AlunoController controllerPai) {
        this.controllerPai = controllerPai;
        aluno = controllerPai.aluno;
        lblNomeAluno.setText(controllerPai.aluno.getNome());
        tblViewBoletim.setItems(FXCollections.observableList(controllerPai.aluno.getMatriculas()));
    }

    @FXML
    private void tblVwDisciplinaClick(Event event) {
        MouseEvent me = null;
        if (event.getEventType() == MOUSE_CLICKED) {
            me = (MouseEvent) event;
            if (me.getClickCount() == 2 && tblViewBoletim.getSelectionModel().getSelectedItem() != null) {
                matricula = tblViewBoletim.getSelectionModel().getSelectedItem();
                mostraDisciplinasAluno();
            }
        }
    }
    @FXML
    private void mostraDisciplinasAluno() {
        matricula = tblViewBoletim.getSelectionModel().getSelectedItem();
        String cena = "/fxml/CRUDBoletim.fxml";
        XPopOver popOver = null;
        popOver = new XPopOver(cena, "Editar Disciplina - Boletim Acadêmico", null);
        CRUDBoletimController controllerFilho = popOver.getLoader().getController();
        controllerFilho.setCadastroController(this);
    }

    @FXML
    private void btnFecharClick() {
        controllerPai.tblViewAlunos.requestFocus();
        anchorPane.getScene().getWindow().hide();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }

}
