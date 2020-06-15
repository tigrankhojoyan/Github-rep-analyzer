package com.git.repo.analyzer.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Commit {

    private Author author;
    private Committer committer;

    public Commit() {
    }

    public Commit(Author author, Committer committer) {
        this.author = author;
        this.committer = committer;
    }

    public static class Author {
        private String name;
        private String email;
        private Date date;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date
                                    date) {
            this.date = date;
        }

        @Override
        public String toString() {
            return "Author{" +
                    "name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", date='" + date + '\'' +
                    '}';
        }
    }

    public static class Committer {
        private String name;
        private String email;
        private String date;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        @Override
        public String toString() {
            return "Committer{" +
                    "name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", date='" + date + '\'' +
                    '}';
        }
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Committer getCommitter() {
        return committer;
    }

    public void setCommitter(Committer committer) {
        this.committer = committer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commit commit = (Commit) o;
        return Objects.equals(author, commit.author) &&
                Objects.equals(committer, commit.committer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, committer);
    }

    @Override
    public String toString() {
        return "Commit{" +
                "author=" + author +
                ", committer=" + committer +
                '}';
    }
}
