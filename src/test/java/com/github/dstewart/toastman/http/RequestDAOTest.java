package com.github.dstewart.toastman.http;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RequestDAOTest {
    @Mock
    private RequestClient client;

    @InjectMocks
    private RequestDAO dao;

    @Test
    public void makeRequestResponse() {
        when(client.sendRequest("http://google.com", "GET"))
                .thenReturn(new Response(200, "\"data\""));

        RequestDTO request = new RequestDTO("http://google.com", "GET");
        Response response = dao.makeHttpRequest(request);

        verify(client).sendRequest("http://google.com", "GET");
        assertEquals(200, response.statusCode());
        assertEquals("\"data\"", response.body());
    }
}
