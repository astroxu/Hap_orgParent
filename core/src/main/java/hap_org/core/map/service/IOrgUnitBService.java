package hap_org.core.map.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import hap_org.core.map.dto.OrgUnitB;
import hap_org.core.map.dto.oBaseResponse;
import hap_org.core.map.exception.eBaseException;

import java.util.List;

public interface IOrgUnitBService extends IBaseService<OrgUnitB>, ProxySelf<IOrgUnitBService>{
    oBaseResponse handleOrgUnitDate(IRequest var1, List<OrgUnitB> var2) throws eBaseException;

}
