package model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

/**
 *
 * @author kenanince
 */
@Entity
public class Document extends BaseEntity {

	private static final long serialVersionUID = -6225438902869780705L;

	private String title;
	private String name;
	private String path;
	private String type;

	@ManyToOne(targetEntity = Post.class)
	private Post post;

	public Document() {
	}
	
	public Document(Long id,String title, String name, String path, String type, Post post) {
		super(id);
		this.title = title;
		this.name = name;
		this.path = path;
		this.type = type;
		this.post = post;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Document{" + "id=" + this.getId() + ", name=" + name + ", path=" + path + ", type=" + type + '}';
	}

}
