package dao;

import com.pluralsight.Film;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmDao {
    private final BasicDataSource dataSource;

    public FilmDao(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }
    public List<Film> getFilmsByActorId(int actorId){
    List <Film> films =new ArrayList<>();
    String sql = """
              SELECT f.film_id, f.title, f.description, f.release_year, f.length
                            FROM film f
                            JOIN film_actor fa ON f.film_id = fa.film_id
                            WHERE fa.actor_id = ?
            """;

        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1,actorId);
            ResultSet result =statement.executeQuery();

            while (result.next()) {
                films.add(new Film(
                        result.getInt("film_id"),
                        result.getString("title"),
                        result.getString("description"),
                        result.getInt("release_year"),
                        result.getInt("length")));
            }

            }catch (SQLException e){
                System.out.println("Error retrieving films: " + e.getMessage());
            }
return films;
        }
}
