package com.yutian.action;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.yutian.constant.CommonConstant;
import com.yutian.entity.Entry;
import com.yutian.entity.Parter;
import com.yutian.entity.Term;
import com.yutian.fw.ActionWrapper;
import com.yutian.service.EntryService;
import com.yutian.service.ParterService;
import com.yutian.service.TermService;
import com.yutian.util.LocalCommonUtil;
import com.yutian.util.Pagination;

public class CreativityAction extends ActionWrapper
{
	private static final long	serialVersionUID	= 7573873848177505195L;
	@Autowired
	private ParterService 		parterService;
	@Autowired
	private EntryService 		entryService;
	@Autowired
	private TermService 		termService;
	
	private Entry				entry;
	private Pagination			page;
	private Pagination 			entrys;
	private Pagination 			terms;
	
	private Parter				parter;
	private Term				term;
	
	private String[] 			departs;
	
	private JSONObject 			asyc_resp;
	
	private File target;
	private String targetContentType;
	private String targetFileName;
	
	public String execute(){
		try{
			entrys = entryService.getPag(pageNo, rows);
			terms = termService.getPag(pageNo,rows);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "index";
	}
	
	public String toLogin(){
		return "login";
	}
	
	public String login(){
		
		return NONE;
	}
	
	public String toRegister(){
		departs = CommonConstant.departments;
		return "register";
	}
	
	public String register(){
		try{
			parterService.save(parter);
			term.setParter(parter);
			termService.save(term);
			asyc_resp = LocalCommonUtil.assembleMsg(CommonConstant.RESP_SUCCESS,"报名成功");
		}catch(Exception e){
			e.printStackTrace();
			asyc_resp = LocalCommonUtil.assembleMsg(CommonConstant.RESP_FAIL, "报名失败");
		}
		return "asyc_resp";
	}
	
	public String list()
	{
		return "list";
	}
	
	public String show(){
		return "show";
	}

	public String upload(){
		ServletContext c = ServletActionContext.getServletContext();
		try {
			String preservePath = "/img/"+targetFileName;
			FileUtils.copyFile(target, new File(c.getRealPath("")+preservePath));
			//success  error=0
			asyc_resp = LocalCommonUtil.assembleMsg(CommonConstant.RESP_SUCCESS,"上传成功",getBasePath()+preservePath);
		} catch (IOException e) {
			//fail error=1
			e.printStackTrace();
			asyc_resp = LocalCommonUtil.assembleMsg(CommonConstant.RESP_FAIL,"上传失败");
		}
		return "asyc_resp";
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

	public String[] getDeparts() {
		return departs;
	}

	public void setDeparts(String[] departs) {
		this.departs = departs;
	}

	public Parter getParter() {
		return parter;
	}

	public void setParter(Parter parter) {
		this.parter = parter;
	}

	public Term getTerm() {
		return term;
	}

	public void setTerm(Term term) {
		this.term = term;
	}

	public JSONObject getAsyc_resp() {
		return asyc_resp;
	}

	public void setAsyc_resp(JSONObject asyc_resp) {
		this.asyc_resp = asyc_resp;
	}

	public String getTargetContentType() {
		return targetContentType;
	}

	public void setTargetContentType(String targetContentType) {
		this.targetContentType = targetContentType;
	}

	public String getTargetFileName() {
		return targetFileName;
	}

	public void setTargetFileName(String targetFileName) {
		this.targetFileName = targetFileName;
	}
	public Entry getEntry() {
		return entry;
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
	}

	public File getTarget() {
		return target;
	}

	public void setTarget(File target) {
		this.target = target;
	}
}
