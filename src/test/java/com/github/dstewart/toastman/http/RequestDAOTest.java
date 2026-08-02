package com.github.dstewart.toastman.http;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RequestDAOTest {
    @Mock
    private RequestClient client;

    @InjectMocks
    private RequestDAO dao;

    @Test
    public void makeRequestSuccess() throws RequestException {
        when(client.sendRequest("https://google.com", "GET"))
                .thenReturn(new Success(200, "Success"));

        RequestDTO request = new RequestDTO("https://google.com", "GET");
        Response response = dao.makeHttpRequest(request);

        verify(client).sendRequest("https://google.com", "GET");
        assertInstanceOf(Success.class, response);
        assertEquals("200", response.display());

        var success = (Success) response;
        assertEquals(200, success.statusCode());
        assertEquals("Success", success.body());
    }

    @Test
    public void makeRequestFailure() throws RequestException {
        when(client.sendRequest("https://google.com", "GET"))
                .thenThrow(new RequestException("connection error"));

        RequestDTO request = new RequestDTO("https://google.com", "GET");
        Response response = dao.makeHttpRequest(request);

        verify(client).sendRequest("https://google.com", "GET");
        assertInstanceOf(Failure.class, response);
        assertEquals("Error: connection error", response.display());

        var failure = (Failure) response;
        assertEquals("connection error", failure.errorMessage());
    }
}
