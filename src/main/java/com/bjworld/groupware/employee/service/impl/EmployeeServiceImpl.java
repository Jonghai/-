package com.bjworld.groupware.employee.service.impl;

import com.bjworld.groupware.employee.service.EmployeeService;
import com.bjworld.groupware.employee.service.impl.EmployeeMapper;
import com.bjworld.groupware.employee.service.impl.EmployeeVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.annotation.Resource;

@Service("employeeService")
public class EmployeeServiceImpl extends EgovAbstractServiceImpl implements EmployeeService {
    @Resource(name="employeeMapper")
    private EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeVO> selectEmployeeList(EmployeeVO paramVO) throws Exception {
        return employeeMapper.selectEmployeeList(paramVO);
    }

    @Override
    public Integer selectEmployeeListTotCnt(EmployeeVO paramVO) throws Exception {
        return employeeMapper.selectEmployeeListTotCnt(paramVO);
    }

    @Override
    public EmployeeVO selectEmployee(EmployeeVO paramVO) throws Exception {
        return employeeMapper.selectEmployee(paramVO);
    }

    @Override
    public void mergeEmployee(EmployeeVO paramVO) throws Exception {
        employeeMapper.mergeEmployee(paramVO);
    }

    @Override
    public void insertEmployee(EmployeeVO paramVO) throws Exception {
        employeeMapper.insertEmployee(paramVO);
    }

    @Override
    public void updateEmployee(EmployeeVO paramVO) throws Exception {
        employeeMapper.updateEmployee(paramVO);
    }

    @Override
    public void deleteEmployee(EmployeeVO paramVO) throws Exception {
        employeeMapper.deleteEmployee(paramVO);
    }

	@Override
	public void updateEmployeeSort(EmployeeVO paramVO) throws Exception {
		employeeMapper.updateEmployeeSort(paramVO);
	}
}
