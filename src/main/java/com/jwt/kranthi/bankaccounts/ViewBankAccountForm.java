package com.jwt.kranthi.bankaccounts;

public class ViewBankAccountForm extends PagenationBean
{

	String sno = "";

	String accountNo = "";

	String bankName = "";

	String bankBranch = "";

	String accPurpose = "";
	
	String schoolSeq = ""; 

	String searchSno = "";

	String searchAccountNo = "";

	String searchBankName = "";

	String searchBankBranch = "";

	String searchAccPurpose = "";
	
	String searchSchoolSeq = "";
	
	String sortColumn = "";
	
	public String getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	public String getSortMode() {
		return sortMode;
	}

	public void setSortMode(String sortMode) {
		this.sortMode = sortMode;
	}

	String sortMode = "";


	public String getSearchSchoolSeq() 
	{
		return searchSchoolSeq;
	}

	public void setSearchSchoolSeq(String searchSchoolSeq) 
	{
		this.searchSchoolSeq = searchSchoolSeq;
	}

	public String getSchoolSeq() 
	{
		return schoolSeq;
	}

	public void setSchoolSeq(String schoolSeq) 
	{
		this.schoolSeq = schoolSeq;
	}
	 
	public String getSno()
	{
		return sno;
	}

	public void setSno(String sno)
	{
		this.sno = sno;
	}

	
	public String getSearchSno()
	{
		return searchSno;
	}

	public void setSearchSno(String searchSno)
	{
		this.searchSno = searchSno;
	}

	
	public String getSearchAccPurpose()
	{
		return searchAccPurpose;
	}

	public void setSearchAccPurpose(String searchAccPurpose)
	{
		this.searchAccPurpose = searchAccPurpose;
	}

	public String getAccPurpose()
	{
		return accPurpose;
	}

	public void setAccPurpose(String accPurpose)
	{
		this.accPurpose = accPurpose;
	}

	public String getSearchBankBranch()
	{
		return searchBankBranch;
	}

	public void setSearchBankBranch(String searchBankBranch)
	{
		this.searchBankBranch = searchBankBranch;
	}

	public String getSearchBankName()
	{
		return searchBankName;
	}

	public void setSearchBankName(String searchBankName)
	{
		this.searchBankName = searchBankName;
	}

	public String getSearchAccountNo()
	{
		return searchAccountNo;
	}

	public void setSearchAccountNo(String searchAccountNo)
	{
		this.searchAccountNo = searchAccountNo;
	}

	public String getBankBranch()
	{
		return bankBranch;
	}

	public void setBankBranch(String bankBranch)
	{
		this.bankBranch = bankBranch;
	}

	public String getBankName()
	{
		return bankName;
	}

	public void setBankName(String bankName)
	{
		this.bankName = bankName;
	}

	public String getAccountNo()
	{
		return accountNo;
	}

	public void setAccountNo(String accountNo)
	{
		this.accountNo = accountNo;
	}

	public void setSearchFields()
	{
		// TODO Auto-generated method stub
		this.searchAccountNo = this.accountNo;
		this.searchBankBranch = this.bankBranch;
		this.searchBankName = this.bankName;
		this.searchAccPurpose = this.accPurpose;
		this.searchSchoolSeq = this.schoolSeq;
	}
	
	public PagenationBean getPaginationBean(){
		return new PagenationBean();
	}
}
