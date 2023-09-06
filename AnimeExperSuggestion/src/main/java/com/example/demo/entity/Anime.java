package com.example.demo.entity;

public class Anime {

    private String genres;
    private String format;
    private String episodes;
    private String rating;

    public Anime() {
    }

    public Anime(String genres, String format, String episodes, String rating) {
        this.genres = genres;
        this.format = format;
        this.episodes = episodes;
        this.rating = rating;
    }

    public Anime(String format, String episodes, String rating) {
        this.format = format;
        this.episodes = episodes;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Anime{" +
                "genres=" + genres +
                ", format='" + format + '\'' +
                ", episodes=" + episodes +
                ", rating=" + rating +
                '}';
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getEpisodes() {
        return episodes;
    }

    public void setEpisodes(String episodes) {
        this.episodes = episodes;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}

