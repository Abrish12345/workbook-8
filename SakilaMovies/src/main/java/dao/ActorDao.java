package dao;

import com.pluralsight.Actor;
import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActorDao {
    private final BasicDataSource dataSource;

    public ActorDao(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Actor> searchByLastName(String lastName) {
        List<Actor> actors = new ArrayList<>();
        String sql = "SELECT actor_id, first_name, last_name FROM actor WHERE last_name = ?";

                try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
                    
        statement.setString(1, lastName);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                actors.add(new Actor(
                        result.getInt("actor_id"),
                        result.getString("first_name"),
                        result.getString("last_name")));

            }

        }catch (SQLException e){
    System.out.println("Error finding actors: " + e.getMessage());
        }
        return actors;
    }

}

