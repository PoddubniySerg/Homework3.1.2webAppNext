import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyLittleServer {

    private final static int PORT = 8080;

    public static void main(String[] args) throws IOException {
        //сервер
        ServerSocket serverSocket = new ServerSocket(PORT);
        Socket clientServerSocket = serverSocket.accept();
        PrintWriter outSrv = new PrintWriter(clientServerSocket.getOutputStream(), true);
        BufferedReader inSrv = new BufferedReader(new InputStreamReader(clientServerSocket.getInputStream()));
        outSrv.println("Hello! Write your name.");
        String name = inSrv.readLine();

        while (true) {
            outSrv.println("Are you child? (yes/no) or enter \"end\" for exit.");
            switch (inSrv.readLine()) {
                case "yes":
                    outSrv.println(String.format("Welcome to the kids area, %s! Let's play!", name));
                    break;
                case "no":
                    outSrv.println(String.format("Welcome to the adult zone, %s! Have a good rest, or a good working day!", name));
                    break;
                case "end":
                    outSrv.println("Bye!");
                    clientServerSocket = serverSocket.accept();
                    outSrv = new PrintWriter(clientServerSocket.getOutputStream(), true);
                    inSrv = new BufferedReader(new InputStreamReader(clientServerSocket.getInputStream()));
                    outSrv.println("Hello! Write your name.");
                    name = inSrv.readLine();
                    break;
                default:
                    outSrv.println("I don't understand. Try again. (yes/no)");
                    break;
            }
        }

    }
}