package thinktank;

import bean.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "dispatcher", urlPatterns = {"/dispatcher", "/nav"})
public class Dispatcher extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        String url = "/index.jsp";
        String signal = request.getParameter("signal");
        String mesg = "";
        HttpSession session = request.getSession();
        boolean forward = true;
        
        switch(signal){
            case "login":
                String phone = request.getParameter("phone");
                String pin = request.getParameter("pin");
                ArrayList<String> loginRes = Marshall.doLogin(phone, pin);
                
                String key_res = loginRes.get(0);
                switch(key_res){
                    case "success":
                        url = "/watchboard.jsp";
                        User user = new User(phone, loginRes.get(1), loginRes.get(2), loginRes.get(3), loginRes.get(4));
                        session.setAttribute("user", user);
                        request.setAttribute("user", user);
                        request.setAttribute("r_type", "wb");
                        break;
                    case "failed":
                        mesg = "Authentication failed";
                        break;
                    case "none":
                        mesg = "Sorry, no such account found.";
                        break;
                    case "disabled":
                        mesg = "Account, disabled. Contact admin.";
                        break;
                    default:
                        mesg = "We're currently experiencing server issues. Please, try later.";
                        break;
                }
                break;
            case "create_user":
                url = "/manageusers.jsp";
                String color;
                String f_name = request.getParameter("f_name");
                String l_name = request.getParameter("l_name");
                String u_phone = request.getParameter("phone");
                String role = request.getParameter("role");
                boolean c_res = Marshall.createUser(u_phone, f_name, l_name, role);
                if(!c_res){
                    color = "green";
                    mesg = "User added successfully";
                }else{
                    color = "red";
                    mesg = "Oops! Something went wrong.";
                }
                request.setAttribute("color", color);
                break;
            case "manageuser":
                String action = request.getParameter("data1");
                String m_phone = request.getParameter("data2");
                Marshall.manageUser(action, m_phone);
                forward = false;
                break;
            default:
                break;
        }
        if(forward){
            request.setAttribute("info", mesg);
            getServletContext().getRequestDispatcher(url).forward(request, response);
        }else{
            String r_url = response.encodeRedirectURL("manageusers.jsp");
            response.sendRedirect(r_url);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="DoPost&DoGet">
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Dispatcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Dispatcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
