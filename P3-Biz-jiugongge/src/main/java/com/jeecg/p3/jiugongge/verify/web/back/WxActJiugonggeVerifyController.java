package com.jeecg.p3.jiugongge.verify.web.back;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.jeecgframework.p3.core.util.SystemTools;
import javax.servlet.http.HttpServletResponse;
import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import com.jeecg.p3.jiugongge.verify.entity.WxActJiugonggeVerify;
import com.jeecg.p3.jiugongge.verify.service.WxActJiugonggeVerifyService;
import org.jeecgframework.p3.core.web.BaseController;

 /**
 * 描述：</b>WxActJiugonggeVerifyController<br>审核员管理
 * @author junfeng.zhou
 * @since：2018年04月18日 18时17分28秒 星期三 
 * @version:1.0
 */
@Controller
@RequestMapping("/verify/back/wxActJiugonggeVerify")
public class WxActJiugonggeVerifyController extends BaseController{
  @Autowired
  private WxActJiugonggeVerifyService wxActJiugonggeVerifyService;
  
/**
  * 列表页面
  * @return
  */
@RequestMapping(value="list",method = {RequestMethod.GET,RequestMethod.POST})
public void list(@ModelAttribute WxActJiugonggeVerify query,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
	 	PageQuery<WxActJiugonggeVerify> pageQuery = new PageQuery<WxActJiugonggeVerify>();
	 	pageQuery.setPageNo(pageNo);
	 	pageQuery.setPageSize(pageSize);
	 	VelocityContext velocityContext = new VelocityContext();
		pageQuery.setQuery(query);
		velocityContext.put("ActId", query.getActId());
		velocityContext.put("wxActJiugonggeVerify",query);
		velocityContext.put("pageInfos",SystemTools.convertPaginatedList(wxActJiugonggeVerifyService.queryPageList(pageQuery)));
		String viewName = "jiugongge/verify/wxActJiugonggeVerify-list.vm";
		ViewVelocity.view(request,response,viewName,velocityContext);
}

 /**
  * 详情
  * @return
  */
@RequestMapping(value="toDetail",method = RequestMethod.GET)
public void wxActJiugonggeVerifyDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
		VelocityContext velocityContext = new VelocityContext();
		String viewName = "jiugongge/verify/wxActJiugonggeVerify-detail.vm";
		WxActJiugonggeVerify wxActJiugonggeVerify = wxActJiugonggeVerifyService.queryById(id);
		velocityContext.put("wxActJiugonggeVerify",wxActJiugonggeVerify);
		ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 跳转到添加页面
 * @return
 */
@RequestMapping(value = "/toAdd",method ={RequestMethod.GET, RequestMethod.POST})
public void toAddDialog(HttpServletRequest request,HttpServletResponse response,ModelMap model)throws Exception{
	 VelocityContext velocityContext = new VelocityContext();
	 System.out.println(request.getParameter("actId"));
	 velocityContext.put("ActId",request.getParameter("actId"));
	 String viewName = "jiugongge/verify/wxActJiugonggeVerify-add.vm";
	 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 保存信息
 * @return
 */
@RequestMapping(value = "/doAdd",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doAdd(@ModelAttribute WxActJiugonggeVerify wxActJiugonggeVerify){
	AjaxJson j = new AjaxJson();
	try {
		wxActJiugonggeVerifyService.doAdd(wxActJiugonggeVerify);
		j.setMsg("保存成功");
	} catch (Exception e) {
		j.setSuccess(false);
		j.setMsg("保存失败");
	}
	return j;
}

/**
 * 跳转到编辑页面
 * @return
 */
@RequestMapping(value="toEdit",method = RequestMethod.GET)
public void toEdit(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request) throws Exception{
		 VelocityContext velocityContext = new VelocityContext();
		 WxActJiugonggeVerify wxActJiugonggeVerify = wxActJiugonggeVerifyService.queryById(id);
		 velocityContext.put("wxActJiugonggeVerify",wxActJiugonggeVerify);
		 String viewName = "jiugongge/verify/wxActJiugonggeVerify-edit.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
}

/**
 * 编辑
 * @return
 */
@RequestMapping(value = "/doEdit",method ={RequestMethod.GET, RequestMethod.POST})
@ResponseBody
public AjaxJson doEdit(@ModelAttribute WxActJiugonggeVerify wxActJiugonggeVerify){
	AjaxJson j = new AjaxJson();
	try {
		wxActJiugonggeVerifyService.doEdit(wxActJiugonggeVerify);
		j.setMsg("编辑成功");
	} catch (Exception e) {
		j.setSuccess(false);
		j.setMsg("编辑失败");
	}
	return j;
}


/**
 * 删除
 * @return
 */
@RequestMapping(value="doDelete",method = RequestMethod.GET)
@ResponseBody
public AjaxJson doDelete(@RequestParam(required = true, value = "id" ) String id){
		AjaxJson j = new AjaxJson();
		try {
			wxActJiugonggeVerifyService.doDelete(id);
			j.setMsg("删除成功");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg("删除失败");
		}
		return j;
}


}

