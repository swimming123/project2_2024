package kr.co.ictedu.thismovie0607.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ictedu.thismovie0607.dao.NewMemberDao;
import kr.co.ictedu.thismovie0607.vo.NewMemberVO;



@Service
public class NewMemberService {
	
	@Autowired
	private NewMemberDao newmemberdao;
	
	public void add(NewMemberVO vo) {
		newmemberdao.add(vo);
	}
	public List<NewMemberVO> list(Map<String, String> map){
		return newmemberdao.list(map);
	}
	public NewMemberVO detail(int num) {
		return newmemberdao.detail(num);
	}
	public int idcheck(String id) {
		return newmemberdao.idcheck(id);
	}
	public int getTotal(Map<String, String> paramMap) {
		return newmemberdao.getTotal(paramMap);
	}
}
