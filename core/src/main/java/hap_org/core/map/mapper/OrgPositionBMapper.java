package hap_org.core.map.mapper;

import com.hand.hap.mybatis.common.Mapper;
import hap_org.core.map.dto.OrgPositionB;

import java.util.List;

public interface OrgPositionBMapper extends Mapper<OrgPositionB>{
    int insertOrgPosition(OrgPositionB var);

    //int updateStudents(Students var);
    List<OrgPositionB> queryAllOrgPosition(OrgPositionB var);
}