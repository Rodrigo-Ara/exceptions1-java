package mode.exceptions;

// Exception obriga a tratar, RuntimeException não é obrigado a tratar
public class DomainException extends RuntimeException {
	// objetos de classe serializable podem ser convertidos para byte e serem gravados em arquivos e trafegar na rede
	private static final long serialVersionUID = 1L; // subclasse de Exeption, será obrigado a tratar
	// construtor com String para permitir instânciar a exceção personalizada passando uma mensagem para ela.
	public DomainException(String msg) {
		super(msg);
	}
}
