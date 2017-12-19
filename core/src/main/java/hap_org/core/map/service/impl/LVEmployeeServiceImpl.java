package hap_org.core.map.service.impl;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import hap_org.core.map.dto.Employee;
import hap_org.core.map.dto.LVEmployee;
import hap_org.core.map.dto.leBaseResponse;
import hap_org.core.map.exception.eBaseException;
import hap_org.core.map.mapper.EmployeesMapper;
import hap_org.core.map.service.ILVEmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class LVEmployeeServiceImpl extends BaseServiceImpl<LVEmployee> implements ILVEmployeeService {

    private EmployeesMapper employeesMapper;

    //在日志输出时可打印出日志信息所在类
    private final Logger logger = LoggerFactory.getLogger(OrgPositionBServiceImpl.class);
    //事务回滚
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public leBaseResponse handleLVEmployeeDate(IRequest var1, List<LVEmployee> lvEmployees) throws eBaseException {
        leBaseResponse baseResponse = new leBaseResponse();
        for (LVEmployee lvEmployee : lvEmployees) {
            if (logger.isDebugEnabled()) {
                //Debug logger 在控制台输出以便调试
                logger.debug("OrgPositionUnit Name : {}", lvEmployee.getPositionCode());
            }
            if (lvEmployee.getPositionCode() == null) {
                //自定义异常抛出:

                throw new eBaseException("com.hand.hap.error.IdIsNull", 10001, "ID is null");
            } else {

                //查询数据是否为空
                Employee queryDto = new Employee();
                queryDto.setEmployeeCode(lvEmployee.getEmployeename());
                List<Employee> results = employeesMapper.queryAllEmployee(queryDto);
                if (results == null || results.size() == 0) {

                    /*************************写入员工表数据*******************************/
                    Employee insertDto = new Employee();
                    //员工编码
                    insertDto.setEmployeeCode(lvEmployee.getHrcode());
                    //员工姓名
                    insertDto.setName(lvEmployee.getEmployeename());
                    //员工邮箱
                    insertDto.setEmail(lvEmployee.getEmail());
                    //员工电话
                    insertDto.setMobil(lvEmployee.getMobilenumber());
                    //员工性别
                    if (lvEmployee.getGender().toString() == "男") {
                        insertDto.setGender("M");
                    } else if (lvEmployee.getGender().toString() == "女") {
                        insertDto.setGender("F");
                    }
                    //员工ID号
                    insertDto.setCertificateId(lvEmployee.getHrcode());
                    //员工状态
                    insertDto.setStatus(lvEmployee.getBcstatusnew());
                    //员工证件类型
                    insertDto.setCertificateType("HRCODE");

                    employeesMapper.insertEmployee(insertDto);
                /*************************写入员工表数据*******************************/

                /*************************写入组织表数据*******************************/

                /*************************写入组织表数据*******************************/
                } else {
                   /* orgPositionUnit.setUnitId(results.get(0).getPositionId());
                    orgPositionMapper.insertOrgPosition(orgPositionUnit);*/
                    //studentsMapper.updateStudents(student);
                }
            }
        }
        return baseResponse;
    }


}
