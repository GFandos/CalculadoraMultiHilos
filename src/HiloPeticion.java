import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by 4798978s on 10/02/16.
 */

public class HiloPeticion extends Thread{

    Socket socketescuchado;

    public HiloPeticion(Socket s){

        socketescuchado = s;

    }

    @Override
    public void run() {
        try {
            InputStream is = socketescuchado.getInputStream();
            OutputStream os = socketescuchado.getOutputStream();
            byte[] mensaje = new byte[6000];
            is.read(mensaje);

            String s = new String(mensaje);
            String[] operationArray = s.split(" ");

            for(int i = 0; i < 3; ++i) {
                System.out.print(operationArray[i]);
            }


            System.out.println(mensaje);

            int x = Integer.valueOf(operationArray[0]);
            int y = Integer.valueOf(operationArray[2]);

            char operator = operationArray[1].toCharArray()[0];

            System.out.println(x + " " + operator + " " + y);

            int resultat = 0;

            switch(operator) {

                case '+':
                    resultat = suma(x,y);
                    break;
                case '-':
                    resultat = resta(x,y);
                    break;
                case '/':
                    resultat = divide(x,y);
                    break;
                case '*':
                    resultat = multiply(x,y);
                    break;
                case '%':
                    resultat = mod(x,y);
                    break;
                default:
                    System.out.println("Operador no soportado");

            }

            System.out.println(socketescuchado.getInetAddress().toString());
            System.out.println("Cerrando!");



            os.write(String.valueOf(resultat + " ").getBytes());
            socketescuchado.close();
            is.close();
            os.close();

        } catch (IOException e) {
            e.printStackTrace();
        }





    }

    private int multiply(int x, int y) {
        return x*y;
    }

    private int divide(int x, int y) {
        return x/y;
    }

    private int resta(int x, int y) {
        return x-y;
    }

    private int suma(int x, int y) {
        return x+y;
    }

    private int mod(int x, int y) {
        return x%y;
    }
}
