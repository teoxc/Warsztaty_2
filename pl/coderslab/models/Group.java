package pl.coderslab.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Group {
    private int id;
    private String name;

    public Group (String name){
        this.name = name;
    }

    public Group(){}

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Group[] loadAll (Connection conn)throws SQLException{
        List<Group> groups = new ArrayList<>();
        String sql = "select * from warsztaty.user_group";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        while (rs.next()){
            Group loaded = new Group();
            loaded.id = rs.getInt("id");
            loaded.name = rs.getString("name");
            groups.add(loaded);
        }

        Group[] groupsArr = new Group[groups.size()];
        groupsArr = groups.toArray(groupsArr);
        return groupsArr;
    }

    public static Group loadById(Connection conn, int id) throws SQLException{
        String sql = "select * from warsztaty.user_group where id = ?";
        PreparedStatement preStat = conn.prepareStatement(sql);
        preStat.setInt(1, id);
        ResultSet rs = preStat.executeQuery();
        if(rs.next()){
            Group loaded = new Group();
            loaded.id = rs.getInt("id");
            loaded.name = rs.getString("name");
            return loaded;
        }
        return  null;
    }

    public void deleteGroup (Connection conn)throws SQLException{
        String sql = "delete from warsztaty.user_group where id = ?";
        PreparedStatement preStat = conn.prepareStatement(sql);
        preStat.setInt(1, this.id);
        preStat.executeUpdate();
        this.id = 0;
    }

    public void saveToDB(Connection conn)throws SQLException{
        if (this.id == 0) {
            String sql = "INSERT INTO warsztaty.user_group (name) VALUES (?)";
            String generatedColumns[] = {"ID"};
            PreparedStatement preStat = conn.prepareStatement(sql, generatedColumns);
            preStat.setString(1, this.name);
            preStat.executeUpdate();
            ResultSet rs = preStat.getGeneratedKeys();
            while (rs.next()) {
                this.id = rs.getInt(1);
            }
        }
        else{
            String sql = "update warsztaty.user_group set name = ? where id = ?";
            PreparedStatement preStat = conn.prepareStatement(sql);
            preStat.setString(1,this.name);
            preStat.setInt(2,this.id);
            preStat.executeUpdate();
        }
    }
}
