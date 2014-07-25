package com.yutian.util;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pagination implements Serializable {

	private static final long serialVersionUID = -877329581501192005L;

	private int count;
	
	private int startRow = 0;
	private int pageSize = 20;
	private String url;
	private String param;
	private int currentPage;
	
	private boolean hasPrevious = false;
	private boolean hasNext = false;
	
	private int maxPage ;
	private int previousStartRow;
	private int nextStartRow;
	
	private List<Integer> pageInfo;
	
	private List<?> resultList;
	
	public Pagination(int count,int startRow,int pageSize,String url,String param,List<?> resultList){
		this.count = count;
		this.currentPage = startRow/pageSize==0? 1 : startRow/pageSize+1;
		this.startRow = startRow;
		this.pageSize = pageSize;
		this.url = url;
		this.param = param;
		this.resultList = resultList;
		
		this.maxPage = count % pageSize == 0 ? count/pageSize : count/pageSize +1;
		if(this.maxPage == 0) this.maxPage = 1;
		
		if(currentPage > 1){
			this.hasPrevious = true;
			this.previousStartRow = (currentPage -2)* pageSize;
		}
		if(currentPage < maxPage ){
			this.hasNext = true;
			this.nextStartRow = (currentPage)* pageSize;
		}
		setPageInfo();
		
	}
	
	public Pagination(int startRow,int pageSize,String url,String param){
		this.startRow = startRow;
		this.pageSize = pageSize;
		this.url = url;
		this.param = param;
	}
	
	public Pagination(int startRow,int pageSize){
		this.startRow = startRow;
		this.pageSize = pageSize;
	}
	
	private void setPageInfo(){
		
		List<Integer> list = new ArrayList<Integer>();
		for(int i = currentPage-8;i<currentPage ;i++){
			if(i<1)continue;
			list.add(i);
		}
		for(int i = currentPage;i<currentPage+8 ;i++){
			if(i>maxPage)continue;
			list.add(i);
		}
		this.pageInfo =  list;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
	
	public void addParam(String param) {
		this.param += param;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public boolean isHasPrevious() {
		return hasPrevious;
	}

	public void setHasPrevious(boolean hasPrevious) {
		this.hasPrevious = hasPrevious;
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public int getPreviousStartRow() {
		return previousStartRow;
	}

	public void setPreviousStartRow(int previousStartRow) {
		this.previousStartRow = previousStartRow;
	}

	public int getNextStartRow() {
		return nextStartRow;
	}

	public void setNextStartRow(int nextStartRow) {
		this.nextStartRow = nextStartRow;
	}

	public List<Integer> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(List<Integer> pageInfo) {
		this.pageInfo = pageInfo;
	}
	
	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	
	public List<?> getResultList() {
		return resultList;
	}

	public void setResultList(List<?> resultList) {
		this.resultList = resultList;
	}
}
