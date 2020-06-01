package it.hxl.myblogprod.converter;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter1 extends AbstractHttpMessageConverter<Date> {

    public Date convert(String source) {
        System.out.println("进入转换器");
        String[] patterns = new String[]{"yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd", "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"};
        SimpleDateFormat simpleDateFormat ;
        Date date = null;
        for (String pattern: patterns){
            try {
                simpleDateFormat = new SimpleDateFormat(pattern);
                date = simpleDateFormat.parse(source);
                System.out.println(date);
                break;
            } catch (ParseException e) {
                System.out.println("error");
            }
        }
        return date;
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        System.out.println(clazz);
        return Date.class.isAssignableFrom(clazz);
    }

    @Override
    protected Date readInternal(Class<? extends Date> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        System.out.println("进入转换器");
        String source = StreamUtils.copyToString(inputMessage.getBody(), Charset.forName("UTF-8"));
        String[] patterns = new String[]{"yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd", "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"};
        SimpleDateFormat simpleDateFormat ;
        Date date = null;
        for (String pattern: patterns){
            try {
                simpleDateFormat = new SimpleDateFormat(pattern);
                date = simpleDateFormat.parse(source);
                System.out.println(date);
                break;
            } catch (ParseException e) {
                System.out.println("error");
            }
        }
        return date;
    }

    @Override
    protected void writeInternal(Date date, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

    }
}
