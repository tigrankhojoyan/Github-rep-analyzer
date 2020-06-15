package com.git.repo.analyzer.services.impl;

import com.git.repo.analyzer.model.Contributor;
import com.git.repo.analyzer.model.GitRepositoryCommit;
import com.git.repo.analyzer.model.dto.GitRepositoryDTO;
import com.git.repo.analyzer.services.GithubRepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.invoke.MethodHandles;
import java.util.List;

@Service
public class GithubRepositoryServiceImpl implements GithubRepositoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final String searchPath;
    private final String contributorsPath;
    private final String commitsPath;
    private final RestTemplate restTemplate;

    public GithubRepositoryServiceImpl(@Value("${git.repos.search}") String searchPath,
                                       @Value("${git.repos.contributors}") String contributorsPath,
                                       @Value("${git.repos.commits}") String commitsPath, RestTemplate restTemplate) {
        this.searchPath = searchPath;
        this.contributorsPath = contributorsPath;
        this.commitsPath = commitsPath;
        this.restTemplate = restTemplate;
    }

    /**
     * @see GithubRepositoryService#retrieveRepositoriesByName
     */
    @Override
    public GitRepositoryDTO retrieveRepositoriesByName(String repoName, Integer page) {
        LOGGER.info("Retrieve repositories from Github by name(paged data)");
        ResponseEntity<GitRepositoryDTO> responseEntity =
                restTemplate.exchange(searchPath, HttpMethod.GET, null, GitRepositoryDTO.class, repoName, page);
        GitRepositoryDTO responseBody = responseEntity.getBody();
        int totalPages = 1;
        if (responseBody.getTotalCount() > 10) {
            totalPages = responseBody.getTotalCount() / 10 + (responseBody.getTotalCount() % 10 == 0 ? 0 : 1);
        }
        responseBody.setTotalPagesCount(totalPages);
        responseBody.setCurrentPage(page);

        return responseBody;
    }

    /**
     * @see GithubRepositoryService#retrieveContributorsOfRepository
     */
    @Override
    public List<Contributor> retrieveContributorsOfRepository(String ownerId, String repositoryName) {
        LOGGER.info("Get contributors of {} repository", repositoryName);
        ResponseEntity<List<Contributor>> repoContributors = restTemplate.exchange(contributorsPath, HttpMethod.GET, null,
                new ContributorParametrizedType(), ownerId, repositoryName);
        return repoContributors.getBody();
    }

    /**
     * @see GithubRepositoryService#retrieveRepositoryCommits
     */
    @Override
    public List<GitRepositoryCommit> retrieveRepositoryCommits(String ownerId, String repositoryName) {
        LOGGER.info("Get commits of {} repository", repositoryName);
        ResponseEntity<List<GitRepositoryCommit>> repoCommits = restTemplate.exchange(commitsPath, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<GitRepositoryCommit>>() {
                }, ownerId, repositoryName);
        return repoCommits.getBody();
    }

    public static class ContributorParametrizedType extends ParameterizedTypeReference<List<Contributor>> {

    }
}
