package br.gov.cnpq.exception;


public class ArquivoNaoExistenteException extends NegocioException {

	private static final long serialVersionUID = 1L;
	private static final int FILA_SIS_WEB = 1;

	public ArquivoNaoExistenteException(String msg) {
		super(msg);
	}
	
	public int getFila() {
		return FILA_SIS_WEB;
	}

	public String getSeveridade() {
		return "3";
	}
	
}
