package com.git.repo.analyzer.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document
public class GitRepositoryDetails {
    @Id
    private String id;

    @Indexed
    private GitRepository gitRepository;

    private List<Contributor> contributors;
    private List<GitRepositoryCommit> commits;

    public GitRepositoryDetails(String id, GitRepository gitRepository, List<Contributor> contributors, List<GitRepositoryCommit> commits) {
        this.id = id;
        this.gitRepository = gitRepository;
        this.contributors = contributors;
        this.commits = commits;
    }

    public GitRepositoryDetails() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GitRepository getGitRepository() {
        return gitRepository;
    }

    public void setGitRepository(GitRepository gitRepository) {
        this.gitRepository = gitRepository;
    }

    public List<Contributor> getContributors() {
        return contributors;
    }

    public void setContributors(List<Contributor> contributors) {
        this.contributors = contributors;
    }

    public List<GitRepositoryCommit> getCommits() {
        return commits;
    }

    public void setCommits(List<GitRepositoryCommit> commits) {
        this.commits = commits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GitRepositoryDetails that = (GitRepositoryDetails) o;
        return Objects.equals(gitRepository, that.gitRepository) &&
                Objects.equals(contributors, that.contributors) &&
                Objects.equals(commits, that.commits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gitRepository, contributors, commits);
    }

    @Override
    public String toString() {
        return "GitRepositoryDetails{" +
                "id='" + id + '\'' +
                ", gitRepository=" + gitRepository +
                ", contributors=" + contributors +
                ", commits=" + commits +
                '}';
    }
}
