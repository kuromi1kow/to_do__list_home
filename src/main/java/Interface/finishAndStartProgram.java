package Interface;

import org.example.User;

import java.sql.SQLException;
import java.util.List;

public interface finishAndStartProgram {
    void mainMenu(List<User> users) throws SQLException;
    void exit();
}
