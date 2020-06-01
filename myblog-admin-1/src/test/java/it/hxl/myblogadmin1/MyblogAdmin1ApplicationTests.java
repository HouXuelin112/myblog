package it.hxl.myblogadmin1;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyblogAdmin1ApplicationTests {

    @Test
    public void testT() throws EmailException {
        SimpleEmail email = new SimpleEmail();
        email.setHostName("smtp.163.com");
        email.setAuthentication("17857330861@163.com","OCALXAJIGATNWLAO");
        email.addTo("1281485540@qq.com", "hxlss");
        email.setFrom("17857330861@163.com", "hxl");
        email.setSubject("谁人定我去或留");
        email.setMsg("问句天几高，心中志比天更高，我有我心里故事，梦想有日达成");
        email.send();
    }

    @Test
    void contextLoads() {
    }

}
