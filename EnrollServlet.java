import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/EnrollServlet")
public class EnrollServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // TODO: Implement enrollment logic
        // 1. Get courseId from URL parameter
        // 2. Get current user's session
        // 3. Add course to enrolled list in session
        // 4. Redirect back to DashboardServlet
    }
}