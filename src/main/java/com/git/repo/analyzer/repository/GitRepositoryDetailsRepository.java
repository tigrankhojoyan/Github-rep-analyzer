package com.git.repo.analyzer.repository;

import com.git.repo.analyzer.model.GitRepositoryDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GitRepositoryDetailsRepository extends MongoRepository<GitRepositoryDetails, String> {
    GitRepositoryDetails findFirstByGitRepository_Owner_Name(String ownerName);
}
