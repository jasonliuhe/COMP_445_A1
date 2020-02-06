import java.io.FileReader;
import java.io.Reader;

public class test {

    public static void main(String[] args) {
//        new GET("postman-echo.com", 80, new Request(Request.Request_Type.GET, new Query_Parameters("key1=value1"), Request.HTTP_version.HTTP1_0), true);
//        System.out.println("--------------------------------");
        try{
            Reader fileReader = new FileReader("/Users/liuhe/Library/Mobile Documents/3L68KQB4HG~com~readdle~CommonDocuments/Documents/COMP 445/Assignment/1/COMP_445_A1/src/file.txt");
            int data = fileReader.read();
            System.out.println(data);
            StringBuilder body = new StringBuilder();
            while (data != -1){
                body.append((char) data);
                data = fileReader.read();
            }
            System.out.println(body);
//            "{"+"\"Assignment\": 1"+"}"
            Request req = new Request(Request.Request_Type.POST, "application_json", Request.HTTP_version.HTTP1_0, new Query_Parameters(), new Body(body.toString()));
            new POST("httpbin.org", 80, req, false);
        } catch (Exception e) {

        }

    }
}
