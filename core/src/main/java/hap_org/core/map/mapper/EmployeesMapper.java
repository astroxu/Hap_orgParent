package hap_org.core.map.mapper;

import com.hand.hap.mybatis.common.Mapper;
import hap_org.core.map.dto.Employee;

import java.util.List;

public interface EmployeesMapper extends Mapper<Employee>{
    int insertEmployee(Employee var);

    //int updateStudents(Students var);
    List<Employee> queryAllEmployee(Employee var);
}