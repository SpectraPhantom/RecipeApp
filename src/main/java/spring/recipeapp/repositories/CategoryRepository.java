package spring.recipeapp.repositories;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.repository.CrudRepository;
import spring.recipeapp.domain.Category;

import java.util.Optional;

@EnableMongoRepositories
public interface CategoryRepository extends CrudRepository<Category,String> {

    Optional<Category> findByDescription(String description);
}
