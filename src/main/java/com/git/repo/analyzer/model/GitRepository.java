package com.git.repo.analyzer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GitRepository {

    private String repoDescription;
    private String name;
    private String url;
    private String cloneUrl;
    private Owner owner;

    public GitRepository(String repoDescription, String name, String url, String cloneUrl, Owner owner) {
        this.repoDescription = repoDescription;
        this.name = name;
        this.url = url;
        this.cloneUrl = cloneUrl;
        this.owner = owner;
    }

    public GitRepository() {
    }

    public GitRepository(GitRepositoryBuilder gitRepositoryBuilder) {
        this.repoDescription = gitRepositoryBuilder.repoDescription;
        this.name = gitRepositoryBuilder.name;
        this.url = gitRepositoryBuilder.url;
        this.cloneUrl = gitRepositoryBuilder.cloneUrl;
        this.owner = gitRepositoryBuilder.owner;
    }

    public static class GitRepositoryBuilder {
        private String repoDescription;
        private String name;
        private String url;
        private String cloneUrl;
        private Owner owner;

        public GitRepositoryBuilder withRepoDescription(String repoDescription) {
            this.repoDescription = repoDescription;
            return this;
        }

        public GitRepositoryBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public GitRepositoryBuilder withUrl(String url) {
            this.url = url;
            return this;
        }

        public GitRepositoryBuilder withCloneUrl(String cloneUrl) {
            this.cloneUrl = cloneUrl;
            return this;
        }

        public GitRepositoryBuilder withOwner(Owner owner) {
            this.owner = owner;
            return this;
        }

        public GitRepository build() {
            return new GitRepository(this);
        }

    }

    public String getRepoDescription() {
        return repoDescription;
    }

    @JsonProperty("description")
    public void setRepoDescription(String repoDescription) {
        this.repoDescription = repoDescription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getUrl() {
        return url;
    }

    @JsonProperty("html_url")
    public void setUrl(String url) {
        this.url = url;
    }

    public String getCloneUrl() {
        return cloneUrl;
    }

    @JsonProperty("clone_url")
    public void setCloneUrl(String cloneUrl) {
        this.cloneUrl = cloneUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GitRepository that = (GitRepository) o;
        return Objects.equals(repoDescription, that.repoDescription) &&
                Objects.equals(name, that.name) &&
                Objects.equals(url, that.url) &&
                Objects.equals(cloneUrl, that.cloneUrl) &&
                Objects.equals(owner, that.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(repoDescription, name, url, cloneUrl, owner);
    }

    @Override
    public String toString() {
        return "GitRepository{" +
                "repoDescription='" + repoDescription + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", cloneUrl='" + cloneUrl + '\'' +
                ", owner=" + owner +
                '}';
    }
}
