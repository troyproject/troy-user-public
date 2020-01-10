package com.troy.user.web.controller;

import com.troy.commons.dto.out.Res;
import com.troy.commons.dto.out.ResData;
import com.troy.commons.dto.out.ResFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Han
 */
@Controller
@Api(tags = "用户服务响应码字典")
public class ResponseCodeController {


    /**
     * @ApiOperation(value = "通用响应码字典", notes = "通用响应码字典（点击左下角`Try it out!`按钮查看字典详情）")
     */
    @RequestMapping(value = "/response-code/commons", method = {RequestMethod.GET})
    @ApiOperation(value = "通用响应码字典", notes = "通用响应码字典（不包含401、403、`404`等`HTTP`错误码）")
    @ResponseBody
    @ApiResponses({
            @ApiResponse(code = 0, message = "成功"),
            @ApiResponse(code = 9999, message = "失败"),
            @ApiResponse(code = 9998, message = "参数错误"),
            @ApiResponse(code = 9998, message = "{0} 是无效的"),
            @ApiResponse(code = 9998, message = "{0}[{1}] 是无效的"),
            @ApiResponse(code = 9997, message = "没有权限"),
            @ApiResponse(code = 9996, message = "不支持的请求方法"),
            @ApiResponse(code = 9995, message = "未知的客户端"),
            @ApiResponse(code = 9994, message = "未知的服务请求"),
            @ApiResponse(code = 9993, message = "意外的结果"),
            @ApiResponse(code = 9992, message = "批量处理结果既有成功也有失败"),
            @ApiResponse(code = 9991, message = "重复的业务请求"),
            @ApiResponse(code = 9990, message = "用户认证失败"),
            @ApiResponse(code = 9989, message = "用户或密码错误"),
            @ApiResponse(code = 9988, message = "用户被禁用"),
            @ApiResponse(code = 9987, message = "坏的用户凭证"),
            @ApiResponse(code = 9986, message = "客户端认证失败"),
            @ApiResponse(code = 9985, message = "网关错误"),
            @ApiResponse(code = 1000, message = "客户端不存在"),
            @ApiResponse(code = 1001, message = "邮箱已经绑定"),
            @ApiResponse(code = 1002, message = "邮箱还未绑定"),
            @ApiResponse(code = 1003, message = "手机号已经绑定"),
            @ApiResponse(code = 1004, message = "手机号还未绑定"),
            @ApiResponse(code = 1005, message = "谷歌验证码已经绑定"),
            @ApiResponse(code = 1006, message = "谷歌验证码还未绑定"),
            @ApiResponse(code = 1007, message = "用户当前没有可验证方式"),
            @ApiResponse(code = 1008, message = "绑定类型不能为空"),
            @ApiResponse(code = 1009, message = "验证方式不能为空"),
            @ApiResponse(code = 1010, message = "发送方式不能为空"),
            @ApiResponse(code = 1011, message = "邮箱验证码不正确"),
            @ApiResponse(code = 1012, message = "手机验证码不正确"),
            @ApiResponse(code = 1013, message = "谷歌验证码不正确"),
            @ApiResponse(code = 1014, message = "无效的验证码"),
            @ApiResponse(code = 1015, message = "身份认证未通过"),
            @ApiResponse(code = 1016, message = "人机验证未通过"),
            @ApiResponse(code = 1017, message = "不可解绑，邮箱和手机至少绑定一个"),
            @ApiResponse(code = 1018, message = "用户已存在"),
    })
    public Res<ResData> commons() {
        return ResFactory.getInstance().createRes();
    }
}
