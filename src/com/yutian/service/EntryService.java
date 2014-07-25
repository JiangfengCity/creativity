package com.yutian.service;

import com.yutian.fw.CommonService;
import com.yutian.util.Pagination;

public class EntryService extends CommonService {
	public Pagination getPag(int pageNo,int rows){
		return dao.getPageByHql( "from Entry order by createTime desc", null, pageNo, rows );
	}
}
