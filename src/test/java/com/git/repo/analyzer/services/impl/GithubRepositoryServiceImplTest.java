package com.git.repo.analyzer.services.impl;

import com.git.repo.analyzer.model.Contributor;
import com.git.repo.analyzer.model.GitRepositoryCommit;
import com.git.repo.analyzer.model.dto.GitRepositoryDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
class GithubRepositoryServiceImplTest {

    @Value("${git.repos.search}")
    public String searchPath;

    @Value("${git.repos.contributors}")
    private  String contributorsPath;

    @Value("${git.repos.commits}")
    private String commitsPath;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private GithubRepositoryServiceImpl repositoryService;

    @Test
    public void  retrieveRepositoriesByNameTest() {
        GitRepositoryDTO repositoryDTO = new GitRepositoryDTO(20, 1, 2, new ArrayList<>());
        Mockito.lenient()
                .when(restTemplate.exchange(
                        searchPath, HttpMethod.GET, null, GitRepositoryDTO.class, "repoName", 2))
          .thenReturn(new ResponseEntity(repositoryDTO, HttpStatus.OK));

        GitRepositoryDTO responseEntity = repositoryService.retrieveRepositoriesByName("repoName", 2);

        assertNotNull(responseEntity);
        assertEquals(repositoryDTO.getTotalCount(), responseEntity.getTotalCount());
    }

    @Test
    @Disabled
    public void retrieveContributorsOfRepositoryTest() {
        Contributor contributor1 = new Contributor("aa bb", "ua.com", 30, "vvv.uua");
        Contributor contributor2 = new Contributor("aab bbb", "uaa.com", 10, "vvv.uuaop");
        List<Contributor> contributors = new ArrayList<>(Arrays.asList(contributor1, contributor2));
        Mockito.lenient()
                .when(restTemplate.exchange(
                        contributorsPath,
                        HttpMethod.GET,
                        null,
//                        eq(new ParameterizedTypeReference<List<Contributor>>() {}),
                        isA(GithubRepositoryServiceImpl.ContributorParametrizedType.class),
                        "ownerId", "arepoa"))
                .thenReturn(new ResponseEntity(contributors, HttpStatus.OK));
        List<Contributor> response = repositoryService.retrieveContributorsOfRepository("ownerId", "arepoa");
        assertNotNull(response);
        assertEquals(2, response.size());
    }

    @Test
    @Disabled
    public void retrieveRepositoryCommitsTest() {
        GitRepositoryCommit commit1 = new GitRepositoryCommit();
        GitRepositoryCommit commit2 = new GitRepositoryCommit();
        List<GitRepositoryCommit> commits = new ArrayList<>(Arrays.asList(commit1, commit2));
        Mockito.lenient()
                .when(restTemplate.exchange(
                        commitsPath,
                        HttpMethod.GET,
                        null,
//                        eq(new ParameterizedTypeReference<List<Contributor>>() {}),
                        eq(GithubRepositoryServiceImpl.ContributorParametrizedType.class),
                        "ownerId", "arepoa"))
                .thenReturn(new ResponseEntity(commits, HttpStatus.OK));
        List<GitRepositoryCommit> response = repositoryService.retrieveRepositoryCommits("ownerId", "arepoa");
        assertNotNull(response);
        assertEquals(2, response.size());
    }

}