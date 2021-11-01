package com.bjworld.groupware.adminuser.service.impl;

import com.bjworld.groupware.adminuser.service.AdminUserService;
import com.bjworld.groupware.adminuser.service.impl.AdminUserMapper;
import com.bjworld.groupware.adminuser.service.impl.AdminUserVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.annotation.Resource;

@Service("adminuserService")
public class AdminUserServiceImpl extends EgovAbstractServiceImpl implements AdminUserService {
    @Resource(name="adminuserMapper")
    private AdminUserMapper adminuserMapper;

    @Override
    public List<AdminUserVO> selectAdminUserList(AdminUserVO paramVO) throws Exception {
        return adminuserMapper.selectAdminUserList(paramVO);
    }

    @Override
    public Integer selectAdminUserListTotCnt(AdminUserVO paramVO) throws Exception {
        return adminuserMapper.selectAdminUserListTotCnt(paramVO);
    }

    @Override
    public AdminUserVO selectAdminUser(AdminUserVO paramVO) throws Exception {
        return adminuserMapper.selectAdminUser(paramVO);
    }

    @Override
    public void mergeAdminUser(AdminUserVO paramVO) throws Exception {
        adminuserMapper.mergeAdminUser(paramVO);
    }

    @Override
    public void insertAdminUser(AdminUserVO paramVO) throws Exception {
        adminuserMapper.insertAdminUser(paramVO);
    }

    @Override
    public void updateAdminUser(AdminUserVO paramVO) throws Exception {
        adminuserMapper.updateAdminUser(paramVO);
    }

    @Override
    public void deleteAdminUser(AdminUserVO paramVO) throws Exception {
        adminuserMapper.deleteAdminUser(paramVO);
    }

	@Override
	public AdminUserVO selectAdminUserLogin(AdminUserVO paramVO) throws Exception {
		return adminuserMapper.selectAdminUserLogin(paramVO);
	}
}
