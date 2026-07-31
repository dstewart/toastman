package com.github.dstewart.toastman;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestDAOTest {
    @Test
    public void makeRequestStatusCode() {
        RequestDAO dao = new RequestDAO();
        RequestDTO request = new RequestDTO("http://google.com", "GET");
        assertEquals(301, dao.makeHttpRequest(request));
    }
}
