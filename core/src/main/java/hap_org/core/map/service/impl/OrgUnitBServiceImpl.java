package hap_org.core.map.service.impl;

import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import hap_org.core.map.dto.eBaseResponse;
import hap_org.core.map.dto.oBaseResponse;
import hap_org.core.map.exception.eBaseException;
import hap_org.core.map.mapper.EmployeesAssignMapper;
import hap_org.core.map.mapper.EmployeesMapper;
import hap_org.core.map.mapper.OrgPositionBMapper;
import hap_org.core.map.mapper.OrgUnitBMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hap_org.core.map.dto.OrgUnitB;
import hap_org.core.map.service.IOrgUnitBService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrgUnitBServiceImpl extends BaseServiceImpl<OrgUnitB> implements IOrgUnitBService{
    //不做介绍
    @Autowired
    private OrgUnitBMapper OrgMapper;

    private OrgPositionBMapper OrgPositionMapper;
    private EmployeesMapper EmployeeMapper;
    private EmployeesAssignMapper EmployeeAssignMapper;

    //在日志输出时可打印出日志信息所在类
    private final Logger logger = LoggerFactory.getLogger(OrgUnitBServiceImpl.class);
    //事务回滚
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public oBaseResponse handleOrgUnitDate(IRequest request, List<OrgUnitB> orgUnitB) throws eBaseException {
        oBaseResponse baseResponse = new oBaseResponse();
        int Orgsize = orgUnitB.size();
        //size
        logger.debug("有几个字段",Orgsize);

        for(OrgUnitB orgUnit : orgUnitB){

            if (logger.isDebugEnabled()) {
                //Debug logger 在控制台输出以便调试
                logger.debug("Student Name : {}", orgUnit.getName());
            }
            //判断是哪个表的数据
            if(orgUnit.gettableId() == 0){
                logger.debug("Table ID : {}", orgUnit.gettableId());
                System.out.println("Table ID" + orgUnit.getName());
            }
            if(orgUnit.getUnitId() == null){
                //自定义异常抛出:
                throw new eBaseException("com.hand.hap.error.IdIsNull",10001,"ID is null");
            }else {

                //逻辑实现代码
                OrgUnitB queryDto =  new OrgUnitB();
                queryDto.setUnitId(orgUnit.getUnitId());
                List<OrgUnitB> results = OrgMapper.queryAll(queryDto);

                if(results == null || results.size()==0){
                    //首先判断unitID在源数据表中能否找到数据，如果找得到，就执行更新操作，如果找不到，
                    // 就将unitid设置为空，试用数据表自增的id
                    //orgUnit.setUnitId(null);
                    //判断是否组织类别和组织分类为空处理
                    orgUnit.setUnitCategory(orgUnit.getUnitCategory() == null ? "G" : orgUnit.getUnitCategory());
                    orgUnit.setUnitType(orgUnit.getUnitCode() == null ? "GROUP" : orgUnit.getUnitCode());

                    OrgMapper.insertOrg(orgUnit);

                }else {
                    orgUnit.setUnitId(results.get(0).getUnitId());
                    OrgMapper.insertOrg(orgUnit);
                    //studentsMapper.updateStudents(student);
                }
            }
        }
        return baseResponse;
    }


}