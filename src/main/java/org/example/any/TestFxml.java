/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.any;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.json.XML;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author a.juravel
 */
public class TestFxml {

    static String xml = ""
            + "<employees>\n"
            + "    <numType>123</numType>\n"
            + "    <employee id=\"EMP0001\">\n"
            + "        <first_name>John</first_name>\n"
            + "        <last_name>Doe</last_name>\n"
            + "        <age>26</age>\n"
            + "    </employee>\n"
            + "    <employee id=\"EMP0002\">\n"
            + "        <first_name>Peter</first_name>\n"
            + "        <last_name>Parker</last_name>\n"
            + "        <age>30</age>\n"
            + "        <shedule>\n"
            + "            <from>11.11.2015</from>\n"
            + "            <to>12.12.2015</to>\n"
            + "        </shedule>\n"
            + "    </employee>\n"
            + "</employees>";

    static String json = ""
            + "{"
            + "\"string\":\"str\","
            + "\"integer\":10,"
            + "\"double\":10.123,"
            + "\"integerArea\":[0,1,10,100],"
            + "\"doubleArea\":[0,1.1,10.1,100.1],"
            + "\"objA\":{\"a1\":1,\"a2\":2,\"objB\":{\"b1\":1,\"b2\":2}}"
            + "}";

    static String xml2 = ""
            + "<?xml version='1.0' encoding='UTF-8'?>"
            + "<output>"
            + "  <RECEIVER AGT_ID=\"14849\" DATE_REPORT=\"27.05.2019 13:44:01\"/>"
            + "  <result sms_group_id=\"5232362204\">"
            + "    <sms id=\"5232362211\" smstype=\"SENDSMS\" phone=\"+79219879131\" sms_res_count=\"1\">" + "<![CDATA[BEELINE MSG 05 Test Тест]]>" + "</sms>"
            + "    <sms id=\"5232362213\" smstype=\"SENDSMS\" phone=\"+79312290310\" sms_res_count=\"1\">" + "<![CDATA[BEELINE MSG 05 Test Тест]]>" + "</sms>"
            + "    <sms id=\"5232362212\" smstype=\"SENDSMS\" phone=\"+79999999999\" sms_res_count=\"1\">" + "<![CDATA[BEELINE MSG 05 Test Тест]]>" + "</sms>"
            + "  </result>"
            + "</output>";


    public static void main (String[] args) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println("--------------------- strXml -> objJson -> strJson -> map -> (strXml, strJson)");
        System.out.println("--- xml2 =    " + xml2);
        JSONObject jObject2 = XML.toJSONObject(xml2);
        System.out.println("--- json2 =    " + jObject2.toString());
        Map map2 = (Map) objectMapper.readValue(jObject2.toString(), LinkedHashMap.class);
        System.out.println("--- map2 =    " + map2);
        String strXml2 = XML.toString(jObject2);
        System.out.println("--- xml2 =    " + strXml2);
        String strJson2 = objectMapper.writer().writeValueAsString(map2);
        System.out.println("--- json2 =    " + strJson2);

        System.out.println("--------------------- strXml -> objJson -> strJson -> map -> (strXml, strJson)");
        System.out.println("--- xml =    " + xml);
        JSONObject jObject = XML.toJSONObject(xml);
        System.out.println("--- json =    " + jObject.toString());
        Map map = (Map) objectMapper.readValue(jObject.toString(), LinkedHashMap.class);
        System.out.println("--- map =    " + map);
        String strXml = XML.toString(jObject);
        System.out.println("--- xml =    " + strXml);
        String strJson = objectMapper.writer().writeValueAsString(map);
        System.out.println("--- json =    " + strJson);

        System.out.println("--------------------- strJson -> objJson -> strJson -> map -> (strXml, strJson)");
        System.out.println("--- json0 =    " + json);
        JSONObject jObject0 = new JSONObject(json);
        Map map0 = (Map) objectMapper.readValue(jObject0.toString(), LinkedHashMap.class);
        System.out.println("--- map0 =    " + map0);
        String strXml0 = XML.toString(jObject0);
        System.out.println("--- xml0 =    " + strXml0);
        String strJson0 = objectMapper.writer().writeValueAsString(map0);
        System.out.println("--- json0 =    " + strJson0);
    }

}

