package com.solutiontech.alb_library.models;

public class PickModel {
    private int id;
    private String person;
    private String father;
    private String phone;
    private String adress;
    private String bookname;
    private String dates;

    public PickModel(int id, String person, String father, String phone, String adress, String bookname, String dates) {
        this.id = id;
        this.person = person;
        this.father = father;
        this.phone = phone;
        this.adress = adress;
        this.bookname = bookname;
        this.dates = dates;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }
}
