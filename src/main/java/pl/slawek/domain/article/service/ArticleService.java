package pl.slawek.domain.article.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.slawek.domain.article.Article;
import pl.slawek.domain.article.repository.ArticleRepository;
import pl.slawek.domain.company.service.CompanyService;


import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository repository;
    private final CompanyService companyService;

    public List<Article> getAll(long companyId) {
        return repository.findAllByCompany_Id(companyId);
    }

    public Article getOne(long articleId) {

        return repository.findById(articleId).orElseThrow(() -> new EntityNotFoundException("Article not found for id: " + articleId));
    }

    public Article getOne(long companyId, long articleId) {

        return repository.findArticleByIdAndCompany(articleId, companyService.getOne(companyId)).orElseThrow(() -> new EntityNotFoundException("Article not found for id: " + articleId));
    }

    @Transactional
    public Article add(long companyId, Article article) {
        article.setCompany(companyService.getOne(companyId));
        return repository.save(article);
    }

    public void delete(long companyId, long articleId) {
        repository.deleteArticleByIdAndCompany_Id(articleId, companyId);
    }

    public void deleteAllArticles(long companyId) {
        repository.deleteArticleByCompany_Id(companyId);
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
