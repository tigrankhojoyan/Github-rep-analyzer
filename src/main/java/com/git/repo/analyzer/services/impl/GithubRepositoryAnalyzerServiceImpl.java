package com.git.repo.analyzer.services.impl;

import com.git.repo.analyzer.model.Contributor;
import com.git.repo.analyzer.model.GitRepository;
import com.git.repo.analyzer.model.GitRepositoryCommit;
import com.git.repo.analyzer.model.GitRepositoryDetails;
import com.git.repo.analyzer.services.GithubRepositoryAnalyzerService;
import com.git.repo.analyzer.services.GithubRepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GithubRepositoryAnalyzerServiceImpl implements GithubRepositoryAnalyzerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final GithubRepositoryService githubRepositoryService;

    public GithubRepositoryAnalyzerServiceImpl(GithubRepositoryService githubRepositoryService) {
        this.githubRepositoryService = githubRepositoryService;
    }

    /**
     * @see GithubRepositoryAnalyzerService#retrieveRepositoryDetails
      */
    @Override
    public GitRepositoryDetails retrieveRepositoryDetails(GitRepository gitRepository) {
        LOGGER.info("Retrieving contributors and commits for {} repository", gitRepository.getName());
        List<Contributor> contributorsOfRepository = githubRepositoryService.retrieveContributorsOfRepository(gitRepository.getOwner().getName(),
                gitRepository.getName());
        List<GitRepositoryCommit> repositoryCommits = githubRepositoryService.retrieveRepositoryCommits(gitRepository.getOwner().getName(),
                gitRepository.getName());
        //Generates and sets UUID for future persistence
        GitRepositoryDetails gitRepositoryDetails = new GitRepositoryDetails(UUID.randomUUID().toString(), gitRepository, contributorsOfRepository, repositoryCommits);
        return gitRepositoryDetails;
    }

    /**
     * @see GithubRepositoryAnalyzerService#retrieveCommitsCountByName
     */
    @Override
    public Map<String, Integer> retrieveCommitsCountByName(GitRepositoryDetails gitRepositoryDetails) {
        LOGGER.info("Retrieving statistics of last 100 commits");
        //Map in order to keep commits count(k is name of committer, value is count of commits)
        Map<String, Integer> commitsCountByCommitters = new HashMap<>();
        List<GitRepositoryCommit> commits = gitRepositoryDetails.getCommits().stream().
                sorted(Comparator.comparing(o -> o.getCommit().getAuthor().getDate()))
                .limit(100).collect(Collectors.toList());
        for(GitRepositoryCommit commit : commits) {
            if(commitsCountByCommitters.containsKey(commit.getCommit().getAuthor().getName())) {
                commitsCountByCommitters.put(commit.getCommit().getAuthor().getName(),
                        commitsCountByCommitters.get(commit.getCommit().getAuthor().getName()) + 1);
            } else {
                commitsCountByCommitters.put(commit.getCommit().getAuthor().getName(), 1);
            }
        }
        //Sorts data by descending order
        return commitsCountByCommitters.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }
}
