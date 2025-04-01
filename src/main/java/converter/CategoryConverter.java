package converter;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import model.entity.Category;
import model.repo.CategoryRepo;

/**
 *
 * @author kenanince
 */
@ApplicationScoped
@FacesConverter(forClass = Category.class, managed = true)
public class CategoryConverter implements Converter {

	@Inject
	private CategoryRepo repo;

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
		Long id = Long.valueOf(string);
		if (id != null) {
			return repo.find(id);
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object t) {
		Category c = (Category) t;
		if (c != null) {
			return c.getId().toString();
		} else {
			return "";
		}
	}

}
