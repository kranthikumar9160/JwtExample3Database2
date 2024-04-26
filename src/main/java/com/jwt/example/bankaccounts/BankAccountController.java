package com.jwt.example.bankaccounts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.example.models.User;

@RestController
@RequestMapping("/bankaccounts")
public class BankAccountController {
	
	@Autowired
	private BankAccountService bankAccountService;
	
	@GetMapping("/searchBankAccounts")
	public List<User> searchBankAccounts(@RequestBody ViewBankAccountForm viewBankAccountsForm) {
		
		viewBankAccountsForm.setSearchFields();
		
		String where = bankAccountService.getQueryString(viewBankAccountsForm);
		
		
		
	}
}
