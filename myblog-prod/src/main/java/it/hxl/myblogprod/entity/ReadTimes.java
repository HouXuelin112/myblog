package it.hxl.myblogprod.entity;

public class ReadTimes {

    private int id;
    private Blog blog;
    private String macAddr;

    @Override
    public String toString() {
        return "ReadTimes{" +
                "id=" + id +
                ", blog=" + blog +
                ", macAddr='" + macAddr + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public String getMacAddr() {
        return macAddr;
    }

    public void setMacAddr(String macAddr) {
        this.macAddr = macAddr;
    }

    public ReadTimes() {
    }

    public ReadTimes(int id, Blog blog, String macAddr) {
        this.id = id;
        this.blog = blog;
        this.macAddr = macAddr;
    }
}
