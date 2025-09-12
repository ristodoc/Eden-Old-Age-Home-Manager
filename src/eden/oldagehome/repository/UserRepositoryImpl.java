package eden.oldagehome.repository;

import eden.oldagehome.model.User;
import eden.oldagehome.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public User findById(String id) {
        String query = "SELECT * FROM users WHERE user_id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // For simplicity, we are not creating a specific user type here.
                    // In a real application, you would have a factory to create the correct user type.
                    return new User(resultSet.getString("user_id"),
                                  resultSet.getString("name"),
                                  resultSet.getString("email"),
                                  resultSet.getString("password"),
                                  resultSet.getString("user_type")) {
                        @Override
                        public void login() {}

                        @Override
                        public void logout() {}

                        @Override
                        public void changePassword(String newPassword) {}
                    };
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
