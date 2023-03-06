package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.entities.Reservation;

// SOLUÇÃO RUIM, LÓGICA DE VALIDAÇÃO DELEGADA PARA CLASSE Reservation, MÉTODO RETORNANDO String
// PROBLEMA GRAVE DE DELEGAÇÃO amenizado, pois o responsável por saber a reserva é a classe Reservation
public class Program {
	// meu método main não tem q tratar essa exceção, ela será propagada para o método que chama o main
	public static void main(String[] args) throws ParseException {

		// solução 1 (muito ruim): lógica de validação no programa principal
		 Scanner sc = new Scanner(System.in);
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 
		 
		 System.out.print("Room number: ");
		 int number = sc.nextInt();
		 // ou trata ou propaga a exceção, neste caso, throws ParseException gerado pelo sdf.parse();
		 System.out.print("Check-in date (dd/MM/yyyy): ");
		 Date checkIn = sdf.parse(sc.next()); // recebe uma string e converte para o tipo date
		 System.out.print("Check-out date (dd/MM/yyyy): ");
		 Date checkOut = sdf.parse(sc.next());
		 
		 // essa validação de instanciação deveria ser feita no construtor, não no programa principal
		 if (!checkOut.after(checkIn)) { // se a data de checkOut não for inferior a checkIn eu não aceitarei
			    System.out.print("Error in reservation: check-out date must be after check-in date");
		 } else {
			 Reservation reservation = new Reservation(number, checkIn, checkOut);
			 System.out.print("Reservation: " + reservation);
			    
			    
			 System.out.println();
			 System.out.println("Enter data to update the reservation:");
			 System.out.print("Check-in date (dd/MM/yyyy): ");
			 checkIn = sdf.parse(sc.next());
			 System.out.print("Check-out date (dd/MM/yyyy): ");
			 checkOut = sdf.parse(sc.next());
				
			 // salva o retorno da chamada do método updateDates de reservation dentro da string error
			 // se o retorno for diferente de nulo, é pq houve erro, retornando contendo o erro 
			 // logo mostrarei a mensagem armazenada em error
			 // porém se o retorno for NULO entrarei no else e será feito a reserva
			 String error = reservation.updateDates(checkIn, checkOut);
			 if(error != null) {
				 System.err.println("Error in reservation: " + error);
			 }
			 else {
				 System.out.print("Reservation: " + reservation);
			 }
		 }
		 sc.close();
	}
}
