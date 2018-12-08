//package xb.dev.tools.common.web.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.convert.converter.Converter;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//@Configuration
//public class Config implements Converter<String, Date> {
//    private static final String DATE_TIME_FORMATE = "yyyy-MM-dd HH:mm:ss";
//    @Override
//    public Date convert(String source) {
//        Date date = null;
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME_FORMATE);
//        try{
//            date = simpleDateFormat.parse(source);
//        }catch (ParseException e){
//            e.printStackTrace();
//        }
//        return date;
//    }
//}
