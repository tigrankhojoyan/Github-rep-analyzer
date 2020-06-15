package com.git.repo.analyzer.controllers;

import com.git.repo.analyzer.model.GitRepositoryDetails;
import com.git.repo.analyzer.services.GitRepositoryDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.MethodHandles;
import java.util.List;

/**
 * Controller intended to provide API to process with local data
 */
@RestController
@RequestMapping("/api/repodetails")
public class GitRepositoryDetailsRestApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final GitRepositoryDetailsService gitRepositoryDetailsService;

    public GitRepositoryDetailsRestApi(GitRepositoryDetailsService gitRepositoryDetailsService) {
        this.gitRepositoryDetailsService = gitRepositoryDetailsService;
    }

    @GetMapping("/getall")
    public List<GitRepositoryDetails> getAllRepositoryDetails() {
        LOGGER.info("Getting all repository details of");
        return gitRepositoryDetailsService.getAllGitRepositoryDetails();
    }

    @GetMapping("/owner/{owner}")
    public GitRepositoryDetails getRepositoryDetailsByOwnerId(@PathVariable String owner) {
        LOGGER.info("Getting repository details of {} owner ", owner);
        return gitRepositoryDetailsService.findRepositoryByOwner(owner);
    }

    @GetMapping("/{id}")
    public GitRepositoryDetails getRepositoryDetailsId(@PathVariable String id) {
        LOGGER.info("Getting repository details by {} id ", id);
        return gitRepositoryDetailsService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void removeRepositoryDetailsId(@PathVariable String id) {
        gitRepositoryDetailsService.deleteRepositoryDetailsById(id);
        LOGGER.info("The repository details by {} id has been deleted successfully", id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveRepositoryDetails(@RequestBody GitRepositoryDetails repositoryDetails) {
        gitRepositoryDetailsService.save(repositoryDetails);
        LOGGER.info("The {} repository details has been persisted successfully", repositoryDetails);
    }
}
