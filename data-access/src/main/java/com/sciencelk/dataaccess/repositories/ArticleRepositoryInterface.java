package com.sciencelk.dataaccess.repositories;

import com.sciencelk.dataaccess.common.DAOInterface;
import com.sciencelk.dataaccess.entities.Article;
import com.sciencelk.dataaccess.entities.Category;
import com.sciencelk.dataaccess.exceptions.ItemNotFoundException;

import java.util.List;

/**
 * * 
 * Created by SasithaG on 6/10/2015.
 */
public interface ArticleRepositoryInterface extends DAOInterface<Article, Integer>{

    List<Article> getArticleByCategory(Category category, int page, int pageZie) throws ItemNotFoundException;
    
    List<Article> getMostRecent(int page, int pageZie) throws ItemNotFoundException;
}
