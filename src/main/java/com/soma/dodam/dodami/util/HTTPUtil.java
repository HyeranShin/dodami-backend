//package com.soma.dodam.dodami.util;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.FormHttpMessageConverter;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.client.RequestCallback;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Arrays;
//
//public class HttpUtil {
//    public static void downloadPost(String url, String destPath, MultiValueMap<String, Object> parts, RestTemplate restOperations) {
//        HttpHeaders headers = HttpUtil.getHeaders();
//
//        // Optional Accept header
//        RequestCallback requestCallback = request -> {
//            request.getHeaders().addAll(headers);
//            request.getHeaders().setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM, MediaType.ALL));
//            FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
//            formHttpMessageConverter.setCharset(Charset.forName("EUC-KR"));
//            formHttpMessageConverter.write(parts, MediaType.APPLICATION_FORM_URLENCODED, request);
//        };
//
//        // Streams the response instead of loading it all in memory
//        ResponseExtractor<Void> responseExtractor = response -> {
//            // Here I write the response to a file but do what you like
//            String filename = response.getHeaders().getContentDisposition().getFilename();
//            Path path = Paths.get(destPath + "/" + filename);
//            Files.copy(response.getBody(), path);
//            return null;
//        };
//
//        restOperations.execute(URI.create(url), HttpMethod.POST, requestCallback, responseExtractor);
//    }
//
//    public static RestTemplate getRestTemplate(final HttpClientContext context) {
//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
//        factory.setReadTimeout(5000); // milliseconds
//        RestTemplate restOperations = new RestTemplate(factory);
//        restOperations.setRequestFactory(new HttpComponentsClientHttpRequestFactory() {
//            @Override
//            protected HttpContext createHttpContext(HttpMethod httpMethod, URI uri) {
//                if (context.getAttribute(HttpClientContext.COOKIE_STORE) == null) {
//                    context.setAttribute(HttpClientContext.COOKIE_STORE, new BasicCookieStore());
//                    Builder builder = RequestConfig.custom().setRedirectsEnabled(false);
//                    context.setRequestConfig(builder.build());
//                }
//                return context;
//            }
//        });
//
//        return restOperations;
//    }
//
//    public static HttpHeaders getHeaders() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Accept-Encoding", "gzip, deflate, sdch");
//        return headers;
//    }
//
//}
//
//
//
//출처: https://goni9071.tistory.com/entry/restTemplate-large-file-download-stream [고니의꿈]