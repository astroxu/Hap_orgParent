package hap_org.core.map.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import hap_org.core.map.dto.OrgPositionB;
import hap_org.core.map.dto.opBaseResponse;
import hap_org.core.map.exception.eBaseException;

import java.util.List;

public interface IOrgPositionBService extends IBaseService<OrgPositionB>, ProxySelf<IOrgPositionBService>{
    opBaseResponse handleOrgPositionDate(IRequest var1, List<OrgPositionB> var2) throws eBaseException;
}