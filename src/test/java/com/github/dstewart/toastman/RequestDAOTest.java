package com.github.dstewart.toastman;

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
    public void makeRequestStatusCode() {
        when(client.sendRequest("http://google.com", "GET")).thenReturn(200);

        RequestDTO request = new RequestDTO("http://google.com", "GET");
        int statusCode = dao.makeHttpRequest(request);

        verify(client).sendRequest("http://google.com", "GET");
        assertEquals(200, statusCode);
    }
}
