package com.example.springweb.adapters.repositories;

import com.example.springweb.entities.UserEntity;
import com.example.springweb.services.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Qualifier("InMemory")
public class UserRepositoryInMemoryImpl implements UserRepository {
  private final List<UserEntity> storage = new ArrayList<>();
  private long nextId = 0;

  private long getNextId(){
    return nextId++;
  }

  @Override
  public UserEntity saveUser(UserEntity userEntity) {
    userEntity.setId(getNextId());
    storage.add(userEntity);
    return userEntity;
  }

  @Override
  public UserEntity findUserByEmail(String email) {
    return storage.stream().filter(a -> a.getEmail().equals(email)).findFirst().orElse(null);
  }
}
