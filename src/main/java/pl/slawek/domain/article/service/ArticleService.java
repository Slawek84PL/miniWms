package pl.slawek.domain.article.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.slawek.domain.article.Article;
import pl.slawek.domain.article.repository.ArticleRepository;


import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository repository;

    public List<Article> getAll() {
        return repository.findAll();
    }

    public Article getOne(long articleId) {
        return repository.findById(articleId).orElseThrow(() -> new EntityNotFoundException("Article not found for id: " + articleId));
    }

    public Article add(Article article) {
        return repository.save(article);
    }

    public void delete(long articleId) {
        repository.deleteById(articleId);
    }

    public Article updateArticle(long articleId, Article updatedArticle) {

        Article article = getOne(articleId);

        if(updatedArticle.getNumber() != null) {
            article.setNumber(updatedArticle.getNumber());
        }

        if(updatedArticle.getName() != null) {
            article.setName(updatedArticle.getName());
        }

        return repository.save(article);
    }
}
