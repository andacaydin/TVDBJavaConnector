package net.andac.aydin.tvdblibrary.connector.exceptions;

@SuppressWarnings("serial")
public class TVDBOutboundConnectionException extends
		OutboundConnectionException {

	public TVDBOutboundConnectionException(Exception e) {
		super(e);
	}

	String errorMessage = "THETVDB Connection error";
}
