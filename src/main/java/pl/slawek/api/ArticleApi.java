package pl.slawek.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.slawek.domain.article.Article;
import pl.slawek.domain.article.service.ArticleService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/{companyId}/article")
public class ArticleApi {

    private final ArticleService service;

    @GetMapping
    public ResponseEntity<List<Article>> getAll(@PathVariable long companyId) {
        return new ResponseEntity<>(service.getAll(companyId), HttpStatus.OK);
    }

    @GetMapping("{articleId}")
    public ResponseEntity<Article> getOne(@PathVariable long companyId, @PathVariable long articleId) {
        return new ResponseEntity<>(service.getOne(companyId, articleId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Article> add(@PathVariable long companyId,@Valid @RequestBody Article article) {
        return new ResponseEntity<>(service.add(companyId, article), HttpStatus.CREATED);
    }

    @PutMapping("{articleId}")
    public ResponseEntity<Article> update(@PathVariable long articleId, @Valid @RequestBody Article article) {
        return new ResponseEntity<>(service.updateArticle(articleId, article), HttpStatus.OK);
    }

    @PatchMapping("{articleId}")
    public ResponseEntity<Article> updatePart(@PathVariable long articleId, @Valid @RequestBody Article article) {
        return new ResponseEntity<>(service.updatePartArticle(articleId, article), HttpStatus.OK);
    }

    @DeleteMapping("{articleId}")
    public ResponseEntity<?> delete(@PathVariable long companyId, @PathVariable long articleId) {
        service.delete(companyId, articleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@PathVariable long companyId) {
        service.deleteAllArticles(companyId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
