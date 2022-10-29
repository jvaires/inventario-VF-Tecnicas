package br.edu.uni7.tecnicas.repository;
import br.edu.uni7.tecnicas.entities.Commit;
import org.springframework.data.repository.CrudRepository;

public interface CommitRepository extends CrudRepository<Commit, String> {
}
