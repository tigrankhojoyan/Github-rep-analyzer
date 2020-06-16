package com.git.repo.analyzer.repository;

import com.git.repo.analyzer.model.GitRepository;
import com.git.repo.analyzer.model.GitRepositoryDetails;
import com.git.repo.analyzer.model.Owner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
class GitRepositoryDetailsRepositoryTest {

    @Autowired
    private GitRepositoryDetailsRepository gitRepositoryDetailsRepository;

    @Test
    public void testSave() {
        GitRepository gitRepository = new GitRepository.GitRepositoryBuilder()
                .withName("gitAnalyzer")
                .withUrl("aaa.bbb").withCloneUrl("bbb.aaa").withRepoDescription("analyzer for git").build();
        String uuid = UUID.randomUUID().toString();
        GitRepositoryDetails gitRepositoryDetails = new GitRepositoryDetails(uuid, gitRepository, new ArrayList<>(), new ArrayList<>());
        gitRepositoryDetailsRepository.save(gitRepositoryDetails);

        Optional<GitRepositoryDetails> persistedRepo = gitRepositoryDetailsRepository.findById(uuid);
        persistedRepo.orElseThrow(() -> new RuntimeException("empty data"));
        assertEquals(uuid, persistedRepo.get().getId());
    }

    @Test
    public void testFindByOwnerName() {
        Owner owner = new Owner(55998, "Aaa bbb", "avatar.com");
        GitRepository gitRepository = new GitRepository.GitRepositoryBuilder()
                .withName("gitAnalyzer").withOwner(owner)
                .withUrl("aaa.bbb").withCloneUrl("bbb.aaa").withRepoDescription("analyzer for git").build();
        String uuid = UUID.randomUUID().toString();
        GitRepositoryDetails gitRepositoryDetails = new GitRepositoryDetails(uuid, gitRepository, new ArrayList<>(), new ArrayList<>());
        gitRepositoryDetailsRepository.save(gitRepositoryDetails);

        GitRepositoryDetails persistedRepo = gitRepositoryDetailsRepository.findFirstByGitRepository_Owner_Name("Aaa bbb");
        assertNotNull(persistedRepo);
        assertEquals(uuid, persistedRepo.getId());
    }

}