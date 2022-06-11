package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import service.AccountService;

public class loginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // load the session or create a new session if session doesn't exists
        HttpSession session = request.getSession(true);
        
        // if user clicked the logout link
        if(request.getParameter("action") != null) {
            // destroy or invalidate the running session
            session.invalidate();
            request.setAttribute("sessionLogout", true);
        }
        // if user is logged in
        else if(session.getAttribute("username") != null ){
            // redirect to the homepage and stop the code call
            response.sendRedirect("home");
            return;
        }
        
        // load the JSP and stop the code call
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // load the session or create a new session if session doesn't exists
        HttpSession session = request.getSession();
        
        // read paramenter values
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // create account object to later verify login credentials of user
        AccountService account = new AccountService();
        User user = account.login(username, password);
        
        // check if username or password is null or user object is null
        if(username == null || username.equals("") || password == null || password.equals("") || user == null){
            request.setAttribute("inputError", true);
            request.setAttribute("user", username);
            request.setAttribute("password", password);
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
        else{
            session.setAttribute("username", username);
            session.setAttribute("user", user);            
            response.sendRedirect("home");
            return;
        }
    }
}
