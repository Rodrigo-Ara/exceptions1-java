package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import mode.exceptions.DomainException;
import model.entities.Reservation;

// SOLUÇÃO RUIM, LÓGICA DE VALIDAÇÃO DELEGADA PARA CLASSE Reservation, MÉTODO RETORNANDO String
// PROBLEMA GRAVE DE DELEGAÇÃO amenizado, pois o responsável por saber a reserva é a classe Reservation
public class Program {
	// foi retirado o throws do main pq a exceção será tratada e não propagada
	public static void main(String[] args) {

		// solução 1 (muito ruim): lógica de validação no programa principal
		 Scanner sc = new Scanner(System.in);
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 
		 try {
			 System.out.print("Room number: ");
			 int number = sc.nextInt();
			 // ou trata ou propaga a exceção, neste caso, throws ParseException gerado pelo sdf.parse();
			 System.out.print("Check-in date (dd/MM/yyyy): ");
			 Date checkIn = sdf.parse(sc.next()); // recebe uma string e converte para o tipo date
			 System.out.print("Check-out date (dd/MM/yyyy): ");
			 Date checkOut = sdf.parse(sc.next());
			 
			 // construtor já está no bloco try, se houver exceção será interrompido 
			 // e cairá no bloco catch (DomainException e)
			 Reservation reservation = new Reservation(number, checkIn, checkOut);
			 System.out.print("Reservation: " + reservation);
			    
			 System.out.println();
			 System.out.println("Enter data to update the reservation:");
			 System.out.print("Check-in date (dd/MM/yyyy): ");
			 checkIn = sdf.parse(sc.next());
			 System.out.print("Check-out date (dd/MM/yyyy): ");
			 checkOut = sdf.parse(sc.next());
				
			 // o método updateDates não vai mais retornar uma String com a mensagem de erro
			 // irá lançar a exceção, caso ocorra
			 
			 // ou propago a exceção com throws DomainException no método (main)
			 // ou tratar a exceção capturando em um bloco catch
			 reservation.updateDates(checkIn, checkOut);
			 System.out.print("Reservation: " + reservation);
		 }
		 catch (ParseException e) { // erro no parse ou na entrada de dados
			 System.out.println("Invalid date format.");
		 }
//		 catch (IllegalArgumentException e) { // exceção pronta q trata o método updateDates()
//			 System.out.println("Error in reservation: " + e.getMessage());
//		 }
		 catch (DomainException e) { // erro no construtor ou no updateDates com mensagem personalizada
			 System.out.println("Error in reservation: " + e.getMessage());
		 }
		 catch(RuntimeException e) { // trata qualquer possível RuntimeExcepetion que possa vir a aparecer
			 System.out.println("Unexpected error");
		 }
		 sc.close();
	}
}
