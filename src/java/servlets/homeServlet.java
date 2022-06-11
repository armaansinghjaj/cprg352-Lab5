package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class homeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // load the session or create a new session if session doesn't exists
        HttpSession session = request.getSession(true);
        
        // if user is logged out
        if(session.getAttribute("username") == null ){
            // redirect to login and stop the code call
            response.sendRedirect("login");
            return;
        }
        
        // load the JSP an stop the code call
        getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // not used
    }
}
