package model.repo;

import jakarta.enterprise.context.ApplicationScoped;
import model.entity.Category;

/**
 *
 * @author kenanince
 */
@ApplicationScoped
public class CategoryRepo extends AbstractRepo<Category>  {

	public CategoryRepo() {
		super(Category.class);
	}
}
