package com.git.repo.analyzer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Owner {
    private Integer id;
    private String name;
    private String avatarUri;

    public Owner() {
    }

    public Owner(Integer id, String name, String avatarUri) {
        this.id = id;
        this.name = name;
        this.avatarUri = avatarUri;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @JsonProperty("login")
    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUri() {
        return avatarUri;
    }

    @JsonProperty("avatar_url")
    public void setAvatarUri(String avatarUri) {
        this.avatarUri = avatarUri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return Objects.equals(id, owner.id) &&
                Objects.equals(name, owner.name) &&
                Objects.equals(avatarUri, owner.avatarUri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, avatarUri);
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", avatarUri='" + avatarUri + '\'' +
                '}';
    }
}