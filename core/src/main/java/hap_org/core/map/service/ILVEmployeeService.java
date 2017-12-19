package hap_org.core.map.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.core.exception.BaseException;
import com.hand.hap.system.service.IBaseService;
import hap_org.core.map.dto.LVEmployee;
import hap_org.core.map.dto.leBaseResponse;
import hap_org.core.map.exception.eBaseException;

import java.util.List;

public interface ILVEmployeeService extends IBaseService<LVEmployee>, ProxySelf<IOrgUnitBService> {

    leBaseResponse handleLVEmployeeDate(IRequest var1, List<LVEmployee> var2) throws BaseException, eBaseException;

}
