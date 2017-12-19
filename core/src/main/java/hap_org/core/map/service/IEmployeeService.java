package hap_org.core.map.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import hap_org.core.map.dto.Employee;
import hap_org.core.map.dto.eBaseResponse;
import hap_org.core.map.exception.eBaseException;

import java.util.List;

public interface IEmployeeService extends IBaseService<Employee>, ProxySelf<IEmployeeService>{
    eBaseResponse handleEmployeeDate(IRequest var1, List<Employee> var2) throws eBaseException;
}