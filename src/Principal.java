import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        ConvierteMoneda conversor = new ConvierteMoneda();
        Scanner lectura = new Scanner(System.in);
        int opcion = 0;
        double valorUsuario = 0;

        try {
            System.out.println("*********************");
            System.out.println("Bienvenido ");
            System.out.println("Selecciona un tipo de cambio:");
            System.out.println("*********************");

            String menu = """
                    --- Seleccione una opción ----
                    1. COP - CHF
                    2. EUR - USD
                    3. USD - CHF
                    4. CHF - USD
                    5. Salir
                    """;

            while (opcion != 5) {
                System.out.println(menu);
                try {
                    opcion = lectura.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("La opción ingresada no es un número válido. Inténtelo de nuevo.");
                    lectura.next(); // Limpiar el scanner
                    continue;
                }

                switch (opcion) {
                    case 1:
                        System.out.println("Ingrese la cantidad en COP:");
                        valorUsuario = obtenerValorUsuario(lectura);
                        if (valorUsuario == -1) continue; // Si hubo un error, vuelve al menú
                        try {
                            Moneda ratesCopChf = conversor.getRates("COP");
                            double chfRateFromCop = ratesCopChf.getConversion_rates().get("CHF");
                            System.out.println("Resultado: " + (valorUsuario * chfRateFromCop) + " CHF");
                        } catch (IOException e) {
                            System.out.println("Se produjo un error al obtener las tasas de cambio.");
                            e.printStackTrace();
                        }
                        break;

                    case 2:
                        System.out.println("Ingrese la cantidad en EUR:");
                        valorUsuario = obtenerValorUsuario(lectura);
                        if (valorUsuario == -1) continue; // Si hubo un error, vuelve al menú
                        try {
                            Moneda ratesEurUsd = conversor.getRates("EUR");
                            double usdRateFromEur = ratesEurUsd.getConversion_rates().get("USD");
                            System.out.println("Resultado: " + (valorUsuario * usdRateFromEur) + " USD");
                        } catch (IOException e) {
                            System.out.println("Se produjo un error al obtener las tasas de cambio.");
                            e.printStackTrace();
                        }
                        break;

                    case 3:
                        System.out.println("Ingrese la cantidad en USD:");
                        valorUsuario = obtenerValorUsuario(lectura);
                        if (valorUsuario == -1) continue;
                        try {
                            Moneda ratesUsdChf = conversor.getRates("USD");
                            double chfRateFromUsd = ratesUsdChf.getConversion_rates().get("CHF");
                            System.out.println("Resultado: " + (valorUsuario * chfRateFromUsd) + " CHF");
                        } catch (IOException e) {
                            System.out.println("Se produjo un error al obtener las tasas de cambio.");
                            e.printStackTrace();
                        }
                        break;

                    case 4:
                        System.out.println("Ingrese la cantidad en CHF:");
                        valorUsuario = obtenerValorUsuario(lectura);
                        if (valorUsuario == -1) continue; // Si hubo un error, vuelve al menú
                        try {
                            Moneda ratesChfUsd = conversor.getRates("CHF");
                            double usdRateFromChf = ratesChfUsd.getConversion_rates().get("USD");
                            System.out.println("Resultado: " + (valorUsuario * usdRateFromChf) + " USD");
                        } catch (IOException e) {
                            System.out.println("Se produjo un error al obtener las tasas de cambio.");
                            e.printStackTrace();
                        }
                        break;

                    case 5:
                        System.out.println("Gracias por utilizar el conversor, que tenga un buen día.");
                        break;

                    default:
                        System.out.println("Opción no válida, por favor intente de nuevo.");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("La entrada no es válida.");
        }
    }

    private static double obtenerValorUsuario(Scanner lectura) {
        try {
            return lectura.nextDouble();
        } catch (InputMismatchException e) {
            System.out.println("El valor ingresado no es un número. Inténtelo de nuevo.");
            lectura.next();
            return -1;
        }
    }
}
