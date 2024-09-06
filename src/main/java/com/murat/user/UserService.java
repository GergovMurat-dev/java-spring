package com.murat.user;

import com.murat.auth.contracts.CanAuthorizedEntity;
import com.murat.auth.contracts.CreateCanAuthorizedEntityDTO;
import com.murat.auth.contracts.UserServiceContract;
import com.murat.shared.utils.ServiceResult;

public class UserService implements UserServiceContract {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public ServiceResult<CanAuthorizedEntity> create(CreateCanAuthorizedEntityDTO data) {
        User user = new User();

        user.setName(data.getName());
        user.setEmail(data.getEmail());
        user.setPassword(data.getPassword());

        try {
            this.userDAO.save(user);

            return ServiceResult.success(user);
        } catch (Exception e) {
            return ServiceResult.failure("Произошла ошибка на стороне сервера, повторите попытку позднее");
        }
    }
}
