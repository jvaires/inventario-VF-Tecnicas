package br.edu.uni7.tecnicas.services;

import br.edu.uni7.tecnicas.entities.Commit;
import br.edu.uni7.tecnicas.repositories.CommitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommitService {

    @Autowired
    private CommitRepository commitRepository;

    public Optional<Commit> getCommitById(String commitId)
    {
        return commitRepository.findById(commitId);
    }

    public List<Commit> getCommits() {
        return commitRepository.findAll();
    }

    public Commit saveCommit(Commit commit)
    {
        return commitRepository.save(commit);
    }

    public void deleteCommit(String commitId)
    {
        commitRepository.deleteById(commitId);
    }
}
