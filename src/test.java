
public class test {

    public static void main(String[] args) {
//        new GET("postman-echo.com", 80, new Request(Request.Request_Type.GET, new Query_Parameters("key1=value1"), Request.HTTP_version.HTTP1_0), true);
//        System.out.println("--------------------------------");
        Request req = new Request(Request.Request_Type.POST, "application_json", Request.HTTP_version.HTTP1_0, new Query_Parameters(""), new Body("{"+"\"Assignment\": 1"+"}"));
        new POST("httpbin.org", 80, req, false);
    }
}
