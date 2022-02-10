package com.jlp.dresses;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Static helper methods to make testing easier
 *
 * @author John Casey
 */
public class TestHelper {

    public static String getJSONString() {
        try {
            URL resource = TestHelper.class.getClassLoader().getResource("response.json");
            File file = new File(resource.toURI());
            return new String(Files.readAllBytes(file.toPath()));
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
            return "";
        }
    }

}
