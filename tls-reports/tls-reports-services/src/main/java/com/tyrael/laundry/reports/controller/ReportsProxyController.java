package com.tyrael.laundry.reports.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.ProxyAuthenticationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class ReportsProxyController {

    private static final Logger LOG = LoggerFactory.getLogger(ReportsProxyController.class);

    @Autowired
    private Environment env;

    private String server;
    private int port;
    private RestTemplate restTemplate;

    @PostConstruct
    public void configureRestTemplate() {
        server = env.getProperty("saiku.host");
        port = env.getProperty("saiku.port", Integer.class);
        String username = env.getProperty("saiku.username");
        String password = env.getProperty("saiku.password");

        restTemplate = new RestTemplate();

        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(new AuthScope(server, port), new UsernamePasswordCredentials(username, password));
        HttpClientBuilder clientBuilder = HttpClientBuilder.create();

        clientBuilder.useSystemProperties();
        clientBuilder.setProxy(new HttpHost(server, port));
        clientBuilder.setDefaultCredentialsProvider(credsProvider);
        clientBuilder.setProxyAuthenticationStrategy(new ProxyAuthenticationStrategy());

        CloseableHttpClient client = clientBuilder.build();

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setHttpClient(client);

        restTemplate.setRequestFactory(factory);
    }

    @ResponseBody
    @RequestMapping("/saiku/**")
    public String mirrorRest(@RequestBody(required = false) String body, HttpMethod method, HttpServletRequest request,
            HttpServletResponse response) throws URISyntaxException, IOException {

        LOG.debug("Saiku request. body={}, method={}, request={}, response={}", body, method, request, response);

        URI uri = new URI("http", null, server, port, request.getRequestURI(), request.getQueryString(), null);

        ResponseEntity<String> responseEntity =
                restTemplate.exchange(uri, method, new HttpEntity<String>(body), String.class);

        return responseEntity.getBody();
    }

}
