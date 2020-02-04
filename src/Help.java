public class Help {

    public static void help()
    {
        System.out.println("httpc is a curl-like application but supports HTTP protocol only.\n"
                + "Usage:\n"
                + "\t httpc command [arguments]\n"
                + "The commands are:\n"
                + "\t get     executes a HTTP GET request and prints the response.\n"
                + "\t post    executes a HTTP POST request and prints the response.\n"
                + "\t help    prints this screen.\n"
                + "Use \"httpc help [command]\" for more information about a command."
        );

    }


    public static void getHelp()
    {
        System.out.println(
                "Usage: httpc get [-v] [-h key:value] URL\n"
                        + "\r\n"
                        + "Get executes a HTTP GET request for a given URL"
                        + "\r\n"
                        + "\t -v		    Prints the detail of the response such as protocol, status and headers.\n"
                        + "\t -h key:value	Associates headers to HTTP Request with the format 'key:value'.\n"
        );

    }

    public static void postHelp()
    {
        System.out.println(
                "Usage: httpc httpc post [-v] [-h key:value] [-d inline-data] [-f file] URL\n"
                        + "\r\n"
                        + "httpc post [-v] [-h key:value] [-d inline-data] [-f file] URL\n"
                        + "\r\n"
                        + "\t -v			Prints the detail of the response such as protocol, status and headers.\n"
                        + "\t -h key:value	Associates headers to HTTP Request with the format 'key:value'.\n"
                        + "\t -d string		Associates an inline data to the body HTTP POST request.\n"
                        + "\t -f file		Associates the content of a file to the body HTTP POSTrequest.\n"
                        + "\r\n"
                        + "Either [-d] or [-f] can be used but not both."
        );

    }

}
