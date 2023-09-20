package com.fssa.livre.services;
import java.sql.SQLException;
import java.util.List;

import com.fssa.livre.dao.ReadbooksDAO;
import com.fssa.livre.dao.UserBooksDAO;
import com.fssa.livre.dao.exception.DAOException;
import com.fssa.livre.model.Readbooks;
import com.fssa.livre.model.UserBooks;
import com.google.protobuf.ServiceException;

public class UserBooksService {
	
	
	
	
	
	
	   private UserBooksDAO userBooksDAO = new UserBooksDAO();

	    public boolean addUserBook(UserBooks userBooks) throws ServiceException {
	        if (userBooks == null || userBooks.getUserId() <= 0 || userBooks.getReadBookId() <= 0) {
	            throw new ServiceException("Invalid user book data");
	        }

	        try {
	            return userBooksDAO.addUserBook(userBooks);
	        } catch (DAOException e) {
	            throw new ServiceException(e);
	        }
	    }
	    
	    
	    
	    public static List<UserBooks> getUserBooksByUserId(int userId) throws ServiceException {
	        try {
	            UserBooksDAO userBooksDAO = new UserBooksDAO(); 
	            return userBooksDAO.getUserBooksByUserId(userId);
	        } catch (DAOException e) {
	            throw new ServiceException(e);
	        }
	    }


}
