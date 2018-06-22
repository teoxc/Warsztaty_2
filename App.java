import pl.coderslab.models.Group;
import pl.coderslab.models.User;

import java.sql.Connection;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException {
        Connection conn = DBUtil.getConnection();
//        User me = new User("teo2", "qwerty", "teo@teo.com");
//        me.saveToDB(conn);
//        conn.close();
//        System.out.println(me.getId());

//        User loaded = User.loadUserById(conn, 2);
//        conn.close();
//        System.out.println(loaded);

//        User [] users = User.loadAllUsers(conn);
//        conn.close();
//        System.out.println(users.length);
//        System.out.println(users[users.length-1].getEmail());
//        System.out.println(users[users.length-1].getUsername());
//        System.out.println(users[users.length-1].getId());

//        loaded.setEmail("mateo@wp.pl");
//        loaded.setUsername("mateo");
//        loaded.saveToDB(conn);
//        conn.close();

//        loaded.delete(conn);
//        conn.close();

//        Group group = new Group("Sępy");
//        group.saveToDB(conn);

        Group group = Group.loadById(conn, 2);
        group.deleteGroup(conn);
        System.out.println(group.getId());

        conn.close();
    }
}
