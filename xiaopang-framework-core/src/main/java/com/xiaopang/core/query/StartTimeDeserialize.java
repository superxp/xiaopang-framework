package com.xiaopang.core.query;

/**
 * @author necho.duan
 * @title: StartTimeDeserialize
 * @projectName xiaopang-framework
 * @description:
 * @date 2020/12/3 14:11
 */
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;

public class StartTimeDeserialize extends StdDeserializer<Date> {
    protected StartTimeDeserialize() {
        super(Date.class);
    }

    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String dateTime = p.getValueAsString();
        if (StringUtils.isBlank(dateTime)) {
            return null;
        } else {
            int dateTimeLength = dateTime.length();
            return QueryDateUtil.parseDate(dateTime, dateTimeLength);
        }
    }
}
