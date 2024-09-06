package com.murat.auth;

import com.murat.auth.contracts.CanAuthorizedEntity;
import com.murat.auth.contracts.UserServiceContract;
import com.murat.auth.dto.RegisterDTO;
import com.murat.shared.utils.ServiceResult;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserServiceContract userService;

    public AuthService(UserServiceContract userService) {
        this.userService = userService;
    }

    public ServiceResult<CanAuthorizedEntity> register(RegisterDTO data) {

        ServiceResult<CanAuthorizedEntity> createUserServiceResult = this.userService.create(data);

        if (createUserServiceResult.isError()) {
            return createUserServiceResult;
        }

        CanAuthorizedEntity user = createUserServiceResult.getValue();

        return ServiceResult.success(user);
    }
}
