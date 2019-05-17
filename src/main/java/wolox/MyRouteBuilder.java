package wolox;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import wolox.types.PostRequestType;

/**
 * A Camel Java DSL Router
 */
public class MyRouteBuilder extends RouteBuilder {

    private void configureSimpleRouting() {
        // noop	->	If true, the file is not moved or deleted in any way, is good for read only data
        from("file:src/data?noop=true")
                // choice() is like a case
                .choice()
                // each when contains the condition to evaluate and what to do if it's true
                .when(xpath("/person/city = 'London'"))
                .log("UK message")
                .to("file:target/messages/uk")
                // The otherwise define the default flow if none of the when conditions is true
                .otherwise()
                .log("Other message")
                .to("file:target/messages/others");
    }

    private void configureRestRouting() {
        restConfiguration()
                .component("restlet")
                .host("localhost").port("8080")
                .bindingMode(RestBindingMode.auto);

        rest().path("/api").consumes("application/json")
                .get()
                .to("bean:helloBean")
                .post().type(PostRequestType.class)
                .to("bean:postBean");
    }

    /**
     * Configuration of Camel routing rules using Java code
     */
    public void configure() {
        // Interchangeable sample (Between Rest and Simple) which processes the input files
        this.configureRestRouting();
    }

}
