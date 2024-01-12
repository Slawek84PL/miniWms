package pl.slawek.domain.company.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.slawek.domain.company.Article;
import pl.slawek.domain.company.repository.ArticleRepository;


import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository repository;
    private final CompanyService companyService;

    @Transactional(readOnly = true)
    public List<Article> getAll(long companyId) {
        return repository.findAllByCompany_Id(companyId);
    }

    @Transactional(readOnly = true)
    public Article getOne(long articleId) {

        return repository.findById(articleId).orElseThrow(() -> new EntityNotFoundException("Article not found for id: " + articleId));
    }

    @Transactional(readOnly = true)
    public Article getOne(long companyId, long articleId) {

        return repository.findArticleByIdAndCompany(articleId, companyService.getOne(companyId)).orElseThrow(() -> new EntityNotFoundException("Article not found for id: " + articleId));
    }

    @Transactional
    public Article add(long companyId, Article article) {
        article.setCompany(companyService.getOne(companyId));
        return repository.save(article);
    }

    @Transactional
    public void delete(long companyId, long articleId) {
        repository.deleteArticleByIdAndCompany_Id(articleId, companyId);
    }

    @Transactional
    public void deleteAllArticles(long companyId) {
        repository.deleteArticleByCompany_Id(companyId);
    }

    @Transactional
    public Article updateArticle(long articleId, Article updatedArticle) {

        updatedArticle.setId(articleId);

        return repository.save(updatedArticle);
    }

    @Transactional
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
