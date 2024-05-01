package com.jwt.kranthi.bankaccounts;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name="bank_accounts")
public class BankAccount {
	

	public BankAccount() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bankAccountSeq;
    String acNo;
    String bankName;
    String branch;
    String accPurpose;
    String createUser;
    Date createDate;
    String updateUser;
    Date updateDate;
}
