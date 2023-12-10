package pl.slawek.domain.article.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.slawek.domain.article.Article;
import pl.slawek.domain.article.repository.ArticleRepository;
import pl.slawek.domain.company.Company;
import pl.slawek.domain.company.service.CompanyService;


import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository repository;
    private final CompanyService companyService;

    public List<Article> getAll() {
        return repository.findAll();
    }

    public Article getOne(long articleId) {
        return repository.findById(articleId).orElseThrow(() -> new EntityNotFoundException("Article not found for id: " + articleId));
    }

    @Transactional
    public Article add(long companyId, Article article) {
        Company company = companyService.getOne(companyId);
        company.getArticles().add(article);
        return repository.save(article);
    }

    public void delete(long articleId) {
        repository.deleteById(articleId);
    }

    public Article updateArticle(long articleId, Article updatedArticle) {

        updatedArticle.setId(articleId);

        return repository.save(updatedArticle);
    }

    public Article updatePartArticle(long articleId, Article updatedArticle) {

        Article article = getOne(articleId);

        if (updatedArticle.getEan() != null) {
            article.setEan(updatedArticle.getEan());
        }

        if (updatedArticle.getName() != null) {
            article.setName(updatedArticle.getName());
        }

        return repository.save(article);
    }
}
