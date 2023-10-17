package lk.ijse.dep11.ea.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProfilePictureDataAccess {
    private static final PreparedStatement STM_INSERT;
    private static final PreparedStatement STM_UPDATE;
    private static final PreparedStatement STM_DELETE;
    private static final PreparedStatement STM_GET_ALL;
    private static final PreparedStatement STM_GET_LAST_ID;

    static {
        try {
            Connection connection = SingleConnectionDataSource.getInstance().getConnection();
            STM_GET_LAST_ID = connection
                    .prepareStatement("SELECT * FROM profile_picture");
            STM_GET_ALL = connection.prepareStatement("SELECT * FROM profile_picture");
            STM_INSERT = connection
                    .prepareStatement("INSERT INTO profile_picture (employee_id, profile_picture) VALUES (?, ?)");
            STM_UPDATE = connection
                    .prepareStatement("UPDATE employee SET name=?, address=? WHERE id=?");
            STM_DELETE = connection.prepareStatement("DELETE FROM employee WHERE id=?");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
