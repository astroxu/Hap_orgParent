package hap_org.core.map.service.impl;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import hap_org.core.map.dto.eBaseResponse;
import hap_org.core.map.dto.opBaseResponse;
import hap_org.core.map.exception.eBaseException;
import hap_org.core.map.mapper.OrgPositionBMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hap_org.core.map.dto.OrgPositionB;
import hap_org.core.map.service.IOrgPositionBService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrgPositionBServiceImpl extends BaseServiceImpl<OrgPositionB> implements IOrgPositionBService{

    //不做介绍
    @Autowired
    private OrgPositionBMapper orgPositionMapper;

    //在日志输出时可打印出日志信息所在类
    private final Logger logger = LoggerFactory.getLogger(OrgPositionBServiceImpl.class);
    //事务回滚
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public opBaseResponse handleOrgPositionDate(IRequest request, List<OrgPositionB> orgPositionB) throws eBaseException {
        opBaseResponse baseResponse = new opBaseResponse();
        for (OrgPositionB orgPositionUnit : orgPositionB) {
            if (logger.isDebugEnabled()) {
                //Debug logger 在控制台输出以便调试
                logger.debug("OrgPositionUnit Name : {}", orgPositionUnit.getName());
            }
            if (orgPositionUnit.getUnitId() == null) {
                //自定义异常抛出:

                throw new eBaseException("com.hand.hap.error.IdIsNull", 10001, "ID is null");
            } else {

                //逻辑实现代码
                OrgPositionB queryDto = new OrgPositionB();
                queryDto.setPositionId(orgPositionUnit.getPositionId());
                List<OrgPositionB> results = orgPositionMapper.queryAllOrgPosition(queryDto);
                if (results == null || results.size() == 0) {
                    orgPositionMapper.insertOrgPosition(orgPositionUnit);
                } else {
                    orgPositionUnit.setUnitId(results.get(0).getPositionId());
                    orgPositionMapper.insertOrgPosition(orgPositionUnit);
                    //studentsMapper.updateStudents(student);
                }
            }
        }
        return baseResponse;
    }
}