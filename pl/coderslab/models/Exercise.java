package pl.coderslab.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Exercise {
    private int id;
    private String title;
    private String description;
    
    public Exercise(){}
    
    public Exercise(String title, String description){
        this.description = description;
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public static Exercise[] loadAll (Connection conn)throws SQLException {
        List<Exercise> exercises = new ArrayList<>();
        String sql = "select * from warsztaty.exercise";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        while (rs.next()){
            Exercise loaded = new Exercise();
            loaded.id = rs.getInt("id");
            loaded.title = rs.getString("title");
            loaded.description = rs.getString("description");
            exercises.add(loaded);
        }

        Exercise[] exercisesArr = new Exercise[exercises.size()];
        exercisesArr = exercises.toArray(exercisesArr);
        return exercisesArr;
    }

    public static Exercise loadById(Connection conn, int id) throws SQLException{
        String sql = "select * from warsztaty.exercise where id = ?";
        PreparedStatement preStat = conn.prepareStatement(sql);
        preStat.setInt(1, id);
        ResultSet rs = preStat.executeQuery();
        if(rs.next()){
            Exercise loaded = new Exercise();
            loaded.id = rs.getInt("id");
            loaded.description = rs.getString("description");
            loaded.title = rs.getString("title");
            return loaded;
        }
        return  null;
    }

    public void deleteExercise (Connection conn)throws SQLException{
        String sql = "delete from warsztaty.exercise where id = ?";
        PreparedStatement preStat = conn.prepareStatement(sql);
        preStat.setInt(1, this.id);
        preStat.executeUpdate();
        this.id = 0;
    }

    public void saveToDB(Connection conn)throws SQLException{
        if (this.id == 0) {
            String sql = "INSERT INTO warsztaty.exercise (title, description) VALUES (?, ?)";
            String generatedColumns[] = {"ID"};
            PreparedStatement preStat = conn.prepareStatement(sql, generatedColumns);
            preStat.setString(1, this.title);
            preStat.setString(2, this.description);
            preStat.executeUpdate();
            ResultSet rs = preStat.getGeneratedKeys();
            while (rs.next()) {
                this.id = rs.getInt(1);
            }
        }
        else{
            String sql = "update warsztaty.exercise set title = ?, description = ? where id = ?";
            PreparedStatement preStat = conn.prepareStatement(sql);
            preStat.setString(1,this.title);
            preStat.setString(2,this.description);
            preStat.setInt(3,this.id);
            preStat.executeUpdate();
        }
    }

}
