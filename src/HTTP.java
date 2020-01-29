import java.io.IOException;
import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;

public class HTTP {
    public static void GET(String host, int port, String request){
        try {
            Socket socket = new Socket(host, port);

            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();

            String GetRequest = "GET " + request;

            output.write(GetRequest.getBytes());
            output.flush();

            StringBuilder response = new StringBuilder();

            int data = input.read();

            while (data != -1){
                response.append((char) data);
                data = input.read();
            }

            System.out.println(response);
            socket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void POST(String host, int port, String request){
        try {
            Socket socket = new Socket(host, port);

            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();

            String POSTRequest = "POST " + request;

            output.write(POSTRequest.getBytes());
            output.flush();

            StringBuilder response = new StringBuilder();

            int data = input.read();

            while (data != -1){
                response.append((char) data);
                data = input.read();
            }
            System.out.println(response);
            socket.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String host = "postman-echo.com";
        int port = 80;
        String request = "/get?foo1=bar1&foo2=bar2 HTTP/1.0\r\n\r\n";
        GET(host, port, request);

        System.out.println("--------------------------------------------------");

        String body = "{"
							+ "\"key1\":value1,"
							+ "\"key2\":value2"
							+ "}";
        String request1 = "/post? HTTP/1.0\r\n"
							+ "Content-Type:application/json\r\n"
							+ "Content-Length: " + body.length() +"\r\n"
							+ "\r\n"
							+ body;
        POST(host, port, request1);
    }
}
