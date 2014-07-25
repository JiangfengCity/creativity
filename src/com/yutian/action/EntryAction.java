package com.yutian.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.yutian.entity.Entry;
import com.yutian.fw.ActionWrapper;
import com.yutian.service.EntryService;
import com.yutian.service.ParterService;
import com.yutian.service.TermService;
import com.yutian.util.Pagination;

public class EntryAction extends ActionWrapper
{
	private static final long	serialVersionUID	= 7573873848177505195L;
	private Entry				target;
	private Pagination			page;
	
	@Autowired
	private ParterService parterService;
	@Autowired
	private EntryService entryService;
	@Autowired
	private TermService termService;
	
	private Pagination entrys;
	private Pagination terms;
	
	public String execute(){
		try{
			entrys = entryService.getPag(pageNo, rows);
			terms = termService.getPag(pageNo,rows);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "index";
	}
	
	public String list()
	{
		return "list";
	}
	
	public String show(){
		return "show";
	}

	public String toEnter()
	{
		return "toEnter";
	}

	public String doEnter()
	{
		return "save";
	}
	
	
	
	
	@Override
	public Integer getType()
	{
		return null;
	}
	
	public Pagination getPage()
	{
		return page;
	}
	
	public void setPage( Pagination page )
	{
		this.page = page;
	}

	public EntryService getEntryService() {
		return entryService;
	}

	public void setEntryService(EntryService entryService) {
		this.entryService = entryService;
	}

	public ParterService getParterService() {
		return parterService;
	}

	public void setParterService(ParterService parterService) {
		this.parterService = parterService;
	}

	public TermService getTermService() {
		return termService;
	}

	public void setTermService(TermService termService) {
		this.termService = termService;
	}

	public Pagination getEntrys() {
		return entrys;
	}

	public void setEntrys(Pagination entrys) {
		this.entrys = entrys;
	}

	public Pagination getTerms() {
		return terms;
	}

	public void setTerms(Pagination terms) {
		this.terms = terms;
	}

	public Entry getTarget() {
		return target;
	}

	public void setTarget(Entry target) {
		this.target = target;
	}

}
