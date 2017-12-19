package hap_org.core.map.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import hap_org.core.map.dto.EmployeeAssign;
import hap_org.core.map.dto.eaBaseResponse;
import hap_org.core.map.exception.eBaseException;

import java.util.List;

public interface IEmployeeAssignService extends IBaseService<EmployeeAssign>, ProxySelf<IEmployeeAssignService>{
    eaBaseResponse handleEmployeeAssignDate(IRequest var1, List<EmployeeAssign> var2) throws eBaseException;
}
