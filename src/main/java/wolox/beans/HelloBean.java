package wolox.beans;

import org.apache.camel.spi.annotations.Component;

@Component("helloBean")
public class HelloBean {

    public String sayHello() {
        return "Hello, world!";
    }

}
