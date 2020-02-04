import java.io.IOException;
import java.util.Scanner;

public class Httpc {

    public static void main(String[] args) throws IOException {
        String parameter="";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the command line here: ");
        parameter = scanner.nextLine();

        while (!parameter.equals("quit")){
            try
            {
                //the parameter goes here and process due to the HTTP Client Library
                Request req = new Request();
                req.execute(parameter);

            }
            catch(Exception e)
            {

                System.out.println("Error, please type in the right command line");
            }
            System.out.println("\nEnter the command line here: ");
            parameter = scanner.nextLine();
        }
        scanner.close();

        System.out.print("Exit!");


    }

    String url;
    String parameter[];
    String bodyString = "";
    Request.Request_Type rt;
    Request.Content_Type ct;
    Request.HTTP_version httpv;
    Body b = new Body();
    Query_Parameters q = new Query_Parameters();
    Request r;


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
                    this.rt = Request.Request_Type.GET;
                }

                else if (parameter[i].equals("post")){
                    this.rt = Request.Request_Type.POST;
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



        if (rt.equals(Request.Request_Type.GET)){
            r = new Request(rt);
            new GET(url, 80, r);
        }else {
            r = new Request(rt, ct, httpv, q, new Body("")); //Body method not completed yet!!!
            new POST(url, 80, r);
        }



    }

}
