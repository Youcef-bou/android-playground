package com.example.testhttprequests.api.handlers.account;

import java.util.EnumSet;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import com.example.testhttprequests.api.handlers.ErrorHandler;
import com.example.testhttprequests.api.handlers.BasicHandler;
import com.example.testhttprequests.api.handlers.ErrorResponse;
import com.example.testhttprequests.api.handlers.account.LoginHandler.LoginError;

public interface LoginHandler extends BasicHandler, ErrorHandler<LoginError> {
	public void handleSuccess();
	
	public static enum LoginError {
		INVALID_LOGIN("invalid_login"),
		
		UNKNOWN("other");

		private final String value;
		private LoginError(String value) { this.value = value; }
		
		@JsonCreator
		public static LoginError fromValue(String value) {
			for (LoginError error : LoginError.values()) {
				if (error.value.equals(value))
					return error;
			}
			return UNKNOWN;
		}
	}
	
	public static final class LoginResponse extends ErrorResponse<Void, LoginError> {
		@JsonCreator
		private LoginResponse(
				@JsonProperty("okay") boolean okay,
				@JsonProperty("data") Void data,
				@JsonProperty("errors") EnumSet<LoginError> errors) {
			super(okay, data, errors);
		}
		
		public static Class<LoginResponse> getResponseClass() {
			return LoginResponse.class;
		}
	}
}