package com.git.repo.analyzer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Contributor {
    private String contributorName;
    private String avatarUrl;
    private String repositoriesUri;
    private Integer contributions;


    public Contributor(String contributorName, String avatarUrl, Integer contributions, String repositoriesUri) {
        this.contributorName = contributorName;
        this.avatarUrl = avatarUrl;
        this.contributions = contributions;
        this.repositoriesUri = repositoriesUri;
    }

    public Contributor() {
    }

    public String getContributorName() {
        return contributorName;
    }

    @JsonProperty("login")
    public void setContributorName(String contributorName) {
        this.contributorName = contributorName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    @JsonProperty("avatar_url")
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Integer getContributions() {
        return contributions;
    }

    public void setContributions(Integer contributions) {
        this.contributions = contributions;
    }

    public String getRepositoriesUri() {
        return repositoriesUri;
    }

    @JsonProperty("html_url")
    public void setRepositoriesUri(String repositoriesUri) {
        this.repositoriesUri = repositoriesUri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contributor that = (Contributor) o;
        return Objects.equals(contributorName, that.contributorName) &&
                Objects.equals(avatarUrl, that.avatarUrl) &&
                Objects.equals(repositoriesUri, that.repositoriesUri) &&
                Objects.equals(contributions, that.contributions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contributorName, avatarUrl, repositoriesUri, contributions);
    }

    @Override
    public String
    toString() {
        return "Contributor{" +
                "contributorName='" + contributorName + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", repositoriesUri='" + repositoriesUri + '\'' +
                ", contributions=" + contributions +
                '}';
    }
}
