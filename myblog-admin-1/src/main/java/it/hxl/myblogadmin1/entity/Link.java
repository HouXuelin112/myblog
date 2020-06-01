package it.hxl.myblogadmin1.entity;

public class Link {
    private int id;
    private String name;
    private String icon; //对应网站图标的base64编码
    private String description;
    private int status;

    @Override
    public String toString() {
        return "Link{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", addr='" + addr + '\'' +
                '}';
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Link(int id, String name, String icon, String description, int status, String addr) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.description = description;
        this.status = status;
        this.addr = addr;
    }

    public Link() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    private String addr;

    public Link(int id, String name, String icon, String description, String addr) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.description = description;
        this.addr = addr;
    }
}
