package filter;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.file.Files;

/**
 *
 * @author kenanince
 */
@WebServlet(name = "DocumentServlet", urlPatterns = {"/document/*"})
public class DocumentServlet extends HttpServlet {

	private static final long serialVersionUID = 981074856326148802L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String filename = req.getPathInfo();
		
		File file = new File("/Users/kenanince/Tmp/upload/" + filename);
		
		if (!file.isDirectory() && file.exists()) {
			res.reset();
			res.setHeader("Content-Type", getServletContext().getMimeType(filename));
			res.setHeader("Content-Length", String.valueOf(file.length()));
			Files.copy(file.toPath(), res.getOutputStream());
		}
	}

}
