package repository;

import java.util.List;
import model.Cidade;
import model.Disciplina;

import model.Professor;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Murie
 */
public interface ProfessorRepository extends MongoRepository<Professor, String> {

    public Professor findByNome(String nome);
    public Professor findByCpf(String cpf);
    public int countByCpf(String cpf);

    public List<Professor> findByNomeLikeIgnoreCase(String nome);
    public List<Professor> findByNomeLikeIgnoreCaseOrEmailLikeIgnoreCaseOrCpf(String nome,String email,String cpf);
}
