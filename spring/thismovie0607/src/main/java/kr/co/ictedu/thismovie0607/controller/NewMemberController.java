package kr.co.ictedu.thismovie0607.controller;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.ictedu.thismovie0607.Service.NewMemberService;
import kr.co.ictedu.thismovie0607.vo.NewMemberVO;
import kr.co.ictedu.thismovie0607.vo.PageVO;

@RestController
@RequestMapping("/newmember")
public class NewMemberController {
	
	@Autowired
	private NewMemberService newmemberservice;
	
	@Autowired
	private PageVO pageVO;
	
	// @value => application.properties의 key값으로 설정값을 가져와서 변수에 저장한다.
	@Value("${spring.servlet.multipart.location}")
	private String filePath;
	
	@GetMapping("getPath")
	public String getPathTest() {
		System.out.println("path : " + filePath);
		return filePath;
	}
	
	//public String upboardAdd(@RequestParam("file") MultipartFile mf, UpboardVO vo) {
	@PostMapping("/newmemberAdd")
	public ResponseEntity<?> upboardAdd(@RequestParam("file") 
							MultipartFile mf, NewMemberVO vo,
							HttpServletRequest request) {
		if (mf == null || mf.isEmpty()) {
			throw new IllegalArgumentException("파일이 전송되지 않았습니다.");
		}
		vo.setReip(request.getRemoteAddr());
		// MultipartFile mf = vo.getMfile();
		String oriFn = mf.getOriginalFilename();
		StringBuffer path = new StringBuffer(); // [서버경로]
		path.append(filePath).append("\\");
		path.append(oriFn);
		System.out.println("FullPath : " + path);
		
		// 1. path로 파일객체 생성
		// 1-1. 추상경로까지 포함해서 경로를 저장해줌
		// 1-2. 없는 경로도 만들어줌
		File f = new File(path.toString());
		// 2. MultipartFile 객체의 transferTo 사용해서 업로드 한 파일을 복제
		try {
			mf.transferTo(f);
		// 3. 이미지 이름을 vo에 저장
			vo.setImgname(oriFn);
			System.out.println("1"+vo.getId());
			System.out.println("2"+vo.getPwd());
			System.out.println("3"+vo.getName());
			System.out.println("4"+vo.getAge());
			System.out.println("5"+vo.getGender());
			System.out.println("6"+vo.getPhone());
			System.out.println("7"+vo.getEmail());
			System.out.println("8"+vo.getImgname());
			System.out.println("9"+vo.getReip());
			vo.setMemdate("0000.00.00");
			System.out.println("10"+vo.getMemdate());	
			newmemberservice.add(vo);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		} 
		
		// vue로 통신하기 이전
		//return "redirect:getPath";
		return ResponseEntity.ok().body(1);
	}
	
	/*@PostMapping("/add")
	public ModelAndView addupboard(@RequestBody UpboardVO vo) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:list");
		return mav;
	}*/

	@RequestMapping("/list")
	public List<NewMemberVO> listupboard(NewMemberVO vo,Model model,
			@RequestParam Map<String, String> paramMap){
		System.out.println("파라미터 출력: ");
		System.out.println("검색 시, 링크 시 넘어오는 파라미터 출력: ");
		String cPage = paramMap.get("cPage");
		System.out.println("cPage:" + cPage);
		System.out.println("************************");
		
		// 1. totalRecord
		// PageVO: 전체 데이터의 수를 저장하시오.
		pageVO.setTotalRecord(newmemberservice.getTotal(paramMap));
		int totalRecord = pageVO.getTotalRecord();
		// 몇개인지 세야하니까 int로 했다.
		System.out.println("1. TotalRecord : " + totalRecord);
		
		//2. totalPage
		// totalRecord/numPerpage
		System.out.println("2. TotalPage 검수1 : " + (totalRecord/pageVO.getNumPerPage()));
		System.out.println("3. TotalPage 검수2 : " + (totalRecord/(double)pageVO.getNumPerPage()));
		int totalPage = (int) Math.ceil(totalRecord/(double)pageVO.getNumPerPage());
		                          //올림 (소수점이 나올 수 없게)
		System.out.println("4. TotalPage 검수3 : " + totalPage);
		// PageVO의 Total 페이지에 저장하기
		pageVO.setTotalPage(totalPage);
		System.out.println("5. 최종검수 totalPage : " + pageVO.getTotalPage());
		System.out.println("********************");
		
		// 3. totalBlock // 전체 블록 구하기 => 전체페이지(totalPage) / 보여줄 블록 수 (pagePerBlock)
		// totalBlock = 6/5;
		int totalBlock = (int) Math.ceil((double) totalPage/ pageVO.getPagePerBlock());
		System.out.println("3. totalBlock : " + totalBlock);
		pageVO.setTotalBlock((int) Math.ceil((double) pageVO.getTotalPage()));
		System.out.println("3. totalBlock : " + pageVO.getTotalBlock());
		System.out.println("*********************");
		// 현재 페이지를 요청할 때 파라미터로 현재 페이지 값을 받는다. 1~~~~~~~~~~~n
		if (cPage != null) {
			pageVO.setNowPage(Integer.parseInt(cPage));
		}else {
			pageVO.setNowPage(1); //cPage가 없으면 1페이지
		}
		System.out.println("4. nowPage : " + pageVO.getNowPage());
		// 공식임
		// beginPerPage , endPerPage 값 구하기
		// beginPerPage = (nowPage - 1) * numPerPage + 1;
		                //   2-1=1 * 10 + 1 = 11 
		// endPerPage = (beginPerPage - 1) + numPerPage;
		// System.out.println("5. beginPerPage = " + beginPerPage);
		// System.out.println("5. endPerPage = " + endPerPage);
		pageVO.setBeginPerPage((pageVO.getNowPage() - 1)
				* pageVO.getNumPerPage() + 1);
		pageVO.setEndPerPage((pageVO.getBeginPerPage() - 1)
				+ pageVO.getNumPerPage());
		System.out.println("5. beginPerPage = " + pageVO.getBeginPerPage());
		System.out.println("5. endPerPage = " + pageVO.getEndPerPage());
		
	// 페이지 테스트를  하기위해서 Map 생성하고 넣어보자.
	Map<String, String> map = new HashMap<String, String>();
	map.put("begin", String.valueOf(pageVO.getBeginPerPage()));
	map.put("end", String.valueOf(pageVO.getEndPerPage()));
	
	// *****기본 map에 paramMap과 합치기
	map.putAll(paramMap);
	System.out.println("============Map All=============");
	for(Map.Entry<String, String> e : map.entrySet()) {
		System.out.println(e.getKey() + "," + e.getValue());
	}
	
	
	List<NewMemberVO> list = newmemberservice.list(map);
	
	// 시작 값, 종료 값
	// int startPage = (int)((nowPage - 1) / pagePerBlock) * pagePerBlock + 1;
	// int endPage = startPage + pagePerBlock -1;
	// endPage의 연산의 범위가 우리가 구한 totalPage값 미만이라면
	// totalPage의 값으로 대입시킨다.
	int startPage = (int)((pageVO.getNowPage() - 1)
						/ pageVO.getPagePerBlock()) 
			            * pageVO.getPagePerBlock() + 1;
	                 
	
	int endPage = startPage + pageVO.getPagePerBlock() - 1;
	
	
	if(endPage > pageVO.getTotalPage()) {
		endPage = pageVO.getTotalPage();
	}
	model.addAttribute("startPage", startPage); //블록에 시작 페이지 값
	model.addAttribute("endPage", endPage); //블록에 종료 페이지 값
	model.addAttribute("page",pageVO); // nowPage,pagePerBlock,totalPage
		return newmemberservice.list(map);
	}
	
	@GetMapping("/detail")
	public NewMemberVO detail(NewMemberVO vo) {
		return newmemberservice.detail(vo.getNum());
	}
	
	@RequestMapping("/idcheck")
	public int idcheck(@RequestBody NewMemberVO vo) {
		String id = vo.getId();
		System.out.println("연결완"+id);

		newmemberservice.idcheck(id);
		System.out.println(newmemberservice.idcheck(id));
		return newmemberservice.idcheck(id);
	}
	
}

