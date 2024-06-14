package kr.co.ictedu.thismovie0607.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ictedu.thismovie0607.dao.UpboardDao;
import kr.co.ictedu.thismovie0607.vo.UpboardVO;

@Service
public class UpboardService {
	 @Autowired
	    private UpboardDao upboardDao;
	    
	    public void upAdd(UpboardVO vo) {
	        upboardDao.upAdd(vo);
	    }
	    
	    public List<UpboardVO>list(Map<String, String>map){
	    	return upboardDao.upList(map);
	    }
	    
	    public void hit(int num) {
	        upboardDao.upHit(num);
	    }
	    
	    public UpboardVO detail(int num) {
	    	hit(num);
	    	return upboardDao.upDetail(num);
	    }
	    
	    public void delete(int num) {
	        upboardDao.upDelete(num);
	    }
	    
	    public int upTotalCount(Map<String, String>map) {
	    	return upboardDao.upTotalCount(map);
	    }
	    
	    public void update(UpboardVO vo) {
	    	upboardDao.upUpdate(vo);
	    }
}
