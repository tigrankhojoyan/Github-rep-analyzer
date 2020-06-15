package com.git.repo.analyzer.services.impl;

import com.git.repo.analyzer.model.GitRepositoryDetails;
import com.git.repo.analyzer.repository.GitRepositoryDetailsRepository;
import com.git.repo.analyzer.services.GitRepositoryDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GitRepositoryDetailsServiceImpl implements GitRepositoryDetailsService<GitRepositoryDetails> {

    private final GitRepositoryDetailsRepository gitRepositoryDetailsRepository;

    public GitRepositoryDetailsServiceImpl(GitRepositoryDetailsRepository gitRepositoryDetailsRepository) {
        this.gitRepositoryDetailsRepository = gitRepositoryDetailsRepository;
    }

    @Override
    public void save(GitRepositoryDetails repositoryDetails) {
        if(gitRepositoryDetailsRepository != null) {
            gitRepositoryDetailsRepository.save(repositoryDetails);
        }
    }

    @Override
    public List<GitRepositoryDetails> getAllGitRepositoryDetails() {
        return gitRepositoryDetailsRepository.findAll();
    }

    @Override
    public GitRepositoryDetails findById(String id) {
        return gitRepositoryDetailsRepository.findById(id).orElse(null);
    }

    @Override
    public GitRepositoryDetails findRepositoryByOwner(String owner) {
        return gitRepositoryDetailsRepository.findFirstByGitRepository_Owner_Name(owner);
    }

    @Override
    public void deleteRepositoryDetailsById(String id) {
        gitRepositoryDetailsRepository.deleteById(id);
    }
}
