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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.co.ictedu.thismovie0607.Service.UpboardService;
import kr.co.ictedu.thismovie0607.vo.PageVO;
import kr.co.ictedu.thismovie0607.vo.UpboardVO;

@RestController
@RequestMapping("/uboard")
public class UpboardController {
@Autowired
	private UpboardService upboardService;
	@Autowired
	private PageVO pageVO;
	 @Value("${spring.servlet.multipart.location}")
	    private String filePath;
	 
	 public String getPathTest() {
		 System.out.println("path : " + filePath);
		 return filePath;
	 }
	 
	// http://localhost/thismovie0607/uboard/upList?cPage=1
	 @RequestMapping("/upList")
	 public List<UpboardVO> upList(Model model, @RequestParam Map<String, String>paramMap){
		 System.out.println("파라미터 출력");
	        System.out.println("검색시 넘어오는 파라미터 출력");
	        String cPage = paramMap.get("cPage");
	        System.out.println("cPage : " + cPage + ", searchType:" + paramMap.get("searchType") + ", searchValue:" + paramMap.get("searchValue"));
	        System.out.println("****************************");
	        
	        // 1. total Record
	        pageVO.setTotalRecord(upboardService.upTotalCount(paramMap));
	        int totalRecord = pageVO.getTotalRecord();
	        System.out.println("1. TotalRecord : " + totalRecord);
	        System.out.println("****************************");
	        
	        // 2. total Page
	        int totalPage = (int) Math.ceil(totalRecord / (double) pageVO.getNumPerPage());
	        pageVO.setTotalPage(totalPage);
	        System.out.println("2-4. TotalPage 최종검수 : " + pageVO.getTotalPage());
	        System.out.println("****************************");
	        
	        // 3. totalBlock
	        int totalBlock = (int) Math.ceil((double) totalPage / pageVO.getPagePerBlock());
	        pageVO.setTotalBlock(totalBlock);
	        System.out.println("3. totalBlock :" + pageVO.getTotalBlock());
	        System.out.println("********************************");
	        
	        // 4. 현재 페이지 요청 시 파라미터로 현재 페이지 값을 받음
	        if(cPage != null) {
	            pageVO.setNowPage(Integer.parseInt(cPage));
	        } else {
	            pageVO.setNowPage(1);
	        }
	        System.out.println("4. nowPage : " + pageVO.getNowPage());
	        
	        // 5. 시작과 끝 페이지 계산
	        pageVO.setBeginPerPage((pageVO.getNowPage() - 1) * pageVO.getNumPerPage() + 1);
	        pageVO.setEndPerPage(pageVO.getBeginPerPage() + pageVO.getNumPerPage() - 1);
	        System.out.println("5-1. beginPerPage = " + pageVO.getBeginPerPage());
	        System.out.println("5-2. endPerPage = " + pageVO.getEndPerPage());
	        
	        // 6. 페이지 테스트를 하기 위해서 Map 생성하고 넣어보자.
	     // http://localhost/thismovie0607/uboard/upList?cPage=3
	        Map<String, String> map = new HashMap<>();
	        map.put("begin", String.valueOf(pageVO.getBeginPerPage()));
	        map.put("end", String.valueOf(pageVO.getEndPerPage()));
	        
	        // 기존 map에 paramMap 과 합치기
	        map.putAll(paramMap);
	        System.out.println("6. 기존 map에 paramMap 합침");
	        for (Map.Entry<String, String> e : map.entrySet()) {
	            System.out.println(e.getKey() + "," + e.getValue());
	        }
	          
	        List<UpboardVO> list = upboardService.list(map);
	          
	        int startPage = (int) ((pageVO.getNowPage() - 1) / pageVO.getPagePerBlock()) * pageVO.getPagePerBlock() + 1;
	        int endPage = startPage + pageVO.getPagePerBlock() - 1;
	        if (endPage > pageVO.getTotalPage()) {
	            endPage = pageVO.getTotalPage();
	        }
	        System.out.println("7. startPage = " + startPage);
	        System.out.println("7. endPage = " + endPage);
	          
	        return list; 
	 }
	 
	 @PostMapping("/upAdd")
		//public String upboardAdd(@RequestParam("file") MultipartFile mf, UpBoardVO vo) {
			
		
		public ResponseEntity<?> upAdd(@RequestParam("file") MultipartFile mf, UpboardVO vo) {	
		    if (mf == null || mf.isEmpty()) {
		        throw new IllegalArgumentException("파일이 전송되지 않았습니다.");
		    }
			//MultipartFile mf = vo.getMfile();
			String oriFn = mf.getOriginalFilename();
			StringBuffer path = new StringBuffer(); // [서버경로]
			path.append(filePath).append("\\");
			path.append(oriFn);
			System.out.println("FullPath : "+path);
			//1 path로 파일객체 생성 
			File f = new File(path.toString());
			//2. MultipartFile 객체의 transferTo 사용해서 업로드 한 파일을 복제 
			try {
				mf.transferTo(f);
		    //3. 이미지이름을 vo에 저장 
			vo.setImgn(oriFn);
			
			System.out.println("getImgn : " + vo.getImgn() + ", getTitle : " + vo.getTitle() + ", getWriter : " + vo.getWriter()+ ", getContent : " + vo.getContent()+ ", getreip : " + vo.getReip()+ ", getHit : " + vo.getHit()+ ", getBdate : " + vo.getBdate() );
			upboardService.upAdd(vo);
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return ResponseEntity.ok().body(1);
		}
	 
	 @PostMapping("/upUpdate")
	 public ModelAndView upUpdate(UpboardVO vo) {
		 upboardService.update(vo);
		 ModelAndView mav = new ModelAndView();
		 mav.setViewName("redirect:upList");
		 return mav;
	 }
	 
	 @GetMapping("/upDetail/{num}")
	    public UpboardVO upDetail(@PathVariable("num") int num) {
	        UpboardVO upboardVO = upboardService.detail(num);

	        // 이미지 파일의 전체 경로 설정
	        if (upboardVO.getImgn() != null) {
	            String imagePath = filePath + "\\" + upboardVO.getImgn();
	            upboardVO.setImagePath(imagePath);
	        }

	        return upboardVO;
	    }
	 
	 @GetMapping("/upDelete")
	 public ModelAndView upDelete(int num) {
		 upboardService.delete(num);
		 ModelAndView mav = new ModelAndView();
		 mav.setViewName("redirect:upList");
		 return mav;
	 }
	 
	 
}
