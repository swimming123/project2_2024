package kr.co.ictedu.thismovie0607.dao;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

import kr.co.ictedu.thismovie0607.vo.UpboardVO;

@Mapper
public interface UpboardDao {
	public void upAdd(UpboardVO vo);
	public List<UpboardVO> upList(Map<String, String> map);
	public void upHit(int num);
	public UpboardVO upDetail(int num);
	public void upDelete(int num);
	public int upTotalCount(Map<String, String> map);
	public void upUpdate(UpboardVO vo);

}




