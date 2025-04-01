package model.repo;

import jakarta.enterprise.context.ApplicationScoped;
import model.entity.Document;

/**
 *
 * @author kenanince
 */
@ApplicationScoped
public class DocumentRepo extends AbstractRepo<Document>{

	public DocumentRepo() {
		super(Document.class);
	}
}
