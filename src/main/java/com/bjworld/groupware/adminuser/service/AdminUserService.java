package com.bjworld.groupware.adminuser.service;

import com.bjworld.groupware.adminuser.service.impl.AdminUserVO;
import java.util.List;

public interface AdminUserService {

    List<AdminUserVO> selectAdminUserList(AdminUserVO paramVO) throws Exception;

    Integer selectAdminUserListTotCnt(AdminUserVO paramVO) throws Exception;

    AdminUserVO selectAdminUser(AdminUserVO paramVO) throws Exception;

    void mergeAdminUser(AdminUserVO paramVO) throws Exception;

    void insertAdminUser(AdminUserVO paramVO) throws Exception;

    void updateAdminUser(AdminUserVO paramVO) throws Exception;

    void deleteAdminUser(AdminUserVO paramVO) throws Exception;

	AdminUserVO selectAdminUserLogin(AdminUserVO paramVO) throws Exception;
}
