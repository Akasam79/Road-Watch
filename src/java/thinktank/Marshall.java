package thinktank;

import bean.Call;
import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;

public final class Marshall {
    public static Connection dbConnect() throws ClassNotFoundException, SQLException{
        String URL = "jdbc:mysql://localhost:3307/roadwatch?useSSL=false&serverTimezone=Africa/Lagos";
        String USERNAME = "root";
        String PASSWORD = "Prescotcruzy9";
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static ArrayList<String> doLogin(String phone, String pin) throws 
            ClassNotFoundException {
        ArrayList res = new ArrayList();
        try(Connection conn = dbConnect()){
            String query = "SELECT * FROM users WHERE phone = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, phone);
            
            ResultSet result = statement.executeQuery();
            if(result.next()){
                String db_pin = result.getString("pin");
                String status = result.getString("status");
                if(db_pin.equals(pin)){
                    if(status.equals("enabled")){
                        res.add("success");
                        res.add(result.getString("f_name"));
                        res.add(result.getString("l_name"));
                        res.add(result.getString("role"));
                        res.add(status);
                    }else{
                        res.add("disabled");
                    }
                }else{
                    res.add("failed");
                }
            }else{
                res.add("none");
            }
        }catch(SQLException e){
            Logger.getLogger(Marshall.class.getName()).log(Level.SEVERE, pin, e);
            res.add("error");
        }
        return res;
    }

    public static boolean createUser(String u_phone, String f_name, String l_name, String role)
            throws ClassNotFoundException, IOException {
        
        try(Connection conn = dbConnect()){
            String PIN = generatePIN(4);
            String query = "INSERT INTO users(phone,pin,f_name,l_name,role) "
                            + "VALUES(?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, u_phone);
            statement.setString(2, PIN);
            statement.setString(3, f_name);
            statement.setString(4, l_name);
            statement.setString(5, role);
            boolean res = statement.execute();
            if(!res)SendSMS.now("234"+u_phone.substring(1), "Dear, "+f_name
                    + "\nYou have been profiled on"
                    + "RoadWatch as a "+role.toUpperCase()+" "
                    + "personnel.\nYour PIN is "+PIN, "Omney");
            return res;
        }
        catch(SQLException e){
            Logger.getLogger(Marshall.class.getName()).log(Level.SEVERE, null, e);
            return true;
        }
    }
    
    public static String generatePIN(int len){
        SecureRandom rnd = new SecureRandom();
        String digits = "0123456789";
        StringBuilder sb = new StringBuilder(len);
        for(int i=0; i<len; i++){
           sb.append(digits.charAt(rnd.nextInt(digits.length())));
        }
        return sb.toString();
    }

    public static String manageUser(String action, String phone) throws ClassNotFoundException {
        String sql;
        switch (action) {
            case "delete":
                sql = "DELETE FROM users WHERE phone = ?";
                break;
            case "disable":
                sql = "UPDATE users SET status = 'disabled' WHERE phone = ?";
                break;
            default:
                sql = "UPDATE users SET status = 'enabled' WHERE phone = ?";
                break;
        }
        try(Connection dbConn = Marshall.dbConnect()){
            PreparedStatement stmt = dbConn.prepareStatement(sql);
            stmt.setString(1, phone);
            boolean a = stmt.execute();
            return "OK";
        }catch(SQLException e){
            Logger.getLogger(Marshall.class.getName()).log(Level.SEVERE, null, e);
            return "error";
        }
    }
    
    public static ArrayList<Call> getCallLog(String unit) throws ClassNotFoundException{
        ArrayList<Call> call_a = new ArrayList();
        try(Connection dbConn = Marshall.dbConnect()){
            String sql = "SELECT * FROM call_log WHERE incidentType = ? LIMIT 0, 20";
            PreparedStatement stmt = dbConn.prepareStatement(sql);
            stmt.setString(1, unit);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Call call = new Call();
                call.setPhoneNumber(rs.getString("callerNumber"));
                call.setCallTime(rs.getString("callStartTime"));
                call.setDuration(rs.getInt("durationInSeconds"));
                call.setIncidentType(rs.getString("incidentType"));
                call.setURL(rs.getString("recordingUrl"));
                call_a.add(call);
            }
        }catch(SQLException e){
            Logger.getLogger(Marshall.class.getName()).log(Level.SEVERE, null, e);
        }
        return call_a;
    }
}
