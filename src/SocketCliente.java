/**
 * Created by dremon on 27/01/16.
 */


/**
 * TCP: Orientado a connexión segura (es decir, todo paquete enviado, tenía
 * un acuse de recibo por parte del receptor. Tanto emisor y receptor se comunican para
 * acordar los envios.
 */

/**
 * UDP: Envio rápido y por consiguiente sin acuse de recibo (a algo a alguna parte). Ejemplos
 * de conexiones UDP son las videoconferencias.
 */

/**
 * Cliente - Servidor: Cliente era quien pedia información y el servidor quien tenia una serie
 * de recursos que puede ser solicitados.
 */

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class SocketCliente {

    private static String ip = "0.0.0.0";

    public static void main(String[] args) {

        System.out.println("Creado el socket cliente");
        Socket clienteSocket = new Socket();
        System.out.println("Estableciendo connexión");

        Scanner sc = new Scanner(System.in);

        InetSocketAddress addr = new InetSocketAddress(ip, 5555);

        System.out.println("Introduce el primer número: ");
        int x = sc.nextInt();
        System.out.println("Introduce el segundo número: ");
        int y = sc.nextInt();

        System.out.println("Introduce la operación: ");

        String operator = sc.next();

        System.out.println(x + " " + operator + " " + y);

        try {

            clienteSocket.connect(addr);

            InputStream is = clienteSocket.getInputStream();
            OutputStream os = clienteSocket.getOutputStream();

            System.out.println("Enviando mensaje");

            String mensaje =x+ " " + operator + " " +y + " ";

            /*Ponemos el mensaje en el canal
            * RECORDAR que hay que ponerlo en bits*/

            os.write(mensaje.getBytes());

            System.out.println("Mensaje enviado!");

            byte[] result = new byte[25];

            is.read(result);

            String resultString = new String(result).split(" ")[0];
            System.out.println(resultString);

            Date date = new Date();

            String toLog = "Petición de: " + ip + ". A fecha: " + date.toString() + ". Operación: " + mensaje + " = " + resultString + "\n";

            writeLog(toLog);

            clienteSocket.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void writeLog(String result) throws IOException {

        File f = new File ("log");

        if(!f.exists()) {
            f.createNewFile();
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(f, true));

        writer.append(result);
        writer.newLine();
        writer.close();

    }
}
