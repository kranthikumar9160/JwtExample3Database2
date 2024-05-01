package com.jwt.kranthi.controller;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.kranthi.services.ExportCsvUserDataService;

@RestController("/export")
public class ExportCsvUserDataController {
	
	@Autowired
	private ExportCsvUserDataService exportCsvUserDataService;
	
	@GetMapping("/getUserDataCsv")
	public void getUserDataCsv() throws FileNotFoundException {
		exportCsvUserDataService.getUserDataCsv();
	}
}
