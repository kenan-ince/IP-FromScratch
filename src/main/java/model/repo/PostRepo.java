package model.repo;

import jakarta.enterprise.context.ApplicationScoped;
import model.entity.Post;

/**
 *
 * @author kenanince
 */
@ApplicationScoped
public class PostRepo extends AbstractRepo<Post>{

	public PostRepo() {
		super(Post.class);
	}
}
