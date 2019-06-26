package com.aaa.agent;

import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * 环绕增强
 */
public class TimeInterceptor {

    @RuntimeType
    public static Object intercept(@Origin Method method, @SuperCall Callable<?> callable) throws Exception {
        long start = System.currentTimeMillis();
        UUID uuid = UUID.randomUUID();
        try {
            // 原有函数执行
            return callable.call();
        } finally {
            long cost=System.currentTimeMillis() - start;
            System.out.println("================="+method.getName() + ": 执行时间 " + cost+ "ms");
            //sendJsonByPost(jsonStr);
        }
    }




    /**
     * 使用HttpClient发送http的post请求传递json
     */
    public static void sendJsonByPost(String jsonStr){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Post请求
        HttpPost httpPost = new HttpPost("http://localhost:8081/produce");
        httpPost.setEntity(new StringEntity(jsonStr,"UTF-8"));
       	httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        try {
            httpClient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
