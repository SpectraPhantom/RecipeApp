package spring.recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import spring.recipeapp.domain.Category;

public interface CategoryRepository extends CrudRepository<Category,Long> {
}
