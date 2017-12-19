package hap_org.core.map.controllers;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.impl.RequestHelper;
import com.hand.hap.intergration.annotation.HapInbound;
import com.hand.hap.system.controllers.BaseController;
import hap_org.core.map.dto.LVEmployee;
import hap_org.core.map.dto.leBaseResponse;
import hap_org.core.map.exception.eBaseException;
import hap_org.core.map.service.ILVEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
// 通过这里配置使下面的映射都在/api/com/hand/hap/interface下
//如果不想进行校验,这只需要加个public更改成公共接口
//如："/api/public/com/hand/hap/interface"
//则不需要进行OAuth2校验即可直接使用接口
@RequestMapping(value = "/api/public/com/hand/hap/interface")
public class LVEmployeeController extends BaseController{


    private ILVEmployeeService service;

    @RequestMapping(value = "/handleLVEmployee", method = RequestMethod.POST)
    @ResponseBody
    public leBaseResponse handleOrgPositionDat(HttpServletRequest request, @RequestBody List<LVEmployee> LVEmployee) throws eBaseException {
        IRequest iRequest = RequestHelper.newEmptyRequest();
        leBaseResponse response = new leBaseResponse();
        try {
            //调用service.handleStudentsDate
            response = service.handleLVEmployeeDate(iRequest, LVEmployee);
            //如果插入成功
            leBaseResponse.Status status = new leBaseResponse.Status();
            status.setCode(99);
            status.setMessage("插入成功！！");
            response.setStatus(status);
        } catch (Exception e) {
            //获取handleStudentsDate抛出的错误信息
            leBaseResponse.Status status = new leBaseResponse.Status();
            //if判断是否自定义异常
            if (e instanceof eBaseException) {
                //获取自定义异常Code,在service中定义
                status.setCode(((eBaseException) e).getCode());
            } else {
                //设置不可预知的异常Code(具体参数看项目是否有做要求)
                status.setCode(10000);
            }
            //获取抛出的异常信息
            status.setMessage(e.getMessage());
            response.setSuccess(false);
            response.setStatus(status);
        }
        return response;
    }

}
