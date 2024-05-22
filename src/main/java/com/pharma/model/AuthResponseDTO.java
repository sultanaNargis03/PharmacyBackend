package com.pharma.model;

public class AuthResponseDTO 
{
	 private String accessToken;
	 private String tokenType = "Bearer ";
	 private String currentUserName;

	    public String getCurrentUserName() {
		return currentUserName;
	}

		public AuthResponseDTO(String accessToken, String currentUserName) {
			super();
			this.accessToken = accessToken;
			this.currentUserName = currentUserName;
		}


		public AuthResponseDTO() {
			super();
			// TODO Auto-generated constructor stub
		}

		public void setCurrentUserName(String currentUserName) {
			this.currentUserName = currentUserName;
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
			return "AuthResponseDTO [accessToken=" + accessToken + ", tokenType=" + tokenType + ", currentUserName="
					+ currentUserName + "]";
		}
	    
	    
}
