package view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static config.DAO.professorRepository;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import org.springframework.data.domain.Sort;

/**
 * FXML Controller class
 *
 * @author Muriel
 */
public class ProfessorController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView tblView;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         tblView.setItems(
        FXCollections.observableList(professorRepository.findAll(new Sort(new Sort.Order("nome")))));
    }    
    
}
