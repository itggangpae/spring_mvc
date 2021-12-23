package kr.co.adamsoft.domain;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Member {
	@NotNull
	@Pattern(regexp ="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(?:[a-zA-Z]{2,6})$", message="Invalid E-Mail")
	private String email;
	
	@NotNull
	@Size(min=5)
	private String pw;
	private String loginType;
	@DateTimeFormat(pattern="yyyyMMDD")
	private Date birthday;

}
