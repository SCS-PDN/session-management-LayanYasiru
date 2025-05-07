import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        // 1. Check if user is logged in (session)
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("login.html");
        }

        // 2. Create a list of courses (hardcoded)
        List<Course> courseList = new ArrayList<>();
        courseList.add(new Course("CSC101", "CS01", "Dr. "));
        courseList.add(new Course("CSC102", "CS02", "Prof. "));
        courseList.add(new Course("CSC103", "CS03", "Mr. "));

        // 3. Store courses in request attribute
        request.setAttribute("courses", courseList);

        List<DashboardServlet.Course> enrolledCourses = (List<DashboardServlet.Course>) session.getAttribute("enrolledCourses");
        if (enrolledCourses == null) {
            enrolledCourses = new ArrayList<>();
            session.setAttribute("enrolledCourses", enrolledCourses);
        }

        assert session != null;
        request.setAttribute("enrolledCourses", enrolledCourses);


        // 4. Forward to dashboard.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
        dispatcher.forward(request, response);
    }


    public static class Course {
        private String id;
        private String name;
        private String instructor;

        public Course(String id, String name, String instructor) {
            this.id = id;
            this.name = name;
            this.instructor = instructor;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getInstructor() {
            return instructor;
        }
    }
}