package com.securecode.restService.Request;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EditMessageRequest {

	private final int messageId;
	private final int authorId;
	private final String message;

	@JsonCreator
	public EditMessageRequest(@JsonProperty("messageid") int messageId, @JsonProperty("authorid") int authorId, @JsonProperty("message") String message) {
		this.messageId = messageId;
		this.authorId = authorId;
		this.message = message;
	}

	public int getAuthorId() {
		return authorId;
	}

	public int getMessageId() {
		return messageId;
	}

	public String getMessage() {
		return message;
	}

}