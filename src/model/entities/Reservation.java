package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

// SOLUÇÃO RUIM, LÓGICA DE VALIDAÇÃO DELEGADA PARA CLASSE Reservation, MÉTODO RETORNANDO String
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
	
	// método para atualizar as datas
	public String updateDates(Date checkIn, Date checkOut) { // deixou de ser void e retornará string com o erro
		// regra de negócio: datas para atualização não podem ser menores que a data atual 
		Date now = new Date(); // cria data com horário de agora
		// compara data checkIn e checkOut com a atual, só aceitando se for falso
		if (checkIn.before(now) || checkOut.before(now)) { 
			return "Reservation dates for update must be future dates";
		}
		if (!checkOut.after(checkIn)) { // ambos os returns retornam String com o erro
			return "check-out date must be after check-in date";
		}
		// se passar, sem entrar nos ifs farei o checkIn e o checkOut e retornarei nulo, pois foi sem erro
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		return null; // se retorna nullo é porque não deu nenhum erro, mas se for String é pq teve erro
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