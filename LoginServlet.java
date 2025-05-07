import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        // 1. Get username & password from request
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 2. Validate credentials (hardcode a few users)
        // 3. If valid:
        //    - Create session
        //    - Store username in cookie
        //    - Redirect to DashboardServlet
        // 4. If invalid, redirect back to login.html
        
        Map<String, String> users = new HashMap<>();
        users.put("student1", "pass1");
        users.put("student2", "pass2");
        
        if (users.containsKey(username) && users.get(username).equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);

                Cookie userCookie = new Cookie("username", username);
                userCookie.setMaxAge(60 * 60); // 1 hour
                response.addCookie(userCookie);

                response.sendRedirect("DashboardServlet");

            } else {
                response.sendRedirect("login.html");
            }
        }
    }
}