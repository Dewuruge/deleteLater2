package lk.ijse.dep11.ea;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.dep11.ea.db.SingleConnectionDataSource;

import java.io.IOException;
import java.sql.SQLException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        try (var connection = SingleConnectionDataSource.getInstance().getConnection()) {
            launch(args);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        primaryStage.setScene(new Scene(FXMLLoader
                .load(getClass().getResource("/view/LoginForm.fxml"))));
        primaryStage.setTitle("Hello JDBC");
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.centerOnScreen();

    }
}
