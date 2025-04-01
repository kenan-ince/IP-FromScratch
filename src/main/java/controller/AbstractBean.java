package controller;

import jakarta.enterprise.context.Dependent;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import model.repo.Repo;

/**
 *
 * @author kenanince
 * @param <T>
 */
@Dependent
public abstract class AbstractBean<T> implements Serializable {

	private static final long serialVersionUID = 4918352575891053364L;

	private final Class<T> entityClass;

	private T entity;

	@Inject
	protected Repo<T> repo;

	private List<T> list;
	private List<T> listPaginated;

	protected int page = 1;
	protected int PAGESIZE = 5;
	private int entityCount;
	protected int pageCount;
	
	private String right = "form";

	@Inject
	protected FacesContext facesContext;

	public AbstractBean(Class<T> ec) {
		this.entityClass = ec;
	}

	public void create() {
		repo.create(this.entity);
		this.clear();
	}

	public void update() {
		repo.update(getEntity());
	}

	public void delete(T c) {
		repo.delete(c);
	}

	public void clear() {
		try {
			this.entity = entityClass.getDeclaredConstructor().newInstance();
		} catch (Exception ex) {
			addMessage(ex.getMessage());
		}
	}

	public List<T> getList() {
		return this.repo.findPaginated(this.PAGESIZE, this.page);
	}

	public T getEntity() {
		if (this.entity == null) {
			try {
				this.entity = entityClass.getDeclaredConstructor().newInstance();
			} catch (Exception ex) {
				addMessage(ex.getMessage());
			}
		}
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if (page > this.pageCount) {
			this.page = this.pageCount;
		} else {
			this.page = page;
		}
	}

	public void previous() {
		if (this.getPage() == 1) {
			this.setPage(this.getPageCount());
		} else {
			this.setPage(this.getPage() - 1);
		}
	}

	public void next() {
		if (this.getPage() == this.getPageCount()) {
			this.setPage(1);
		} else {
			this.setPage(this.getPage() + 1);
		}
	}

	public int getPageCount() {
		this.pageCount = (int) Math.ceil(this.getEntityCount() / (double) this.PAGESIZE);
		return pageCount;
	}

	public int getEntityCount() {
		return repo.findAll().size();
	}

	public FacesContext getFacesContext() {
		this.facesContext = FacesContext.getCurrentInstance();
		return facesContext;
	}

	public void addMessage(String m) {
		getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, m, m));
	}

	public String getRight() {
		return right;
	}

	public void setRight(T t, String right) {
		this.entity = t;
		this.right = right;
	}

}
