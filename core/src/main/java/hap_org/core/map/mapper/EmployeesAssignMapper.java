package hap_org.core.map.mapper;

import com.hand.hap.mybatis.common.Mapper;
import hap_org.core.map.dto.EmployeeAssign;

import java.util.List;

public interface EmployeesAssignMapper extends Mapper<EmployeeAssign>{
    int insertEmployeeAssign(EmployeeAssign var);
    //int updateStudents(Students var);
    List<EmployeeAssign> queryAllEmployeeAssign(EmployeeAssign var);
}