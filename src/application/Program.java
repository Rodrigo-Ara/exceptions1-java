package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.entities.Reservation;

// SOLUÇÃO MUITO RUIM, POIS O TRATAMENTO OCORRE NO MÉTODO PRINCIPAL, JÁ Q DEVERIA ESTAR NA PRÓPRIA CLASSE Reservation
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
				
			 // método para atualizar as datas
			 // regra de negócio
			 // datas para atualização não podem ser menores que a data atual
			 Date now = new Date(); // cria data com horário de agora
			 if (checkIn.before(now) || checkOut.before(now)) { // compara data checkIn e checkOut com a atual, só aceito se for falso
				 System.out.println("Error in reservation: check-out date must be after check-in date");
			 }
			 else if (!checkOut.after(checkIn)) {
				 System.out.print("Error in reservation: check-out date must be after check-in date");
			 }
			 else { // só atualizara se nenhum dos problemas anteriores ocorrerem
				 reservation.updateDates(checkIn, checkOut);
				 System.out.print("Reservation: " + reservation);
			 }
		 }
		
		 sc.close();
	}

}
