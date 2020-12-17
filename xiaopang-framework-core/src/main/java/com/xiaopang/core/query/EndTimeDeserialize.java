package com.xiaopang.core.query;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Date;

/**
 * @author necho.duan
 * @title: EndTimeDeserialize
 * @projectName xiaopang-framework
 * @description:
 * @date 2020/12/3 14:12
 */
public class EndTimeDeserialize extends StdDeserializer<Date> {
    protected EndTimeDeserialize() {
        super(Date.class);
    }

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String valueAsString = p.getValueAsString();
        if (StringUtils.isBlank(valueAsString)) {
            return null;
        } else {
            int dateTimeLength = valueAsString.length();
            if ("yyyy-MM-dd".length() == dateTimeLength) {
                return QueryDateUtil.parseDate(valueAsString + " 23:59:59", dateTimeLength);
            } else {
                String dateTime = valueAsString.replace("00:00:00", "23:59:59");
                return QueryDateUtil.parseDate(dateTime, dateTimeLength);
            }
        }
    }
}
