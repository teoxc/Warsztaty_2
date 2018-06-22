package pl.coderslab.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    private int id;
    private String created;
    private String updated;
    private String description;
    private int exerciseId;
    private int usersId;

    public Solution(){}
    public Solution(String description,int usersId, int exerciseId){
        this.description = description;
        this.usersId = usersId;
        this.exerciseId = exerciseId;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public int getUsersId() {
        return usersId;
    }

    public String getCreated() {
        return created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void saveToDB (Connection conn)throws SQLException{
        if(this.id == 0){
            String sql = "insert into warsztaty.solution (description, created, exercise_id, users_id, updated) " +
                    "values (?, now(), ?, ?, now())";
            String generatedColumns[] = {"ID"};
            PreparedStatement preStat = conn.prepareStatement(sql, generatedColumns);
            preStat.setInt(2,this.exerciseId);
            preStat.setInt(3,this.usersId);
            preStat.setString(1, this.description);
            preStat.executeUpdate();
            ResultSet rs = preStat.getGeneratedKeys();
            while(rs.next()){
                this.id = rs.getInt(1);
            }
        }
        else{
            String sql = "update warsztaty.solution set description = ?, updated = now() where id = ?";
            PreparedStatement preStat = conn.prepareStatement(sql);
            preStat.setString(1, this.description);
            preStat.setInt(2, this.id);
            preStat.executeUpdate();
        }
    }

    public static Solution[] loadAll (Connection conn)throws SQLException {
        List<Solution> exercises = new ArrayList<>();
        String sql = "select * from warsztaty.solution";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        while (rs.next()){
            Solution loaded = new Solution();
            loaded.id = rs.getInt("id");
            loaded.created = rs.getString("created");
            loaded.description = rs.getString("description");
            loaded.exerciseId = rs.getInt("exercise_id");
            loaded.usersId = rs.getInt("users_id");
            loaded.updated = rs.getString("updated");
            exercises.add(loaded);
        }

        Solution[] exercisesArr = new Solution[exercises.size()];
        exercisesArr = exercises.toArray(exercisesArr);
        return exercisesArr;
    }

    public static Solution loadById(Connection conn, int id) throws SQLException{
        String sql = "select * from warsztaty.solution where id = ?";
        PreparedStatement preStat = conn.prepareStatement(sql);
        preStat.setInt(1, id);
        ResultSet rs = preStat.executeQuery();
        if(rs.next()){
            Solution loaded = new Solution();
            loaded.id = rs.getInt("id");
            loaded.created = rs.getString("created");
            loaded.description = rs.getString("description");
            loaded.exerciseId = rs.getInt("exercise_id");
            loaded.usersId = rs.getInt("users_id");
            loaded.updated = rs.getString("updated");
            return loaded;
        }
        return  null;
    }

    public void deleteSolution (Connection conn)throws SQLException{
        String sql = "delete from warsztaty.solution where id = ?";
        PreparedStatement preStat = conn.prepareStatement(sql);
        preStat.setInt(1, this.id);
        preStat.executeUpdate();
        this.id = 0;
    }

    public static Solution[] loadAllByUserId(Connection conn,int userId) throws SQLException{
        String sql = "select * from warsztaty.solution where users_id = ?";
        PreparedStatement preStat = conn.prepareStatement(sql);
        preStat.setInt(1, userId);
        ResultSet rs = preStat.executeQuery();
        List<Solution> sols = new ArrayList<>();
        while(rs.next()){
            Solution sol = new Solution();
            sol.updated = rs.getString("updated");
            sol.usersId = rs.getInt("users_id");
            sol.exerciseId = rs.getInt("exercise_id");
            sol.created = rs.getString("created");
            sol.description = rs.getString("description");
            sol.id = rs.getInt("id");
            sols.add(sol);
        }
        Solution[] solsArr = new Solution[sols.size()];
        solsArr = sols.toArray(solsArr);
        return solsArr;
    }

    public static Solution[] loadAllByExerciseId (Connection conn,int exerciseId) throws SQLException{
        String sql = "select * from warsztaty.solution where exercise_id = ? ORDER BY updated desc";
        PreparedStatement preStat = conn.prepareStatement(sql);
        preStat.setInt(1, exerciseId);
        ResultSet rs = preStat.executeQuery();
        List<Solution> sols = new ArrayList<>();
        while(rs.next()){
            Solution sol = new Solution();
            sol.updated = rs.getString("updated");
            sol.usersId = rs.getInt("users_id");
            sol.exerciseId = rs.getInt("exercise_id");
            sol.created = rs.getString("created");
            sol.description = rs.getString("description");
            sol.id = rs.getInt("id");
            sols.add(sol);
        }
        Solution[] solsArr = new Solution[sols.size()];
        solsArr = sols.toArray(solsArr);
        return solsArr;
    }


}
