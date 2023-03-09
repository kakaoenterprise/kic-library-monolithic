package com.kep.library.service;

import com.kep.library.dto.ManagerDto;


public interface AccountService {

  boolean createManager(ManagerDto manager);

  ManagerDto getDetailManager(String librarianId);

  String generateToken(ManagerDto manager);

  boolean validationToken(String token);

  ManagerDto checkPassword(ManagerDto manager);

  String authentication(ManagerDto manager);
}
