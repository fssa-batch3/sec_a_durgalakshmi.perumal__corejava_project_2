package com.fssa.livre.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.fssa.livre.model.Readbooks;
import com.fssa.livre.services.ReadbooksService;
import com.fssa.livre.services.exceptions.ServiceException;

public class TestAddbooksFeature {
@Test
	public void testCreateProduct() {
		ReadbooksService readbooksService = new ReadbooksService();
        Readbooks readbooks = new Readbooks(15, "Sun sparks", "imagelink-url", "pdf-link-url", "ADVENTURE");
		
		try {
			assertTrue(readbooksService.AddReadBooks(readbooks));
		}catch(ServiceException e){
			e.printStackTrace();
			fail();
		}
	}


// null value 
@Test
public void testNullProduct() { 
	ReadbooksService readbooksService = new ReadbooksService();
	Readbooks readbooks = null;
	try {
		assertFalse(readbooksService.AddReadBooks(readbooks));
	} catch (ServiceException e) {
		e.printStackTrace();
	}
}

// invalid book name 

@Test
public void testInavlidBookName() {
	ReadbooksService readbooksService = new ReadbooksService();
    Readbooks readbooks = new Readbooks(1, "1D@rk", "imagelink-url", "pdf-link-url", "TRILLER");
	
	try {
		assertTrue(readbooksService.AddReadBooks(readbooks));
	}catch(ServiceException e){
		e.printStackTrace();

	}
}

// invalid category

@Test
public void testInValidCategory() {
	ReadbooksService readbooksService = new ReadbooksService();
    Readbooks readbooks = new Readbooks(1, "you", "imagelink-url", "pdf-link-url", "WOw");
	
	try {
		assertTrue(readbooksService.AddReadBooks(readbooks));
	}catch(ServiceException e){
		e.printStackTrace();
	
	}
}


}

