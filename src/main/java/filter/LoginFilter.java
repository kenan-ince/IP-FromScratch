package filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.entity.SystemUser;

/**
 *
 * @author kenanince
 */
@WebFilter("/*")
public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc) throws IOException, ServletException {
		HttpServletRequest q = (HttpServletRequest) req;
		HttpServletResponse s = (HttpServletResponse) res;

		q.setCharacterEncoding("UTF-8");
		s.setContentType("text/html; charset=utf-8");

		String url = q.getRequestURI();
		HttpSession session = q.getSession();

		SystemUser user = null;

//		fc.doFilter(req, res);

		if (session != null) {
			user = (SystemUser) session.getAttribute("authenticated");
		}

		if (user == null) {
			if (!url.contains("back")) {
				fc.doFilter(req, res);
			} else if (url.contains("back")) {
				s.sendRedirect(q.getContextPath() + "/login.xhtml");
			}
		} else {
			fc.doFilter(req, res);
		}
	}

}
