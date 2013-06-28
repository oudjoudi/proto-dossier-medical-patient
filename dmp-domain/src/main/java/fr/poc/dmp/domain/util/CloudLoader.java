package fr.poc.dmp.domain.util;

import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.InitializingBean;

public class CloudLoader implements InitializingBean
{
    private Properties properties;

    @Override
    public void afterPropertiesSet() throws Exception
    {
        System.out.println("Loading application...");

        String json = System.getenv("VCAP_SERVICES");

        System.out.println("JSON String :: " + json);

        JSONObject o = new JSONObject(json);
        JSONArray postgres = o.getJSONArray("postgresql-9.1");
        JSONObject credentials = postgres.getJSONObject(0).getJSONObject("credentials");
        properties = new Properties();
        properties.put("hibernate.connection.url", "jdbc:postgresql://" + credentials.getString("host") + ":" + credentials.getString("port") + "/"
                + credentials.getString("name"));
        properties.put("hibernate.connection.username", credentials.getString("user"));
        properties.put("hibernate.connection.password", credentials.getString("password"));
    }

    public Properties createProperties()
    {
        return properties;
    }
}
