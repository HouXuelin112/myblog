package it.hxl.myblogprod.entity;

import sun.misc.BASE64Decoder;

import java.io.IOException;
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
    private Date birthday;
    private int age;

    public void setHeadBase64(String base64) throws IOException {
        if (!"".equals(base64)) {
            this.head = new BASE64Decoder().decodeBuffer(base64);
        }
    }

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
                ", birthday=" + birthday +
                ", age=" + age +
                '}';
    }

    public Date getBirthday() {

        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Users(int id, String username, String password, String phone, String email, String nickName, byte[] head, Date registerDate, Date lastAccessTime, Date birthday, int age) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.nickName = nickName;
        this.head = head;
        this.registerDate = registerDate;
        this.lastAccessTime = lastAccessTime;
        this.birthday = birthday;
        this.age = age;
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
