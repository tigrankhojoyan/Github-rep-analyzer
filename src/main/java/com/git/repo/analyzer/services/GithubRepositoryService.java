package com.git.repo.analyzer.services;

import com.git.repo.analyzer.model.Contributor;
import com.git.repo.analyzer.model.GitRepository;
import com.git.repo.analyzer.model.GitRepositoryCommit;
import com.git.repo.analyzer.model.dto.GitRepositoryDTO;

import java.util.List;

public interface GithubRepositoryService {

    /**
     * Retrieves repository by name fro Github
     *
     * @param repoName
     * @return
     */
    GitRepositoryDTO retrieveRepositoriesByName(String repoName, Integer page);

    /**
     * Retrieves contributors list of repository
     *
     * @param ownerId
     * @param repositoryName
     * @return
     */
    List<Contributor> retrieveContributorsOfRepository(String ownerId, String repositoryName);

    /**
     * Retrieves commits of repository
     *
     * @param ownerId
     * @param repositoryName
     * @return
     */
    List<GitRepositoryCommit> retrieveRepositoryCommits(String ownerId, String repositoryName);

}
