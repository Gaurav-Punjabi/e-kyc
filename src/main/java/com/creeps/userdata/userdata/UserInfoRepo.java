package com.creeps.userdata.userdata;

import org.springframework.data.repository.CrudRepository;

public interface UserInfoRepo  extends CrudRepository<UserData, Long> {
    UserData findByUsernameAndPassword(String username, String password);
    UserData findByPhone(String phone);
}
