package com.leadhub.response;

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
public class ApiResponse {

	private String status = "success";
    private Object data;
    
	
	public ApiResponse(Object data) {
        this.data = data;
    }

}
