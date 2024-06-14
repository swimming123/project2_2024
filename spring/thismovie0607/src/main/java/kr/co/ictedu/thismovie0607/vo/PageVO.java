package kr.co.ictedu.thismovie0607.vo;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

@Getter // Lombok을 사용하여 getter 메서드 자동 생성 => 그렇기에 스프링에서의 getter, setter 만들어 주었던 것을 하지 않는다. 
@Setter // Lombok을 사용하여 setter 메서드 자동 생성
@Component // 스프링 컴포넌트 스캔을 통해 빈으로 등록

public class PageVO {
	//페이징 처리를 위한 속성
	private int nowPage; // 1, 현재 페이지 값, 메뉴 페이지와 연동되는 변수
	private int nowBlock; // 1, 현재 블럭 번호, 각 블럭은 여러 페이지로 구성
	private int totalRecord; // 총 게시물 수, 데이터베이스에서 조회하여 설정
	private int numPerPage; // 10, 한 페이지당 보여질 게시물 수
	private int pagePerBlock; // 5, 한 블럭당 보여질 페이지 수
	private int totalPage; // 전체 페이지 수, totalRecord / numPerPage 계산하여 설정
	private int totalBlock; // 전체 블럭 수, totalPage / pagePerBlock 계산하여 설정
	private int beginPerPage; // 각 페이지 별 시작 게시물의 인덱스 값
	private int endPerPage; // 각 페이지 별 마지막 게시물의 인덱스 값

	// 기본 생성자
	public PageVO() {
		this.nowPage = 1; // 초기 현재 페이지를 1로 설정
		this.nowBlock = 1; // 초기 현재 블럭을 1로 설정
		this.numPerPage = 10; // 한 페이지당 기본적으로 10개의 게시물을 표시
		this.pagePerBlock = 5; // 한 블럭당 기본적으로 5개의 페이지를 표시
		System.out.println("페이지 처리 객체가 생성되었습니다.");
	}
}

/*
@Component 사용 이유
@Component 어노테이션은 스프링 프레임워크에서 일반적인 빈(bean)으로 등록할 클래스에 사용됩니다. 
이 어노테이션을 사용하면 스프링이 해당 클래스를 자동으로 스캔하고 빈으로 등록합니다. 
이를 통해 애플리케이션 컨텍스트에서 이 클래스를 관리하고, 
필요 시 다른 클래스에서 의존성 주입을 통해 사용할 수 있습니다.
 * */
 