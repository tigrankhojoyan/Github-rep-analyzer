package com.git.repo.analyzer.services;

import com.git.repo.analyzer.model.GitRepository;
import com.git.repo.analyzer.model.GitRepositoryDetails;

import java.util.Map;

public interface GithubRepositoryAnalyzerService {

    /**
     * Retrieves contributors and commits data of provided
     * repository from Github API
     *
     * @param gitRepository
     * @return
     */
    GitRepositoryDetails retrieveRepositoryDetails(GitRepository gitRepository);

    /**
     * Retrieves the last 100 commits of repository,
     * groups by name and sorts by descending order
     *
     * @param gitRepositoryDetails
     * @return
     */
    Map<String, Integer> retrieveCommitsCountByName(GitRepositoryDetails gitRepositoryDetails);
}
