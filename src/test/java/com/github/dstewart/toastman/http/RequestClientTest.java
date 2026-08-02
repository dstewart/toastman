package com.github.dstewart.toastman.http;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RequestClientTest {

    @Mock
    private HttpClient httpClient;

    @Mock
    private HttpResponse<String> httpResponse;

    @InjectMocks
    private RequestClient requestClient;

    @Test
    public void sendRequestSuccess() throws Exception {
        when(httpResponse.statusCode()).thenReturn(200);
        when(httpResponse.body()).thenReturn("Success");

        when(httpClient.send(HttpRequest.newBuilder()
                .uri(URI.create("https://google.com"))
                .GET()
                .header("Accept", "application/json")
                .build(), HttpResponse.BodyHandlers.ofString()))
                .thenReturn(httpResponse);

        var response = requestClient.sendRequest("https://google.com", "GET");
        assertInstanceOf(Success.class, response);
        assertEquals("200", response.display());

        var success = (Success) response;
        assertEquals(200, success.statusCode());
        assertEquals("Success", success.body());
    }

    @Test
    public void sendRequestFailure() throws Exception {
        var connectException = new ConnectException("Host not reachable");
        when(httpClient.send(HttpRequest.newBuilder()
                .uri(URI.create("https://google.com"))
                .GET()
                .header("Accept", "application/json")
                .build(), HttpResponse.BodyHandlers.ofString()))
                .thenThrow(connectException);

        var exception = assertThrows(RequestException.class, () -> requestClient.sendRequest("https://google.com", "GET"));
        assertEquals(connectException.getClass().getSimpleName(), exception.getMessage());
    }
}
