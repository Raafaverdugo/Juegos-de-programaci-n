package juegoprogramacion;

import java.util.*;

public class juego2 {

	// Función para generar la opción de la computadora (NO imprime nada)
	public static int generarOpcionComputadora() {
		Random random = new Random();
		return random.nextInt(1,4); // Genera un número entre 1 y 3 (incluyendo 3)
	}

	// Función para convertir la opción de número a texto (NO imprime nada)
	public static String convertirOpcionATexto(double opcion) {
		int eleccion = (int) opcion;
		switch (eleccion) {
			case 1: return "Piedra";
			case 2: return "Papel";
			case 3: return "Tijeras";
			default: return "Desconocido";
		}
	}

	// Función para determinar el ganador de una ronda (NO imprime nada)
	public static int determinarGanador(double opcionUsuario, int opcionComputadora) {
		if (opcionUsuario == opcionComputadora) {
			return 0; // Empate
		} else if ((opcionUsuario == 1 && opcionComputadora == 3) || // Piedra gana a Tijeras
				(opcionUsuario == 2 && opcionComputadora == 1) || // Papel gana a Piedra
				(opcionUsuario == 3 && opcionComputadora == 2)) { // Tijeras gana a Papel
			return 1; // Gana el usuario
		} else {
			return -1; // Gana la computadora
		}
	}

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		double opcion = 0;
		double numRondas = 0, elecfin = 0;
		boolean comienza = true;
		int puntosUsuario = 0;
		int puntosComputadora = 0;
		int empates = 0;

		while (comienza) {

			puntosUsuario = 0;
			puntosComputadora = 0;
			empates = 0;

			try {
				System.out.print("¿Cuántas rondas quieres jugar? ");
				numRondas = scanner.nextInt();
				while (numRondas < 1) {
					System.out.println("Introduce un número mayor que 0: ");
					numRondas = scanner.nextInt();
				}
			} catch (InputMismatchException ex) {
				scanner.nextLine();
				System.out.println("Introduce un número entero positivo");
				continue;
			}

			for (int i = 1; i <= numRondas; i++) {
				System.out.println("\n--- Ronda " + i + " ---");

				boolean opcionValida = false; // Controlar si la opción es válida
				while (!opcionValida) {
					try {
						System.out.println("Elige una opción:");
						System.out.println("1. Piedra");
						System.out.println("2. Papel");
						System.out.println("3. Tijeras");
						System.out.print("Tu elección: ");
						opcion = scanner.nextDouble();

						if (opcion >= 1 && opcion <= 3) {
							opcionValida = true; // Opción válida
						} else {
							System.out.println("Introduce una elección válida entre 1 y 3.");
						}

					} catch (InputMismatchException ex) {
						scanner.nextLine(); // Limpiar el buffer
						System.out.println("Introduce una opción válida (1, 2 o 3).");
					}
				}

				int opcionComputadora = generarOpcionComputadora();

				System.out.println("Tú elegiste: " + convertirOpcionATexto(opcion));
				System.out.println("La máquina eligió: " + convertirOpcionATexto(opcionComputadora));

				int resultado = determinarGanador(opcion, opcionComputadora);

				if (resultado == 1) {
					System.out.println("¡Ganaste esta ronda!");
					puntosUsuario++;
				} else if (resultado == -1) {
					System.out.println("La máquina gana esta ronda.");
					puntosComputadora++;
				} else {
					System.out.println("Esta ronda es un empate.");
					empates++;
				}
			}

			System.out.println("\n--- RESULTADOS FINALES ---");
			System.out.println("Tus puntos: " + puntosUsuario);
			System.out.println("Puntos de la máquina: " + puntosComputadora);
			System.out.println("Empates: " + empates);

			if (puntosUsuario > puntosComputadora) {
				System.out.println("¡Felicidades, ganaste el juego!");
			} else if (puntosUsuario < puntosComputadora) {
				System.out.println("La máquina gana el juego.");
			} else {
				System.out.println("El juego termina en empate.");
			}

			boolean eleccionValida = false;
			while (!eleccionValida) {
				try {
					System.out.println("¿Quieres seguir jugando? (1 = Sí, 2 = No): ");
					elecfin = scanner.nextInt();
					if (elecfin == 1) {
						System.out.println("Volviendo a empezar el juego...");
						comienza = true;
						eleccionValida = true;
					} else if (elecfin == 2) {
						System.out.println("Gracias por jugar este maravilloso juego. ¡Hasta pronto!");
						comienza = false;
						eleccionValida = true;
					} else {
						System.out.println("Introduce 1 (Sí) o 2 (No).");
					}
				} catch (InputMismatchException ex) {
					scanner.nextLine(); // Limpiar el scanner
					System.out.println("Introduce una opción válida (1 o 2).");
				}
			}
		}
		scanner.close();
	}
}
