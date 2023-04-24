package org.example.any;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedList;

public class ConvertJson {
    public static void main(String[] args) throws JsonProcessingException {
        String jstr = ""
        + "["
                + "{'a': 'a1', 'b': 123, 'c':123.456, 'd':true, 'ts':['a','b','c'], 'ti':[11,22,33]}"
                + ","
                + "{'a': 'a2', 'b': 234, 'c':234.456, 'td':[1.1, 2.2, 3.3]}"
        + "]";

        System.out.println(" jstr = " + jstr);
        jstr = jstr.replace("'", "\"");
        System.out.println(" jstr = " + jstr);

        ObjectMapper objectMapper = new ObjectMapper();

        // TO Object
        Object jobj = objectMapper.readValue(jstr, LinkedList.class);
        System.out.println(" jobj = " + jobj);
        System.out.println(" jobj.class = " + jobj.getClass());

        // TO Json
        jstr = objectMapper.writeValueAsString(jobj).toString();
        System.out.println(" jstr = " + jstr);
    }
}
