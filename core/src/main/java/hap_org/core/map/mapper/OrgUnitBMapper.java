package hap_org.core.map.mapper;

import com.hand.hap.mybatis.common.Mapper;
import hap_org.core.map.dto.OrgUnitB;

import java.util.List;

public interface OrgUnitBMapper extends Mapper<OrgUnitB>{
    int insertOrg(OrgUnitB var);

    //int updateStudents(Students var);
    List<OrgUnitB> queryAll(OrgUnitB var);
}