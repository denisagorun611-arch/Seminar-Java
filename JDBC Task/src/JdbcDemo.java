import java.sql.*;

public class JdbcDemo {

    private static final String URL      = "jdbc:h2:mem:moviesdb;DB_CLOSE_DELAY=-1";
    private static final String USER     = "sa";
    private static final String PASSWORD = "";

    private static void createTable(Connection conn) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS movies (
                    id     INT AUTO_INCREMENT PRIMARY KEY,
                    title  VARCHAR(200) NOT NULL,
                    genre  VARCHAR(100),
                    year   INT,
                    rating DOUBLE
                )
                """;
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table 'movies' created.");
        }
    }

    private static void insertMovie(Connection conn, String title, String genre, int year, double rating)
            throws SQLException {
        String sql = "INSERT INTO movies (title, genre, year, rating) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.setString(2, genre);
            ps.setInt(3, year);
            ps.setDouble(4, rating);
            ps.executeUpdate();
            System.out.println("Inserted: " + title);
        }
    }

    private static void printAllMovies(Connection conn) throws SQLException {
        String sql = "SELECT id, title, genre, year, rating FROM movies ORDER BY id";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            System.out.println("\n ID | Title                        | Genre      | Year | Rating");
            System.out.println("----+------------------------------+------------+------+-------");
            while (rs.next()) {
                System.out.printf(" %-3d| %-29s| %-11s| %-5d| %.1f%n",
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("genre"),
                        rs.getInt("year"),
                        rs.getDouble("rating"));
            }
        }
    }

    private static void findMovieById(Connection conn, int id) throws SQLException {
        String sql = "SELECT * FROM movies WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Found: [" + rs.getInt("id") + "] " +
                            rs.getString("title") + " (" + rs.getInt("year") + ") " +
                            "- " + rs.getString("genre") + " - Rating: " + rs.getDouble("rating"));
                } else {
                    System.out.println("No movie found with id " + id);
                }
            }
        }
    }

    private static void updateMovieRating(Connection conn, int id, double newRating) throws SQLException {
        String sql = "UPDATE movies SET rating = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, newRating);
            ps.setInt(2, id);
            int rows = ps.executeUpdate();
            System.out.println("Updated rating for id=" + id + ". Rows affected: " + rows);
        }
    }

    private static void deleteMovie(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM movies WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println("Deleted movie id=" + id + ". Rows affected: " + rows);
        }
    }

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connected to H2 in-memory database.\n");

            createTable(conn);

            System.out.println("\n=== INSERT ===");
            insertMovie(conn, "Inception",       "Sci-Fi",   2010, 8.8);
            insertMovie(conn, "The Godfather",   "Crime",    1972, 9.2);
            insertMovie(conn, "Interstellar",    "Sci-Fi",   2014, 8.6);
            insertMovie(conn, "Parasite",        "Thriller", 2019, 8.5);
            insertMovie(conn, "The Dark Knight", "Action",   2008, 9.0);

            System.out.println("\n=== READ ALL ===");
            printAllMovies(conn);

            System.out.println("\n=== FIND BY ID ===");
            findMovieById(conn, 3);
            findMovieById(conn, 99);

            System.out.println("\n=== UPDATE ===");
            updateMovieRating(conn, 3, 9.0);
            printAllMovies(conn);

            System.out.println("\n=== DELETE ===");
            deleteMovie(conn, 4);
            printAllMovies(conn);

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}