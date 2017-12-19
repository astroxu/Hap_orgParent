package hap_org.core.map.service.impl;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import hap_org.core.map.dto.eBaseResponse;
import hap_org.core.map.exception.eBaseException;
import hap_org.core.map.mapper.EmployeesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hap_org.core.map.dto.Employee;
import hap_org.core.map.service.IEmployeeService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class EmployeesServiceImpl extends BaseServiceImpl<Employee> implements IEmployeeService{


    //不做介绍
    @Autowired
    private EmployeesMapper employeeMapper;
    //在日志输出时可打印出日志信息所在类
    private final Logger logger = LoggerFactory.getLogger(EmployeesServiceImpl.class);
    //事务回滚
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public eBaseResponse handleEmployeeDate(IRequest request, List<Employee> employees) throws eBaseException {
        eBaseResponse baseResponse = new eBaseResponse();
        for(Employee employee : employees){
            if (logger.isDebugEnabled()) {
                //Debug logger 在控制台输出以便调试
                logger.debug("Employee Name : {}", employee.getName());
            }
            if(employee.getEmployeeId() == null){
                //自定义异常抛出:
                throw new eBaseException("com.hand.hap.error.IdIsNull",10001,"ID is null");
            }else {
                //逻辑实现代码
                Employee queryDto =  new Employee();
                queryDto.setEmployeeId(employee.getEmployeeId());
                List<Employee> results = employeeMapper.queryAllEmployee(queryDto);
                if(results == null || results.size()==0){
                    employeeMapper.insertEmployee(employee);
                    /* if (employee.getEffectiveEndDate())*/
                     /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    System.out.println(sdf.format(new Date()));*/
                }else {
                    employee.setEmployeeId(results.get(0).getEmployeeId());
                    employeeMapper.insertEmployee(employee);
                    //studentsMapper.updateStudents(student);
                }
            }
        }
        return baseResponse;
    }


}