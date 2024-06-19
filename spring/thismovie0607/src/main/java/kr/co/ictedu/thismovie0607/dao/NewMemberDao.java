package kr.co.ictedu.thismovie0607.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.ictedu.thismovie0607.vo.NewMemberVO;



@Mapper
public interface NewMemberDao {
	public void add(NewMemberVO vo);
	public List<NewMemberVO> list(Map<String, String> map);
	public NewMemberVO detail(int num);
	public int idcheck(String id);
	public int getTotal(Map<String, String> paramMap);
}
