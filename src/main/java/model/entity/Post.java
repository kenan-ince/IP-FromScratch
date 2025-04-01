package model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;

/**
 *
 * @author kenanince
 */
@Entity
public class Post extends BaseEntity {

	private static final long serialVersionUID = -725328770041604008L;

	@ManyToMany(targetEntity = Category.class)
	@JoinTable(
			name = "category_post",
			joinColumns = @JoinColumn(name = "post_id"),
			inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<Category> categories;

	private String title;

	private String content;

	@OneToMany(targetEntity = Document.class,orphanRemoval = true, mappedBy = "post")
	private List<Document> docs;

	public Post() {
	}

	public Post(Long id, List<Category> categories, String title, String content) {
		super(id);
		this.categories = categories;
		this.title = title;
		this.content = content;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Document> getDocs() {
		return docs;
	}

	public void setDocs(List<Document> docs) {
		this.docs = docs;
	}

	@Override
	public String toString() {
		return "Post{" + "id=" + this.getId() + ", categories=" + categories + ", title=" + title + ", content=" + content + '}';
	}

}
