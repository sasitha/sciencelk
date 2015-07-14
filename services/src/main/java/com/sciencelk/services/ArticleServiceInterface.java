package com.sciencelk.services;

import com.sciencelk.dataaccess.exceptions.ItemNotFoundException;
import com.sciencelk.dto.ArticleDetails;

import java.util.List;

/**
 * *
 * Created by SasithaG on 6/16/2015.
 */
public interface ArticleServiceInterface {
    
    List<ArticleDetails> getAllArticles() throws ItemNotFoundException;
    
    List<ArticleDetails> getArticleByCategory(int categoryId, int page, int pageSize) throws ItemNotFoundException;
    
    ArticleDetails addNewArticle(ArticleDetails newArticle);
    
    ArticleDetails getArticleById(int articleId) throws ItemNotFoundException;

}
