package view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static config.Config.ALTERAR;
import static config.Config.EXCLUIR;
import static config.Config.INCLUIR;
import static config.Config.df;
import static config.DAO.cidadeRepository;
import static config.DAO.professorRepository;
import static config.DAO.ufRepository;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Cidade;
import model.Matricula;
import model.Professor;
import model.Uf;
import org.json.JSONObject;
import org.springframework.data.domain.Sort;
import utility.XPopOver;

/**
 * FXML Controller class
 *
 * @author Muriel
 */
public class ProfessorController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public char acao;
    public Professor professor;
    @FXML
    public TableView<Professor> tblView;
    @FXML
    private MaterialDesignIconView btnIncluir;
    @FXML
    private MaterialDesignIconView btnAlterar;
    @FXML
    private MaterialDesignIconView btnExcluir;
    @FXML
    private MaterialDesignIconView btnDisciplinas;
    @FXML
    private TextField txtFldPesquisar;
    @FXML
    private MaterialDesignIconView btnPesquisar;
    @FXML
    private MenuItem mnIncluir;
    @FXML
    private MenuItem mnAlterar;
    @FXML
    private MenuItem mnExcluir;
    @FXML
    private MenuItem mnDisciplinas;

    @FXML
    private void acIncluir() {
        acao = INCLUIR;
        professor = new Professor();
        showCRUD();
    }

    @FXML
    private void acAlterar() {
        acao = ALTERAR;
        professor = tblView.getSelectionModel().getSelectedItem();
        showCRUD();
    }

    @FXML
    private void acExcluir() {
        acao = EXCLUIR;
        professor = tblView.getSelectionModel().getSelectedItem();
        showCRUD();
    }

    @FXML
    private void acPesquisar() {
        tblView.setItems(FXCollections.observableList(
                professorRepository.findByNomeLikeIgnoreCase(txtFldPesquisar.getText())));
    }

    @FXML
    private void acLimpar() {
        txtFldPesquisar.setText("");
        tblView.setItems(
                FXCollections.observableList(professorRepository.findAll(new Sort(new Sort.Order("nome")))));
    }

    @FXML
    private void acDisciplinas() {
        professor = tblView.getSelectionModel().getSelectedItem();
        String cena = "/fxml/CRUDProfessorDisciplina.fxml";
        XPopOver popOver;
        popOver = new XPopOver(cena, "Lista de disciplinas", null);
        CRUDProfessorDisciplinaController controllerFilho = popOver.getLoader().getController();
        controllerFilho.setCadastroController(this);
    }

    private void showCRUD() {
        String cena = "/fxml/CRUDProfessor.fxml";
        XPopOver popOver = null;

        switch (acao) {
            case INCLUIR:
                popOver = new XPopOver(cena, "Inclusão de Professor", null);
                break;
            case ALTERAR:
                popOver = new XPopOver(cena, "Alteração de Professor", null);
                break;
            case EXCLUIR:
                popOver = new XPopOver(cena, "Exclusão de Professor", btnExcluir);
                break;
        }
        CRUDProfessorController controllerFilho = popOver.getLoader().getController();
        controllerFilho.setCadastroController(this);
    }

    @FXML
    private void btnExportJsonClick() {
        List<Professor> lstProf = new ArrayList<Professor>();
        try {
            String nomeArq = "C:\\Engenharia-de-Software\\2-Ano\\Linguagens-de-Programacao\\4-Bimestre\\Professores.json";
            File file = new File(nomeArq);
            file.createNewFile();
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            lstProf = professorRepository.findAll();

            JSONObject eJSON = new JSONObject();
            for (Professor j : lstProf) {
                eJSON.put("nome", j.getNome());
                eJSON.put("cpf", j.getCpf());
                eJSON.put("ativo", j.isAtivo());
                eJSON.put("email", j.getEmail());
                eJSON.put("cidade", j.getCidade());
                eJSON.put("uf", j.getCidade().getUf());

//                eJSON.put("dataCadastro", j.getDataCadastroFormat());
                bw.write(eJSON.toString() + "\n");
            }
            bw.close();
        } catch (Exception e) {

        }

    }

    @FXML
    private void btnImportJsonClick() {

        final Stage stage = null;
        Professor p1 = null;
        FileChooser fileChooser = new FileChooser();
        String linha;
        BufferedReader br = null;

        fileChooser.setTitle(
                "Escolha o seu arquivo:");
        fileChooser.setInitialDirectory(
                new File("C:\\Engenharia-de-Software\\2-Ano\\Linguagens-de-Programacao\\4-Bimestre\\"));
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "Text Files", "*.txt", "*.json", "JSON"
        );

        fileChooser.getExtensionFilters()
                .add(extFilter);

        List<Professor> info = new ArrayList<>();
        Professor pessoaTmp;

        try {
            br = new BufferedReader(new FileReader(String.valueOf(fileChooser.showOpenDialog(stage))));

            while ((linha = br.readLine()) != null) {
                JSONObject eJSON = new JSONObject(linha);
                pessoaTmp = new Professor();
                if (eJSON.getString("nome") != null && eJSON.getString("cpf") != null) {
                    pessoaTmp.setNome(eJSON.getString("nome"));
                    pessoaTmp.setCpf(eJSON.getString("cpf"));
                    if (eJSON.getString("email") != null) {
                        pessoaTmp.setEmail(eJSON.getString("email"));
                    }
                    pessoaTmp.setAtivo(eJSON.getBoolean("ativo"));

                    if (eJSON.getString("cidade") != null) {

                        if (cidadeRepository.findByNome(eJSON.getString("cidade")) != null) {
                            pessoaTmp.setCidade(cidadeRepository.findByNome(eJSON.getString("cidade")));
                        } else if (cidadeRepository.findByNome(eJSON.getString("cidade")) == null) {
                            Cidade cidadeTemp;
                            Uf ufTemp;
                            ufTemp = ufRepository.findBySiglaLikeIgnoreCase(eJSON.getString("uf"));
                            if (ufTemp != null) {
                                cidadeTemp = new Cidade(eJSON.getString("cidade"), ufTemp);
                                cidadeRepository.insert(cidadeTemp);
                                pessoaTmp.setCidade(cidadeRepository.findByNome(eJSON.getString("cidade")));
                            }

                        }

                    }
                }

                p1 = professorRepository.findByCpf(pessoaTmp.getCpf());

                if (p1 == null) {
                    System.out.println("Insert");
                    professorRepository.insert(pessoaTmp);
                } else {
                    pessoaTmp.setId(p1.getId());

                    pessoaTmp.setDepartamento(p1.getDepartamento());
                    if (p1.getDataCadastro() != null) {
                        pessoaTmp.setDataCadastro(p1.getDataCadastro());
                    } else {
                        pessoaTmp.setDataCadastro(LocalDate.now());
                    }
                    System.out.println("Save");
                    professorRepository.save(pessoaTmp);
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
            }
        }

        tblView.refresh();

        tblView.setItems(
                FXCollections.observableList(professorRepository.findAll(new Sort(new Sort.Order("nome")))));

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblView.setItems(
                FXCollections.observableList(professorRepository.findAll(new Sort(new Sort.Order("nome")))));
        btnAlterar.visibleProperty().bind(
                Bindings.isEmpty((tblView.getSelectionModel().getSelectedItems())).not());
        btnExcluir.visibleProperty().bind(btnAlterar.visibleProperty());
        btnDisciplinas.visibleProperty().bind(btnAlterar.visibleProperty());
        mnAlterar.visibleProperty().bind(btnAlterar.visibleProperty());
        mnExcluir.visibleProperty().bind(btnAlterar.visibleProperty());
        mnDisciplinas.visibleProperty().bind(btnAlterar.visibleProperty());
        btnPesquisar.disableProperty().bind(txtFldPesquisar.textProperty().isEmpty());

        tblView.setRowFactory(tableView
                -> {
            TableRow<Professor> row = new TableRow<>();

            row.itemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null && (newValue.isAtivo())) {
                    row.getStyleClass().add("ativo");
                } else {
                    row.getStyleClass().remove("ativo");

                }

            });

            return row;
        });
    }

}
