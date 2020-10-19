package edu.glut.tiny.data.entity;


public class ContactItem {
    private String username;
    private String createDate;
    private boolean isAdd = false;

    public ContactItem() {
    }

    public ContactItem(String username, String createDate, boolean isAdd) {
        this.username = username;
        this.createDate = createDate;
        this.isAdd = isAdd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public boolean isAdd() {
        return isAdd;
    }

    public void setAdd(boolean add) {
        isAdd = add;
    }
}
