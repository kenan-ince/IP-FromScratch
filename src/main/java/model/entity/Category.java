package model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.List;

/**
 *
 * @author kenanince
 */
@Entity
public class Category extends BaseEntity {

	private static final long serialVersionUID = 2507491709832107083L;

	@ManyToOne(optional = true, cascade = CascadeType.REFRESH, targetEntity = Category.class)
	private Category parent;

	@Column(unique = true)
	private String title;

	@ManyToMany(targetEntity = Post.class)
	@JoinTable(
			name = "category_post",
			joinColumns = @JoinColumn(name = "category_id"),
			inverseJoinColumns = @JoinColumn(name = "post_id"))
	private List<Post> posts;

	public Category() {
		super();
	}

	public Category(Long id, Category parent, String title) {
		super(id);
		this.parent = parent;
		this.title = title;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Category{" + "id=" + this.getId() + ", parent=" + parent + ", title=" + title + '}';
	}
}
