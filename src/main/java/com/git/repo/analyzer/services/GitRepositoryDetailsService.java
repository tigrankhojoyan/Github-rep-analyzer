package com.git.repo.analyzer.services;

import com.git.repo.analyzer.model.GitRepositoryDetails;

import java.util.List;

/**
 * Service to provide CRUD operations for {@link GitRepositoryDetails} class
 * @param <T>
 */
public interface GitRepositoryDetailsService<T extends GitRepositoryDetails> {
    void save(T repositoryDetails);
    List<T> getAllGitRepositoryDetails();
    T findById(String id);
    T findRepositoryByOwner(String owner);
    void deleteRepositoryDetailsById(String id);
}
