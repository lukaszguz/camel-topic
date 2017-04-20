package pl.guz.serializer;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.core.convert.converter.Converter;

public class JodaDateTimeToStringConverter implements Converter<DateTime, String> {

    @Override
    public String convert(DateTime dateTime) {
        return "2015-02-16 05:17:03+0100";
//        return ISODateTimeFormat.dateTime()
//                                .print(dateTime);
    }
}
