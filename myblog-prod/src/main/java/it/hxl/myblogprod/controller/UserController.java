package it.hxl.myblogprod.controller;

import com.alibaba.fastjson.JSON;
import it.hxl.myblogprod.entity.Users;
import it.hxl.myblogprod.mapper.BlogMapper;
import it.hxl.myblogprod.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.imageio.stream.MemoryCacheImageInputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Controller
public class UserController {

    @Autowired
    public BlogMapper blogMapper;
    @Autowired
    private UserMapper userMapper;

    @ResponseBody
    @RequestMapping("/user/wxLogin")
    public void wxLogin (String code, String state) {
        System.out.println(code + ":" + state);
    }

    @RequestMapping("/getJson")
    @ResponseBody
    public Object getJson(HttpServletResponse response){
        response.addHeader("Access-Control-Allow-Origin", "*");
        Map map = new HashMap();
        map.put("code", 0);
        map.put("data", userMapper.findRecentVisitor());
        return JSON.toJSON(map);
    }

    @RequestMapping("/showDate")
    @ResponseBody
    public String showDate(@RequestBody MultipartFile file, HttpServletResponse response) throws IOException {
//        response.setHeader("Access-Control-Allow-Origin", "*");
        System.out.println(Arrays.toString(file.getBytes()));
        return "ssss";
    }

    @RequestMapping("/getDate")
//    @ResponseBody
    public String getDate(Model model) {
        model.addAttribute("date", new Date());
        return "showUsers";
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/insert")
    @ResponseBody
    public String insertImage(HttpServletResponse response, Model model) throws IOException {
        InputStream in = UserController.class.getResourceAsStream("/static/image/diary/bg5.jpg");
        MemoryCacheImageInputStream imageInputStream = new MemoryCacheImageInputStream(in);
        int length = in.available();
        byte[] bytes = new byte[length];
        imageInputStream.read(bytes);
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String bs64 = base64Encoder.encode(bytes);


        /*读取网络图片*/
/*        InputStream in = CommonUtils.getInputStreamFromUrl("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=49535227,1043391805&fm=26&gp=0.jpg");
        if (in == null) {
            return "error";
        }
        byte[] bytes = new byte[in.available()];
        in.read(bytes);*/


        jdbcTemplate.update("insert into links(icon, name, addr, description, status) values(?,?,?,?,?)",bs64, "hxl.blog", "http://hxl.blog.cn", "侯雪林的博客", 1 );
        in.close();
//        byte[] b = jdbcTemplate.queryForObject("select head from admin where id=1", new RowMapper<byte[]>() {
//            @Override
//            public byte[] mapRow(ResultSet rs, int rowNum) throws SQLException {
//                byte[] b = rs.getBytes("head");
//                return b;
//            }
//        });
//        String adminName = jdbcTemplate.queryForObject("select adminName from admin where id=1", new RowMapper<String>() {
//            @Override
//            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
//               return rs.getString("adminName");
//            }
//        });
//        Admin admin = jdbcTemplate.queryForObject("select * from admin where id=1", new RowMapper<Admin>() {
//            @Override
//            public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
//                return new Admin(
//                        rs.getInt("id"),
//                        rs.getString("adminName"),
//                        rs.getString("password"),
//                        rs.getString("realName"),
//                        rs.getString("phone"),
//                        rs.getString("email"),
//                        rs.getString("nickName"),
//                        rs.getBytes("head")
//                );
//            }
//        });
//        BASE64Encoder encoder = new BASE64Encoder();
//        String ba64 = encoder.encode(b);
//        System.out.println(ba64);
//        model.addAttribute("admin", admin);
//        model.addAttribute("bs64", ba64);
//        model.addAttribute("e", encoder);
//        model.addAttribute("adminName", adminName);


//        response.getOutputStream().write(b);
//        jdbcTemplate.update("insert into admin(head,adminName,password,realName,phone,email,nickName) values (?,?,?,?,?,?,?)", bytes, "hxl112", "qwe123", "侯雪林", "17857330861", "17857330861@163.com", "凌云");
//        return bytes;
        return "showUsers";
    }

    @RequestMapping("/showUsers")
    public String showUsers(Model model, @RequestParam(value = "id", defaultValue = "*")String id){
        System.out.println(jdbcTemplate.getDataSource().getClass().getPackage());
        System.out.println(id);
        String sql = "select * from users";
        if (id != null && !id.equals("*")){
            try{
                int i = Integer.parseInt(id);
                sql += " where id = " + id;
            }catch (Exception e){
                model.addAttribute("errorSelect", e.getMessage());
                return "showUsers";
            }
        }
        List<Users> users = jdbcTemplate.query(sql, new RowMapper<Users>() {
            @Override
            public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
                Users users = new Users();
                users.setId(rs.getInt("id"));
                users.setUsername(rs.getString("username"));
                users.setPassword(rs.getString("password"));
                return users;
            }
        });
        model.addAttribute("users1", users);
        return "showUsers";
    }
}
