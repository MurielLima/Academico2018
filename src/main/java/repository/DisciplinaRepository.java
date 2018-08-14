package repository;

import java.util.List;
import model.Disciplina;
//import model.Professor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DisciplinaRepository extends MongoRepository<Disciplina, String> {
    
//        public Integer countByProfessor(Professor professor);
    public Disciplina findByCodigo(String codigo);
    public void deleteByCodigo(String codigo);
    public List<Disciplina> findByNomeLikeIgnoreCase(String nome);
    
}
