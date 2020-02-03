import java.io.IOException;
import java.util.Scanner;


public class cURL {

    public static void main(String[] args) throws IOException {
        String parameter="";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the command line here: ");
        parameter = scanner.nextLine();

        while (!parameter.equals("quit")){
            try
            {
                //the parameter goes here and process due to the HTTP Client Library
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

}
