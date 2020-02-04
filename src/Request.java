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

//    enum Content_Type is to define the content type of the body.
//    by default using x-www-form-urlencoded.
    public enum Content_Type{
        application_json("application/json\r\n"),
        x_www_form_urlencoded("x-www-form-urlencoded\r\n"),
        multipart_form_data("multipart/form-data\r\n");

        private String value;

        Content_Type(String s) {
            value = s;
        }

        @Override
        public String toString() {
            return value;
        }
    }




    String url;
    String parameter[];
    String request = "";
    String bodyString = "";
    Request_Type rt;
    Content_Type ct;
    HTTP_version httpv;
    Body b = new Body();
    Query_Parameters q = new Query_Parameters();
    Request r;



    Body body = new Body();
    Query_Parameters query = new Query_Parameters();
    Content_Type content_type = Content_Type.x_www_form_urlencoded;
    HTTP_version http_version = HTTP_version.HTTP1_0;



    public Request(){

    }

//    constructor of the Request
//    user must choose the request_type
    public Request(Request_Type request_type){
        if (request_type.equals(Request_Type.GET)){
            request = "GET /get" + query.getQuery_Parameter() + http_version.toString() + "\r\n";
        } else {
            request = "POST /post" + query.getQuery_Parameter() + http_version.toString()
                + "Content-Type:" + content_type.toString()
                + "Content-Length: " + body.getBodyLength() + "\r\n"
                + "\r\n" + body.getBodyContent();
        }
    }

//    another constructor of the Request
//    If request_Type is GET, then the Content_Type and Body should be empty.
    public Request(Request_Type request_type, Content_Type type, HTTP_version version, Query_Parameters query_parameters, Body body){
        this.body = body;
        this.content_type = type;
        this.query = query_parameters;
        http_version = version;

        if (request_type.equals(Request_Type.GET)){
            this.request = "GET /get" + query.getQuery_Parameter() + http_version.toString() + "\r\n";
        } else {
            this.request = "POST /post" + query.getQuery_Parameter()  + http_version.toString()
                + "Content-Type:" + content_type.toString()
                + "Content-Length: " + body.getBodyLength() + "\r\n"
                + "\r\n" + body.getBodyContent();
        }
    }

    public String getRequest() {
        return request;
    }


    public String getBody(String str){

        //TODO

        return bodyString;
    }


    public void execute(String str){

        this.parameter = str.split("\\s+");

        if (parameter.length <= 0 || !parameter[0].equals("httpc")){
            System.out.println("Invalid command line, please type in again.");
        }
        else {

            for (int i = 1; i < parameter.length; i++) {

                if (parameter[i].equals("help")) {
                    if (parameter[i + 1].equals("get")) {
                        Help.getHelp();
                    } else if (parameter[i + 1].equals("post")) {
                        Help.postHelp();
                    } else {
                        Help.help();
                    }
                }

                else if (parameter[i].equals("-v")){
                    //TODO
                }

                else if (parameter[i].equals("-h")){
                    //TODO
                }

                else if (parameter[i].equals("get")){
                    this.rt = Request_Type.GET;
                }

                else if (parameter[i].equals("post")){
                    this.rt = Request_Type.POST;
                }

                else if (parameter[i].contains("http://")){
                    this.url = parameter[i];
                }

                else if (parameter[i].equals("-d")){
                    //TODO
                }

                else if (parameter[i].equals("-f")){
                    //TODO
                }

            }

        }



        if (rt.equals(Request_Type.GET)){
            r = new Request(rt);
            new GET(url, 80, r);
        }else {
            r = new Request(rt, ct, httpv, q, new Body("")); //Body method not completed yet!!!
            new POST(url, 80, r);
        }



    }



}
