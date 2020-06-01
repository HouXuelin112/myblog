package it.hxl.myblogprod.entity;

import java.util.Arrays;

public class Admin {
    private int id;
    private String adminName;
    private String password;
    private String realName;
    private String phone;
    private String email;
    private String nickName;
    private byte[] head;

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", adminName='" + adminName + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", nickName='" + nickName + '\'' +
                ", head=" + Arrays.toString(head) +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
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

    public Admin() {
    }

    public Admin(int id, String adminName, String password, String realName, String phone, String email, String nickName, byte[] head) {
        this.id = id;
        this.adminName = adminName;
        this.password = password;
        this.realName = realName;
        this.phone = phone;
        this.email = email;
        this.nickName = nickName;
        this.head = head;
    }
}
