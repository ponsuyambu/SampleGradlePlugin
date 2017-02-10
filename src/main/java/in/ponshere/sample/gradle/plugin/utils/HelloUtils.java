package in.ponshere.sample.gradle.plugin.utils;

/**
 * Utils class for the Task.
 * @author Ponsuyambu V
 */
public class HelloUtils {

    String greetMessage;

    public HelloUtils(String message){
        greetMessage = message;
    }

    public String greet(){
        return greetMessage;
    }
}
