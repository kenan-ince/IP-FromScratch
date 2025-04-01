package controller;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.util.List;
import model.entity.Category;

/**
 *
 * @author kenanince
 */
@Named(value = "cb")
@ViewScoped
public class CategoryBean extends AbstractBean<Category> {

	private static final long serialVersionUID = -3614181072426519405L;

	public CategoryBean() {
		super(Category.class);
	}

	@Override
	public void create() {
		List<Category> tmp = repo.findByField("title", this.getEntity().getTitle());
		if (tmp == null || tmp.isEmpty()) {
			repo.create(this.getEntity());
			this.clear();
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("The category with title: " + this.getEntity().getTitle() + " already exists!"));
		}
	}
}
