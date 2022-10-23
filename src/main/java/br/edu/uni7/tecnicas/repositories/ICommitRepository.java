package br.edu.uni7.tecnicas.repositories;

import br.edu.uni7.tecnicas.entities.Commit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommitRepository extends JpaRepository<Commit, String> {
}
