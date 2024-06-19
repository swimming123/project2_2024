package kr.co.ictedu.thismovie0607.vo;
import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("mvo")
public class MemberVO {
	private int number;
	private String id;
	private String pwd;
	private String name;
	private String email;
	private String tel;
	private String reip;
	private String mdate;
}
