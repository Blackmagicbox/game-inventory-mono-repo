package com.backend.gameinventoryapi.catalog;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;


@Entity
public class Game {
    private @Id @GeneratedValue Long id;
    private String name;
    private String description;
    private String genre;
    private String platform;

    public Game() {

    }
    public Game(String name, String description, String genre, String platform) {
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.platform = platform;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPlatform() {
        return platform;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        return Objects.equals(id, game.id) &&
                Objects.equals(name, game.name) &&
                Objects.equals(description, game.description) &&
                Objects.equals(genre, game.genre) &&
                Objects.equals(platform, game.platform);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, genre, platform);
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", genre='" + genre + '\'' +
                ", platform='" + platform + '\'' +
                '}';
    }
}
