package com.challenge.emailservice.service.mailgun;

import com.challenge.emailservice.EmailTestData;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.util.MultiValueMap;

import static org.junit.Assert.*;

public class MailGunPayloadBuilderTest {

    @Test
    public void shouldBuildMapContainingEmailData() {
        // given
        MailGunPayloadBuilder builder = new MailGunPayloadBuilder();

        // when
        MultiValueMap<String, String> map = builder.build(EmailTestData.createEmail());

        // then
        Assertions.assertThat(map.keySet()).contains("to", "from", "subject", "text");
        Assertions.assertThat(map.get("to").get(0)).isEqualTo("to@email.com");
        Assertions.assertThat(map.get("from").get(0)).isEqualTo("FROM <from@email.com>");
    }
}