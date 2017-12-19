package hap_org.core.map.controllers;

import com.hand.hap.core.impl.RequestHelper;
import com.hand.hap.intergration.annotation.HapInbound;
import hap_org.core.map.dto.eBaseResponse;
import hap_org.core.map.exception.eBaseException;
import org.springframework.stereotype.Controller;
import com.hand.hap.system.controllers.BaseController;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import hap_org.core.map.dto.Employee;
import hap_org.core.map.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.BindingResult;
import java.util.List;

    @Controller
// 通过这里配置使下面的映射都在/api/com/hand/hap/interface下
//如果不想进行校验,这只需要加个public更改成公共接口
//如："/api/public/com/hand/hap/interface"
//
    @RequestMapping(value = "/api/public/com/hand/hap/interface")
    public class EmployeesController extends BaseController{

    @Autowired
    private IEmployeeService service;

        @RequestMapping(value = "/handleEmps", method = RequestMethod.POST)
        @ResponseBody
        @HapInbound(apiName = "hap_org.core.map.handleEmps")
        public eBaseResponse handleEmployeeDate(HttpServletRequest request, @RequestBody List<Employee> Employee) throws eBaseException {
            IRequest iRequest = RequestHelper.newEmptyRequest();
            eBaseResponse response = new eBaseResponse();
            try {
                //调用service.handleStudentsDate
                response = service.handleEmployeeDate(iRequest, Employee);
                //如果插入成功
                eBaseResponse.Status status = new eBaseResponse.Status();
                status.setCode(99);
                status.setMessage("插入成功！！");
                response.setStatus(status);
            } catch (Exception e) {
                //获取handleStudentsDate抛出的错误信息
                eBaseResponse.Status status = new eBaseResponse.Status();
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

    @RequestMapping(value = "/hr/employee/query")
    @ResponseBody
    public ResponseData query(Employee dto, @RequestParam(defaultValue = DEFAULT_PAGE) int page,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize, HttpServletRequest request) {
        IRequest requestContext = createRequestContext(request);
        return new ResponseData(service.select(requestContext,dto,page,pageSize));
    }

    @RequestMapping(value = "/hr/employee/submit")
    @ResponseBody
    public ResponseData update(@RequestBody List<Employee> dto, BindingResult result, HttpServletRequest request){
        getValidator().validate(dto, result);
        if (result.hasErrors()) {
        ResponseData responseData = new ResponseData(false);
        responseData.setMessage(getErrorMessage(result, request));
        return responseData;
        }
        IRequest requestCtx = createRequestContext(request);
        return new ResponseData(service.batchUpdate(requestCtx, dto));
    }

    @RequestMapping(value = "/hr/employee/remove")
    @ResponseBody
    public ResponseData delete(HttpServletRequest request,@RequestBody List<Employee> dto){
        service.batchDelete(dto);
        return new ResponseData();
    }
    }