import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MyClient {

    private final static int PORT = 8080;
    private final static String HOST = "netology.homework";

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        //клиент
        Socket clientSocket = new Socket(HOST, PORT);
        PrintWriter outClient = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader inClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        System.out.println(inClient.readLine());
        outClient.println(scanner.nextLine());

        while (true) {
            System.out.println(inClient.readLine());
            input = scanner.nextLine();
            outClient.println(input);
            System.out.println(inClient.readLine() + "\n\n");
            if (input.equals("end")) return;
        }
    }
}
