package com.git.repo.analyzer.controllers;

import com.git.repo.analyzer.model.GitRepositoryDetails;
import com.git.repo.analyzer.services.GitRepositoryDetailsService;
import com.git.repo.analyzer.services.GithubRepositoryAnalyzerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Map;

/**
 * Controller intended to show bookmarked repository data
 */
@Controller
@RequestMapping("/bookmarks")
public class GitReposBookmarksController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final GitRepositoryDetailsService gitRepositoryDetailsService;
    private final GithubRepositoryAnalyzerService analyzerService;

    public GitReposBookmarksController(GitRepositoryDetailsService gitRepositoryDetailsService, GithubRepositoryAnalyzerService analyzerService) {
        this.gitRepositoryDetailsService = gitRepositoryDetailsService;
        this.analyzerService = analyzerService;
    }

    @GetMapping
    public String getAllGitRepositoryDetails(final Model model) {
        LOGGER.info("Retrieving all bookmarked Github repositories");
        List<GitRepositoryDetails> repositoryDetails = gitRepositoryDetailsService.getAllGitRepositoryDetails();
        model.addAttribute("repositoryDetails", repositoryDetails);
        LOGGER.debug("Go to bookmarks page");
        return "bookmarks";
    }

    @GetMapping("/{id}")
    public String getAnGitRepositoryDetails(@PathVariable("id") String id, Model model) {
        LOGGER.debug("Retrieving repository by {} id", id);
        GitRepositoryDetails repositoryDetails = gitRepositoryDetailsService.findById(id);
        Map<String, Integer> last100CommitsByName = analyzerService.retrieveCommitsCountByName(repositoryDetails);
        model.addAttribute("repositoryDetails", repositoryDetails);
        model.addAttribute("commitsByName", last100CommitsByName);
        return "repoDetails";
    }
}
