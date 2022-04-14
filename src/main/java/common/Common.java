package common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Common.
 */
public class Common {

    private static final Logger logger = LogManager.getLogger();

    /**
     * Postgre sql connection result set.
     *
     * @param connectionString   the connection string
     * @param connectionUser     the connection user
     * @param connectionPassword the connection password
     * @param sql                the sql
     * @return the result set
     */
    public ResultSet PostgreSqlConnection(String connectionString,
                                          String connectionUser,
                                          String connectionPassword,
                                          String sql
    ) {
        ResultSet resultSet = null;
        try (Connection connection = DriverManager.getConnection(connectionString,connectionUser,connectionPassword))
        {

            logger.info("Connected to PostgreSQL database!");
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
        return resultSet;
    }

    /**
     * Crete pet json map.
     *
     * @param id            the id
     * @param category_id   the category id
     * @param category_name the category name
     * @param name          the name
     * @param photoUrls     the photo urls
     * @param tag_id        the tag id
     * @param tag_name      the tag name
     * @param status        the status
     * @return the map
     */
    public Map<String, Object> cretePetJson (Object id,
                                             Integer category_id,
                                             String category_name,
                                             String name,
                                             String[] photoUrls,
                                             Integer tag_id,
                                             String tag_name,
                                             String status ) {



        Map<String, Object> category = new HashMap<>();
        category.put("id", category_id );
        category.put("name", category_name);


        Map<Object, Object>  tags = new HashMap<>();
        tags.put("id", tag_id);
        tags.put("name", tag_name);

        Object [] tags_arr = {tags};

        Map<String, Object> jsonAsMapPet = new HashMap<>();
        jsonAsMapPet.put("id", id);
        jsonAsMapPet.put("category", category);
        jsonAsMapPet.put("name", name);
        jsonAsMapPet.put("photoUrls", photoUrls);
        jsonAsMapPet.put("tags", tags_arr);
        jsonAsMapPet.put("status", status);

        return  jsonAsMapPet;
    }

    /**
     * Crete user json map.
     *
     * @param id         the id
     * @param username   the username
     * @param firstName  the first name
     * @param lastName   the last name
     * @param password   the password
     * @param phone      the phone
     * @param userStatus the user status
     * @return the map
     */
    public Map<String, Object> creteUserJson (Object id,
                                              String username,
                                              String firstName,
                                              String lastName,
                                              String password,
                                              String phone,
                                              int userStatus) {





        Map<String, Object> jsonAsMapUser = new HashMap<>();
        jsonAsMapUser.put("id", id);
        jsonAsMapUser.put("username", username);
        jsonAsMapUser.put("firstName", firstName);
        jsonAsMapUser.put("lastName", lastName);
        jsonAsMapUser.put("password", password);
        jsonAsMapUser.put("phone", phone);
        jsonAsMapUser.put("userStatus", userStatus);

        return  jsonAsMapUser;
    }
}
