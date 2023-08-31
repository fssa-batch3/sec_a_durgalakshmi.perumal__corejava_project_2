package com.fssa.livre.services;

import java.util.List;

import com.fssa.livre.dao.UserDAO;
import com.fssa.livre.dao.exception.DAOException;
import com.fssa.livre.model.User;
import com.fssa.livre.services.exceptions.ServiceException;
import com.fssa.livre.validation.UserValidator;
import com.fssa.livre.validation.exceptions.InvalidUserException;

public class UserService {

    /**
     * Registers a new user.
     *
     * @param user,The user to be registered.
     * @return True if the user is registered successfully, false otherwise.
     * @throws ServiceException If an error occurs while validating or registering the user.
     */
    public boolean registerUser(User user) throws ServiceException {
        UserDAO userDAO = new UserDAO();
        try {
            UserValidator.validUser(user);
            if (userDAO.register(user)) {
                System.out.println(user.getEmail() + " successfully added");
                return true;
            } else {
                System.out.println("Registration not successful");
                return false;
            }
        } catch (DAOException | InvalidUserException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Authenticates a user during login.
     *
     * @param email, The email of the user.
     * @param password The password of the user.
     * @return True if the login credentials are valid, false otherwise.
     * @throws ServiceException If an error occurs while validating the login credentials.
     */
    public boolean loginUser(String email, String password) throws ServiceException {
        try {
            return UserValidator.validateLogin(email, password);
        } catch (InvalidUserException e) {
            throw new ServiceException(e);
        }
    }
     
    /**
     * @return
     * @throws ServiceException
     */
    public List<User> getAllUserList() throws ServiceException{
    	try {
			return UserDAO.getAllUsers();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
    }
}