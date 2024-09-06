package com.murat.auth.contracts;

import com.murat.shared.utils.ServiceResult;

public interface UserServiceContract {
    ServiceResult<CanAuthorizedEntity> create(CreateCanAuthorizedEntityDTO data);
}
