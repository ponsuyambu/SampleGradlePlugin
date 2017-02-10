package in.ponshere.sample.gradle.plugin;

import in.ponshere.sample.gradle.plugin.utils.HelloUtils;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HelloUtilsTest {
    @Test
    public void greet_the_user() {
        String greeting = "Hello World!";
        HelloUtils helloUtils = new HelloUtils(greeting);

        String actualGreeting = helloUtils.greet();

        assertThat(actualGreeting, is(greeting));
    }
}