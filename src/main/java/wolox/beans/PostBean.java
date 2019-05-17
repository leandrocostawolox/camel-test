package wolox.beans;

import wolox.types.PostRequestType;
import org.springframework.stereotype.Component;

@Component
public class PostBean {

    public String response(PostRequestType input) {
        return "Thanks for your post, " + input.getName() + "!";

        // Do some further processing here
    }
}
