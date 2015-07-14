package com.sciencelk.dto;

import java.util.List;

/**
 * * 
 * Created by SasithaG on 6/10/2015.
 */
public class ArticleDetails {
    
    private int id;
    private UserDetails author;
    private UserDetails moderator;
    private String title;
    private String body;
    private List<Tag> tags;
    private int hitCount;
    private String intro;
    private int category;
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserDetails getAuthor() {
        return author;
    }

    public void setAuthor(UserDetails author) {
        this.author = author;
    }

    public UserDetails getModerator() {
        return moderator;
    }

    public void setModerator(UserDetails moderator) {
        this.moderator = moderator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public int getHitCount() {
        return hitCount;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
