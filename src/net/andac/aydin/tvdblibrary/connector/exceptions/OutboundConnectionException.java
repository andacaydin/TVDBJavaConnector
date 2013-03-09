package net.andac.aydin.tvdblibrary.connector.exceptions;

@SuppressWarnings("serial")
public abstract class OutboundConnectionException extends Exception {

	final String ERRORMESSAGE = "Error occured in Outbound Operation";

	public OutboundConnectionException(Exception e) {
		super(e);
	}

	public OutboundConnectionException(String message) {
		super(message);
	}
}
