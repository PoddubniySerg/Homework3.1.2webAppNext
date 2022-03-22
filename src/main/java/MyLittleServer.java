import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyLittleServer {

    private final static int PORT = 8080;

    public static void main(String[] args) {
        //сервер
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (!serverSocket.isClosed()) {
                if (isServerClosed(serverSocket)) serverSocket.close();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }


    }

    public static boolean isServerClosed(ServerSocket serverSocket) {
        try (Socket clientServerSocket = serverSocket.accept();
             PrintWriter outSrv = new PrintWriter(clientServerSocket.getOutputStream(), true);
             BufferedReader inSrv = new BufferedReader(new InputStreamReader(clientServerSocket.getInputStream()))) {
            outSrv.println("Hello! Write your name.");
            String name = inSrv.readLine();

            while (!clientServerSocket.isClosed()) {
                outSrv.println("Are you child? (yes/no)\nEnter \"end\" for exit and \"close\" for stop server.");
                switch (inSrv.readLine()) {
                    case "yes":
                        outSrv.println(String.format("Welcome to the kids area, %s! Let's play!", name));
                        break;
                    case "no":
                        outSrv.println(String.format("Welcome to the adult zone, %s! Have a good rest, or a good working day!", name));
                        break;
                    case "end":
                        outSrv.println("Bye!");
                        return false;
                    case "close":
                        outSrv.println("Bye!");
                        return true;
                    default:
                        outSrv.println("I don't understand. Try again. (yes/no)");
                        break;
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return false;
    }
}