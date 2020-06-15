package com.git.repo.analyzer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GitRepositoryCommit {
    private Commit commit;
    private Owner author;
    private Owner committer;

    public GitRepositoryCommit() {
    }

    public GitRepositoryCommit(Commit commit, Owner author, Owner committer) {
        this.commit = commit;
        this.author = author;
        this.committer = committer;
    }

    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }

    public Owner getAuthor() {
        return author;
    }

    public void setAuthor(Owner author) {
        this.author = author;
    }

    public Owner getCommitter() {
        return committer;
    }

    public void setCommitter(Owner committer) {
        this.committer = committer;
    }

    @Override
    public String toString() {
        return "GitRepositoryCommit{" +
                "commit=" + commit +
                ", author=" + author +
                ", committer=" + committer +
                '}';
    }
}
