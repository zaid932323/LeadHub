package com.leadhub.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ErrorResponse {

	private String status = "error";
    private ErrorResponseDetails errorResponse;
    

	public ErrorResponse(String code, List<String> messages) {
        this.errorResponse = new ErrorResponseDetails(code, messages);
    }
}
