package com.sciencelk.services;

import com.sciencelk.dataaccess.entities.Article;
import com.sciencelk.dataaccess.entities.User;
import com.sciencelk.dataaccess.exceptions.ItemNotFoundException;
import com.sciencelk.dto.ArticleDetails;
import com.sciencelk.dto.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * * 
 * Created by SasithaG on 6/16/2015.
 */
@Service
public class ArticleService implements ArticleServiceInterface{
    
    @Autowired
    private ArticleRepositoryInterface articleRepository;
    
    @Autowired
    private UserRepositoryInterface userRepository;
    
    @Autowired
    private CategoryRepositoryInterface categoryRepository;

    @Transactional
    @Override
    public List<ArticleDetails> getAllArticles() throws ItemNotFoundException {
        List<ArticleDetails> articleDetailsList = new ArrayList<ArticleDetails>();
        for (Article article : articleRepository.getAll()){
            articleDetailsList.add(getArticleDetailsObject(article));
        }
        return articleDetailsList;
    }

    @Transactional
    @Override
    public List<ArticleDetails> getArticleByCategory(int categoryId, int page, int pageSize) throws ItemNotFoundException {
        List<ArticleDetails> articleDetailsList  = new ArrayList<ArticleDetails>();

        if (categoryId > 1) {
            for (Article article : articleRepository.getArticleByCategory(categoryRepository.findById(categoryId), page, pageSize)){
                articleDetailsList.add(getArticleDetailsObject(article));
            }
        } else {
            for (Article article : articleRepository.getMostRecent(page, pageSize)){
                articleDetailsList.add(getArticleDetailsObject(article));
            }
        }
        return articleDetailsList;
    }

    @Transactional
    @Override
    public ArticleDetails addNewArticle(ArticleDetails newArticle) {
        try {
            Article article = getArticleObject(newArticle);
            articleRepository.saveOrUpdate(article);
            return getArticleDetailsObject(article);
        } catch (ItemNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    @Override
    public ArticleDetails getArticleById(int articleId) throws ItemNotFoundException{
        return getArticleDetailsObject(articleRepository.findById(articleId));
    }

    private ArticleDetails getArticleDetailsObject(Article article){
        ArticleDetails articleDetails = new ArticleDetails();
        articleDetails.setId(article.getId());
        if (article.getUserByAuthor() != null) {
            articleDetails.setAuthor(getUserDetails(article.getUserByAuthor()));
        }
        if (article.getUserByModerator() != null) {
            articleDetails.setModerator(getUserDetails(article.getUserByModerator()));
        }
        articleDetails.setBody(article.getContent());
        articleDetails.setTitle(article.getTitle());
        articleDetails.setIntro(article.getIntroduction());
        articleDetails.setHitCount(article.getHits());
        articleDetails.setCategory(article.getCategory().getId());
        return articleDetails;
    }
    private UserDetails getUserDetails(User user){
        UserDetails userDetails = new UserDetails();
        userDetails.setNickName(user.getName());
        userDetails.setUserName(user.getName());
        userDetails.setUserId(user.getId());
        return userDetails;
    }
    
    private Article getArticleObject(ArticleDetails details) throws ItemNotFoundException{
        Article article = new Article();
        article.setCategory(categoryRepository.findById(details.getCategory()));
        article.setTitle(details.getTitle());
        article.setIntroduction(details.getIntro());
        article.setContent(details.getBody());
        article.setHits(0);
        return article;
    }
}
