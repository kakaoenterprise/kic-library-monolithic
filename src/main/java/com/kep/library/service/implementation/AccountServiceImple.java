package com.kep.library.service.implementation;

import com.kep.library.dto.ManagerDto;
import com.kep.library.entity.ManagerEntity;
import com.kep.library.factory.JwtFactory;
import com.kep.library.repository.ManagerAccountRepository;
import com.kep.library.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("AccountService")
public class AccountServiceImple implements AccountService {

  //@Autowired
  private final JwtFactory jwtFactory = new JwtFactory();
  @Autowired
  private ManagerAccountRepository managerAccountRepository;

  @Override
  public boolean createManager(ManagerDto manager) {
    try {
      managerAccountRepository.save(manager.toEntity());
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  @Override
  public ManagerDto getDetailManager(String librarianId) {
    ModelMapper modelMapper = new ModelMapper();
    ManagerDto manangerDto = modelMapper.map(
            managerAccountRepository
                    .findByLibrarianId(librarianId), ManagerDto.class);

    return manangerDto;
  }

  @Override
  public String generateToken(ManagerDto manager) {
    return jwtFactory.generateToken(manager);
  }

  @Override
  public boolean validationToken(String token) {
    try {
      jwtFactory.decodeJwt(token);
    } catch (Exception e) {
      return false;
    }
    return false;
  }

  @Override
  public ManagerDto checkPassword(ManagerDto managerDto) {
    ModelMapper modelMapper = new ModelMapper();
    ManagerEntity manager = managerAccountRepository.findByLibrarianIdAndPassword(
            managerDto.getLibrarianId(),
            managerDto.getPassword());
    if (manager != null)
      managerDto = modelMapper.map(manager, ManagerDto.class);
    else managerDto = null;
    return managerDto;
  }

  @Override
  public String authentication(ManagerDto manager) {
    if (null == checkPassword(manager)) {
      return "";
    } else {
      return generateToken(manager);
    }
  }

}
