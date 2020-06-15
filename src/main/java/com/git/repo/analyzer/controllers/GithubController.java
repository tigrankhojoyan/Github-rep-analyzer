package com.git.repo.analyzer.controllers;

import com.git.repo.analyzer.model.GitRepository;
import com.git.repo.analyzer.model.GitRepositoryDetails;
import com.git.repo.analyzer.services.GithubRepositoryAnalyzerService;
import com.git.repo.analyzer.services.GithubRepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.invoke.MethodHandles;
import java.util.Map;

/**
 * Controller intended to retrieve data from 'GitHub' rest api(https://api.github.com)
 */
@Controller
public class GithubController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final GithubRepositoryService githubRepositoryService;
    private final GithubRepositoryAnalyzerService analyzerService;

    public GithubController(GithubRepositoryService githubRepositoryService,
                            GithubRepositoryAnalyzerService analyzerService) {
        this.githubRepositoryService = githubRepositoryService;
        this.analyzerService = analyzerService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam("query") String query,
                         @RequestParam(name = "page", defaultValue = "1") Integer page,
                         final Model model) {
        LOGGER.info("Searching for {} repository in Github", query);
        if (page > 100) {
            model.addAttribute("errorMessage", "Only the first 1000 search results are available");
            return "index";
        } else {
            if(page < 0) {
                page = 1;
            }
        }

        model.addAttribute("repos", this.githubRepositoryService.retrieveRepositoriesByName(query, page));
        model.addAttribute("query", query);
        return "index";
    }

    @PostMapping("/repodetails")
    public String getAnalytics(@ModelAttribute GitRepository githubRepo, final Model model) {
        LOGGER.info("Retrieving details of {} repository", githubRepo.getName());
        GitRepositoryDetails repositoryDetails = analyzerService.retrieveRepositoryDetails(githubRepo);
        Map<String, Integer> last100CommitsByName = analyzerService.retrieveCommitsCountByName(repositoryDetails);
        model.addAttribute("repositoryDetails", repositoryDetails);
        model.addAttribute("commitsByName", last100CommitsByName);
        return "repoDetails";
    }

}
