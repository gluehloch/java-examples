package encoding;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URLEncoder;

import org.junit.jupiter.api.Test;

class UrlEncoding {

    /**
     * If the encode creates a new string, the next encode will change the encode
     * too.
     *
     * @throws Exception Unsupported Encoding Exception
     */
    @Test
    void encode() throws Exception {
        assertThat(URLEncoder.encode("string", "UTF-8")).isEqualTo("string");
        assertThat(URLEncoder.encode("str str", "UTF-8")).isEqualTo("str+str");
        assertThat(URLEncoder.encode("str+str", "UTF-8")).isEqualTo("str%2Bstr");
        assertThat(URLEncoder.encode("str%2Bstr", "UTF-8")).isEqualTo("str%252Bstr");
    }

}