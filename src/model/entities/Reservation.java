package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import mode.exceptions.DomainException;

// SOLUÇÃO RUIM, LÓGICA DE VALIDAÇÃO DELEGADA PARA CLASSE Reservation, MÉTODO RETORNANDO String
public class Reservation {
	
	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	// tipo date e as entradas no formato dia/mes/ano, por isso o SimpleDateFormat para uso no toString
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // máscara de formatação da data
	
	// por ter mudado o DomainException de Exception para RuntimeException, 
	// fico desobrigado a lançar throws DomainException no método
	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
		// boa prática de programação defensiva, tratar as exceções no começo dos métodos 
		if (!checkOut.after(checkIn)) { 
			throw new DomainException ("check-out date must be after check-in date");
		}
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

	// não houve setCheckIn e setCheckOut pois o método updateDates é o responsável por alterá-los
	public Date getCheckIn() { // set nao configurado, pois quem irá alterar data é o método updateDates
		return checkIn;
	}

	public Date getCheckOut() { // set nao configurado, pois quem irá alterar data é o método updateDates
		return checkOut;
	}
	
	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime(); // .getTime retorna a quantidade em milisegundos da data
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS); // converte o valor de diff que estava em milissegundo para dias.
	}
	
	// método para atualizar as datas
	// voltou a ser void para tratar a exceção
	// devido ao throws DomainException este método  updateDates pode lançar exceção
	// por ter mudado o DomainException de Exception para RuntimeException, 
	// fico desobrigado a lançar throws DomainException no método
	public void updateDates(Date checkIn, Date checkOut) { 
		// regra de negócio: datas para atualização não podem ser menores que a data atual 
		Date now = new Date(); // cria data com horário de agora
		// lógica q compara data checkIn e checkOut com a atual, só aceitando se ambas forem falsas
		if (checkIn.before(now) || checkOut.before(now)) { // checking ou checkout antes de agora?
			// se houver erro, lançará exceção, nada de retornar mensagem com o erro
			throw new DomainException ("Reservation dates for update must be future dates"); 
		}
		// lógica em que o checkout tem que ser depois do checking para atualização
		if (!checkOut.after(checkIn)) { 
			throw new DomainException ("check-out date must be after check-in date");
		}
		// se passar, sem entrar nos ifs farei o checkIn e o checkOut
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