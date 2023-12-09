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
@RequestMapping("/api/v1/article")
public class ArticleApi {

    private final ArticleService service;

    @GetMapping
    public ResponseEntity<List<Article>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Article> getOne(@PathVariable long id) {
        return new ResponseEntity<>(service.getOne(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Article> add(@Valid @RequestBody Article article) {
        return new ResponseEntity<>(service.add(article), HttpStatus.CREATED);
    }

    @PutMapping("{articleId}")
    public ResponseEntity<Article> update(@PathVariable long articleId, @Valid @RequestBody Article article) {
        return new ResponseEntity<>(service.updateArticle(articleId, article), HttpStatus.OK);
    }

    @PatchMapping("{articleId}")
    public ResponseEntity<Article> updatePart(@PathVariable long articleId, @Valid @RequestBody Article article) {
        return new ResponseEntity<>(service.updatePartArticle(articleId, article), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
