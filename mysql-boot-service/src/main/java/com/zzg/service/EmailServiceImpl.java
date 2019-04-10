package com.zzg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzg.api.EmailService;
import com.zzg.entity.Email;
import com.zzg.mapper.EmailMapper;

@Service
public class EmailServiceImpl implements EmailService {
	@Autowired
	private EmailMapper mapper;

	@Override
	public List<Email> getEmails() {
		// TODO Auto-generated method stub
		return mapper.getEmails();
	}

}
