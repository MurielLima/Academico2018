package view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import model.Cidade;
import model.Disciplina;
import model.Professor;
import model.Uf;
import static config.DAO.disciplinaRepository;
import static config.DAO.professorRepository;
import static config.DAO.cidadeRepository;
import static config.DAO.ufRepository;

public class PrincipalController implements Initializable {
    

    
    Disciplina disciplina;
    Professor professor;
    Cidade cidade;
    Uf uf;
    List<Cidade> lstCit =   new ArrayList<Cidade>();
    List<Uf> lstUf  =   new ArrayList<Uf>();
    List<Disciplina> lstDisc    =   new ArrayList<Disciplina>();
    List<Professor> lstProf    =   new ArrayList<Professor>();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
//        /**
//         * Salva os estados e suas Siglas
//         */
//        uf = new Uf("Paraná","PR");
//        ufRepository.save(uf);
//        uf = new Uf("Rio Grande de Sul","RS");
//        ufRepository.save(uf);
//        uf = new Uf("São Paulo","SP");
//        ufRepository.save(uf);
//        uf = new Uf("Rio de Janeiro","RJ");
//        ufRepository.save(uf);
//        uf = new Uf("Amazonas","AM");
//        ufRepository.save(uf);
//        uf = new Uf("Piauí","PI");
//        ufRepository.save(uf);
//        uf = new Uf("Maranhão","MA");
//        ufRepository.save(uf);
//        uf = new Uf("Tocantins","TO");
//        ufRepository.save(uf);
//        uf = new Uf("Rio Grande do Norte","RN");
//        ufRepository.save(uf);
//         /**
//         * Encontra as Siglas na tabela uf e cria um link com a cidade
//         */
//        uf =  ufRepository.findBySiglaLikeIgnoreCase("PR");
//        
//        cidade = new Cidade("Porto Amazonas",uf);
//        cidadeRepository.save(cidade);
//        
//        uf =  ufRepository.findBySiglaLikeIgnoreCase("RS");
//        cidade = new Cidade("Gramado",uf);
//        cidadeRepository.save(cidade);
//       
//        uf =  ufRepository.findBySiglaLikeIgnoreCase("SP");
//        cidade = new Cidade("São Paulo",uf);
//        cidadeRepository.save(cidade);
//        
//        uf =  ufRepository.findBySiglaLikeIgnoreCase("RJ");
//        cidade = new Cidade("Rio de Janeiro",uf);
//        cidadeRepository.save(cidade);
//            /**
//             * Cria professor com cidade
//             */
//            professor =   new Professor("Antonio","antonio@email.com","123298131",cidadeRepository.findByNome("Porto Amazonas"));
//            professorRepository.save(professor);
//            professor =   new Professor("José","jose@email.com","2131290331",cidadeRepository.findByNome("Gramado"));
//            professorRepository.save(professor);
//            professor =   new Professor("Bruna","bruna@email.com","90932131",cidadeRepository.findByNome("Rio de Janeiro"));
//            professorRepository.save(professor);
//            /**
//             * -------------------------
//             */
//            /**
//             * Cria disciplinas com professor
//             */
//             professor =  professorRepository.findByNome("Antonio");
//             disciplina =   new Disciplina(professor,"64565","Matemática",10,"Nenhuma");
//             disciplinaRepository.save(disciplina);
    }    
}
