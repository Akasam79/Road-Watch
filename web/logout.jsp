<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession session3 = request.getSession();
    session3.invalidate();
    getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
%>
                
