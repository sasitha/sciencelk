package com.sciencelk.dataaccess.entities;
// Generated Jul 13, 2015 9:30:45 PM by Hibernate Tools 4.3.1


import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Article generated by hbm2java
 */
@Entity
@Table(name="article"
)
public class Article  implements java.io.Serializable {


     private Integer id;
     private Category category;
     private User userByAuthor;
     private User userByModerator;
     private String title;
     private String content;
     private String introduction;
     private int hits;
     private Date publishedDate;
     private Date acceptedDate;
     private Set<ArticleHasTag> articleHasTags = new HashSet<ArticleHasTag>(0);

    public Article() {
    }

	
    public Article(Category category, String title, String content, String introduction, int hits) {
        this.category = category;
        this.title = title;
        this.content = content;
        this.introduction = introduction;
        this.hits = hits;
    }
    public Article(Category category, User userByAuthor, User userByModerator, String title, String content, String introduction, int hits, Date publishedDate, Date acceptedDate, Set<ArticleHasTag> articleHasTags) {
       this.category = category;
       this.userByAuthor = userByAuthor;
       this.userByModerator = userByModerator;
       this.title = title;
       this.content = content;
       this.introduction = introduction;
       this.hits = hits;
       this.publishedDate = publishedDate;
       this.acceptedDate = acceptedDate;
       this.articleHasTags = articleHasTags;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="ID", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CATEGORY", nullable=false)
    public Category getCategory() {
        return this.category;
    }
    
    public void setCategory(Category category) {
        this.category = category;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="AUTHOR")
    public User getUserByAuthor() {
        return this.userByAuthor;
    }
    
    public void setUserByAuthor(User userByAuthor) {
        this.userByAuthor = userByAuthor;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="MODERATOR")
    public User getUserByModerator() {
        return this.userByModerator;
    }
    
    public void setUserByModerator(User userByModerator) {
        this.userByModerator = userByModerator;
    }

    
    @Column(name="TITLE", nullable=false, length=60)
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    
    @Column(name="CONTENT", nullable=false)
    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    
    @Column(name="INTRODUCTION", nullable=false)
    public String getIntroduction() {
        return this.introduction;
    }
    
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    
    @Column(name="HITS", nullable=false)
    public int getHits() {
        return this.hits;
    }
    
    public void setHits(int hits) {
        this.hits = hits;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="PUBLISHED_DATE", length=19)
    public Date getPublishedDate() {
        return this.publishedDate;
    }
    
    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="ACCEPTED_DATE", length=19)
    public Date getAcceptedDate() {
        return this.acceptedDate;
    }
    
    public void setAcceptedDate(Date acceptedDate) {
        this.acceptedDate = acceptedDate;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="article")
    public Set<ArticleHasTag> getArticleHasTags() {
        return this.articleHasTags;
    }
    
    public void setArticleHasTags(Set<ArticleHasTag> articleHasTags) {
        this.articleHasTags = articleHasTags;
    }




}

