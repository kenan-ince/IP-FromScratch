package controller;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.entity.Document;
import model.entity.Post;
import model.repo.DocumentRepo;
import model.repo.Repo;

/**
 *
 * @author kenanince
 */
@Named(value = "pb")
@ViewScoped
public class PostBean extends AbstractBean<Post> {

	private static final long serialVersionUID = 3811745329021647635L;

	private String title;
	private Part doc;

	private static String uploadPath = "/Users/kenanince/Tmp/upload/";

	@Inject
	protected Repo<Document> dRepo;

	private List<Document> documents;

	public PostBean() {
		super(Post.class);
	}

	@Override
	public void setEntity(Post entity) {
		super.setEntity(entity);
		super.setRight(entity, "form");
	}
	
	

	public void uploadFile() {
		try {
			InputStream in = doc.getInputStream();
			File f = new File(uploadPath + doc.getSubmittedFileName());
			Files.copy(in, f.toPath());

			Document d = new Document();
			d.setTitle(title);
			d.setName(f.getName());
			d.setPath(f.getParent());
			d.setType(doc.getContentType());
			d.setPost(this.getEntity());

			dRepo.create(d);

		} catch (IOException ex) {
			Logger.getLogger(PostBean.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public List<Document> getDocuments() {
		return dRepo.findByField("post", this.getEntity());
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Part getDoc() {
		return doc;
	}

	public void setDoc(Part doc) {
		this.doc = doc;
	}
}
