package com.git.repo.analyzer.services.impl;

import com.git.repo.analyzer.model.*;
import com.git.repo.analyzer.services.GithubRepositoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GithubRepositoryAnalyzerServiceImplTest {

    @Mock
    private GithubRepositoryService githubRepositoryService;

    @InjectMocks
    private GithubRepositoryAnalyzerServiceImpl analyzerService;

    @Test
    public void retrieveRepositoryDetailsTest() {
        Contributor contributor = new Contributor("John Smith", "https://bb.com", 25, "https://myrepo.com");
        GitRepositoryCommit commit = new GitRepositoryCommit();
        given(githubRepositoryService.retrieveContributorsOfRepository("John Smith", "repo")).willReturn(Arrays.asList(contributor));
        given(githubRepositoryService.retrieveRepositoryCommits("John Smith", "repo")).willReturn(Arrays.asList(commit));
        Owner owner = new Owner(15, "John Smith", "aa.oooo");
        GitRepository gitRepository = new GitRepository.GitRepositoryBuilder().withRepoDescription("test app").withName("repo")
                .withCloneUrl("cloneurl").withUrl("aa.bbb").withOwner(owner).build();
//                new GitRepository("test app", "repo", "String url", "String cloneUrl", owner);

        GitRepositoryDetails gitRepositoryDetails = analyzerService.retrieveRepositoryDetails(gitRepository);

        assertNotNull(gitRepository);
        assertEquals(1, gitRepositoryDetails.getCommits().size());
        assertEquals(1, gitRepositoryDetails.getContributors().size());
    }

    @Test
    public void retrieveCommitsCountByNameTest() {
        GitRepositoryCommit commit1 = new GitRepositoryCommit(new Commit(), new Owner(), new Owner());
        commit1.getCommit().setAuthor(new Commit.Author());
        commit1.getCommit().getAuthor().setDate(new Date());
        commit1.getCommit().getAuthor().setName("John Smith");

        GitRepositoryCommit commit2 = new GitRepositoryCommit(new Commit(), new Owner(), new Owner());
        commit2.getCommit().setAuthor(new Commit.Author());
        commit2.getCommit().getAuthor().setDate(new Date());
        commit2.getCommit().getAuthor().setName("John Smith");

        GitRepositoryCommit commit3 = new GitRepositoryCommit(new Commit(), new Owner(), new Owner());
        commit3.getCommit().setAuthor(new Commit.Author());
        commit3.getCommit().getAuthor().setName("aa bb");
        commit3.getCommit().getAuthor().setDate(new Date());
        GitRepositoryDetails gitRepositoryDetails = new GitRepositoryDetails();
        gitRepositoryDetails.setCommits(new ArrayList<>(Arrays.asList(commit1, commit2, commit3)));

        Map<String, Integer> commitsCountByName = analyzerService.retrieveCommitsCountByName(gitRepositoryDetails);

        assertEquals(2, commitsCountByName.size());
        assertEquals(2, commitsCountByName.get("John Smith"));
        assertEquals(1, commitsCountByName.get("aa bb"));

    }


}