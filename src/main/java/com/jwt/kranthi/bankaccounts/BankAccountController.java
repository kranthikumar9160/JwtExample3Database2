package com.jwt.kranthi.bankaccounts;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.kranthi.models.User;

@RestController
@RequestMapping("/bankaccounts")
public class BankAccountController {
	
	@Autowired
	private BankAccountService bankAccountService;
	
	@GetMapping("/searchBankAccounts")
	public List<User> searchBankAccounts(@RequestBody ViewBankAccountForm viewBankAccountsForm) throws SQLException {
		
		viewBankAccountsForm.setSearchFields();
		
		String where = bankAccountService.getQueryString(viewBankAccountsForm);
		
		bankAccountService.setViewRows(viewBankAccountsForm, true);
		
		
		
		
	}
}
