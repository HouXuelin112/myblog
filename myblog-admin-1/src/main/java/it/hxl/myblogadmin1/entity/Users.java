package it.hxl.myblogadmin1.entity;

import java.util.Arrays;
import java.util.Date;

public class Users {

    private int id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String nickName;
    private byte[] head;
    private Date registerDate;
    private Date lastAccessTime;

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", nickName='" + nickName + '\'' +
                ", head=" + Arrays.toString(head) +
                ", registerDate=" + registerDate +
                ", lastAccessTime=" + lastAccessTime +
                '}';
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public byte[] getHead() {
        return head;
    }

    public void setHead(byte[] head) {
        this.head = head;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(Date lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public Users(int id, String username, String password, String phone, String email, String nickName, byte[] head, Date registerDate, Date lastAccessTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.nickName = nickName;
        this.head = head;
        this.registerDate = registerDate;
        this.lastAccessTime = lastAccessTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Users() {
    }

    public Users(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
