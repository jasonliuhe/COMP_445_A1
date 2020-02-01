//Body class is to create body for POST request.
//Body should locate at the end of the request.
//The body format should follow the Content-Type of request.
//By default the body is "{}" means empty

public class Body {
    String body;

    public Body(){
        body = "{}";
    }

    public Body(String body){
        this.body = body;
    }

    public int getBodyLength(){
        return body.length();
    }

    public String getBodyContent() {
        return body;
    }
}
