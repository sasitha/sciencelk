package com.sciencelk.dataaccess.repositories;

import com.sciencelk.dataaccess.common.AbstractDAO;
import com.sciencelk.dataaccess.entities.Article;
import com.sciencelk.dataaccess.entities.Category;
import com.sciencelk.dataaccess.exceptions.ItemNotFoundException;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * * 
 * Created by SasithaG on 6/10/2015.
 */
@Transactional
@Repository
public class ArticleRepository extends AbstractDAO<Article, Integer> implements ArticleRepositoryInterface {
    
    public ArticleRepository(){
        super(Article.class);
    }

    @Transactional
    @Override
    public List<Article> getArticleByCategory(Category category, int page, int pageZie) throws ItemNotFoundException {
        Criteria criteria = getCurrentSession().createCriteria(Article.class);
        criteria.add(Restrictions.eq("category", category));
        criteria.setMaxResults(pageZie);
        criteria.setFirstResult((page-1)*pageZie);
        
        return criteria.list();
    }

    @Transactional
    @Override
    public List<Article> getMostRecent(int page, int pageZie) throws ItemNotFoundException {
        Criteria criteria = getCurrentSession().createCriteria(Article.class);
        criteria.addOrder(Order.desc("publishedDate"));
        criteria.setMaxResults(pageZie);
        criteria.setFirstResult((page-1)*pageZie);
        
        return criteria.list();
    }
}
