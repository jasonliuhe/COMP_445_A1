//Request class is to create request for both GET and POST.
//
import javax.swing.text.AbstractDocument;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;


public class Request {

//    enum Request_Type is to define the request is use for GET or POST.
    public enum Request_Type{
        GET,
        POST;
    }

//    enum HTTP_version is to define the request running on which version of HTTP.
//    by default using HTTP1.0
    public enum HTTP_version{
        HTTP1_0("HTTP/1.0\r\n"),
        HTTP1_1("HTTP/1.1\r\n");

        private String value;

        HTTP_version(String s) {
            value = s;
        }

        @Override
        public String toString() {
            return value;
        }
    }


    String request = "";
    Body body = new Body();
    Query_Parameters query = new Query_Parameters();
    String content_type = "application/json\r\n";
    HTTP_version http_version = HTTP_version.HTTP1_0;



    public Request(){
        request = "GET /get" + query.getQuery_Parameter() + http_version.toString() + "\r\n";
    }

//    constructor of the Request
//    user must choose the request_type
    public Request(Request_Type request_type){
        if (request_type.equals(Request_Type.GET)){
            request = "GET /get" + query.getQuery_Parameter() + http_version.toString() + "\r\n";
        } else {
            request = "POST /post" + query.getQuery_Parameter() + http_version.toString()
                + "Content-Type:" + content_type
                + "Content-Length: " + body.getBodyLength() + "\r\n"
                + "\r\n" + body.getBodyContent();
        }
    }

//    constructor of the Request for GET
//    user must choose the request_type
    public Request(Request_Type request_type, Query_Parameters query_parameters, HTTP_version http_version){
        this.query = query_parameters;
        this.http_version = http_version;
        if (request_type.equals(Request_Type.GET)){
            request = "GET /get" + query.getQuery_Parameter() + http_version.toString() + "\r\n";
        }
    }

//    another constructor of the Request for POST
//    If request_Type is GET, then the Content_Type and Body should be empty.
    public Request(Request_Type request_type, String content_type, HTTP_version http_version, Query_Parameters query_parameters, Body body){
        this.body = body;
        this.content_type = content_type;
        this.query = query_parameters;
        this.http_version = http_version;

        if(request_type.equals(Request_Type.POST)){
            this.request = "POST /post" + query.getQuery_Parameter()  + http_version.toString()
                + "Content-Type:" + this.content_type + "\r\n"
                + "Content-Length: " + body.getBodyLength() + "\r\n"
                + "\r\n" + body.getBodyContent();
        }
    }

    public String getRequest() {
        return request;
    }



}
