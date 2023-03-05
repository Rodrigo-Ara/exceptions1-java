package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

// SOLUÇÃO MUITO RUIM, POIS O TRATAMENTO OCORRE NO MÉTODO PRINCIPAL, JÁ Q DEVERIA ESTAR NA PRÓPRIA CLASSE Reservation
public class Reservation {
	
	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	// tipo date e as entradas no formato dia/mes/ano, por isso o SimpleDateFormat para uso no toString
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // máscara de formatação da data
	
	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	// não houve o setCheckIn e setCheckOut pois o método updateDates é o responsável por alterá-los
	public Date getCheckIn() { // set nao configurado, pois quem irá alterar data é o método
		return checkIn;
	}

	public Date getCheckOut() { // set nao configurado, pois quem irá alterar data é o método
		return checkOut;
	}
	
	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime(); // .getTime retorna a quantidade em milisegundos da data
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS); // converte o valor de diff que estava em milissegundo para dias.
	}

	public void updateDates(Date checkIn, Date checkOut) {
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	
	@Override
	public String toString() {
		return "Room "
				+ roomNumber
				+ ", checkIn: "
				+ sdf.format(checkIn) // tipo date e as entradas estão em dia/mes/ano, por isso o SimpleDateFormat
				+", checkOut: "
				+ sdf.format(checkOut)
				+ ", duração: "
				+ duration()
				+ " nights \n";
	}
}