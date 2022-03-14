package br.com.pocheroku.utiltest;


import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

public class WireMockstubs {

    public static void getChatBotReturn(String message, String bodyFile) {
        String url = String.format("/get?bid=BBBBBBB&key=AAAAAAA&uid=CCCCCCC&msg=%s",
                message);

        WireMock.stubFor(WireMock.get(url).willReturn(aResponse()
                .withBodyFile(bodyFile)
                .withHeader("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE)));
    }
}
