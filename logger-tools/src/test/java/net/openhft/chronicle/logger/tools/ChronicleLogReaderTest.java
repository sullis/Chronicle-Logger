package net.openhft.chronicle.logger.tools;

import net.openhft.chronicle.wire.WireType;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChronicleLogReaderTest {
    @Before
    public void setup() {
        System.setProperty(
                "logback.configurationFile",
                System.getProperty("resources.path")
                        + "/logback-chronicle-binary-appender.xml");
    }

    @Test
    public void readTest() {
        final Logger logger = LoggerFactory.getLogger("binary-chronicle");
        logger.info("test {} {} {}", 1, 100L, 100.123D);
        logger.info("test {} {} {}", 2, 100L, 100.123D);
        logger.info("test {} {} {}", 3, 100L, 100.123D);

        ChronicleLogReader reader = new ChronicleLogReader(System.getProperty("java.io.tmpdir") + "/chronicle-logback/binary-chronicle", WireType.BINARY_LIGHT);
        reader.processLogs(ChronicleLogReader::printf, false);

        //ChroniCat.main(new String[] {System.getProperty("java.io.tmpdir") + "/chronicle-logback/binary-chronicle"});
    }
}
