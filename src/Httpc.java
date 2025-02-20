import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.io.*;

public class Httpc {

    public static void main(String[] args) throws IOException {
        String parameter = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the command line here: ");
        parameter = scanner.nextLine();

        while (!parameter.equals("quit")) {
            try {
                //the parameter goes here and process due to the HTTP Client Library
                execute(parameter);

            } catch (MalformedURLException URLe) {
                System.out.println(URLe);
            } catch (Exception e) {
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



    public static void execute(String str) throws MalformedURLException {

        URL url = null;
        String parameters[];
        String urlString = null; //
        String hostString = "";
        String bodyString = "";
        boolean v = false; //if user input contains "-v", set v to true, GET will display header
        boolean h = false;
        boolean d = false;
        boolean f = false;

        Help help = new Help();
        String contentType = "application/json";
        Request.Request_Type requestType = Request.Request_Type.GET;
        Request.HTTP_version httpVersion = Request.HTTP_version.HTTP1_0;
        Body body = null;
        Query_Parameters queryParameters = new Query_Parameters();
        Request request;

        File file = null;
        FileReader fileReader;


        //split the user input into string array chunks.
        parameters = str.split("\\s+");

        
        //check if the user input is empty or not start with httpc
        if (parameters.length <= 1 || !parameters[0].equals("httpc")) {
            System.out.println("Invalid command line.");
        } else {

            for (int i = 1; i < parameters.length; i++) {

                // for help commands
                if (parameters[i].equals("help")) {

                    if (parameters.length > i + 1 && parameters[i + 1].equals("get")) {
                        help.getHelp();

                    } else if (parameters.length > i + 1 && parameters[i + 1].equals("post")) {
                        help.postHelp();

                    } else {
                        help.help();
                    }

                    // check if user wants verbose info
                } else if (parameters[i].equals("-v")) {
                    //TODO
                    v = true;

                    //check if user wants different content type
                } else if (parameters[i].equals("-h")) {
                    //TODO
                    h = true;

                    for (int l = 0; l < parameters[i+1].length(); l++){
                        if (parameters[i+1].charAt(l)==':'){
                            contentType = "";
                            for (int m = l+1; m < parameters[i+1].length(); m++){
                                contentType += parameters[i+1].charAt(m);
                            }

                        }
                    }

                    //check if the user wants GET
                } else if (parameters[i].equals("get")) {
                    requestType = Request.Request_Type.GET;

                    //check if the user wants POST
                } else if (parameters[i].equals("post")) {
                    requestType = Request.Request_Type.POST;

                    //check if the host is in correct format and put it into url
                } else if (parameters[i].contains("http://")) {

                    urlString = parameters[i];
                    String urlStringNoQuote = urlString.replace("\'", ""); //remove '' from string

                    url = new URL(urlStringNoQuote);

                    hostString = url.getHost();
                    if (url.getQuery() == null) {
                        new Query_Parameters("");
                    } else {
                        new Query_Parameters(url.getQuery());
                    }

                    //check if user wants different version of HTTP
                } else if (parameters[i].contains("version")) {
                    if (parameters[i].contains("1.0")) {
                        httpVersion = Request.HTTP_version.HTTP1_0;
                    } else {
                        httpVersion = Request.HTTP_version.HTTP1_1;
                    }

                    //check if user wants to associates an inline data to the body HTTP POST
                } else if (parameters[i].equals("-d")) {
                    //TODO
                    d = true;

//                    if (parameters[i+1].contains("{")){
//
//                        for (int j = 0; j < str.length(); j++){
//                            if (str.charAt(j) == '{') {
//                                for (int k = j; k < str.length(); k++){
//                                    if (str.charAt(k) == '}'){
//                                        bodyString += '}';
//                                        break;
//                                    } else {
//                                        bodyString += str.charAt(k);
//                                    }
//                                }
//                            }
//                        }
//                    }

                    for (int j = 0; j < str.length(); j++){
                       if (str.charAt(j) == '\''){
                           j++;
                           for (int k = j; k < str.length(); k++){
                               if (str.charAt(k) == '\''){
                                   break;
                               } else{
                                   bodyString += str.charAt(k);
                               }
                           } break;
                       }
                    }

                    body = new Body(bodyString);

                    //check if user wants to associate the content of a file to the body HTTP POST
                } else if (parameters[i].equals("-f")) {
                    //TODO
                    f = true;

                    try{
                        file = new File(parameters[i+1]);
                        System.out.println("file: " + file);

                        fileReader = new FileReader(file);
                        System.out.println("fileReader: " + fileReader);
                        int character;
                        while ((character=fileReader.read())!= -1) {
                            bodyString += (char)character;
                        }
                        System.out.println(bodyString);
                        body = new Body(bodyString);
                    } catch (FileNotFoundException e) {
                        System.out.println("Please input Absolute Path of the file!");
                        //e.printStackTrace();
                    } catch (IOException e) {
                        System.out.println("IO Exception occurred");
                        //e.printStackTrace();
                    }


                }


            }
            

        }

        //retrieved all information from user's input, now we could send our request and receive info
        try{
            if (requestType.equals(Request.Request_Type.GET) && urlString != null) {
                request = new Request(requestType, queryParameters, httpVersion);

                new GET(hostString, 80, request, v);

            } else if (requestType.equals(Request.Request_Type.POST) && urlString != null) {
                request = new Request(requestType, contentType, httpVersion, queryParameters, body);
                if (d == true && f == true){
                    System.out.println("Either [-d] or [-f] can be used but not both.");
                }else {
                    new POST(hostString, 80, request, v);
                }

            }
        } catch (NullPointerException e){
            //System.out.println(e);
            System.out.println("Due to the input mistake of file path, we cannot provide services.");
        } catch (Exception e){
            //System.out.println(e);
            System.out.println("Some error occurred, please check your input.");
        }



    }

}