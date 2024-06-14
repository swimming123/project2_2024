package kr.co.ictedu.thismovie0607.vo;
import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Alias("upvo") // UpBoard VO
@Setter
@Getter
public class UpboardVO {
	 private int num;
     private String title;
     private String writer;
     private String content;
     private int hit;
     private String reip;
     private String bdate;
     private String imgn;
     private MultipartFile mfile;
     private String imagePath;
}
