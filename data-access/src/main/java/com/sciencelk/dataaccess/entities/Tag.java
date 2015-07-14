package com.sciencelk.dataaccess.entities;
// Generated Jul 13, 2015 9:30:45 PM by Hibernate Tools 4.3.1


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Tag generated by hbm2java
 */
@Entity
@Table(name="tag"
)
public class Tag  implements java.io.Serializable {


     private Integer id;
     private String name;
     private Set<ArticleHasTag> articleHasTags = new HashSet<ArticleHasTag>(0);

    public Tag() {
    }

	
    public Tag(String name) {
        this.name = name;
    }
    public Tag(String name, Set<ArticleHasTag> articleHasTags) {
       this.name = name;
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

    
    @Column(name="NAME", nullable=false, length=45)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="tag")
    public Set<ArticleHasTag> getArticleHasTags() {
        return this.articleHasTags;
    }
    
    public void setArticleHasTags(Set<ArticleHasTag> articleHasTags) {
        this.articleHasTags = articleHasTags;
    }




}


