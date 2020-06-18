<%@page import="bean.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <nav class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
            <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">RoadWatch</a>
            <input class="form-control form-control-dark w-100" type="text" placeholder="Search" aria-label="Search">
            <ul class="navbar-nav px-3">
                <li class="nav-item text-nowrap">
                    <a class="nav-link" href="javascript:{}" onclick="logout(); return false;">Sign out</a>
                </li>
            </ul>
        </nav>
        
        <%
            HttpSession session3 = request.getSession();
            User user = (User) session3.getAttribute("user");
            if(user==null){
                getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            }
            String s_dboard="", s_fire="", s_med="", s_obs="", s_traff="", s_road="", s_mu="";
            String uri = request.getRequestURI();
            String pageName = uri.substring(uri.lastIndexOf("/")+1);
            if(pageName.equals("fire.jsp")){
                s_fire = "active";
            }else if(pageName.equals("medical.jsp")){
                s_med = "active";
            }else if(pageName.equals("obstruction.jsp")){
                s_obs = "active";
            }else if(pageName.equals("traffic.jsp")){
                s_traff = "active";
            }else if(pageName.equals("roadcrash.jsp")){
                s_road = "active";
            }else if(pageName.equals("manageusers.jsp")){
                s_mu = "active";
            }else{
                s_dboard = "active";
            }
            
            
        %>    

        <div class="container-fluid">
            <div class="row">
                <nav class="col-md-2 d-none d-md-block bg-light sidebar">
                    <div class="sidebar-sticky">
                        <ul class="nav flex-column">
                            <li class="nav-item">
                                <a class="nav-link <%= s_dboard%>" href="javascript:{}" onclick="link('<%= user.getRole()%>', this.id)" id="watchboard.jsp">
                                    <span data-feather="home"></span>
                                    Watchboard 
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link <%= s_fire%>" href="javascript:{}" onclick="link('<%= user.getRole()%>', this.id)" id="fire.jsp">
                                  <span data-feather="droplet"></span>
                                  Fire 
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link <%= s_med%>" href="javascript:{}" onclick="link('<%= user.getRole()%>', this.id)" id="medical.jsp">
                                    <span data-feather="plus-square"></span>
                                    Medical
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link <%= s_obs%>" href="javascript:{}" onclick="link('<%= user.getRole()%>', this.id)" id="obstruction.jsp">
                                  <span data-feather="truck"></span>
                                  Obstruction
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link <%= s_road%>" href="javascript:{}" onclick="link('<%= user.getRole()%>', this.id)" id="roadcrash.jsp">
                                    <span data-feather="zap-off"></span>
                                    Road Crash
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link <%= s_traff%>" href="javascript:{}" onclick="link('<%= user.getRole()%>', this.id)" id="traffic.jsp">
                                    <span data-feather="alert-triangle"></span>
                                    Traffic
                                </a>
                            </li>
                        </ul>
                        
                        <ul class="nav flex-column">
                            <li class="nav-item">
                                <a class="nav-link <%= s_mu%>" href="javascript:{}" onclick="link('<%= user.getRole()%>', this.id)" id="manageusers.jsp">
                                    <span data-feather="users"></span>
                                    Manage Users
                                </a>
                            </li>
                        </ul>
                    </div>
                </nav>
        <script src="js/nav.js"></script>
    </body>
</html>
