package com.fssa.livre.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fssa.livre.dao.exception.DAOException;
import com.fssa.livre.model.Readbooks;
import com.fssa.livre.model.User;
import com.fssa.livre.model.UserBooks;
import com.fssa.livre.util.ConnectionDb;

public class UserBooksDAO {
	
	
	  public boolean addUserBook(UserBooks userBooks) throws DAOException {
	        String insertQuery = "INSERT INTO user_books (user_id, readbook_id) VALUES (?, ?)";

	        try (Connection connection = ConnectionDb.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

	            preparedStatement.setInt(1, userBooks.getUserId());
	            preparedStatement.setInt(2, userBooks.getReadBookId());

	            int rowsAffected = preparedStatement.executeUpdate();
	            return rowsAffected > 0;
	        } catch (SQLException e) {
	            throw new DAOException(e);
	        }
	    }
	  
	  
	  public void updateUserBook(int userId, int bookId, long duration, String status) throws DAOException {
	        String updateQuery = "UPDATE user_books SET duration = ?, status = ? WHERE user_id = ? AND readbook_id = ?";

	        try (Connection connection = ConnectionDb.getConnection();
	             PreparedStatement pst = connection.prepareStatement(updateQuery)) {
	            pst.setLong(1, duration);
	            pst.setString(2, status);
	            pst.setInt(3, userId);
	            pst.setInt(4, bookId);

	            int rowsUpdated = pst.executeUpdate();

	            if (rowsUpdated == 0) {
	                throw new DAOException("Failed to update user book.");
	            }
	        } catch (SQLException e) {
	            throw new DAOException(e);
	        }
	    }
		  
	  public static boolean isUserBookExists(int userId, int readBookId) throws DAOException {
		    String query = "SELECT COUNT(*) FROM user_books WHERE user_id = ? AND readbook_id = ?";

		    try (Connection connection = ConnectionDb.getConnection();
		         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

		        preparedStatement.setInt(1, userId);
		        preparedStatement.setInt(2, readBookId);

		        try (ResultSet resultSet = preparedStatement.executeQuery()) {
		            return resultSet.next() && resultSet.getInt(1) > 0; // Simplified return statement
		        }
		    } catch (SQLException e) {
		        throw new DAOException(e);
		    }
		}

    
	  public static List<UserBooks> getUserBooksByUserId(int userId) throws DAOException {
	        List<UserBooks> userBooksList = new ArrayList<>();
	        String query = "SELECT * FROM user_books WHERE user_id = ?";

	        try (Connection connection = ConnectionDb.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

	            preparedStatement.setInt(1, userId);

	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                while (resultSet.next()) {
	                    UserBooks userBook = mapResultSetToUserBooks(resultSet);
	                    userBooksList.add(userBook);
	                }
	            }
	        } catch (SQLException e) {
	            throw new DAOException(e);
	        }

	        return userBooksList;
	    }
	  private static UserBooks mapResultSetToUserBooks(ResultSet resultSet) throws SQLException {
		    int userId = resultSet.getInt("user_id");
		    int readBookId = resultSet.getInt("readbook_id");
		    
		    return new UserBooks(userId, readBookId);
		}


}
