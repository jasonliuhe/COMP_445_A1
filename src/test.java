
public class test {

    public static void main(String[] args) {
        new GET("postman-echo.com", 80, new Request(Request.Request_Type.GET));
        System.out.println("--------------------------------");
        Request req = new Request(Request.Request_Type.POST, Request.Content_Type.application_json, Request.HTTP_version.HTTP1_0, new Query_Parameters("info=info"), new Body("{"
							+ "\"key1\":value1,"
							+ "\"key2\":value2"
							+ "}"));
        System.out.println(req.getRequest());
        new POST("httpbin.org", 80, req);
    }
}
