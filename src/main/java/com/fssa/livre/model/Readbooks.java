package com.fssa.livre.model;

public class Readbooks {
    private int readbook_id;
    private String bookname;
    private String imagelink;
    private String pdflink;
    private String category;

    // Getters and setters
    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getImagelink() {
        return imagelink;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }

    public String getPdflink() {
        return pdflink;
    }

    public void setPdflink(String pdflink) {
        this.pdflink = pdflink;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getReadbook_id() {
        return readbook_id;
    }

    public void getReadbook_id(int newBookId) {
        this.readbook_id = newBookId;
    }

    // Constructors
    public Readbooks(int readbook_id, String bookname, String imagelink, String pdflink, String category) {
        super();
        this.readbook_id = readbook_id;
        this.bookname = bookname;
        this.imagelink = imagelink;
        this.pdflink = pdflink;
        this.category = category;
    }

    public Readbooks() {
        // Default constructor
    }


    public Readbooks(String bookname, String imagelink, String pdflink, String category) {
    	   this.bookname = bookname;
           this.imagelink = imagelink;
           this.pdflink = pdflink;
           this.category = category;
	}
    

	// Override toString method 
    @Override
    public String toString() {
        return "Readbooks [bookname=" + bookname + ", imagelink=" + imagelink + ", pdflink=" + pdflink
                + ", category=" + category + "]";
    }
}