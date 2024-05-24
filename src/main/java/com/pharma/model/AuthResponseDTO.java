package com.pharma.model;

public class AuthResponseDTO 
{
	 private String accessToken;
	 private String tokenType = "Bearer ";

		public AuthResponseDTO(String accessToken, String tokenType) {
		super();
		this.accessToken = accessToken;
		this.tokenType = tokenType;
	}
		public AuthResponseDTO() {
			super();
			// TODO Auto-generated constructor stub
		}
			public AuthResponseDTO(String accessToken) {
		        this.accessToken = accessToken;
		    }
		public String getAccessToken() {
			return accessToken;
		}

		public void setAccessToken(String accessToken) {
			this.accessToken = accessToken;
		}

		public String getTokenType() {
			return tokenType;
		}

		public void setTokenType(String tokenType) {
			this.tokenType = tokenType;
		}

		@Override
		public String toString() {
			return "AuthResponseDTO [accessToken=" + accessToken + ", tokenType=" + tokenType + "]";
		}
	    
	    
}
