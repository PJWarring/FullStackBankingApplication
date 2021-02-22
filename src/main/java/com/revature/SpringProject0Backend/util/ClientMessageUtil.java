package com.revature.SpringProject0Backend.util;

public class ClientMessageUtil {
	public static final ClientMessage SUCCESSFULLY_DELETED = new ClientMessage("Successfully deleted");
	public static final ClientMessage DELETION_FAILED = new ClientMessage("Failed deletion");
	public static final ClientMessage VALID_FIELDS = new ClientMessage("Valid fields");
	public static final ClientMessage INVALID_FIELDS = new ClientMessage("Invalid fields");
}
