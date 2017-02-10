package in.ponshere.sample.gradle.plugin.extensions;

/**
 * Extension class.
 *
 * @author Ponsuyambu V
 */
public class DemoPluginExtension {
    private String message = "Default Greeting from Gradle";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
