/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import static config.Config.INCLUIR;
import static config.DAO.disciplinaRepository;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import model.Disciplina;
import org.springframework.data.domain.Sort;
import utility.XPopOver;

/**
 * FXML Controller class
 *
 * @author Muriel
 */
public class DisciplinaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView tblView;
    public char acao;
    private Disciplina disciplina;
    @FXML 
    private MaterialDesignIconView btnIncluir;
    @FXML 
    private void acIncluir(){
        acao = INCLUIR;
        disciplina = new Disciplina();
        String cena =   "/fxml/CRUDDisciplina.fxml";
        XPopOver popOver    =   null;
        popOver = new XPopOver(cena,"Inclusão de Disciplina", btnIncluir);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblView.setItems(
        FXCollections.observableList(disciplinaRepository.findAll(new Sort(new Sort.Order("nome")))));
    }    
    
}
