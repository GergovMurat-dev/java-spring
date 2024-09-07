package com.murat.auth.contracts;

import com.murat.shared.utils.ServiceResult;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServiceContract extends UserDetailsService {
    ServiceResult<CanAuthorizedEntity> create(CreateCanAuthorizedEntityDTO data);

    ServiceResult<CanAuthorizedEntity> createDefaultAdmin();
}
