package ru.inno.todo.apache;

import org.apache.http.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class MyResponseInterceptor implements HttpResponseInterceptor {
    @Override
    public void process(HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException {
        System.out.println(httpResponse.getStatusLine());
        for (Header header : httpResponse.getAllHeaders()) {
            System.out.println(header);
        }

        System.out.println("BODY: ===>");
        if (httpResponse.getEntity() == null) {
            System.out.println("NO BODY");
        } else {
            HttpEntity entity = httpResponse.getEntity();
            String body = EntityUtils.toString(entity);
            System.out.println(body);

            StringEntity newBody = new StringEntity(body);
            httpResponse.setEntity(newBody);
        }
        // Можем модифицировать ответ
//        httpResponse.removeHeaders("Content-Length"); //2
//        httpResponse.addHeader("Content-Length", "0");

    }
}
