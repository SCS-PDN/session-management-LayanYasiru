import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/EnrollServlet")
public class EnrollServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Get courseId from URL parameter
        String courseId = request.getParameter("courseId");

        // 2. Get current user's session
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("login.html");
        }

        List<DashboardServlet.Course> courseList = new ArrayList<>();
        courseList.add(new DashboardServlet.Course("CSC101", "CS01", "Dr. "));
        courseList.add(new DashboardServlet.Course("CSC102", "CS02", "Prof. "));
        courseList.add(new DashboardServlet.Course("CSC103", "CS03", "Mr. "));

        // 3. Add course to enrolled list in session
        assert session != null;
        List<DashboardServlet.Course> enrolledCourses = (List<DashboardServlet.Course>) session.getAttribute("enrolledCourses");
        for (DashboardServlet.Course course : courseList) {
            if (course.getId().equals(courseId)) {
                enrolledCourses.add(course);
            }
        }
        session.setAttribute("enrolledCourses", enrolledCourses);

        // 4. Redirect back to DashboardServlet
        response.sendRedirect("DashboardServlet");
    }
}
