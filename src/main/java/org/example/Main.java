package org.example;
import java.sql.SQLException;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) throws SQLException {
        ArrayList<User> users = new ArrayList();
        toDoList ex = new toDoList();
        ex.mainMenu(users);
    }
}
/*
select * from roles id;
INSERT INTO roles
(id, name, surname, password)
VALUES
(1, 'Asylkhan', 'Geniyat', '1234');
delete from roles where id>0;
*/
