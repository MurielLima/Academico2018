package view;

import static config.DAO.alunoRepository;
import static config.DAO.cidadeRepository;
import static config.DAO.departamentoRepository;
import static config.DAO.disciplinaRepository;
import static config.DAO.professorRepository;
import static config.DAO.turnoRepository;
import static config.DAO.ufRepository;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import model.Cidade;
import model.Disciplina;
import model.Professor;
import model.Uf;
import model.Aluno;
import model.Departamento;
import model.Matricula;
import model.Turno;

public class PrincipalController implements Initializable {

    Disciplina disciplina;
    Professor professor;
    Cidade cidade;
    Uf uf;
    Aluno aluno;   
    List<Turno> lstTurno = new ArrayList<Turno>();
    List<Departamento> lstDep = new ArrayList<Departamento>();
    List<Aluno> lstAlu = new ArrayList<Aluno>();
    List<Cidade> lstCit = new ArrayList<Cidade>();
    List<Uf> lstUf = new ArrayList<Uf>();
    List<Disciplina> lstDisc = new ArrayList<Disciplina>();
    List<Professor> lstProf = new ArrayList<Professor>();
    List<Matricula> lstMatricula = new ArrayList<Matricula>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        /**
//         * Encontra as Siglas na tabela uf e cria um link com a cidade
//         */
//        uf =  ufRepository.findBySiglaLikeIgnoreCase("PR");
//        
//        cidade = new Cidade("Irati",uf);
//        cidadeRepository.insert(cidade);
//        
//        cidade = new Cidade("Palmeira",uf);
//        cidadeRepository.insert(cidade);
//        
//        cidade = new Cidade("Curitiba",uf);
//        cidadeRepository.insert(cidade);
//        
//        cidade = new Cidade("Pirai",uf);
//        cidadeRepository.insert(cidade);
//        
////        
////       
//        int tamA;
//        tamA=10;
//        String nomes[]=new String[tamA];
//        
//        nomes[0]="Eduardo";
//        nomes[1]="Muriel";
//        nomes[2]="Lucas";
//        nomes[3]="Joao";
//        nomes[4]="Marcelo";
//        nomes[5]="Carlos";
//        nomes[6]="luciano";
//        nomes[7]="Gabriela";
//        nomes[8]="Rafaela";
//        nomes[9]="ELias";
//        
//        for(int i=0;i<tamA;i++){
//            alunoRepository.insert(new Aluno(nomes[i],nomes[i]+"@email.com","1"+i*323,LocalDate.now().minusYears(i*2),cidadeRepository.findByNome("Curitiba")));
////            professorRepository.insert(new Professor(nomes[i],nomes[i]+"@ig.com","9"+i*541, cidadeRepository.findByNome("Palmeira")));
//        }
     
//        lstAlu  =   alunoRepository.findAll();
//        for (Aluno a:lstAlu){
//            if(a.getDataCadastro()!=null){
//            a.setDataCadastro(LocalDate.now());
//            }
//            try{
//                alunoRepository.save(a);
//            }catch(Exception e){
//                System.out.println("Erro ao cadaastrar");
//            }
//        }
//        lstDisc  =   disciplinaRepository.findAll();
//        for (Disciplina a:lstDisc){
//            a.setSemestral(true);
//            try{
//                disciplinaRepository.save(a);
//            }catch(Exception e){
//                System.out.println("Erro ao cadaastrar");
//            }
//        }        
//        lstDep.add(new Departamento("DEINFO","Departamento de Informatica"));
//        lstDep.add((new Departamento("SCATE","Setor de Ciencia Agrariaas e de Tecnologia")));
//        lstDep.add((new Departamento("NTI","Nucleo de Tecnologia")));
//        
//        for(Departamento d:lstDep){
//            departamentoRepository.insert(d);
//        }
//        
//        lstTurno.add(new Turno("Matutino",'M'));
//        lstTurno.add(new Turno("Vespertino",'V'));
//        lstTurno.add(new Turno("Noturno",'N'));
//        lstTurno.add(new Turno("Integral",'I'));
//        for (Turno a:lstTurno){
//          
//            try{
//                turnoRepository.insert(a);
//            }catch(Exception e){
//                System.out.println("Erro ao cadaastrar");
//            }
//        }        
//        lstDep.add(new Departamento("DEINFO","Departamento de Informatica"));
//        lstDep.add((new Departamento("SCATE","Setor de Ciencia Agrariaas e de Tecnologia")));
//        lstDep.add((new Departamento("NTI","Nucleo de Tecnologia")));
//        
//        for(Departamento d:lstDep){
//            departamentoRepository.insert(d);
//        }
//       
//          LocalDate dtTeste = LocalDate.parse("16/08/1999",df);
//        System.out.println(df.format(dtTeste));
//        lstMatricula.add(new Matricula(disciplinaRepository.findByCodigo("123"),70,75,0,2));
//        lstMatricula.add(new Matricula(disciplinaRepository.findByCodigo("12345"),70,75,0,2));

//        aluno   =   new Aluno("Jussara","email@email.com","21313231",cidadeRepository.findByNome("PORTO AMAZONAS"),lstMatricula);
//        alunoRepository.save(aluno);
//        for (Matricula m:lstMatricula){
//            System.out.println(m.getDisciplina());
//            System.out.println(m.getMedia());
//            System.out.println(m.getStatus());
//            System.out.println("----------------------");
//            
//        }
        
      
//uf = new Uf("Acre", "AC");;;;
//        ufRepository.save(uf);
//        uf = new Uf("Alagoas", "AL");
//        ufRepository.save(uf);
//        uf = new Uf("Amapá", "AP");
//        ufRepository.save(uf);
//        uf = new Uf("Amazonas", "AM");
//        ufRepository.save(uf);
//        uf = new Uf("Bahia", "BA");
//        ufRepository.save(uf);
//        uf = new Uf("Ceará", "CE");
//        ufRepository.save(uf);
//        uf = new Uf("Distrito Federal", "DF");
//        ufRepository.save(uf);
//        uf = new Uf("Espírito Santo", "ES");
//        ufRepository.save(uf);
//        uf = new Uf("Goiás", "GO");
//        ufRepository.save(uf);
//        uf = new Uf("Maranhão", "MA");
//        ufRepository.save(uf);
//        uf = new Uf("Mato Grosso", "MT");
//        ufRepository.save(uf);
//        
//        uf = new Uf("Mato Grosso do Sul", "MS");
//        ufRepository.save(uf);
//        uf = new Uf("Minas Gerais", "MG");
//        ufRepository.save(uf);
//        uf = new Uf("Pará", "PA");
//        ufRepository.save(uf);
//        uf = new Uf("Paraíba", "PB");
//        ufRepository.save(uf);
//        uf = new Uf("Paraná", "PR");
//        ufRepository.save(uf);
//        uf = new Uf("Pernambuco", "PE");
//        ufRepository.save(uf);
//        uf = new Uf("Piauí", "PI");
//        ufRepository.save(uf);
//        uf = new Uf("Rio de Janeiro", "RJ");
//        ufRepository.save(uf);
//        uf = new Uf("Rio Grande do Norte", "RN");
//        ufRepository.save(uf);
//        uf = new Uf("Rio Grande do Sul", "RS");
//        ufRepository.save(uf);
//        
//        uf = new Uf("Rondônia", "RO");
//        ufRepository.save(uf);
//        uf = new Uf("Roraima", "RR");
//        ufRepository.save(uf);
//        uf = new Uf("Santa Catarina", "SC");
//        ufRepository.save(uf);
//        uf = new Uf("São Paulo", "SP");
//        ufRepository.save(uf);
//        uf = new Uf("Sergipe", "SE");
//        ufRepository.save(uf);
//        uf = new Uf("Tocantins", "TO");
//        ufRepository.save(uf);
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
         /**
         * Encontra as Siglas na tabela uf e cria um link com a cidade
         */
//        uf =  ufRepository.findBySiglaLikeIgnoreCase("PR");;
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
