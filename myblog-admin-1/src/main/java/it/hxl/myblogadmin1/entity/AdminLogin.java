package it.hxl.myblogadmin1.entity;

import java.util.Date;

public class AdminLogin {
    private int id;
    private int adminId;
    private String loginIp;
    private Date lastAccessTime;
    private int status;

    @Override
    public String toString() {
        return "AdminLogin{" +
                "id=" + id +
                ", adminId=" + adminId +
                ", loginIp='" + loginIp + '\'' +
                ", lastAccessTime=" + lastAccessTime +
                ", status=" + status +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(Date lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public AdminLogin() {
    }

    public AdminLogin(int id, int adminId, String loginIp, Date lastAccessTime, int status) {
        this.id = id;
        this.adminId = adminId;
        this.loginIp = loginIp;
        this.lastAccessTime = lastAccessTime;
        this.status = status;
    }
}
