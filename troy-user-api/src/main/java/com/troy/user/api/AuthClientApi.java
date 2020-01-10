package com.troy.user.api;


import com.troy.commons.dto.in.Req;
import com.troy.commons.dto.out.Res;
import com.troy.commons.dto.out.ResData;
import com.troy.commons.dto.out.ResPage;
import com.troy.user.api.constants.Constants;
import com.troy.user.dto.in.IdReqData;
import com.troy.user.dto.in.IdsReqData;
import com.troy.user.dto.in.auth.client.AuthClientAddReqData;
import com.troy.user.dto.in.auth.client.AuthClientModifyReqData;
import com.troy.user.dto.in.auth.client.AuthClientQueryReqData;
import com.troy.user.dto.out.auth.client.AuthClientDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * description 鉴权客户端服务
 *
 * @author Han
 * @date 2018-09-30 14:42
 */
public interface AuthClientApi {

    /**
     * description: 新增鉴权客户端
     *
     * @param req
     * @return
     */
    @RequestMapping(value = Constants.URL_AUTH_CLIENT_ADD, method = {RequestMethod.POST})
    Res<ResData> add(Req<AuthClientAddReqData> req);

    /**
     * description: 修改鉴权客户端
     *
     * @param req
     * @return
     */
    @RequestMapping(value = Constants.URL_AUTH_CLIENT_MODIFY, method = {RequestMethod.POST})
    Res<ResData> modify(Req<AuthClientModifyReqData> req);

    /**
     * description: 查看客户端
     *
     * @param req
     * @return
     */
    @RequestMapping(value = Constants.URL_AUTH_CLIENT_VIEW, method = {RequestMethod.POST})
    Res<AuthClientDetails> view(Req<IdReqData> req);

    /**
     * 启用客户端
     *
     * @param req
     * @return
     */
    @RequestMapping(value = Constants.URL_AUTH_CLIENT_ENABLE, method = {RequestMethod.POST})
    Res<ResData> enable(Req<IdsReqData> req);

    /**
     * 禁用客户端
     *
     * @param req
     * @return
     */
    @RequestMapping(value = Constants.URL_AUTH_CLIENT_DISABLE, method = {RequestMethod.POST})
    Res<ResData> disable(Req<IdsReqData> req);

    /**
     * description: 删除鉴权客户端
     *
     * @param req
     * @return
     */
    @RequestMapping(value = Constants.URL_AUTH_CLIENT_REMOVE, method = {RequestMethod.POST})
    Res<ResData> remove(Req<IdReqData> req);

    /**
     * description: 分页查询鉴权客户端
     *
     * @param req
     * @return
     */
    @RequestMapping(value = Constants.URL_AUTH_CLIENT_LIST_PAGE, method = {RequestMethod.POST})
    Res<ResPage<AuthClientDetails>> pageList(Req<AuthClientQueryReqData> req);
}
