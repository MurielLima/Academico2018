package repository;

import java.util.List;
import model.Cidade;

import model.Professor;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Murie
 */
public interface ProfessorRepository extends MongoRepository<Professor, String> {

    public Professor findByNome(String nome);

    public List<Professor> findByNomeLikeIgnoreCase(String nome);
}
