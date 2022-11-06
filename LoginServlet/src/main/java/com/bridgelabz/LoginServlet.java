package com.bridgelabz;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

@WebServlet(
        description = "Login servlet testing",
        urlPatterns = {"/LoginServlet"},
        initParams = {
                @WebInitParam(name = "username", value = "Pallavi"),
                @WebInitParam(name = "password", value = "Password@123")
        }
)
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get request parameters for username and password
        String username = req.getParameter("user");
        String password = req.getParameter("pwd");

        //get servlet config init params
        String userId = getServletConfig().getInitParameter("username");
        String pass = getServletConfig().getInitParameter("password");

       String nameValidate = "^[A-Z]{1}[a-z]{2,}";
        String passwordValidate = "^(?=.*[A-z])(?=.*[0-9])([a-zA-Z0-9@._-]).{8,}$";
        if (userId.equals(username) && username.matches(nameValidate) && pass.equals(password) && password.matches(passwordValidate)){
            req.setAttribute("username", username);
            req.getRequestDispatcher("loginSuccess.jsp").forward(req,resp);
        }else{
            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = resp.getWriter();
            out.write("<b><font color=red> User name or password is wrong!.</font></b>");
            requestDispatcher.include(req, resp);
            out.close();
        }
    }
}