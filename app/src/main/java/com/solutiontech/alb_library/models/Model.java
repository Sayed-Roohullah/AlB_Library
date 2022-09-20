package com.solutiontech.alb_library.models;

public class Model{
    private int id;
    private byte[]proavatar;
    private String bookname;
    private String authorname;
    private String shell;
    private String dates;


    public Model(int id, byte[] proavatar, String bookname, String authorname, String shell, String dates) {
        this.id = id;
        this.proavatar = proavatar;
        this.bookname = bookname;
        this.authorname = authorname;
        this.shell = shell;
        this.dates = dates;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getProavatar() {
        return proavatar;
    }

    public void setProavatar(byte[] proavatar) {
        this.proavatar = proavatar;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public String getShell() {
        return shell;
    }

    public void setShell(String shell) {
        this.shell = shell;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }
}