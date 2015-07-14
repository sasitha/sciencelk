package com.sciencelk.dataaccess.entities;
// Generated Jul 13, 2015 9:30:45 PM by Hibernate Tools 4.3.1


import javax.persistence.*;

/**
 * ArticleHasTag generated by hbm2java
 */
@Entity
@Table(name="article_has_tag"
)
public class ArticleHasTag  implements java.io.Serializable {


     private ArticleHasTagId id;
     private Article article;
     private Tag tag;

    public ArticleHasTag() {
    }

    public ArticleHasTag(ArticleHasTagId id, Article article, Tag tag) {
       this.id = id;
       this.article = article;
       this.tag = tag;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="articleId", column=@Column(name="ARTICLE_ID", nullable=false) ), 
        @AttributeOverride(name="tagId", column=@Column(name="TAG_ID", nullable=false) ) } )
    public ArticleHasTagId getId() {
        return this.id;
    }
    
    public void setId(ArticleHasTagId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ARTICLE_ID", nullable=false, insertable=false, updatable=false)
    public Article getArticle() {
        return this.article;
    }
    
    public void setArticle(Article article) {
        this.article = article;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TAG_ID", nullable=false, insertable=false, updatable=false)
    public Tag getTag() {
        return this.tag;
    }
    
    public void setTag(Tag tag) {
        this.tag = tag;
    }




}


