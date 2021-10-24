package stringformat;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingSl4j {

    final Logger logger = LoggerFactory.getLogger(LoggingSl4j.class);

    @Test
    public void logSomething() {
        logger.info("Das ist eine Logging Ausgabe.");
    }

}
