package com.troy.user.web.controller;

import com.troy.commons.dto.in.Req;
import com.troy.commons.dto.out.Res;
import com.troy.commons.dto.out.ResFactory;
import com.troy.user.api.InviteCodeApi;
import com.troy.user.dao.model.user.InviteCodeModel;
import com.troy.user.dto.constants.InviteCodeStatusEnum;
import com.troy.user.dto.in.user.InviteCodeReqData;
import com.troy.user.service.internal.user.InviteCodeService;
import com.troy.user.util.ShareCodeUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 生成邀请码
 */
@Slf4j
@RestController
public class InviteCodeController extends AbstractController implements InviteCodeApi {

    @Autowired
    private InviteCodeService inviteCodeService;

    /*一次生成邀请码数量*/
    private static Integer NUM_CODE = 10;
    /*

    @Deprecated
    @Override
    @ApiOperation(value = "新增用户邀请码", notes = "新增用户邀请码")
    public Res generate() {
        List<InviteCodeModel> list = new ArrayList<>();
        for (int i = 0;i < NUM_CODE; i++){
            InviteCodeModel model = new InviteCodeModel();
            model.setInviteCode(ShareCodeUtil.generateShortUuid());
            model.setStatus(InviteCodeStatusEnum.USE.getCode());
            model.setCreateTime(new Date());
            list.add(model);
        }
        if (!CollectionUtils.isEmpty(list)){
            Integer num  = inviteCodeService.insertBatch(list);
        }
        return ResFactory.getInstance().success(null);
    }
    */

}
