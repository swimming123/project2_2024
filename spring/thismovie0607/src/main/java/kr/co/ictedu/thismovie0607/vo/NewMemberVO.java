package kr.co.ictedu.thismovie0607.vo;
import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("newmemvo")
public class NewMemberVO {
	private int num;
	private String id;
	private String pwd;
	private String name;
	private String age;
	private String gender;
	private String phone;
	private String email;
	private String imgname;
	private String reip;
	private String height;
	private String weight;
	private String bmi;
	private String pred;
	private String memdate;
	private MultipartFile mfile;
}