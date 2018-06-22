import pl.coderslab.models.Exercise;
import pl.coderslab.models.Group;
import pl.coderslab.models.Solution;
import pl.coderslab.models.User;

import java.sql.Connection;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException {
        Connection conn = DBUtil.getConnection();
//        User me = new User("teo2", "qwerty", "fw@wp.pl");
//        me.saveToDB(conn);
//        System.out.println(me.getId());

//        User loaded = User.loadUserById(conn, 2);
//        System.out.println(loaded);

//        User [] users = User.loadAllUsers(conn);
//        System.out.println(users.length);
//        System.out.println(users[users.length-1].getEmail());
//        System.out.println(users[users.length-1].getUsername());
//        System.out.println(users[users.length-1].getId());

//        loaded.setEmail("mateo@wp.pl");
//        loaded.setUsername("mateo");
//        loaded.saveToDB(conn);

//        loaded.delete(conn);

//        Group group = new Group("Sępy");
//        group.saveToDB(conn);

//        Group group = Group.loadById(conn, 2);
//        group.deleteGroup(conn);
//        System.out.println(group.getId());

//        Exercise exer = new Exercise("Zadanie", "bla bla bla");
//        System.out.println(exer.getId());
//        exer.saveToDB(conn);
//        System.out.println(exer.getId());

//        Solution sol = new Solution("asdfghj",3,3);
//        System.out.println(sols[1].getCreated());
//        sols[1].deleteSolution(conn);
//        sol.saveToDB(conn);

//        Solution[] sols = Solution.loadAllByExerciseId(conn,3);
//        for(int i = 0; i < sols.length; i++){
//            System.out.println(sols[i].getUpdated());
//        }

        User[] users = User.loadAllByGrupId(conn,3);
        for(int i = 0; i < users.length; i++){
            System.out.println(users[i].getUsername());
        }


        conn.close();
    }
}
