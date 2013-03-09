package net.andac.aydin.tvdblibrary.connector.exceptions;

@SuppressWarnings("serial")
public class TVDBOutboundConnectionException extends
		OutboundConnectionException {

	public TVDBOutboundConnectionException(Exception e) {
		super(e);
	}

	public TVDBOutboundConnectionException(String message) {
		super(message);
	}

	String errorMessage = "THETVDB Connection error";
}
