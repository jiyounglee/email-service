package com.challenge.emailservice.service.sender.mailgun;

import com.challenge.emailservice.EmailTestData;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.util.MultiValueMap;

import java.io.UnsupportedEncodingException;

public class MailGunPayloadBuilderTest {

    @Test
    public void shouldBuildMapContainingBasicEmailData() throws UnsupportedEncodingException {
        // given
        MailGunPayloadBuilder builder = new MailGunPayloadBuilder();

        // when
        MultiValueMap<String, String> map = builder.build(EmailTestData.createEmail());

        // then
        Assertions.assertThat(map.keySet()).contains("to", "from", "subject", "text");
        Assertions.assertThat(map.get("to").get(0)).isEqualTo("to@email.com");
        Assertions.assertThat(map.get("from").get(0)).isEqualTo("FROM <from@email.com>");
    }

    @Test
    public void shouldBuildMapContainingFullEmailData() throws UnsupportedEncodingException {
        // given
        MailGunPayloadBuilder builder = new MailGunPayloadBuilder();

        // when
        MultiValueMap<String, String> map = builder.build(EmailTestData.createFullEmail());

        // then
        Assertions.assertThat(map.keySet()).contains("to", "from", "cc", "bcc", "subject", "text");
        Assertions.assertThat(map.get("from").get(0)).isEqualTo("FROM <from@email.com>");
        Assertions.assertThat(map.get("to")).contains("TO ONE <to1@email.com>", "to2@email.com");
        Assertions.assertThat(map.get("cc")).contains("cc@email.com");
        Assertions.assertThat(map.get("bcc")).contains("bcc1@email.com", "bcc2@email.com");
    }
}