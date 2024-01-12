package pl.slawek.domain.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.slawek.domain.company.Article;
import pl.slawek.domain.company.Company;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findAllByCompany_Id(long companyId);

    Optional<Article> findArticleByIdAndCompany(long articleId, Company one);

    void deleteArticleByIdAndCompany_Id(long articleId, long companyId);

    void deleteArticleByCompany_Id(long companyId);
}
