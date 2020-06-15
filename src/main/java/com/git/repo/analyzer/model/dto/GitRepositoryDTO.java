package com.git.repo.analyzer.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.git.repo.analyzer.model.GitRepository;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GitRepositoryDTO {
    private Integer totalCount;
    private Integer currentPage;
    private Integer totalPagesCount;

    public GitRepositoryDTO() {
    }

    public GitRepositoryDTO(Integer totalCount, Integer currentPage, Integer totalPagesCount, List<GitRepository> repositories) {
        this.totalCount = totalCount;
        this.currentPage = currentPage;
        this.totalPagesCount = totalPagesCount;
        this.repositories = repositories;
    }

    private List<GitRepository> repositories;

    public List<GitRepository> getRepositories() {
        return repositories;
    }

    @JsonProperty("items")
    public void setRepositories(List<GitRepository> repositories) {
        this.repositories = repositories;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    @JsonProperty("total_count")
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPagesCount() {
        return totalPagesCount;
    }

    public void setTotalPagesCount(Integer totalPagesCount) {
        this.totalPagesCount = totalPagesCount;
    }

    @Override
    public String toString() {
        return "GitRepositoryDTO{" +
                "totalCount=" + totalCount +
                ", currentPage=" + currentPage +
                ", totalPagesCount=" + totalPagesCount +
                ", repositories=" + repositories +
                '}';
    }
}
