package com.bliszkot.parsexml;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class ViewActivity extends AppCompatActivity {
    TextView ljson, lxml;
    int mode = 0;
    Element emp;
    JSONObject empobject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        ljson = findViewById(R.id.json_view);
        lxml = findViewById(R.id.xml_view);
        mode = getIntent().getIntExtra("mode", 0);
        if (mode == 1) {
            parsejson();
        } else if (mode == 2) {
            parsexml();
        }
    }

    private void parsexml() {
        try {
            InputStream inputStream = getAssets().open("input.xml");

            emp = (Element) DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(inputStream)
                    .getElementsByTagName("employee")
                    .item(0);

            lxml.setText("City Name: " + emp.getElementsByTagName("city_name").item(0).getTextContent() + "\n");
            xmlAppend("Latitude");
            xmlAppend("Longitude");
            xmlAppend("Temperature");
            xmlAppend("Humidity");
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
    }

    private void parsejson() {
        try {
            InputStream is = getAssets().open("input.json");

            byte[] data = new byte[is.available()];
            is.read(data);

            String readData = new String(data);
            empobject = new JSONObject(readData).getJSONObject("employee");

            ljson.setText("City Name: " + empobject.getString("city_name") + "\n");
            jsonAppend("Latitude");
            jsonAppend("Longitude");
            jsonAppend("Temperature");
            jsonAppend("Humidity");
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private void xmlAppend(String tagName) {
        lxml.append(tagName + ": " + emp.getElementsByTagName(tagName).item(0).getTextContent() + "\n ");
    }

    private void jsonAppend(String key) throws JSONException {
        ljson.append(key + ": " + empobject.getString(key) + "\n");
    }
}