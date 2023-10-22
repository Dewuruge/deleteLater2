package lk.ijse.dep11.ea.db;

import lk.ijse.dep11.ea.tm.Employee;

import javax.xml.transform.Result;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDataAccess {

    private static final PreparedStatement STM_INSERT;
    private static final PreparedStatement STM_UPDATE;
    private static final PreparedStatement STM_UPDATE_IMG;
    private static final PreparedStatement STM_DELETE;
    private static final PreparedStatement STM_INSERT_IMG;
    private static final PreparedStatement STM_GET_ALL_AND_IMAGE;
    private static final PreparedStatement STM_GET_ALL;
    private static final PreparedStatement STM_GET_A_EMPLOYEE;
    private static final PreparedStatement STM_GET_LAST_ID;
    private static final PreparedStatement STM_GET_LOGGING_ROLE;

    static {
        try {
            Connection connection = SingleConnectionDataSource.getInstance().getConnection();
            STM_GET_A_EMPLOYEE= connection.prepareStatement("SELECT * FROM employee JOIN profile_picture on employee.id = profile_picture.employee_id WHERE employee.id =?");

            STM_GET_LOGGING_ROLE=connection.prepareStatement("SELECT role FROM employee WHERE password=? AND username =?");
            STM_GET_LAST_ID = connection
                    .prepareStatement("SELECT * FROM employee ORDER BY id DESC FETCH FIRST ROWS ONLY ");
            STM_GET_ALL = connection.prepareStatement("SELECT * FROM employee");
            STM_GET_ALL_AND_IMAGE = connection.prepareStatement("SELECT * FROM employee LEFT JOIN profile_picture on employee.id = profile_picture.employee_id");
            STM_INSERT = connection
                    .prepareStatement("INSERT INTO employee (id, name, dob, nic, contact, password, username, role, status, address, branch) VALUES (?, ?, ?,?,?,?,?,?,?,?,?)");
            STM_INSERT_IMG =connection.prepareStatement("INSERT INTO profile_picture (employee_id, profile_picture) VALUES (?,?)");
            STM_UPDATE = connection
                    .prepareStatement("UPDATE employee SET name=?, dob=? , nic=?, contact=?, password=?, username=?, role=?, status=?, address=?, branch=? WHERE id=?");
            STM_UPDATE_IMG = connection
                    .prepareStatement("UPDATE profile_picture SET profile_picture =? WHERE employee_id=?");
            STM_DELETE = connection.prepareStatement("DELETE FROM employee WHERE id=?");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void saveEmployee(Employee employee) throws SQLException {
        STM_INSERT.setString(1, employee.getId());
        STM_INSERT.setString(2, employee.getName());
        STM_INSERT.setDate(3, java.sql.Date.valueOf(employee.getDOB()));
        STM_INSERT.setString(4, employee.getNic());
        STM_INSERT.setString(5, employee.getContact());
        STM_INSERT.setString(6, employee.getPassword());
        STM_INSERT.setString(7, employee.getUsername());
        STM_INSERT.setString(8, employee.getRole());
        STM_INSERT.setString(9, employee.getStatus());
        STM_INSERT.setString(10, employee.getAddress());
        STM_INSERT.setString(11,employee.getBranch());

        if(employee.getByteImg()!=null){
            STM_INSERT_IMG.setString(1, employee.getId());
            STM_INSERT_IMG.setBytes(2, employee.getByteImg());
            STM_INSERT_IMG.executeUpdate();
        }


        STM_INSERT.executeUpdate();
    }
        public static List<Employee> getAllEmployees() throws SQLException {
            ResultSet rst = STM_GET_ALL_AND_IMAGE.executeQuery();
            List<Employee> employeeList = new ArrayList<>();
            while (rst.next()) {
                String id = rst.getString("id");
                String name = rst.getString("name");
                String address = rst.getString("address");
                String nic = rst.getString("nic");
                LocalDate DOB = rst.getDate("DOB").toLocalDate();
                String branch = rst.getString("branch");
                String password = rst.getString("password");
                String username = rst.getString("username");
                String role = rst.getString("role");
                String status = rst.getString("status");
                String contact = rst.getString("contact");
                byte[] byteImage = rst.getBytes("profile_picture");

                employeeList.add(new Employee(id, name, nic, address, DOB, branch, status, contact, password, role,username,byteImage));
            }

            return employeeList;
        }

    public static void updateEmployee(Employee employee) throws SQLException {
        STM_UPDATE.setString(11, employee.getId());
        STM_UPDATE.setString(1, employee.getName());
        STM_UPDATE.setDate(2, java.sql.Date.valueOf(employee.getDOB()));
        STM_UPDATE.setString(3, employee.getNic());
        STM_UPDATE.setString(4, employee.getContact());
        STM_UPDATE.setString(5, employee.getPassword());
        STM_UPDATE.setString(6, employee.getUsername());
        STM_UPDATE.setString(7, employee.getRole());
        STM_UPDATE.setString(8, employee.getStatus());

        STM_UPDATE.setString(9, employee.getAddress());
        STM_UPDATE.setString(10,employee.getBranch());
        STM_UPDATE_IMG.setBytes(1,employee.getByteImg());
        STM_UPDATE_IMG.setString(2,employee.getId());


        STM_UPDATE.executeUpdate();
        STM_UPDATE_IMG.executeUpdate();
    }

    public static String getLastCustomerId() throws SQLException {
        ResultSet rst = STM_GET_LAST_ID.executeQuery();
        if (rst.next()){
            return rst.getString(1);
        }else{
            return null;
        }
    }
    public static String getEmployeeRole(String userName, String password ) throws SQLException {
        STM_GET_LOGGING_ROLE.setString(1,password);
        STM_GET_LOGGING_ROLE.setString(2,userName);
        ResultSet rst = STM_GET_LOGGING_ROLE.executeQuery();
        if(rst.next())
        {
            return rst.getString("role");}
        else {
            return null;
        }

    }
    public static Employee getOneEmployee(String ID) throws SQLException {
        STM_GET_A_EMPLOYEE.setString(1,ID);
        ResultSet rst = STM_GET_A_EMPLOYEE.executeQuery();
       if(rst.next()) {
            String id = rst.getString("id");
            String name = rst.getString("name");
            String address = rst.getString("address");
            String nic = rst.getString("nic");
            LocalDate DOB = rst.getDate("DOB").toLocalDate();
            String branch = rst.getString("branch");
            String password = rst.getString("password");
            String username = rst.getString("username");
            String role = rst.getString("role");
            String status = rst.getString("status");
            String contact = rst.getString("contact");
            byte[] byteImage = rst.getBytes("profile_picture");

            return new Employee(id, name, nic, address, DOB, branch, status, contact, password, role,username,byteImage);
        }

        return null;
    }
}
