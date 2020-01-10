package com.troy.user.web.controller;

import com.troy.commons.dto.in.Req;
import com.troy.commons.dto.out.Res;
import com.troy.commons.dto.out.ResData;
import com.troy.commons.dto.out.ResPage;
import com.troy.commons.proxy.GreedyRequestProxy;
import com.troy.commons.proxy.SimpleRequestProxy;
import com.troy.user.api.AuthClientApi;
import com.troy.user.dto.in.IdReqData;
import com.troy.user.dto.in.IdsReqData;
import com.troy.user.dto.in.auth.client.AuthClientAddReqData;
import com.troy.user.dto.in.auth.client.AuthClientModifyReqData;
import com.troy.user.dto.in.auth.client.AuthClientQueryReqData;
import com.troy.user.dto.out.auth.client.AuthClientDetails;
import com.troy.user.service.internal.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * description 鉴权客户端 HTTP 服务
 *
 * @author Han
 * @date 2018-10-18 16:31
 */
@RestController
public class AuthClientController extends AbstractController implements AuthClientApi {

    @Autowired
    private AuthService authService;

    /**
     * description: 添加客户端
     *
     * @param req
     * @return
     */
    @Override
    public Res<ResData> add(@RequestBody Req<AuthClientAddReqData> req) {
        Res<ResData> res = this.process(req, (SimpleRequestProxy<AuthClientAddReqData>) (reqHead, reqData) -> {
            this.authService.add(reqData);
            return true;
        });
        return res;
    }

    /**
     * description: 修改客户端
     *
     * @param req
     * @return
     */
    @Override
    public Res<ResData> modify(@RequestBody Req<AuthClientModifyReqData> req) {
        Res<ResData> res = this.process(req, (SimpleRequestProxy<AuthClientModifyReqData>) (reqHead, reqData) -> {
            this.authService.modify(reqData);
            return true;
        });
        return res;
    }

    /**
     * description: 查看客户端
     *
     * @param req
     * @return
     */
    @Override
    public Res<AuthClientDetails> view(@RequestBody Req<IdReqData> req) {
        Res<AuthClientDetails> res = this.process(req, (GreedyRequestProxy<IdReqData, AuthClientDetails>) (reqHead, reqData) -> {
            AuthClientDetails resData = this.authService.view(reqData);
            return resData;
        });
        return res;
    }

    /**
     * 启用客户端
     *
     * @param req
     * @return
     */
    @Override
    public Res<ResData> enable(@RequestBody Req<IdsReqData> req) {
        Res<ResData> res = super.process(req, (SimpleRequestProxy<IdsReqData>) (reqHead, reqData) -> {
            boolean state = this.authService.enable(req.getData());
            return state;
        });
        return res;
    }

    /**
     * 禁用客户端
     *
     * @param req
     * @return
     */
    @Override
    public Res<ResData> disable(@RequestBody Req<IdsReqData> req) {
        Res<ResData> res = super.process(req, (SimpleRequestProxy<IdsReqData>) (reqHead, reqData) -> {
            boolean state = this.authService.disable(req.getData());
            return state;
        });
        return res;
    }

    /**
     * description: 移除客户端
     *
     * @param req
     * @return
     */
    @Override
    public Res<ResData> remove(@RequestBody Req<IdReqData> req) {
        Res<ResData> res = this.process(req, (SimpleRequestProxy<IdReqData>) (reqHead, reqData) -> {
            this.authService.remove(reqData);
            return true;
        });
        return res;
    }

    /**
     * description: 查询客户端
     *
     * @param req
     * @return
     */
    @Override
    public Res<ResPage<AuthClientDetails>> pageList(@RequestBody Req<AuthClientQueryReqData> req) {
        Res<ResPage<AuthClientDetails>> res = this.process(req, (GreedyRequestProxy<AuthClientQueryReqData, ResPage<AuthClientDetails>>) (reqHead, reqData) -> {
            ResPage<AuthClientDetails> pageInfo = this.authService.query(reqData);
            return pageInfo;
        });
        return res;
    }
}
