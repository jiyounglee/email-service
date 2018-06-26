package com.challenge.emailservice.service.sender.mailgun;

import com.challenge.emailservice.EmailTestData;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.util.MultiValueMap;

import java.io.UnsupportedEncodingException;

public class MailGunPayloadBuilderTest {

    @Test
    public void shouldBuildMapContainingEmailData() throws UnsupportedEncodingException {
        // given
        MailGunPayloadBuilder builder = new MailGunPayloadBuilder();

        // when
        MultiValueMap<String, String> map = builder.build(EmailTestData.createEmail());

        // then
        Assertions.assertThat(map.keySet()).contains("to", "from", "subject", "text");
        Assertions.assertThat(map.get("to").get(0)).isEqualTo("to@email.com");
        //Assertions.assertThat(map.get("from").get(0)).isEqualTo("FROM <from@email.com>");
    }
}