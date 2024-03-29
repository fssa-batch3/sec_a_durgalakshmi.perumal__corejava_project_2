package com.fssa.livre.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fssa.livre.dao.exception.DAOException;
import com.fssa.livre.model.Readbooks;
import com.fssa.livre.util.ConnectionDb;

public class ReadbooksDAO {

  

	//AddReadBooks  

 
/** 
 * Adds a new Readbooks record to the database.
 *
 * @param readbooks The Readbooks object containing book information.
 * @return True if the record was added successfully, false otherwise.
 * @throws DAOException If there is an issue with the database operation.
 */
public boolean addReadBooks(Readbooks readbooks) throws DAOException { 
    String insertQuery = "INSERT INTO readbooks (bookname, imagelink, pdflink, category) VALUES (?, ?, ?, ?)";

    try (Connection connection = ConnectionDb.getConnection();
         PreparedStatement pst = connection.prepareStatement(insertQuery);) {

        pst.setString(1, readbooks.getBookname());
        pst.setString(2, readbooks.getImagelink());
        pst.setString(3, readbooks.getPdflink());
        pst.setString(4, readbooks.getCategory());

        int rows = pst.executeUpdate(); 
        
        return (rows == 1);
    } catch (SQLException e) {
        throw new DAOException(e);
    }
}	
	
	//update ReadBooks
	
/**
 * Updates an existing book's information in the database.
 *
 * @param readbooks The Readbooks object containing updated book information.
 * @return True if the record was updated successfully, false otherwise.
 * @throws DAOException If there is an issue with the database operation.
 */
public boolean updateBooks(Readbooks readbooks) throws DAOException {
    String updateQuery = "UPDATE readbooks SET bookname=?, imagelink=?, pdflink=?, category=? WHERE readbook_id=?";

    try (Connection connection = ConnectionDb.getConnection();
         PreparedStatement updatepst = connection.prepareStatement(updateQuery);) {

        updatepst.setString(1, readbooks.getBookname());
        updatepst.setString(2, readbooks.getImagelink());
        updatepst.setString(3, readbooks.getPdflink());
        updatepst.setString(4, readbooks.getCategory());
        updatepst.setInt(5, readbooks.getReadbookid());

        int rows = updatepst.executeUpdate();

        return (rows == 1);
    } catch (SQLException e) {
        throw new DAOException(e);
    }
}


	
	
	//DeleteReadBooks
/**
 * Deletes a book record from the database based on the provided ID.
 *
 * @param id The ID of the book record to be deleted.
 * @return True if the record was deleted successfully, false otherwise.
 * @throws DAOException If there is an issue with the database operation.
 */

public boolean deleteBooks(int id) throws DAOException {
    String deleteQuery = "DELETE FROM readbooks WHERE readbook_id = ?";

    try (Connection connect = ConnectionDb.getConnection();
         PreparedStatement deletePst = connect.prepareStatement(deleteQuery);) {

        deletePst.setInt(1, id);
        
        int rows = deletePst.executeUpdate();

        return (rows == 1);
    } catch (SQLException e) {
        throw new DAOException(e);
    }
}


/**
 * Searches for readbooks by category in the database.
 *
 * @param category The category to search for readbooks.
 * @return A list of matching Readbooks objects.
 * @throws DAOException If there is an issue with the database operation.
 */
public static List<Readbooks> searchReadbooksByCategory(String category) throws DAOException {
    String searchQuery = "SELECT * FROM readbooks WHERE category = ?";
    List<Readbooks> result = new ArrayList<>();

    try (Connection connection = ConnectionDb.getConnection();
         PreparedStatement pst = connection.prepareStatement(searchQuery);) {

    	
        pst.setString(1, category);

        try (ResultSet resultSet = pst.executeQuery()) {
            while (resultSet.next()) {
                Readbooks readbooks = new Readbooks();
                readbooks.setBookname(resultSet.getString("bookname"));
                readbooks.setImagelink(resultSet.getString("imagelink"));
                readbooks.setPdflink(resultSet.getString("pdflink"));
                readbooks.setCategory(resultSet.getString("category"));
                result.add(readbooks);
            }
        }
    } catch (SQLException e) {
        throw new DAOException(e);
    }

    return result;
}


/**
 * Retrieves a list of all readbooks from the database.
 *
 * @return A list of Readbooks objects representing all readbooks.
 * @throws DAOException If an error occurs while accessing the database.
 */
public static List<Readbooks> getAllReadbooks() throws DAOException {
    final String selectAllReadbooksQuery = "SELECT * FROM readbooks";
    List<Readbooks> readbooksList = new ArrayList<>();
    
    try (Connection connect = ConnectionDb.getConnection();
         Statement statement = connect.createStatement();
         ResultSet rs = statement.executeQuery(selectAllReadbooksQuery)) {
        
        while (rs.next()) {
            int readbookId = rs.getInt("readbook_id");
            String bookname = rs.getString("bookname");
            String imagelink = rs.getString("imagelink");
            String pdflink = rs.getString("pdflink");
            String category = rs.getString("category");
            
            Readbooks readbooks = new Readbooks(readbookId, bookname, imagelink, pdflink, category);
            readbooksList.add(readbooks);
        }
    } catch (SQLException e) {
        throw new DAOException(e);
    }
    
    return readbooksList;
} 

//read readbooks

/**
* Retrieves a Readbooks object from the database based on the given ID.
*
* @param id The ID of the Readbooks record to retrieve.
* @return The retrieved Readbooks object, or null if not found.
* @throws DAOException If there's a database-related error.
*/
public static Readbooks getReadBooksById(int id) throws DAOException {
String selectQuery = "SELECT * FROM readbooks WHERE readbook_id = ?";

try (Connection connection = ConnectionDb.getConnection();
     PreparedStatement pst = connection.prepareStatement(selectQuery);) {
    pst.setInt(1, id);
    ResultSet rs = pst.executeQuery();

    if (rs.next()) {
        Readbooks readbooks = new Readbooks();
        readbooks.setReadbookid(rs.getInt("readbook_id"));
        readbooks.setBookname(rs.getString("bookname"));
        readbooks.setImagelink(rs.getString("imagelink"));
        readbooks.setPdflink(rs.getString("pdflink"));
        readbooks.setCategory(rs.getString("category"));

        return readbooks;
    }
} catch (SQLException e) {
    throw new DAOException(e);
}
return null;
}


public List<Readbooks> getBooksByUser(int userId) throws DAOException {
    String selectQuery = "SELECT * FROM readbooks WHERE user_id = ?"; // Assuming there's a user_id column in your database table.

    List<Readbooks> booksList = new ArrayList<>();

    try (Connection connection = ConnectionDb.getConnection();
         PreparedStatement pst = connection.prepareStatement(selectQuery);) {
        pst.setInt(1, userId);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            Readbooks readbooks = new Readbooks();
            readbooks.setReadbookid(rs.getInt("readbook_id"));
            readbooks.setBookname(rs.getString("bookname"));
            readbooks.setImagelink(rs.getString("imagelink"));
            readbooks.setPdflink(rs.getString("pdflink"));
            readbooks.setCategory(rs.getString("category"));

            booksList.add(readbooks);
        }
    } catch (SQLException e) {
        throw new DAOException(e);
    }

    return booksList;
}

	
}
