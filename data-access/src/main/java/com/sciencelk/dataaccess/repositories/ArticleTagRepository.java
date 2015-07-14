package com.sciencelk.dataaccess.repositories;

import com.sciencelk.dataaccess.common.AbstractDAO;
import com.sciencelk.dataaccess.entities.ArticleHasTag;
import com.sciencelk.dataaccess.entities.ArticleHasTagId;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * * 
 * Created by SasithaG on 6/10/2015.
 */
@Transactional
@Repository
public class ArticleTagRepository extends AbstractDAO<ArticleHasTag, ArticleHasTagId> implements ArticleTagRepositoryInterface{
    
    public ArticleTagRepository(){
        super(ArticleHasTag.class);        
    }
}
