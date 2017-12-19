package hap_org.core.map.service.impl;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import hap_org.core.map.dto.eBaseResponse;
import hap_org.core.map.dto.eaBaseResponse;
import hap_org.core.map.exception.eBaseException;
import hap_org.core.map.mapper.EmployeesAssignMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hap_org.core.map.dto.EmployeeAssign;
import hap_org.core.map.service.IEmployeeAssignService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class EmployeesAssignServiceImpl extends BaseServiceImpl<EmployeeAssign> implements IEmployeeAssignService{

    //不做介绍
    @Autowired
    private EmployeesAssignMapper empAssignMapper;
    //在日志输出时可打印出日志信息所在类
    private final Logger logger = LoggerFactory.getLogger(EmployeesAssignServiceImpl.class);
    //事务回滚
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public eaBaseResponse handleEmployeeAssignDate(IRequest request, List<EmployeeAssign> employeesAssign) throws eBaseException {
        eaBaseResponse baseResponse = new eaBaseResponse();
        for(EmployeeAssign employeeAssign : employeesAssign){
            if (logger.isDebugEnabled()) {
                //Debug logger 在控制台输出以便调试
                logger.debug("AssignId : {}", employeeAssign.getAssignId());
            }
            if(employeeAssign.getAssignId() == null){
                //自定义异常抛出:
                throw new eBaseException("com.hand.hap.error.IdIsNull",10001,"ID is null");
            }else {
                //逻辑实现代码
                EmployeeAssign queryDto =  new EmployeeAssign();
                queryDto.setAssignId(employeeAssign.getAssignId());
                List<EmployeeAssign> results = empAssignMapper.queryAllEmployeeAssign(queryDto);
                if(results == null || results.size()==0){
                    empAssignMapper.insertEmployeeAssign(employeeAssign);
                }else {
                    employeeAssign.setAssignId(results.get(0).getAssignId());
                    empAssignMapper.insertEmployeeAssign(employeeAssign);
                    //studentsMapper.updateStudents(student);
                }
            }
        }
        return baseResponse;
    }


}