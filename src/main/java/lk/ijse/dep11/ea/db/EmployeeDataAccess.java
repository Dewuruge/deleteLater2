package lk.ijse.dep11.ea.db;

import lk.ijse.dep11.ea.tm.Employee;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDataAccess {

    private static final PreparedStatement STM_INSERT;
    private static final PreparedStatement STM_UPDATE;
    private static final PreparedStatement STM_DELETE;
    private static final PreparedStatement STM_INSERT_IMG;
    private static final PreparedStatement STM_GET_ALL_AND_IMAGE;
    private static final PreparedStatement STM_GET_ALL;
    private static final PreparedStatement STM_GET_LAST_ID;

    static {
        try {
            Connection connection = SingleConnectionDataSource.getInstance().getConnection();
            STM_GET_LAST_ID = connection
                    .prepareStatement("SELECT * FROM employee");
            STM_GET_ALL = connection.prepareStatement("SELECT * FROM employee");
            STM_GET_ALL_AND_IMAGE = connection.prepareStatement("SELECT * FROM employee JOIN profile_picture on employee.nic = profile_picture.employee_nic");
            STM_INSERT = connection
                    .prepareStatement("INSERT INTO employee (id, name, dob, nic, contact, password, username, role, status, address, branch) VALUES (?, ?, ?,?,?,?,?,?,?,?,?)");
            STM_INSERT_IMG =connection.prepareStatement("INSERT INTO profile_picture (employee_nic, profile_picture) VALUES (?,?)");
            STM_UPDATE = connection
                    .prepareStatement("UPDATE employee SET name=?, address=? WHERE id=?");
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
        STM_INSERT_IMG.setString(1,employee.getNic());
        STM_INSERT_IMG.setBytes(2,employee.getByteImg());


        STM_INSERT.executeUpdate();
        STM_INSERT_IMG.executeUpdate();
    }
        public static List<Employee> getAllCustomers() throws SQLException {
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
}
