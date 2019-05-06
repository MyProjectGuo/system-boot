package com.system.boot.handler;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult implements Serializable {

	private static final long serialVersionUID = -4585729326513999074L;

	private Integer code;
	private String message;
	private Object data;
}
