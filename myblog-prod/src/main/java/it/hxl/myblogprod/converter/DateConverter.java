package it.hxl.myblogprod.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String source) {
        System.out.println("进入日期转换器");
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
}
