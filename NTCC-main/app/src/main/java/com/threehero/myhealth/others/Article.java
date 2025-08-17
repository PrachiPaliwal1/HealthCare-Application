package com.threehero.myhealth.others;

// Article.java
public class Article {
    private String headline;
    private String author;
    private String url;

    public Article(String headline, String author, String url) {
        this.headline = headline;
        this.author = author;
        this.url = url;
    }

    public String getHeadline() {
        return headline;
    }

    public String getAuthor() {
        return author;
    }

    public String getUrl() {
        return url;
    }
}
