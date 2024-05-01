package com.jwt.kranthi.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwt.kranthi.models.User;
import com.jwt.kranthi.repositories.ExportCsvUserDataRepository;

@Service
public class ExportCsvUserDataService {

	@Autowired
	private ExportCsvUserDataRepository exportCsvUserDataRepository;
	
	
	public void getUserDataCsv() throws FileNotFoundException {
		List<User> users = exportCsvUserDataRepository.findAll();

		PrintWriter pw =  new PrintWriter(new File("C://Users/Admin/Downloads/user_data.csv"));

		StringBuilder sb = new StringBuilder();
		
		sb.append("Id");
		sb.append("email");
		sb.append("name");
		sb.append("password");
		sb.append("\r\n");
		
		for(User user : users) {
			sb.append(user.getId());
			sb.append(",");
			sb.append(user.getEmail());
			sb.append(",");
			sb.append(user.getName());
			sb.append(",");
			sb.append(user.getPassword());
			sb.append("\r\n");
		}
		
		pw.write(sb.toString());
		pw.close();
	}
}
