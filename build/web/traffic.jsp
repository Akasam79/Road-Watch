<%@page import="java.util.ArrayList"%>
<%@page import="thinktank.Marshall"%>
<%@page import="bean.Call"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <title>RoadWatch | Traffic Unit</title>

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <style>
            .bd-placeholder-img {
                font-size: 1.125rem;
                text-anchor: middle;
                -webkit-user-select: none;
                -moz-user-select: none;
                -ms-user-select: none;
                user-select: none;
            }

            @media (min-width: 768px) {
                .bd-placeholder-img-lg {
                  font-size: 3.5rem;
                }
            }
        </style>
        <link href="css/dashboard.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="nav.jsp" />
        <%
            ArrayList<Call> audio_a = Marshall.getCallLog("traffic");
            int l = audio_a.size();
            %>

                <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
                    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                        <h1 class="h2">Traffic Unit</h1>
                    </div>
                    <!--<canvas class="my-4 w-100" id="myChart" width="900" height="380"></canvas>-->
                    <h2>Report Log</h2>
                    <div class="table-responsive">
                        <table class="table table-striped table-sm">
                            <thead>
                                <tr style="text-align: center;">
                                  <th>#</th>
                                  <th>Time</th>
                                  <th>Phone Number</th>
                                  <th>Incident Type</th>
                                  <th>FeedBack</th>
                                  <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for(int i=0; i<l; i++){
                                        Call call = audio_a.get(i);
                                        int p_l = call.getPhoneNumber().length();
                                %>
                                <tr style="text-align: center;">
                                    <td><%=i+1%></td>
                                    <td><%=call.getCallTime()%></td>
                                    <td><%="0"+call.getPhoneNumber().substring(p_l-10)%></td>
                                    <td><%=call.getIncidentType().toUpperCase() %></td>
                                    <td>
                                        <form action="nav" method="POST">
                                            <input type="text" name="res_action" placeholder="Enter response">
                                            <button type="submit">Send</button>
                                        </form>
                                    </td>
                                    <td>
                                        <audio controls>
                                            <source src="<%=call.getURL()%>" type="audio/mpeg">
                                        </audio>
                                    </td>
                                </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                </main>
            </div>
        </div>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script>window.jQuery || document.write('<script src="/docs/4.3/assets/js/vendor/jquery-slim.min.js"><\/script>')</script><script src="/docs/4.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/feather-icons/4.9.0/feather.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.min.js"></script>
    <script src="js/dashboard.js"></script></body>
</html>

