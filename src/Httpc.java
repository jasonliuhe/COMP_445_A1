import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Httpc {

    public static void main(String[] args) throws IOException {
        String parameter="";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the command line here: ");
        parameter = scanner.nextLine();

        while (!parameter.equals("quit")){
            try {
                //the parameter goes here and process due to the HTTP Client Library
                execute(parameter);

            }
            catch(MalformedURLException URLe){
                System.out.println(URLe);
            }
            catch(Exception e) {
                e.printStackTrace();
//                System.out.println(e.printStackTrace());
                System.out.println("Please type in the right command line");
            }
            System.out.println("\nEnter the command line here: ");
            parameter = scanner.nextLine();
        }
        scanner.close();

        System.out.print("Exit!");


    }




//    public String getBody(String str){
//
//        //TODO
//
//        return bodyString;
//    }


    public static void execute(String str) throws MalformedURLException {

        String parameters[];
        URL url = null;
        String urlString = null;

        Help help = new Help();
        String bodyString = "";
        Request.Request_Type requestType = null;
        Request.Content_Type contentType = null;
        Request.HTTP_version httpVersion = null;
        Body body = null;
        Query_Parameters queryParameters = new Query_Parameters();
        Request request;

        parameters = str.split("\\s+");
//        System.out.println(parameters.length);
//        for (int j = 0; j < parameters.length; j++){
//            System.out.println(parameters[j]);
//        }

        if (parameters.length <= 1 || !parameters[0].equals("httpc")){
            System.out.println("Invalid command line.");
        }
        else {

            for (int i = 1; i < parameters.length; i++) {

                if (parameters[i].equals("help")) {

                    if (parameters.length > i+1 && parameters[i + 1].equals("get")) {
                        help.getHelp();

                    } else if (parameters.length > i+1 && parameters[i + 1].equals("post")) {
                        help.postHelp();

                    } else {
                        help.help();
                    }
                }

//                else if (parameters[i].equals("-v")){
//                    //TODO
//                }
//
//                else if (parameters[i].equals("-h")){
//                    //TODO
//                }

                else if (parameters[i].equals("get")){
                    requestType = Request.Request_Type.GET;
                }

                else if (parameters[i].equals("post")){
                    requestType = Request.Request_Type.POST;
                }

//                else if (parameters[i].contains("application/json")){
//                    contentType = Request.Content_Type.application_json;
//                }
//
//                else if (parameters[i].contains("x_www_form_urlencoded")){
//                    contentType = Request.Content_Type.x_www_form_urlencoded;
//                }
//
//                else if (parameters[i].contains("multipart_form_data")){
//                    contentType = Request.Content_Type.multipart_form_data;
//                }

                else if (parameters[i].contains("http://")){
                    //System.out.println("in http");
                    urlString = parameters[i];
                    String urlStringNoQuote = urlString.replace("\'","");
                    System.out.println(urlStringNoQuote);
                    url = new URL(urlStringNoQuote);
                    body = new Body(url.getQuery());
                    System.out.println(body.getBodyContent());
                    System.out.println("after http");

                }

                else if (parameters[i].contains("version")){
                    if (parameters[i].contains("1.0")){
                        httpVersion = Request.HTTP_version.HTTP1_0;
                    }else {
                        httpVersion = Request.HTTP_version.HTTP1_1;
                    }
                }

//                else if (parameters[i].equals("-d")){
//                    //TODO
//                }
//
//                else if (parameters[i].equals("-f")){
//                    //TODO
//                }



            }

        }

        if (requestType.equals(Request.Request_Type.GET)){
            request = new Request(requestType);
            new GET(urlString, 80, request);
        }else {
            request = new Request(requestType, contentType, httpVersion, queryParameters, body); //Body method not completed yet!!!
            new POST(urlString, 80, request);
        }

    }

}