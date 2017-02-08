import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by dremon on 27/01/16.
 */
public class SocketServidor {

    public static void main(String[] args) {

        boolean guay = true;

        System.out.println("Creando servidor");

        /*El constructor del serversocket es diferente que el del cliente.
        Tiene métodos que el normal no tiene.
         */

        try {
            ServerSocket serverSocket = new ServerSocket();

            System.out.println("Realizando el bind");
            InetSocketAddress addr = new InetSocketAddress("0.0.0.0", 5555);

            /*
            bind: significa vincular
             */
            /*
            El servidor escucha en la dirección que le digamos.
             */
            /**
             * El servidor tiene que ser la misma màquina que ejecuta el programa servidor?
             */

            serverSocket.bind(addr);
            System.out.println("Escuchando");

            /**
             * Aquí és donde el servidor se quedará escuchando!
             */

            while(guay){
                Socket socketdeescucha = serverSocket.accept();
                HiloPeticion th = new HiloPeticion(socketdeescucha);
                th.start();
            }
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}