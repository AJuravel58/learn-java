package org.example.any;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedList;

public class ConvertJson {
    public static void main(String[] args) throws JsonProcessingException {
        String jstr = ""
        + "["
                + "{'a': 'a1', 'b': 123, 'c':123.456, 'd':true}"
                + ","
                + "{'a': 'a2', 'b': 234, 'c':234.456}"
        + "]";

        System.out.println(" jstr = " + jstr);
        jstr = jstr.replace("'", "\"");
        System.out.println(" jstr = " + jstr);

        ObjectMapper objectMapper = new ObjectMapper();

        // TO Object
        Object ostr = objectMapper.readValue(jstr, LinkedList.class);
        System.out.println(" ostr = " + ostr);

        // TO Json
        jstr = objectMapper.writeValueAsString(ostr).toString();
        System.out.println(" jstr = " + jstr);
    }
}
