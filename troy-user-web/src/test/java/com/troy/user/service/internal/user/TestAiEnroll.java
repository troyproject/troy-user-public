package com.troy.user.service.internal.user;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.troy.commons.dto.constants.Constants;
import com.troy.commons.dto.in.Req;
import com.troy.commons.dto.in.ReqData;
import com.troy.commons.dto.in.ReqHead;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//import com.alibaba.druid.util.Base64;


enum AppType{
	COMMON("通用", 0),
	RETAIL_BOSS("零售老板内参", 1),
	INVESTOR_APP("创投助手", 2),
	REAL_ESTATE_DAILY("地产未来日报", 3),
	JINGCHAIN_APP("鲸链", 4),
	DISCLOSE_MINI("内幕小程序", 5);

	private AppType(String desc, int val){

	}
}

public class TestAiEnroll {
	/**
	 * url	http://ppp.com
currencyUnit	CNY
position	CO_FOUNDER
amount	900
contactWeixin	111441
brief	哈哈哈哈哈哈哈
weixin
name	创建时间的项目
cardUrl	https://pic.36krcnd.com/avatar/201802/05080520/7jhk249m3y4wgdaq.jpg
startDate	2015-01-01
logo	https://pic.36krcnd.com/avatar/201802/05080313/8hxa58xoctxybi5k.jpg
tags	人工智能
type	APP
bpUrl	http://krplus-priv.b0.upaiyun.com/file/201802/05080502/短信发送接口Http版 (1)_duti4fjokofqgpdd.pdf
phase	A_PLUS
	 * @param args
	 */

		static String domain="http://rongtest01.36kr.com/dddddd/mobi-investor";
		static HttpClientHisdataService http=new HttpClientHisdataService();
		static Map<String,String> map=new HashMap<>();
		static Map<String,String> headers=new HashMap<>();
		static HttpResult httpResult=new HttpResult();

		public static void main(String[] args) {

			System.out.println(new Date());
			String str="Failed to invoke the method getKridByUid in the service com.kr.msg.api.service.UserBindService. Tried 1 times of the providers [192.168.5.1:36010] (1/1) from the registry 192.168.2.14:2181 on the consumer 169.254.69.238 using the dubbo version 2.8.7-SPRING4. Last error is: Failed to invoke remote method: getKridByUid, provider: dubbo://192.168.5.1:36010/com.kr.msg.api.service.UserBindService?anyhost=true&application=kr-investor-mobile&check=false&connections=0&default.check=false&dubbo=2.8.7-SPRING4&generic=false&interface=com.kr.msg.api.service.UserBindService&loadbalance=random&logger=slf4j&methods=getUidByKrid,getKridByUid&monitor=dubbo%3A%2F%2F192.168.2.14%3A2181%2Fcom.alibaba.dubbo.registry.RegistryService%3Fapplication%3Dkr-investor-mobile%26dubbo%3D2.8.7-SPRING4%26file%3D%2Fdata%2Fwork%2Fdubbo.registry.cache.kr-investor-mobile%26logger%3Dslf4j%26pid%3D32568%26protocol%3Dregistry%26refer%3Ddubbo%253D2.8.7-SPRING4%2526interface%253Dcom.alibaba.dubbo.monitor.MonitorService%2526pid%253D32568%2526timestamp%253D1542187934524%26registry%3Dzookeeper%26timestamp%3D1542187934524&organization=java_backend&owner=wangshengren&pid=32568&retries=0&revision=2.1.1&side=consumer&timeout=20000&timestamp=1542187934517&version=1.0, cause: message can not send, because channel is closed . url:dubbo://192.168.5.1:36010/com.kr.msg.api.service.BasePushService?anyhost=true&application=kr-investor-mobile&check=false&codec=dubbo&connections=0&default.check=false&dubbo=2.8.7-SPRING4&generic=false&heartbeat=60000&interface=com.kr.msg.api.service.BasePushService&loadbalance=random&logger=slf4j&methods=sendCmd,asyncPushToMobile,pushToMobile&monitor=dubbo%3A%2F%2F192.168.2.14%3A2181%2Fcom.alibaba.dubbo.registry.RegistryService%3Fapplication%3Dkr-investor-mobile%26dubbo%3D2.8.7-SPRING4%26file%3D%2Fdata%2Fwork%2Fdubbo.registry.cache.kr-investor-mobile%26logger%3Dslf4j%26pid%3D32568%26protocol%3Dregistry%26refer%3Ddubbo%253D2.8.7-SPRING4%2526interface%253Dcom.alibaba.dubbo.monitor.MonitorService%2526pid%253D32568%2526timestamp%253D1542187931256%26registry%3Dzookeeper%26timestamp%3D1542187931255&organization=java_backend&owner=wangshengren&pid=32568&retries=0&revision=2.1.1&side=consumer&timeout=20000&timestamp=1542187931237&version=1.0";
			///System.out.println(str.length());

/*			headers.put("user-agent","andriod");
			String domain="http://rongtest01.36kr.com/dddddd/mobi-investor/config?version=3.7.2";
			//httpResult=http.httpPost(domain, 60000, 60000, 60000, headers, map, null, null, false);
			HttpResult httpResult=http.httpGet(domain, null);
			System.err.println(httpResult);*/

			//getHot();
			//math();
			//System.exit(0);

			String date="2015-01-01";
			//System.err.println(NumberUtils.isDigits(date));

			String url="http://localhost:8082/kr-investor-mobi-webapp/company/create";
			//url="http://localhost:8082/kr-investor-mobi-webapp/company/v3/fetchUrl";
			//url="http://localhost:8082/kr-investor-mobi-webapp/ai/index/signUp";
			//url="http://localhost:8082/kr-investor-mobi-webapp/ai/checkSerOrg";
			//url="http://localhost:8082/kr-investor-mobi-webapp/ai/index/pre/signUp?actId=5t5t5t";
			//url="http://localhost:8082/kr-investor-mobi-webapp/activity/v2/apply?as=startup";
			//url="http://rongtest01.36kr.com/dddddd/mobi-investor/ai/index/pre/signUp";
			//url="http://rongtest01.36kr.com/dddddd/mobi-investor/ai/checkSerOrg";
			//url="https://rongtest01.36kr.com/dddddd/mobi-investor/vote/aiVote?itemId=3142&tagName=COMPANY";
			//url="https://rongtest01.36kr.com/dddddd/mobi-investor/vote/aiVote?itemId=3142&tagName=ORG";
			  //url="https://rong.36kr.com/dddddd/mobi-investor/vote/aiVote?itemId=52917386&tagName=COMPANY";
			//url="http://localhost:8082/kr-investor-mobi-webapp/vote/aiVote?itemId=52917386&tagName=COMPANY";
		    /*  url="http://localhost:8082/kr-investor-mobi-webapp/company/117619/sendbpemail?bp=http://krplus-priv.b0.upaiyun.com/20161229/%E8%93%9D%E9%B2%B8%E4%BA%92%E5%8A%A8_50b0d353cea5fac3edb13e9181378753_.pdf?_upt%3Df89e0a131521083142&id_type=crm";
			  url="http://localhost:8082/kr-investor-mobi-webapp/user/profile";
			  url="http://localhost:8082/kr-investor-mobi-webapp/rank/investor/list?page=1&pageSize=20&industry=10";
			  url="https://rongtest01.36kr.com/n/dddddd/investor/auth?action=h5";
			  url="http://localhost:8082/kr-investor-mobi-webapp/investor/5832";*/
			  //url="https://rong.36kr.com/dddddd/mobi-investor/sixin/list/read";
			//url="https://rong.36kr.com/dddddd/mobi-investor/sixin/v2/list?type=followed&page=0&pageSize=20&ts=0";
			//url="http://localhost:8082/kr-nrong-web/investor/auth?action=h5";
			//url="http://localhost:8082/kr-investor-mobi-webapp/sixin/dialog/374890971?lastId=&pageSize=10&flip_type=0&source=0&statsRefer=search_list&crmCid=41771";
			  url="https://rongtest01.36kr.com/n/dddddd/investor/auth?action=h5";
			//url="http://localhost:8082/kr-investor-mobi-webapp/lp/1";
			//url="http://localhost:8082/kr-investor-mobi-webapp/search/v2/company?keyword=备注名&page=1&pageSize=20";
			  //url="http://localhost:8082/kr-investor-mobi-webapp/lp/list?type=FAMILY_PERSON";
			 //url="http://localhost:8082/kr-investor-mobi-webapp/lp/getInvestList/24";
			  //url="http://rongtest01.36kr.com/dddddd/mobi-investor/lp/list?type=FAMILY_PERSON&pageSize=20";
			  //url="http://localhost:8082/kr-investor-mobi-webapp/sixin/dialog/374890971?lastId=&pageSize=10&flip_type=0&source=0&statsRefer=search_list&crmCid=28943";
			 //url="https://rongtest01.36kr.com/dddddd/mobi-investor/sixin/1752154562";
			 //url="http://localhost:8082/kr-investor-mobi-webapp/sixin/1752154562";
			 //url="http://localhost:8082/kr-investor-mobi-webapp/msg?endpoint=INVESTOR&limit=5&markReaded=true";
			 //url="https://rongtest01.36kr.com/dddddd/msg?endpoint=INVESTOR&limit=5&markReaded=true";
			   url="https://rongtest01.36kr.com/dddddd/mobi-investor/company/117619/sendbpemail?bp=https://krplus-normal.b0.upaiyun.com/crm/20160627/[IU%E5%AE%9D%E8%B4%9D%E7%9C%BC]%E5%95%86%E4%B8%9A%E8%AE%A1%E5%88%92%E4%B9%A6.pdf?_upt%3Deae9888e1522207600&id_type=crm"; //发送BP到邮箱
			   url="http://localhost:8082/kr-investor-mobi-webapp/company/117619/sendbpemail?bp=https://krplus-normal.b0.upaiyun.com/crm/20160627/[IU%E5%AE%9D%E8%B4%9D%E7%9C%BC]%E5%95%86%E4%B8%9A%E8%AE%A1%E5%88%92%E4%B9%A6.pdf?_upt%3Deae9888e1522207600&id_type=crm";
			   url="https://rongtest01.36kr.com/dddddd/mobi-investor/sixin/entrepreneur/list?type=followed&page=0&pageSize=20&ts=0";
			 //url="http://localhost:8082/kr-investor-mobi-webapp/sixin/entrepreneur/list?type=followed&page=0&pageSize=20&ts=0";
			   url="http://localhost:8082/kr-investor-mobi-webapp/lp/93473";
			   url="http://localhost:8082/kr-investor-mobi-webapp/lp/getInvestList/93473";
			   //url="http://localhost:8082/kr-investor-mobi-webapp/carTrans/index/second";
			   //url="http://localhost:8082/kr-investor-mobi-webapp/carTrans/index/search?keyword=汽车";
			   url="http://localhost:8082/kr-investor-mobi-webapp/carTrans/all_org";
			   url="http://localhost:8082/kr-investor-mobi-webapp/carTrans/search?orgName=汽车";
			   url="http://localhost:8082/kr-investor-mobi-webapp/label_dict/carTrans/companys?labelName=汽车交通";
			   url="https://rongtest01.36kr.com/dddddd/mobi-investor/label_dict/carTrans/companys?labelName=汽车交通";
			   url="https://rongtest01.36kr.com/dddddd/mobi-investor/label_dict/carTrans/search?comName=汽车";
			   url="https://rongtest01.36kr.com/dddddd/mobi-investor/carTrans/index/second";
			   url="https://rongtest01.36kr.com/dddddd/mobi-investor/carTrans/index/search?keyword=华";
			   url="https://rongtest01.36kr.com/dddddd/mobi-investor/org_car_trans/all_org";
			   url="https://rongtest01.36kr.com/dddddd/mobi-investor/org_car_trans/service/all_org";
			   url="https://rong.36kr.com/dddddd/mobi-investor/lp/list?type=FAMILY_PERSON";
			   url="https://rong.36kr.com/dddddd/mobi-investor/lp/92694";
			   url="http://localhost:8082/kr-investor-mobi-webapp/label_dict/carTrans/companys?labelName=汽车交通";
			   url="https://rongtest01.36kr.com/dddddd/mobi-investor/org_car_trans/all_org";
			   url="http://localhost:8082/kr-investor-mobi-webapp/org_car_trans/all_org";
			   url="http://localhost:8082/kr-investor-mobi-webapp/org_car_trans/service/all_org";
			   url="http://172.16.6.211:8080";
			   //url="http://localhost:8082/kr-investor-mobi-webapp/lp/92694";
			   url="https://rongtest01.36kr.com/dddddd/mobi-investor/org_car_trans/search?orgName=车";
			   url="https://rongtest01.36kr.com/dddddd/mobi-investor/org_car_trans/service/search?orgName=车";
			   url="http://localhost:8082/kr-investor-mobi-webapp/v3/company/8732/companyUsers";
			   url="http://localhost:8082/kr-investor-mobi-webapp/v3/company/60227?statsRefer=search_list";
			   //url="https://rong.36kr.com/dddddd/mobi-investor//lp/getInvestList/93012";
			   url="http://localhost:8082/kr-investor-mobi-webapp/sixin/dialog/1264549224?lastId=&pageSize=10&flip_type=0&source=0&statsRefer=search_list&crmCid=165848";
			   //url="http://localhost:8082/kr-investor-mobi-webapp/sixin/unread";
			   url="http://localhost:8082/kr-investor-mobi-webapp/search/v2/org?keyword=红杉资本&page=0&pageSize=20";
			   //url="https://rongtest01.36kr.com/dddddd/mobi-investor/search/v2/org?keyword=金鹰红利&page=0&pageSize=20";
			   url="http://localhost:8082/kr-investor-mobi-webapp/lp/getInvestList/93473";
			   url="http://localhost:8082/kr-investor-mobi-webapp/lp/93012";
			   url="http://172.16.6.108:8082/kr-investor-mobi-webapp/v3/geetest/dddddd1?phone=18888";
			   url="http:rongtest01.36kr.com/dddddd/mobi-investor/v3/geetest/dddddd1";
			   url="http://localhost:8082/kr-investor-mobi-webapp/v1/send_verify_code";
			   url="http://localhost:8082/kr-investor-mobi-webapp/v3/geetest/dealResult";
			   url="http://rongtest01.36kr.com/dddddd/mobi-investor/lp/list?type=OTHERS";
			   url="http://rongtest01.36kr.com/dddddd/mobi-investor/lp/92577";
			   //url="https://rongtest01.36kr.com/dddddd/mobi-investor/lp/93012";
			   url="http://localhost:8082/kr-passport-webapp/token?grant_type=password";
			   url="http://localhost:8082/kr-investor-mobi-webapp/investor/auth/state";
			   url="http://localhost:8082/kr-investor-mobi-webapp/sixin/v2?type=followed";
			   url="http://localhost:8082/kr-investor-mobi-webapp/sixin/v2/list?type=followed&page=0&pageSize=20&ts=0";
			   url="http://localhost:8082/kr-investor-mobi-webapp/user/buyBP"; //购买BP
			   url="http://localhost:8082/kr-investor-mobi-webapp/charge"; //充值鲸币
			   url="http://localhost:8082/kr-investor-mobi-webapp/investor/auth?action=h5";//获得通过邀请码注册
			   //url="http://localhost:8082/kr-investor-mobi-webapp/investor/reward/ANDROID";
			   //url="http://localhost:8082/kr-investor-mobi-webapp/user/balance";
			   //url="http://172.16.6.108:8082/kr-investor-mobi-webapp/fundraise/dailyBonus/ANDROID";
			   //url="http://localhost:8082/kr-investor-mobi-webapp/user/profile";
			   url="http://172.16.6.108:8082/kr-investor-mobi-webapp/fundraise/judgeInvestor";


			   /*			map.put("actId", "5t5t5t");
			map.put("name", "在一起");
			map.put("wechat", "31231231");
			map.put("card", "https://pic.36krcnd.com/avatar/201803/06074944/fgjagpvfdbg5rufg.jpeg");
			map.put("type", "0");
			map.put("sessionNameStr", "C1,C3");
			map.put("projectIntro", "阿道夫发撒方式");
			map.put("projectName", "粉粉粉粉粉");
			//map.put("position", "这应该是LP的职位");
			map.put("email", "123@321.com");
			//map.put("founderName", "测试founderName");
			//map.put("founderPhone", "测试founderPhone");
			//map.put("founderPosition", "测试founderPosition");
			map.put("investorRoleEnum", "FA");//LP ORG_INVESTOR
			map.put("businessCard", "https://pic.36krcnd.com/avatar/201803/16063941/arffey71advonrio.jpg");
			map.put("realName", "20180327FA身份认证");//20180327LP身份认证
			map.put("avatar", "https://pic.36krcnd.com/avatar/201803/20065733/e0rueotmr8wih4ht.jpg");
			map.put("weixin", "qwerasd123");
			map.put("orgType", "FA");//LP ORG_INVESTOR
			map.put("email","LPLPLP@163.com");
			map.put("orgName", "FA 公司呵呵后");
//			map.put("commonEmail", "478478@163.com");
			map.put("investorPosition","这是FA 神奇的职位");
//			map.put("position", "这是LP职位修改为机构职位");
/*			map.put("type","FAMILY_PERSON");
			map.put("page", "1");
			map.put("pageSize", "10");
			map.put("content", "gogogo qqq");
			map.put("statsRefer", "search_list");
			map.put("companyName", "听见你的声音");*/

			//headers.put("cookie", "kr_plus_id=414953; kr_plus_token=Xqo3fTrrFMKZ_9V7xr5wC6jHF7Gy_F6vyH218___; krid_user_version=1"); //132的号
			//headers.put("cookie", "kr_plus_id=116802043; kr_plus_token=Xqo3fTrrFMKZ_9V7xr5wC6jHF7Gy_F6vyH218___; krid_user_version=1");
			//headers.put("cookie", "sajssdk_2015_cross_new_user=1; kr_plus_id=1043242052; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%221043242052%22%2C%22%24device_id%22%3A%22161f95fcdff45-0b5c24aa4ae10d-2b0c3866-304500-161f95fce00450%22%2C%22first_id%22%3A%22161f95fcdff45-0b5c24aa4ae10d-2b0c3866-304500-161f95fce00450%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E5%BC%95%E8%8D%90%E6%B5%81%E9%87%8F%22%2C%22%24latest_referrer%22%3A%22http%3A%2F%2Fpassport.36kr.com%2Fpages%2F%3Fok_url%3Dhttp%253A%252F%252Flocalhost%253A8082%252F%2523%252FfrInvestor%253FactivityName%253D6y6y6y%2526channel%253Dundefined%2526sessionNameStr%253D%26theme%3Dhttp%253A%252F%252Flocalhost%253A8082%252Flogin-th%22%2C%22%24latest_referrer_host%22%3A%22passport.36kr.com%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC%22%7D%7D");
			//headers.put("cookie", "kr_plus_id=669704142; kr_plus_token=PTpI9SN3tirEAOltrx79PLc5sF13foYW433_____; krid_user_version=1");
			//headers.put("X-Real-IP", "0.0.0.0");
			//headers.put("Proxy-Client-IP", "112.64.98.49");
			//headers.put("WL-Proxy-Client-IP", "112.64.68.12");
			//headers.put("x-forwarded-for ", "0.0.0.0,1.1.1.1");
			//headers.put("User-Agent", "36kr-Tou-Android/3.7.0.beta (MI 6; Android 7.1.1; Scale/1920x1080) Mozilla/5.0 (Linux; Android 7.1.1; MI 6 Build/NMF26X; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/61.0.3163.98 Mobile Safari/537.36");
			//headers.put("Cookie","kr_plus_id=175136230; kr_plus_token=VgL4si1IHRtTzLUVd347Ao1Ja5x_8gzBQP66____; krid_user_version=1");
			//headers.put("Host","rongtest01.36kr.com");
			//headers.put("Refer", "https://rongtest01.36kr.com/m/");
			//headers.put("Cookie", "kr_plus_id=396212; kr_plus_token=7oM3rmqWd84eBtO5Kpm6c9zs7isYaQUMA62_____; krid_user_version=1");
			//map.put("type", "followed");
			//headers.put("Cookie", "kr_plus_id=1952806706; kr_plus_token=e9KeZj5wQwjTPrbmvYzL1EVCpwgl5442462_____; krid_user_version=1");
			//headers.put("cookie", "kr_plus_id=1708129734; kr_plus_token=cSBadIrWQifUptopwi8Dz_V3cmH7U4881747____; krid_user_version=1");//投资人身份
			//headers.put("cookie", "kr_plus_id=1246432996; kr_plus_token=DuKQFgpKo6SYxQM2vMXTAuQslPs21542927_____; krid_user_version=1; sajssdk_2015_cross_new_user=1; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%221246432996%22%2C%22%24device_id%22%3A%22162422e078fbf-0e0077e4bfd203-28643715-230400-162422e07909a%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%7D%2C%22first_id%22%3A%22162422e078fbf-0e0077e4bfd203-28643715-230400-162422e07909a%22%7D");
			  headers.put("cookie", "kr_plus_id=1593119819; kr_plus_token=Fp5qOTD23V213oEHuHszVqOUGVLrfoB5879_____; krid_user_version=1");
			//headers.put("cookie", "kr_plus_id=2094667294; kr_plus_token=kEHnRV5D5JswGkz8wverbrcxBBgP4716465_____; krid_user_version=1");//普通身份 23300032222
			//headers.put("cookie", "kr_plus_id=947671376; kr_plus_token=AwvkjgJRYsGiHEXeVKrIPShll184377_6__66_4_; krid_user_version=1;"); //个人投资人 23300002223
			//headers.put("cookie", "kr_plus_id=1078531253; kr_plus_token=H8hkflI7mMGRPbTCISDh_3XLyd1mqX23_1879___; krid_user_version=1");
			  headers.put("cookie", "kr_plus_id=663671872; kr_plus_token=Xqo3fTrrFMKZ_9V7xr5wC6jHF7Gy_F6vyH218___; krid_user_version=1");//FA
			//headers.put("cookie", "kr_plus_id=1922800629; kr_plus_token=bzO6aLvm7sVxY5CYRXwhovldLipK7221618_____; krid_user_version=1; kr_plus_id=1043533078; kr_plus_token=ef9CuWF2xtJcGBL74ZtFjC3QuhENcS_77193____; krid_user_version=1"); //机构投资人 2330000904
			//headers.put("cookie", "kr_plus_id=1604095502; kr_plus_token=Em8KN2TkX_9hHON8CRE3haHXALxws8_p_136e5__; krid_user_version=1"); //LP申请      2330000522
			//headers.put("cookie", "kr_plus_id=63556338; kr_plus_token=3kNW7HO666uitlyuBiv65WFC_je8jfIpyN71____; krid_user_version=1"); //机构投资人申请      2330000521
		    //headers.put("cookie", "kr_plus_id=332774382; kr_plus_token=BuvpTeWv1BseHYbJcMN4R5BsmFBp1588__758___; krid_user_version=1"); //FA申请      2330000520
		    //headers.put("cookie", "kr_plus_id=1509620272; kr_plus_token=wpHXQVkBzFhuB2NW3r435644EbQLhiwxM3_4____; krid_user_version=1"); //线上投资人
			  headers.put("cookie", "kr_plus_id=1118842251; kr_plus_token=YLTj9dgNAHuGffu_ne3AH7bCg2pBYt862646____; krid_user_version=1");//LP 23300013322
			  headers.put("cookie", "Hm_lvt_e8ec47088ed7458ec32cde3617b23ee3=1523876815,1523876818,1523878043,1523878643; Hm_lpvt_e8ec47088ed7458ec32cde3617b23ee3=1523879886; kr_stat_uuid=EK78i25397998; download_animation=1; _kr_p_se=97107a0d-c667-40b3-a49e-da4273072a6d; krid_user_id=396212; krid_user_version=1035; kr_plus_id=396212; kr_plus_token=WsPH7KqRnVX9jcyikzHbxk3qoWcI4155377_____; sajssdk_2015_cross_new_user=1; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22396212%22%2C%22%24device_id%22%3A%22162ce522ac2600-0086f7ff6e6a18-2d604637-304704-162ce522ac37cf%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%7D%2C%22first_id%22%3A%22162ce522ac2600-0086f7ff6e6a18-2d604637-304704-162ce522ac37cf%22%7D");//
			//headers.put("cookie", "kr_plus_id=396212;   kr_plus_token=YjijKI1butP7MpEERvt5m5SmfE4Mpi425_37____; krid_user_version=1");//
			  headers.put("cookie", "kr_plus_id=374890971; kr_plus_token=iIT4WhT2bQxvynahD3vjhzsD21Fs8TZ4743_____; krid_user_version=1");//LP 23300013322
		      headers.put("User-Agent", "36kr-Tou-Android");
		      headers.put("cookie", "kr_plus_id=1855133572; kr_plus_token=_cJDe3C4k_Y_gNSg2l8yKKIj4Kykg_1uxAD7459_; krid_user_version=1");// 此账号 23300012222 是  23300000409 邀请注册的 机构投资人
		      //headers.put("cookie", "kr_plus_id=2094667294; kr_plus_token=kEHnRV5D5JswGkz8wverbrcxBBgP4716465_____; krid_user_version=1");// 普通用户23300032222 是 23300012222 邀请注册的 机构投资人
		      //headers.put("cookie", "kr_plus_id=1254029645; kr_plus_token=yTKe8WGDbEPnP67BkCVKuUsmq1Nda289_648____; krid_user_version=1");// 23300002222
		      //headers.put("cookie", "kr_plus_id=1526179892; kr_plus_token=W4DLsHMKFQIwwYgP6ZOf_CS3y7wcv567u157____; krid_user_version=1"); //  23300015130 此账号邀请过2人成为投资人 获得240鲸币
		      //headers.put("cookie", "kr_plus_id=1063288005; kr_plus_token=VOXWK8FRbB_PvEzLsWz4ivyXrrZb25338489____; krid_user_version=1"); //  23300015031 此账号是被邀请认证成为投资人 获得120鲸币
		      //headers.put("cookie", "kr_plus_id=1922800629; kr_plus_token=bzO6aLvm7sVxY5CYRXwhovldLipK7221618_____; krid_user_version=1");// LP 23300000409
		      //headers.put("cookie", "kr_plus_id=931500037; kr_plus_token=k35fBQr_A2gO76t6onSNHlNUiMyK7U7djQ16____; krid_user_version=1"); //自己认证成功的LP  23361110000
		      //headers.put("cookie", "kr_plus_id=1246485731; kr_plus_token=Za1dUlb22U88eRGE6OhX1Nwb9k8P7yt1MiY_____; krid_user_version=1"); //模拟认证的LP  23364440000
		      //headers.put("cookie", "kr_plus_id=366456147; kr_plus_token=i6aHxHN6zZY9dqHy6mR9Ll4guGGiFyl1412_____; krid_user_version=1"); //募资季身份认证   23367770000
			    headers.put("cookie", "kr_plus_id=175136230; kr_plus_token=VgL4si1IHRtTzLUVd347Ao1Ja5x_8gzBQP66____; krid_user_version=1"); //机构投资人身份23300014444
			    headers.put("cookie", "kr_plus_id=855263620; kr_plus_token=USiBoARzxUntT7SJbaaEKqqepm548731123_____; krid_user_version=1"); //募资季身份认证  23368880000
			headers.put("User-Agent", "36kr-Tou-Android/5.2.3.beta (MX5; Android 5.1; Scale/1920x1080; device_id/ef36ca6c26d656ea857e72ce61a949c2)");

			Map<String, Object> mmap=new HashMap<>();






			try {

				//getAccountTradeConvert(map, headers);
				//getAccountExchangeList(map, headers);
				//getAccountCoinList(map, headers);
				//bindAccountWithExch(map, headers);
				//getexchangeParentList(map, headers);
				//aboutRiskControlSms(map, headers);
				//aboutSms(map, headers);
				//aboutVoice(map, headers);
				//tryAopService(map, headers);
				//aboutRiskApiFault(map, headers);
				//aboutMarketFluctution(map, headers);
				//aboutWorthFluctution(map, headers);
				//aboutLargeScaleMarket(map, headers);
				//aboutHighFrequency(map, headers);
				//aboutRiskUnauthCoin(map, headers);
				//aboutRiskNetWorth(map, headers);
				//aboutRiskLeverange(map, headers);
				//judgeRiskCode(map, headers);
				//getRiskCode(map, headers);
				//getRiskDetail(map, headers);
				//sendSms(map, headers);
				//getIpList(map, headers);
				//orderPay();
				//内幕项目搜索(map, headers);
				//支付结果页面(map, headers);
				//获取项目详情页(map,headers);
				//fundraiseCom();
				//getFundraiseRcmd();
				//prosetAdd(mmap, headers);
				//getFundraise();
				//getJzService();
				//testJzApply();
				//getRecomendUid();
				//dataRead(map, headers);
				//getAwardDetail();
				//Thread.sleep(Integer.MAX_VALUE);
				//while(true){
					//pushList();
				//}
				//udeskCreate();
				//getUdeskResult();
			} catch (Exception e) {
				e.printStackTrace();
			}
			//enrollActivity(map,headers);

//			for (int i = 0; i<Integer.MAX_VALUE; i++) {
//				try {
//					多线程请求();
//					//获取项目详情页(map,headers);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
			//充值鲸币(map, headers);
			//enrollJzService(map, headers);
			//login(map);
			//getSystemMsg(map, headers);
			//收藏夹项目集列表(map, headers);

			//updatePush(map, headers);
			//insertPush(map, headers);
			//insertGroupManager(map, headers);
			//enrollResult(map, headers);
			//getActInmail(map, headers);
			//修改icon(map,headers);
			//keywordList(map, headers);
			//insertKeyword(map, headers);
			//getCmsCookie(map, headers);
			//updateCompany();
			//reportSearch();
			//setPass();
			//setReportPass();
			//getReportList();
			//updateSendTime();
			//reportAuditList();
			//updateAdv();
			//addAdv();
			//getAdv(map, headers);
			//advList(map, headers);
			//download(map, headers);
			//activityDetail();
			//ifEnroll(map, headers);
			//interval(map,headers);
			//nowDays(map,headers);
			//applyList(map,headers);

			//queryActivity(map, headers);
			//updateActivity(map, headers);
			//后台创建新活动(map, headers);
			//testLogin(map, headers);
			//后台编辑任务(map, headers);
			//身份验证极验(map, headers);
			//增加精选项目集缩略图(map, headers);
			//更改精选项目集缩略图(map, headers);
			//测试用户协议(map, headers);
			//测试灰名单(map, headers);
			//投资人灰名单(map, headers);
			//创业者服务包(map, headers);
			//钉钉机器人(map,headers);
			//项目集收藏(map,headers);
			//项目集点赞分享(map,headers);
			//项目集详情页项目列表(map, headers);
			//项目集详情页辅助(map, headers);
			//testStringUtil();
			//admin后台修改LP基金(map, headers);
				//admin基金后台详情页(map, headers);
			//基金详情页(map, headers);
			//admin基金后台(map,headers);
			//创捷接口();
				//上传图片(map,headers);
			    //小程序图片(map,headers);
			    //内幕小程序评论(map,headers);
			    //获取icon图标(map,headers);
			    //内幕小程序后台更改评论(map, headers);
			    //内幕小程序评论列表(map, headers);
			    //新版募资季搜索(map, headers);
			    //LP搜索(map,headers);
			    //标签搜索(map, headers);
			    //新版募资季获取报名列表(map, headers);
			    //新版募资季修改状态(map, headers);
			    //新版募资季LP列表(map,headers);
			    //新版募资季首页(map,headers);
			    //新版募资季列表(map,headers);
			    //新版募资季机构报名(map,headers);
			    //新版募资季LP报名(map, headers);
			    //获取机构详情(map, headers);
			    //修改icon权限(map, headers);
			    //获取投资事件(map, headers);
			    //获取榜单状态(map, headers);
			    //获取所有热度(map,headers);
			    //获取图标(map,headers);
			    //获取数据组图标(map,headers);
			    //插入icon(map,headers);
			    //获取新版榜单(map,headers);
			    //获取单类图标(map,headers);
			    //获取一级领域(map,headers);
			    //获取二级领域(map,headers);
			    //获取投资机会列表(map,headers);
			    //中投(map,headers);
			    //导出excel(map,headers);
			    //查询用户数量(map,headers);
			    //修改项目基本信息(map,headers);
			    //查询用户状态(map,headers);
			    //调查问券查询(map,headers);
			    //测试suggest(map,headers);
			    //搜索身份(map,headers);
			    //修改用户机构(map,headers);
			    //机构搜索(map,headers);
			    //修改BP(map,headers);
			    //服务机构搜索(map,headers);
			    //调查问券(map,headers);
			    //消费生活(map,headers);
			     //初始化票数(map,headers);
			    //测试邀请创业者认证(map,headers);
			      //测试发送短息(map,headers);
			     //测试推送(map,headers);
			    //LP详情页(map,headers);
			    //获取轮播(map,headers);
			   //搜索关键字(map,headers);
			   //搜索投资人(map,headers);
			   //获取募资季首页(map,headers);
			  //获取拒绝的身份(map,headers);
			 //认证身份加鲸币(map,headers);
			 //测试回调(map,headers);
			 //获取投资人信息(map,headers);
			 //获取机构基本信息(map,headers);
			 //触发机构工单(map,headers);
			//测试回调(map,headers);
			//获取字典(map,headers);
			//孵化器报名(map,headers);
			//adminProfile(map,headers);
			//募资季创业者报名(map,headers);
			//募资季华录百纳机构报名(map,headers);
			//募资季机构投资人报名(map,headers);
			//募资季登陆查看机构状态(map,headers);
			//募资季报名(map, headers);
			//募资季judgeHasEnroll(map, headers);
			//募资季judgeInvestor(map, headers);
			//募资季身份认证孵化器(map,headers);
			//募资季身份认证投资人(map,headers);
			//募资季身份认证FA等身份(map,headers);
		    //募资季身份认证LP(map,headers);
		    //认证LP(map);
		    //认证投资人(map);

			//buyBP(map);
			//私信列表(map);
		    //认领项目(map);
			//sendCode(map);
			//geeResult(map);
			//token(map);
			//httpResult=new HttpResult();
			//httpResult=http.httpPost(map.get("reqUrl"), map);
			//httpResult=http.httpPost(map.get("reqUrl"), 0, 0, 0, headers, map, null, null, false, "post");
		    //httpResult=http.httpGet(map.get("reqUrl"), null, headers);
/*			    try {
			    	Map<String, Object> subject=new HashMap<>();
			    	内幕小程序发布(subject,headers);
			    	url=subject.get("reqUrl").toString();
			    	subject.remove("reqUrl");
			    	String sss=JSONObject.toJSONString(subject);
			    	System.err.println(sss);
			    	httpResult=http.httpPostStringEntity(url, 60000, 60000, 60000, headers, sss, null, null, false);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}*/
				/*String str="http://data-internaltest.corp.36kr.com/hot?cmd=get_hottag_list_by_tags&tags=手机回收,拍卖竞价,二手机械交易&size=7&interval=1";
				httpResult=http.httpGet(str,null,headers);*/
			    //System.err.println(httpResult);

		}



	private static void getAccountTradeConvert(Map<String, String> map2, Map<String, String> headers) throws Exception{
		Map<String, Object> map=new HashMap<>();
		String url="http://192.168.1.162:8093/xxxxx/assets/getAccountCoinList";
		headers.put("authorization","Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJncmFudF90eXBlIjoicGFzc3dvcmQiLCJ1c2VyX25hbWUiOiJ0Y2xiaEAxNjMuY29tIiwic2NvcGUiOlsiZGVmYXVsdCJdLCJleHAiOjE1NzQyNDQ3MDUsInVzZXJJZCI6IjExOTM4NjY0MTA0Mjc5MDQwMDIiLCJqdGkiOiI2ZDNkN2EzNy1hOTZlLTQ0YTMtOWY3NC1jNDQ3YzdlN2FjNjIiLCJjbGllbnRfaWQiOiJ0cm95LXVzZXIifQ.Auul9aIbfSdVbDl6RpbzEJj92D_67HmySWEFVemyxV0PZm7s0D1-Hi9vPukSI6Zl-dfShqTZgac8__4lMSXeVXt37pmLPJ_0JnOs7kcYpyiDG0yhWrsMdK-6aZyugxNLRGmdOI9VEtKgkJYy2IP1kqaKHQXvyP4M0s0pmnNnU00");
		map.put("accountBindId","1197399376844324866");
		map.put("accountId","1173866748765790210");
		map.put("accountOwnership","2");
		map.put("hideSmallAmount", "1");//隐藏
		headers.put("content-type","application/json");
		Map<String, Object> head=new HashMap<>();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("data", map);
		head.put("clientId", "troy-web");
		jsonObject.put("head", head);
		String sss=jsonObject.toJSONString();
		System.err.println(sss);
		httpResult=http.httpPostStringEntity(url, 60000*10, 60000*10, 60000*10, headers, sss, null, null, false);
		System.out.println(httpResult);
	}




	private static void getAccountExchangeList(Map<String, String> map2, Map<String, String> headers) throws Exception{
		Map<String, Object> map=new HashMap<>();
		String url="http://192.168.1.162:8093/xxxxx/accountBind/getBalanceOfExchange";
		headers.put("authorization","Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJncmFudF90eXBlIjoicGFzc3dvcmQiLCJ1c2VyX25hbWUiOiJ0Y2xiaEAxNjMuY29tIiwic2NvcGUiOlsiZGVmYXVsdCJdLCJleHAiOjE1NzQyNDQ3MDUsInVzZXJJZCI6IjExOTM4NjY0MTA0Mjc5MDQwMDIiLCJqdGkiOiI2ZDNkN2EzNy1hOTZlLTQ0YTMtOWY3NC1jNDQ3YzdlN2FjNjIiLCJjbGllbnRfaWQiOiJ0cm95LXVzZXIifQ.Auul9aIbfSdVbDl6RpbzEJj92D_67HmySWEFVemyxV0PZm7s0D1-Hi9vPukSI6Zl-dfShqTZgac8__4lMSXeVXt37pmLPJ_0JnOs7kcYpyiDG0yhWrsMdK-6aZyugxNLRGmdOI9VEtKgkJYy2IP1kqaKHQXvyP4M0s0pmnNnU00");
		map.put("accountBindId","1197399376844324866");
		map.put("accountId","1173866748765790210");
		map.put("exchangeParentCode","okex");
		map.put("apiKey","apikey123456");
		map.put("apiSecret","apiSecret123456");
		map.put("hideSmallAmount", "1");//隐藏
		headers.put("content-type","application/json");
		Map<String, Object> head=new HashMap<>();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("data", map);
		head.put("clientId", "troy-web");
		jsonObject.put("head", head);
		String sss=jsonObject.toJSONString();
		System.err.println(sss);
		httpResult=http.httpPostStringEntity(url, 60000*10, 60000*10, 60000*10, headers, sss, null, null, false);
		System.out.println(httpResult);
	}





	private static void getAccountCoinList(Map<String, String> map2, Map<String, String> headers) throws Exception{
		Map<String, Object> map=new HashMap<>();
		String url="http://192.168.1.162:8093/xxxxx/assets/getAccountCoinList";
		headers.put("authorization","Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJncmFudF90eXBlIjoicGFzc3dvcmQiLCJ1c2VyX25hbWUiOiJ0Y2xiaEAxNjMuY29tIiwic2NvcGUiOlsiZGVmYXVsdCJdLCJleHAiOjE1NzQyNDQ3MDUsInVzZXJJZCI6IjExOTM4NjY0MTA0Mjc5MDQwMDIiLCJqdGkiOiI2ZDNkN2EzNy1hOTZlLTQ0YTMtOWY3NC1jNDQ3YzdlN2FjNjIiLCJjbGllbnRfaWQiOiJ0cm95LXVzZXIifQ.Auul9aIbfSdVbDl6RpbzEJj92D_67HmySWEFVemyxV0PZm7s0D1-Hi9vPukSI6Zl-dfShqTZgac8__4lMSXeVXt37pmLPJ_0JnOs7kcYpyiDG0yhWrsMdK-6aZyugxNLRGmdOI9VEtKgkJYy2IP1kqaKHQXvyP4M0s0pmnNnU00");
/*		private String exchangeParentCode;
		private String apiKey;
		private String apiSecret;
		private String exportIp;
		private String passphrase;*/
		map.put("accountId","1164074920108494850");
		map.put("accountId","1173866748765790210");
		map.put("exchangeParentCode","okex");
		map.put("apiKey","apikey123456");
		map.put("apiSecret","apiSecret123456");
		map.put("hideSmallAmount", "1");//隐藏
		headers.put("content-type","application/json");
		Map<String, Object> head=new HashMap<>();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("data", map);
		head.put("clientId", "troy-web");
		jsonObject.put("head", head);

		String sss=jsonObject.toJSONString();
		System.err.println(sss);
		httpResult=http.httpPostStringEntity(url, 60000*10, 60000*10, 60000*10, headers, sss, null, null, false);
		System.out.println(httpResult);
	}




	private static void bindAccountWithExch(Map<String, String> map2, Map<String, String> headers) throws Exception{
		Map<String, Object> map=new HashMap<>();
		String url="http://192.168.1.162:8093/xxxxx/accountBind/bindExch";
		headers.put("authorization","Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJncmFudF90eXBlIjoicGFzc3dvcmQiLCJ1c2VyX25hbWUiOiJ0Y2xiaEAxNjMuY29tIiwic2NvcGUiOlsiZGVmYXVsdCJdLCJleHAiOjE1NzQyNDQ3MDUsInVzZXJJZCI6IjExOTM4NjY0MTA0Mjc5MDQwMDIiLCJqdGkiOiI2ZDNkN2EzNy1hOTZlLTQ0YTMtOWY3NC1jNDQ3YzdlN2FjNjIiLCJjbGllbnRfaWQiOiJ0cm95LXVzZXIifQ.Auul9aIbfSdVbDl6RpbzEJj92D_67HmySWEFVemyxV0PZm7s0D1-Hi9vPukSI6Zl-dfShqTZgac8__4lMSXeVXt37pmLPJ_0JnOs7kcYpyiDG0yhWrsMdK-6aZyugxNLRGmdOI9VEtKgkJYy2IP1kqaKHQXvyP4M0s0pmnNnU00");
/*		private String exchangeParentCode;
		private String apiKey;
		private String apiSecret;
		private String exportIp;
		private String passphrase;*/
		map.put("accountId","2");
		map.put("accountName","bind-9999888");
		map.put("exchangeParentCode","okex");
		map.put("apiKey","apikey12345678");
		map.put("apiSecret","apiSecret12345678");
		map.put("exportIp","192.168.1.74,192.168.1.111");
		map.put("passphrase", "12345678");
		map.put("accountOwnership", 2);
		headers.put("content-type","application/json");
		Map<String, Object> head=new HashMap<>();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("data", map);
		head.put("clientId", "troy-web");
		jsonObject.put("head", head);

		String sss=jsonObject.toJSONString();
		System.err.println(sss);
		httpResult=http.httpPostStringEntity(url, 60000*10, 60000*10, 60000*10, headers, sss, null, null, false);
		System.out.println(httpResult);
	}


	private static void getexchangeParentList(Map<String, String> map2, Map<String, String> headers) throws Exception{
		Map<String, Object> map=new HashMap<>();
		String url="http://192.168.1.162:8093/xxxxx/dddddd/public/xxxxxParentList";
		map.put("phoneNumber","13261750653");
		map.put("phoneAreaCode","+86");
		map.put("language","ZH_CN");
		map.put("type","RISK_CONTROL");
		map.put("channel", "SMS");
		Map<String, String> paramMap=new HashMap<>();
		paramMap.put("product","激进策略摩根大通团队融资账户");
		paramMap.put("code","85%");
		map.put("ttsParam",JSONObject.toJSONString(paramMap));
		headers.put("content-type","application/json");
		Map<String, Object> head=new HashMap<>();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("data", map);
		head.put("clientId", "troy-web");
		jsonObject.put("head", head);

		String sss=jsonObject.toJSONString();
		System.err.println(sss);
		httpResult=http.httpPostStringEntity(url, 60000*10, 60000*10, 60000*10, headers, sss, null, null, false);
		System.out.println(httpResult);
	}



	private static void aboutRiskControlSms(Map<String, String> map2, Map<String, String> headers) throws Exception{
		Map<String, Object> map=new HashMap<>();
		String url="http://192.168.1.162:8091/user/notify/riskControl-code";
		map.put("phoneNumber","13261750653");
		map.put("phoneAreaCode","+86");
		map.put("language","ZH_CN");
		map.put("type","RISK_CONTROL");
		map.put("channel", "SMS");
		Map<String, String> paramMap=new HashMap<>();
		paramMap.put("product","激进策略摩根大通团队融资账户");
		paramMap.put("code","85%");
		map.put("ttsParam",JSONObject.toJSONString(paramMap));
		headers.put("content-type","application/json");
		Map<String, Object> head=new HashMap<>();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("data", map);
		head.put("clientId", "troy-web");
		jsonObject.put("head", head);

		String sss=jsonObject.toJSONString();
		System.err.println(sss);
		httpResult=http.httpPostStringEntity(url, 60000*10, 60000*10, 60000*10, headers, sss, null, null, false);
		System.out.println(httpResult);
	}


	private static void aboutSms(Map<String, String> map2, Map<String, String> headers) throws Exception{
		Map<String, Object> map=new HashMap<>();
		String url="http://192.168.1.162:8095/notifier/sms/send";
		map.put("phoneNumber","13261750653");
		map.put("phoneAreaCode","+86");
		map.put("content","【TROY】纽交所API故障，请及时查看");
		map.put("huaXin", true);
		Map<String, String> paramMap=new HashMap<>();
		paramMap.put("product","激进策略摩根大通团队融资账户");
		paramMap.put("code","85%");
		map.put("ttsParam",JSONObject.toJSONString(paramMap));
		headers.put("content-type","application/json");
		Map<String, Object> head=new HashMap<>();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("data", map);
		head.put("clientId", "troy-web");
		jsonObject.put("head", head);

		String sss=jsonObject.toJSONString();
		System.err.println(sss);
		httpResult=http.httpPostStringEntity(url, 60000*10, 60000*10, 60000*10, headers, sss, null, null, false);
		System.out.println(httpResult);
	}




	private static void aboutVoice(Map<String, String> map2, Map<String, String> headers) throws Exception{
		Map<String, Object> map=new HashMap<>();
		String url="http://192.168.1.162:8095/notifier/voice/send";
		map.put("phoneNumber","18611787905");
		map.put("phoneAreaCode","+86");
		map.put("ttsCode","TTS_168415602");
		Map<String, String> paramMap=new HashMap<>();
		paramMap.put("product","激进策略摩根大通团队融资账户");
		paramMap.put("code","85%");
		map.put("ttsParam",JSONObject.toJSONString(paramMap));
		headers.put("content-type","application/json");
		Map<String, Object> head=new HashMap<>();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("data", map);
		head.put("clientId", "troy-web");
		jsonObject.put("head", head);

		String sss=jsonObject.toJSONString();
		System.err.println(sss);
		httpResult=http.httpPostStringEntity(url, 60000*10, 60000*10, 60000*10, headers, sss, null, null, false);
		System.out.println(httpResult);
	}


	private static void tryAopService(Map<String, String> map2, Map<String, String> headers2) throws Exception{
		Map<String, Object> map=new HashMap<>();
		String url="http://localhost:8080/user/addUser";
		map.put("riskApiFaultId","1194947796928913410");
		map.put("riskControlId","1");
		map.put("status","1");
		map.put("riskControlRootId","8");
		headers.put("content-type","application/json");
		Map<String, Object> head=new HashMap<>();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("data", map);
		head.put("clientId", "troy-web");
		jsonObject.put("head", head);


		String sss=jsonObject.toJSONString();
		System.err.println(sss);
		httpResult=http.httpPostStringEntity(url, 60000*10, 60000*10, 60000*10, headers, sss, null, null, false);
		System.out.println(httpResult);
		}


	private static void aboutRiskApiFault(Map<String, String> map2, Map<String, String> headers) throws Exception{
		Map<String, Object> map=new HashMap<>();
		String url="http://192.168.1.162:8096/risk/dddddd/addApiFault";
		url="http://192.168.1.162:8096/risk/dddddd/getApiFault";
		//map.put("riskApiFaultId","1194947796928913410");
		map.put("riskControlId","1195577338223730690");
		map.put("status","1");
		map.put("riskControlRootId","8");
		headers.put("content-type","application/json");
		map.put("phone",Arrays.asList("10086", "10010" ,"13800138000"));
		map.put("remark","API故障备注TEST3");
		map.put("warningMode", "1"); //1短信,2电话
		Map<String, Object> head=new HashMap<>();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("data", map);
		head.put("clientId", "troy-web");
		jsonObject.put("head", head);


		String sss=jsonObject.toJSONString();
		System.err.println(sss);
		httpResult=http.httpPostStringEntity(url, 60000*10, 60000*10, 60000*10, headers, sss, null, null, false);
		System.out.println(httpResult);
	}


	private static void aboutMarketFluctution(Map<String, String> map2, Map<String, String> headers) throws Exception{
		Map<String, Object> map=new HashMap<>();
		String url="http://192.168.1.162:8096/risk/dddddd/addMarketFluctuation";
		//url="http://192.168.1.162:8096/risk/dddddd/getMarketFluctuation";
		//url="http://192.168.1.162:8096/risk/dddddd/getMarketTime";
		map.put("riskControlId","1195577338223730690");
		map.put("status","1");
		map.put("riskControlRootId","7");
		headers.put("content-type","application/json");
		map.put("phone",Arrays.asList("13800138000", "10010"));
		map.put("remark","价格大幅波动的备注TEST7");
		Map<String, Object> head=new HashMap<>();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("data", map);
		head.put("clientId", "troy-web");
		jsonObject.put("head", head);

		JSONArray riskLeverageArray=new JSONArray();
		for (int i = 0; i < 2; i++) {
			JSONObject json=new JSONObject();
			if(i==0){
				//json.put("riskMarketFluctuationId","1195580628013490178");
				json.put("warningMode", "2"); //1短信,2电话
				json.put("priceChange", "165000.5");//
				json.put("detectionTime", "60"); //
				json.put("coinName","BTC");
			}else if(i==1){
				//json.put("riskMarketFluctuationId","1195580628537778177");
				json.put("warningMode", "1"); //1短信,2电话
				json.put("priceChange", "175000.5");//
				json.put("detectionTime", "1440"); //
				json.put("coinName","BTC");
			}else{
				json.put("warningMode", "2"); //1话
				json.put("priceChange", "55000.5");//
				json.put("detectionTime", "3"); //
				json.put("coinName","BTC");

			}

			riskLeverageArray.add(json);
		}
		map.put("riskMarketFluctuationArray", riskLeverageArray);

		String sss=jsonObject.toJSONString();
		System.err.println(sss);
		httpResult=http.httpPostStringEntity(url, 60000*10, 60000*10, 60000*10, headers, sss, null, null, false);
		System.out.println(httpResult);
	}



	private static void aboutWorthFluctution(Map<String, String> map2, Map<String, String> headers) throws Exception{
		Map<String, Object> map=new HashMap<>();
		String url="http://192.168.1.162:8096/risk/dddddd/addWorthFluctuation";
		//url="http://192.168.1.162:8096/risk/dddddd/getWorthFluctuation";
		map.put("riskControlId","1195577338223730690");
		map.put("status","1");
		map.put("riskControlRootId","6");
		headers.put("content-type","application/json");
		map.put("phone",Arrays.asList("13800138000", "10010"));
		map.put("remark","净值大幅波动的备注TEST5");
		Map<String, Object> head=new HashMap<>();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("data", map);
		head.put("clientId", "troy-web");
		jsonObject.put("head", head);

		JSONArray riskLeverageArray=new JSONArray();
		for (int i = 0; i < 3; i++) {
			JSONObject json=new JSONObject();
			if(i==0){
				//json.put("riskLargeScaleId","1194906658700349442");
				json.put("warningMode", "2"); //1短信,2电话
				json.put("worthChange", "18455.5232");//
				json.put("detectionTime", "1.5"); //
			}else if(i==1){
				//json.put("riskLargeScaleId","1194906671136460801");
				json.put("warningMode", "1"); //1短信,2电话
				json.put("worthChange", "18299.5231");//
				json.put("detectionTime", "2.5"); //
			}else{
				json.put("warningMode", "2"); //1短信,2电话
				json.put("worthChange", "193.5123");//
				json.put("detectionTime", "3"); //

			}

			riskLeverageArray.add(json);
		}
		map.put("riskWorthFluctuationArray", riskLeverageArray);

		String sss=jsonObject.toJSONString();
		System.err.println(sss);
		httpResult=http.httpPostStringEntity(url, 60000*10, 60000*10, 60000*10, headers, sss, null, null, false);
		System.out.println(httpResult);
	}



	private static void aboutLargeScaleMarket(Map<String, String> map2, Map<String, String> headers) throws Exception{
		Map<String, Object> map=new HashMap<>();
		String url="http://192.168.1.162:8096/risk/dddddd/addLargeScaleMarket";
		//url="http://192.168.1.162:8096/risk/dddddd/getLargeScaleMarket";
		map.put("riskControlId","1195577338223730690");
		map.put("status","1");
		map.put("riskControlRootId","5");
		headers.put("content-type","application/json");
		map.put("phone",Arrays.asList("13800138000", "10010"));
		map.put("remark","大规模市场的备注TEST7");
		Map<String, Object> head=new HashMap<>();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("data", map);
		head.put("clientId", "troy-web");
		jsonObject.put("head", head);

		JSONArray riskLeverageArray=new JSONArray();
		for (int i = 0; i < 3; i++) {
			JSONObject json=new JSONObject();
			if(i==0){
				json.put("riskLargeScaleId","1195579920845447169");
				json.put("warningMode", "1"); //1短信,2电话
				json.put("marketScale", "84777.5");//预警频率1仅一次, 5(5分钟一次)
				//json.put("delStatus", "1"); //是否删除 0否,1是
			}else if(i==1){
				json.put("riskLargeScaleId","1195579921109688322");
				json.put("warningMode", "2"); //1短信,2电话
				json.put("marketScale", "8299.5");//预警频率1仅一次, 5(5分钟一次)

			}else{
				json.put("warningMode", "2"); //1短信,2电话
				json.put("marketScale", "85777.5");//预警频率1仅一次, 5(5分钟一次)
			}

			riskLeverageArray.add(json);
		}
		map.put("riskLargeScaleMarketArray", riskLeverageArray);

		String sss=jsonObject.toJSONString();
		System.err.println(sss);
		httpResult=http.httpPostStringEntity(url, 60000*10, 60000*10, 60000*10, headers, sss, null, null, false);
		System.out.println(httpResult);
	}



	private static void aboutHighFrequency(Map<String, String> map2, Map<String, String> headers) throws Exception{
		Map<String, Object> map=new HashMap<>();
		String url="http://192.168.1.162:8096/risk/dddddd/addHignFrequency";
		//url="http://192.168.1.162:8096/risk/dddddd/getHignFrequency";
		map.put("riskControlId","1195577338223730690");
		map.put("status","1");
		map.put("riskControlRootId","4");
		headers.put("content-type","application/json");
		map.put("phone",Arrays.asList("10086", "10010"));
		map.put("remark","高频预警的备注TEST65");
		Map<String, Object> head=new HashMap<>();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("data", map);
		head.put("clientId", "troy-web");
		jsonObject.put("head", head);

		JSONArray riskLeverageArray=new JSONArray();
		for (int i = 0; i < 3; i++) {
			JSONObject json=new JSONObject();
			if(i==0){
				json.put("riskHighFrequencyId","1195578888803393538");
				json.put("warningMode", "2"); //1短信,2电话
				json.put("frequency", "6.5");//预警频率1仅一次, 5(5分钟一次)
				//json.put("delStatus", "1"); //是否删除 0否,1是
			}else if(i==1){
				json.put("riskHighFrequencyId","1195578889063440385");
				json.put("warningMode", "1"); //1短信,2电话
				json.put("frequency", "5.5");//预警频率1仅一次, 5(5分钟一次)

			}else{
				json.put("warningMode", "2"); //1短信,2电话
				json.put("frequency", "5.5");//预警频率1仅一次, 5(5分钟一次)
			}

			riskLeverageArray.add(json);
		}
		map.put("riskHighFrequencyArray", riskLeverageArray);

		String sss=jsonObject.toJSONString();
		System.err.println(sss);
		httpResult=http.httpPostStringEntity(url, 60000*10, 60000*10, 60000*10, headers, sss, null, null, false);
		System.out.println(httpResult);
	}


	private static void aboutRiskUnauthCoin(Map<String, String> map2, Map<String, String> headers) throws Exception{

			String str="BTC,ETH,EOS,LTC,BCH,BSV,TRX,ETC,TROY";
			Set<String> set=new HashSet<>();
			set.add(str);
			List<String> returnList=new ArrayList();
			returnList=new ArrayList<>(set);
		System.out.println(returnList.size());
		String[] split = returnList.get(0).split(",");
		System.out.println(split.length);

			Map<String, Object> map=new HashMap<>();
		String url="http://192.168.1.162:8096/risk/dddddd/addAuthCoin";
		url="http://192.168.1.162:8096/risk/dddddd/getAuthCoin";
		url="http://192.168.1.162:8096/risk/dddddd/getCoinList";
		map.put("riskControlId","1195577338223730690");
		map.put("status","1");
		map.put("riskControlRootId","3");
		headers.put("content-type","application/json");
		map.put("phone",Arrays.asList("10010", "10086"));
		map.put("remark","授权币种修改测试缓存3");
		map.put("spotCoinName","BTC,ETH,EOS,LTC,BCH");
		map.put("futureCoinName","BTC,ETH,LTC,BCH");
		map.put("warningMode", "2"); //1短信,2电话
		map.put("warningFrequency", "5");//预警频率1仅一次, 5(5分钟一次)
		map.put("closePosition", "1"); //强制平仓0否,1是
		//map.put("chargeUnit","BTC");//计价单位
		//map.put("delStatus", "1"); //是否删除 0否,1是
		Map<String, Object> head=new HashMap<>();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("data", map);
		head.put("clientId", "troy-web");
		jsonObject.put("head", head);


		String sss=jsonObject.toJSONString();
		System.err.println(sss);
		httpResult=http.httpPostStringEntity(url, 60000*10, 60000*10, 60000*10, headers, sss, null, null, false);
		System.out.println(httpResult);
	}



	private static void aboutRiskNetWorth(Map<String, String> map2, Map<String, String> headers) throws Exception{
		Map<String, Object> map=new HashMap<>();
		String url="http://192.168.1.162:8096/risk/dddddd/addNetWorhAlert";
		//url="http://192.168.1.162:8096/risk/dddddd/getNetWorthAlert";
		map.put("riskControlId","1195577338223730690");
		map.put("status","1");
		map.put("riskControlRootId","2");
		headers.put("content-type","application/json");
		map.put("phone",Arrays.asList("13800138000", "10010"));
		map.put("remark","净值预警的备注TEST3");
		Map<String, Object> head=new HashMap<>();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("data", map);
		head.put("clientId", "troy-web");
		jsonObject.put("head", head);

		JSONArray riskLeverageArray=new JSONArray();
		for (int i = 0; i < 1; i++) {
			JSONObject json=new JSONObject();
			if(i==0){
				json.put("riskNetWorthId","1195578470031499265");
				json.put("netWorth", "40.441212");
				json.put("warningMode", "2"); //1短信,2电话
				json.put("warningFrequency", "5");//预警频率1仅一次, 5(5分钟一次)
				json.put("closePosition", "1"); //强制平仓0否,1是
				json.put("chargeUnit","BTC");//计价单位
				//json.put("delStatus", "1"); //是否删除 0否,1是
			}else if(i==1){
				//json.put("riskNetWorthId","1194560405097480194");
				json.put("netWorth", "47.777");
				json.put("warningMode", "1"); //1短信,2电话
				json.put("warningFrequency", "1");//预警频率1仅一次, 5(5分钟一次)
				json.put("closePosition", "0"); //强制平仓0否,1是
				json.put("chargeUnit","BTC");//计价单位

			}else{
				json.put("netWorth", "55.99");
				json.put("warningMode", "2"); //1短信,2电话
				json.put("warningFrequency", "1");//预警频率1仅一次, 5(5分钟一次)
				json.put("closePosition", "1"); //强制平仓0否,1是
				json.put("chargeUnit","BTC");//计价单位
			}

			riskLeverageArray.add(json);
		}
		map.put("riskNetWorthAlertArray", riskLeverageArray);

		String sss=jsonObject.toJSONString();
		System.err.println(sss);
		httpResult=http.httpPostStringEntity(url, 60000*10, 60000*10, 60000*10, headers, sss, null, null, false);
		System.out.println(httpResult);
	}




	private static void aboutRiskLeverange(Map<String, String> map2, Map<String, String> headers) throws Exception{
		Map<String, Object> map=new HashMap<>();
		String url="http://192.168.1.162:8096/risk/dddddd/addLeverage";
		url="http://192.168.1.162:8096/risk/dddddd/getLeverage";
		map.put("riskControlId","1195577338223730690");
		map.put("status","1");
		map.put("riskControlRootId","1");
		headers.put("content-type","application/json");
		map.put("phone",Arrays.asList("10086", "10010"));
		map.put("remark","杠杆的备注TEST5");
		Map<String, Object> head=new HashMap<>();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("data", map);
		head.put("clientId", "troy-web");
		jsonObject.put("head", head);

		JSONArray riskLeverageArray=new JSONArray();
		for (int i = 0; i < 3; i++) {
			JSONObject json=new JSONObject();
			if(i==0){
				//json.put("riskLeverageControlId","1195578001083146242");
				json.put("leverage", "127.33");
				json.put("warningMode", "1"); //1短信,2电话
				json.put("warningFrequency", "5");//预警频率1仅一次, 5(5分钟一次)
				json.put("closePosition", "1"); //强制平仓0否,1是
				json.put("delStatus", "0"); //是否删除 0否,1是
			}else if(i==1){
				//json.put("riskLeverageControlId","1194851860479320065");
				json.put("leverage", "97.33");
				json.put("warningMode", "2"); //1短信,2电话
				json.put("warningFrequency", "1");//预警频率1仅一次, 5(5分钟一次)
				json.put("closePosition", "0"); //强制平仓0否,1是
				json.put("delStatus", "0"); //是否删除 0否,1是
			}else{
				//json.put("riskLeverageControlId","1195578001083146242");
				json.put("leverage", "107.33");
				json.put("warningMode", "2"); //1短信,2电话
				json.put("warningFrequency", "1");//预警频率1仅一次, 5(5分钟一次)
				json.put("closePosition", "1"); //强制平仓0否,1是
				json.put("delStatus", "0"); //是否删除 0否,1是
			}

			riskLeverageArray.add(json);
		}
		map.put("riskLeverageArray", riskLeverageArray);

		String sss=jsonObject.toJSONString();
		System.err.println(sss);
		httpResult=http.httpPostStringEntity(url, 60000*10, 60000*10, 60000*10, headers, sss, null, null, false);
		System.out.println(httpResult);
	}


	private static void judgeRiskCode(Map<String, String> map, Map<String, String> headers) throws Exception{
		String url="http://192.168.1.162:8091/user/notify/riskControl/judge-code";

		map.put("phone","13261750653");
		map.put("code","046830");
		headers.put("content-type","application/json");

		Map<String, Object> head=new HashMap<>();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("data", map);
		head.put("clientId", "troy-web");
		jsonObject.put("head", head);

		String sss=jsonObject.toJSONString();
		System.err.println(sss);
		httpResult=http.httpPostStringEntity(url, 60000, 60000, 60000, headers, sss, null, null, false);
		System.out.println(httpResult);

	}



	private static void getRiskCode(Map<String, String> map, Map<String, String> headers) throws Exception{
		String url="http://192.168.1.162:8091/user/notify/riskControl-code";

		map.put("phoneNumber","13261750655");
		map.put("phoneAreaCode","+86");
		map.put("language","ZH_CN");
		map.put("type","RISK_CONTROL");
		map.put("channel","SMS");
		headers.put("content-type","application/json");

		Map<String, Object> head=new HashMap<>();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("data", map);
		head.put("clientId", "troy-web");
		jsonObject.put("head", head);

		//String sss=JSONObject.toJSONString(map);
		String sss=jsonObject.toJSONString();
		System.err.println(sss);
		httpResult=http.httpPostStringEntity(url, 60000, 60000, 60000, headers, sss, null, null, false);
		System.out.println(httpResult);

	}





	static class RiskCotrolResDto extends Req implements Serializable {
		/**
		 * 1项目,2投资机构,3基金机构,4服务机构
		 */
		public String exchCode;
		public String coinName;
		public String accountBindId;
		public String accountId;
		public Integer userId;
		public String accountName;
	}

	private static void getRiskDetail(Map<String, String> map, Map<String, String> headers) throws Exception{
		String url="http://192.168.1.162:8096/risk/dddddd/detail";
		//HttpResult httpResult = http.httpPost(url, headers);
		//System.out.println(httpResult);

/*		Map<String, Object> map=new HashMap<>();
		String url="http://localhost:8083/hearsayapi/subject/";
		url="http://localhost:8082/stratupService/pushList";
		url="http://localhost:8082//redress/add";*/
		//url="http://dapaiwei.com/?/question/1041";
		//url="http://rongtest01.36kr.com/dddddd/mobi-investor/redress/add";

		map.put("exchCode","huobi");
		map.put("chargeUnit","BTC");
		map.put("accountBindId","5");
		map.put("accountId","5");
		map.put("accountName","accoName");
		headers.put("content-type","application/json");
		//headers.put("cookie", "kr_plus_id=663671872; kr_plus_token=WNUF_cC1717JAiQ4SQ4DP1Cdd6fqOe5Smxg1____; krid_user_version=1");


		JSONObject jsonObject=new JSONObject();
		jsonObject.put("data", map);

		String sss=JSONObject.toJSONString(map);
		sss=jsonObject.toJSONString();
		System.err.println(sss);
		headers.put("authorization","Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJncmFudF90eXBlIjoicGFzc3dvcmQiLCJ1c2VyX25hbWUiOiJ0Y2xiaEAxNjMuY29tIiwic2NvcGUiOlsiZGVmYXVsdCJdLCJleHAiOjE1NzQyNDQ3MDUsInVzZXJJZCI6IjExOTM4NjY0MTA0Mjc5MDQwMDIiLCJqdGkiOiI2ZDNkN2EzNy1hOTZlLTQ0YTMtOWY3NC1jNDQ3YzdlN2FjNjIiLCJjbGllbnRfaWQiOiJ0cm95LXVzZXIifQ.Auul9aIbfSdVbDl6RpbzEJj92D_67HmySWEFVemyxV0PZm7s0D1-Hi9vPukSI6Zl-dfShqTZgac8__4lMSXeVXt37pmLPJ_0JnOs7kcYpyiDG0yhWrsMdK-6aZyugxNLRGmdOI9VEtKgkJYy2IP1kqaKHQXvyP4M0s0pmnNnU00");

		httpResult=http.httpPostStringEntity(url, 60000, 60000, 60000, headers, sss, null, null, false);
		System.out.println(httpResult);



		}

	private static void sendSms(Map<String, String> map, Map<String, String> headers) {
		String url="http://192.168.1.162:10088/code?username=admin";
		HttpResult httpResult = http.httpPost(url, headers);
		System.out.println(httpResult);

	}

	private static void getIpList(Map<String, String> map, Map<String, String> headers) {
			String url="https://troy-admin-test.jar.today/ipcontrol/ipwhite/iplist";
			HttpResult httpResult = http.httpPost(url, headers);
			System.out.println(httpResult);
	}

	private static void 内幕项目搜索(Map<String, String> map2, Map<String, String> headers2) {
		String url="http://localhost:8082/search/testThread?keyword=红杉资本&page=1&pageSize=5";
		//url="https://rongtest01.36kr.com/dddddd/krhearsay/hearsayapi/subject/suggetCompany?word=红杉资本";
		//url="https://rongtest01.36kr.com/dddddd/mobi-investor/search/v2/company?keyword=陆金所";
		map.put("reqUrl", url);
		headers.put("cookie", "kr_plus_id=663671872; kr_plus_token=WNUF_cC1717JAiQ4SQ4DP1Cdd6fqOe5Smxg1____; krid_user_version=1");
		httpResult=http.httpGet(map.get("reqUrl"), null, headers);
		//httpResult=http.httpPost(url, 0, 0, 0, headers, map, null, null, false, "post");

	}


	private static void orderPay(){
/*		channel: ALIPAY_WAP
		successUrl: http://rongtest01.jingdata.com/m/#/jingPaySuccess?payNo=2019102900000069671118254021
		cancelUrl: http://rongtest01.jingdata.com/m/#/jingPayFail?payNo=2019102900000069671118254021
		templateId: 0
		payLinkId: 70*/
		Map<String, String> map=new HashMap<>();
		Map<String, String> headers=new HashMap<>();
		headers.put("cookie", "kr_plus_id=4308; kr_plus_token=H7LnWTSxtn9TWcMQxagGnFRB4kbF515585_2____; krid_user_version=1");
		String url="http://localhost:8082/charge/orderPay?channel=ALIPAY_WAP&templateId=0&payLinkId=70&successUrl=http://www.baidu.com";
		//map.put("channel", "ALIPAY_WAP");
		//map.put("successUrl", "http://rongtest01.jingdata.com/m/#/jingPaySuccess?payNo=2019102900000069671118254021");
		//map.put("cancelUrl", "http://rongtest01.jingdata.com/m/#/jingPayFail?payNo=2019102900000069671118254021");
		//map.put("templateId", "0");
		//map.put("payLinkId", "70");
		httpResult=http.httpPost(url, 0, 0, 0, headers, map, null, null, false, "post");


	}

	private static void 支付结果页面(Map<String, String> map, Map<String, String> headers) {
		String url="http://localhost:8082/charge/dealToken?token=2019103000000088651116332784";
		headers.put("cookie", "kr_plus_id=467068467; kr_plus_token=wrAxOW1zqKD67vD2VqAufqHf1R9qyUo9246_____; krid_user_version=1");
		headers.put("cookie", "kr_plus_id=1563611706; kr_plus_token=W4JKeJLRaFGmE4J22iNQUkdVePblu183944_____; krid_user_version=1");
		headers.put("cookie", "kr_plus_id=4308; kr_plus_token=H7LnWTSxtn9TWcMQxagGnFRB4kbF515585_2____; krid_user_version=1");
		headers.put("cookie", "kr_plus_id=1042265924; kr_plus_token=3Lez7EnYUT2gdwXYpzy6PTzQnLO4IN36886_____; krid_user_version=1");


		httpResult=http.httpPost(url, 0, 0, 0, headers, map, null, null, false, "post");
		System.out.println(httpResult);
	}


	private static void 获取项目详情页(Map<String, String> map, Map<String, String> headers) {
		String url="http://localhost:8082/v3/company/81041560?statsRefer=others"; //9093 思必驰   1123 36氪
		//url="http://rongtest01.36kr.com/dddddd/mobi-investor/v3/company/142083?statsRefer=others";
		//url="http://localhost:8082/v3/company/34383?statsRefer=search_list";
		headers.put("cookie","kr_plus_id=663671872; kr_plus_token=WNUF_cC1717JAiQ4SQ4DP1Cdd6fqOe5Smxg1____; krid_user_version=1");
		//headers.put("cookie","kr_plus_id=858612505; kr_plus_token=sBykvwnolUP8ld_nD8hJfViA5oG_PL2632566___; krid_user_version=1");
		headers.put("User-Agent", "36kr-Tou-Android/5.3.3 (MX5; Android 5.1; Scale/1920x1080; device_id/ef36ca6c26d656ea857e72ce61a949c2)");
		httpResult=http.httpGet(url,null,headers);
		System.out.println(httpResult);
	}

	private static void fundraiseCom() {
		String url="http://localhost:8082/fundraise/2019/capitalTopComDetail?activityId=5d82f19bc2b4d4389e748ab9";
		//url="http://localhost:8082/fundraise/2019/capitalComVote?id=5d8c83ea5d53c25d7bb30137";
		//url="http://localhost:8082/fundraise/2019/contact?companyId=150520";
		url="http://rong.jingdata.com/dddddd/mobi-investor/fundraise/2019/capitalTopComDetail?activityId=5d82f18dc2b4d4389e748ab8";

		//headers.put("cookie", "kr_plus_id=663671872; kr_plus_token=WNUF_cC1717JAiQ4SQ4DP1Cdd6fqOe5Smxg1____; krid_user_version=1");
		//headers.put("cookie","sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%221306834032%22%2C%22%24device_id%22%3A%2216d72558f30934-0ae291c11cefc58-556d377f-250125-16d72558f317c9%22%2C%22props%22%3A%7B%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%2C%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%7D%2C%22first_id%22%3A%2216d72558f30934-0ae291c11cefc58-556d377f-250125-16d72558f317c9%22%7D; kr_plus_id=1306834032; kr_plus_token=j_zhjy1IUf9QGD2pSjvYdSBkMfKNa849394__2__; krid_user_version=1; uitoken=h7k9d-xb8msu2qofh3yixfgz6uuvsq6lxzpw98dl2xfk7mhokqwix1lm1kn8fu9r");
		//httpResult=http.httpPost(url, 0, 0, 0, headers, map, null, null, false, "post");
		httpResult=http.httpGet(url, null, headers);
		//System.out.println(httpResult);
		}

	private static void getFundraiseRcmd() {
		String url="http://localhost:8082/fundraise/2019/capitalProSet";
		//url="http://rongtest01.jingdata.com/dddddd/mobi-investor/fundraise/2019/capitalProSet";
		//url="http://rongtest01.jingdata.com/dddddd/mobi-investor/fundraise/2019/todayRecommend";
		headers.put("cookie", "kr_plus_id=663671872; kr_plus_token=WNUF_cC1717JAiQ4SQ4DP1Cdd6fqOe5Smxg1____; krid_user_version=1");
		headers.put("cookie", "kr_plus_id=761983229; kr_plus_token=DkxIkESzhdniMq2FFaudofOq1AT8487193_9____; krid_user_version=1");
		httpResult=http.httpGet(url, null, headers);
		System.out.println(httpResult);
	}

	private static void getFundraise() {
		String url="http://personal-admin.corp.jingdata.com/dddddd/fundraise/2019/applyResult/list?type=1&page=1&pageSize=505";
		headers.put("cookie","FOCUS_ID=5-9-26482-bs; FOCUS_EMAIL=tclbh@163.com; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%221673f7ead335f7-0bd03f4c4296ee-4313362-1049088-1673f7ead34278%22%2C%22%24device_id%22%3A%221673f7ead335f7-0bd03f4c4296ee-4313362-1049088-1673f7ead34278%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%7D%7D; Hm_lvt_066da718ccf0d5656b81620a125fd3d6=1561537680,1564035723; UM_distinctid=16d2518af7ec6d-0f1f9884599f47-4313362-100200-16d2518af7f5be; SESSION_AD=c923470d-d369-41d3-a1d5-0ae53e13d25e");
		httpResult=http.httpGet(url, null, headers);
		//System.out.println(httpResult);
	}

	private static void testJzApply() {
		String url="http://localhost:8082/fundraise/2019/sendList";
		headers.put("cookie","sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2216d01389ca3871-0f0c43fbf2a1ea8-441c2b6d-304500-16d01389ca4dda%22%2C%22%24device_id%22%3A%2216d01389ca3871-0f0c43fbf2a1ea8-441c2b6d-304500-16d01389ca4dda%22%2C%22props%22%3A%7B%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%2C%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%7D%7D; sajssdk_2015_cross_new_user=1");
		headers.put("User-Agent","Mozilla/5.0 (iPhone; CPU iPhone OS 12_4 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148 /sa-sdk-ios/sensors-verify/www.jingdata.com?default 36kr-Tou-iOS/5.4.2 (unknown) (UID:718188323); iOS 12.4; Scale/3.0; device_id/ADCD6360-B470-4066-8764-1FEDE74460F2;");
		httpResult=http.httpGet(url, null, headers);
			//"Mozilla/5.0 (iPhone; CPU iPhone OS 12_4 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148 /sa-sdk-ios/sensors-verify/www.jingdata.com?default 36kr-Tou-iOS/5.4.2 (unknown) (UID:718188323); iOS 12.4; Scale/3.0; device_id/ADCD6360-B470-4066-8764-1FEDE74460F2;"
	}

	static JSONArray jsonArrayAll=new JSONArray();

	private static void getJzService() {
		String url="http://localhost:8082/fundraise/2019/getRecommendUid";
		for (int i = 1; i <= 6; i++) {
			url="http://personal-admin.corp.jingdata.com/dddddd/jzService/apply/list?jzServiceId="+i+"&pageSize=650";
			headers.put("cookie","FOCUS_ID=5-9-26482-bs; FOCUS_EMAIL=tclbh@163.com; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%221673f7ead335f7-0bd03f4c4296ee-4313362-1049088-1673f7ead34278%22%2C%22%24device_id%22%3A%221673f7ead335f7-0bd03f4c4296ee-4313362-1049088-1673f7ead34278%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%7D%7D; Hm_lvt_066da718ccf0d5656b81620a125fd3d6=1561537680,1564035723; SESSION_AD=2038e6e9-2cc6-457a-a5a7-53608c81928f");
			httpResult=http.httpGet(url, null, headers);
			String resposeString = httpResult.getResposeString();
			JSONObject jsonObject = JSONObject.parseObject(resposeString);
			JSONObject data = jsonObject.getJSONObject("data");
			JSONArray jsonArray = data.getJSONArray("data");
			for (int j = 0; j < jsonArray.size(); j++) {
				Map<String, Object> map =  (Map)jsonArray.get(j);
				map.remove("companyNames");
				map.remove("productName");
				jsonArrayAll.add(map);
			}
			
		}

		System.out.println(jsonArrayAll);

	}


	private static void getRecomendUid(){
		String url="http://localhost:8082/fundraise/2019/getRecommendUid";
		url="http://rongtest01.36kr.com/dddddd/mobi-investor/fundraise/2019/getRecommendUid";
		headers.put("cookie", "kr_plus_id=467068467; kr_plus_token=wrAxOW1zqKD67vD2VqAufqHf1R9qyUo9246_____; krid_user_version=1");
		headers.put("cookie", "kr_plus_id=377380504; kr_plus_token=nw9T6fXXYLopnHjVfdKrqwGg6_Cbb4933799____; krid_user_version=1");
		//headers.put("cookie", "kr_plus_id=1306783583; kr_plus_token=WDy3z51ne92Vuew3PtuvKRf1_EqqYZINL895____; krid_user_version=1");

		//httpResult=http.httpPostStringEntity(url, 60000, 60000, 60000, headers, sss, null, null, false);
		httpResult=http.httpPost(url, 0, 0, 0, headers, map, null, null, false, "post");
		System.out.println(httpResult);

	}


	private static void dataRead(Map<String, String> map, Map<String, String> headers) {
		String url="http://localhost:8082/stratupService/dataRead?id=1";
		url="http://localhost:8082/investor/workbench/view_my_project_stat?page=1&statPeriod=0&statUserInvestorType=0";
		headers.put("cookie","kr_plus_id=1414168454; kr_plus_token=KiZIKRcNRgHz5ajlo7PEJRJ_r4r4pm95_3989___; krid_user_version=1");
		httpResult=http.httpGet(url, null, headers);
		System.out.println(httpResult);
	}


	private static void getUdeskResult() {
		String url="http://localhost:8082/redress/result/2056772331";
		//url="http://localhost:8082/redress/list/0";
		headers.put("cookie", "kr_plus_id=663671872; kr_plus_token=WNUF_cC1717JAiQ4SQ4DP1Cdd6fqOe5Smxg1____; krid_user_version=1");
		//url="http://rongtest01.36kr.com/dddddd/mobi-investor/redress/result/2004088127";
		url="http://rongtest01.36kr.com/dddddd/mobi-investor/redress/list/1";
		httpResult=http.httpGet(url, null, headers);
		System.out.println(httpResult);
		}


	private static void getAwardDetail(){
		String url="http://localhost:8082/fundraise/2019/getAward";
		//url="http://rongtest01.36kr.com/dddddd/mobi-investor/fundraise/2019/getAward";
		headers.put("cookie", "kr_plus_id=467068467; kr_plus_token=wrAxOW1zqKD67vD2VqAufqHf1R9qyUo9246_____; krid_user_version=1");
		headers.put("cookie", "kr_plus_id=1033908659; kr_plus_token=DtbFA3Y_nbcA7KbWLmk3p31Guu5xQjSK4793____; krid_user_version=1");

		//httpResult=http.httpPostStringEntity(url, 60000, 60000, 60000, headers, sss, null, null, false);
		httpResult=http.httpPost(url, 0, 0, 0, headers, map, null, null, false, "post");
		System.out.println(httpResult);

	}

	private static void udeskCreate() throws Exception{
		Map<String, Object> map=new HashMap<>();
		String url="http://localhost:8083/hearsayapi/subject/";
		url="http://localhost:8082/stratupService/pushList";
		url="http://localhost:8082//redress/add";
		//url="http://dapaiwei.com/?/question/1041";
		//url="http://rongtest01.36kr.com/dddddd/mobi-investor/redress/add";

		class UdeskRedress implements Serializable {
			/**
			 * 1项目,2投资机构,3基金机构,4服务机构
			 */
			private Integer redressOrigin;
			/**
			 * 纠错位置
			 */
			private String redressLocation;
			/**
			 * (纠错类型)信息修改(0),信息补充(1)
			 */
			private String redressType;
			/**
			 * 纠错内容
			 */
			private String redressContent;

			/**
			 * 问题描述
			 */
			private String desc;
			private Integer organizationId;
		}

		UdeskRedress udeskRedress=new UdeskRedress();
		udeskRedress.desc="supplyOrder";
		udeskRedress.redressContent="项目介绍,产品及服务";
		udeskRedress.redressType="信息修改";
		udeskRedress.redressLocation="基本信息";
		udeskRedress.redressOrigin=1;
		udeskRedress.organizationId=21312321;
		map.put("redressDesc","history");
		map.put("redressContent","商业模式");
		map.put("redressType","信息补充");
		map.put("redressLocation","融资信息");
		map.put("redressOrigin",1);
		map.put("organizationId",66481797);
		map.put("name","无人不识君");
		headers.put("content-type","application/json");
		//headers.put("Cookie","FOCUS_ID=5-9-26482-bs; FOCUS_EMAIL=tclbh@163.com; Hm_lvt_066da718ccf0d5656b81620a125fd3d6=1560084441,1561537680; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%221673f7ead335f7-0bd03f4c4296ee-4313362-1049088-1673f7ead34278%22%2C%22%24device_id%22%3A%221673f7ead335f7-0bd03f4c4296ee-4313362-1049088-1673f7ead34278%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC(%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80)%22%7D%7D; SESSION_AD=e6e811d0-ec7c-48a5-9567-f7b4d520e0ea");
		//headers.put("content-type", "application/x-www-form-urlcoded");
		headers.put("cookie", "kr_plus_id=663671872; kr_plus_token=WNUF_cC1717JAiQ4SQ4DP1Cdd6fqOe5Smxg1____; krid_user_version=1");

		String sss=JSONObject.toJSONString(map);
		System.err.println(sss);
		httpResult=http.httpPostStringEntity(url, 60000, 60000, 60000, headers, sss, null, null, false);
		System.out.println(httpResult);
	}


	private static void pushList() throws Exception{
			Map<String, Object> map=new HashMap<>();
			String url="http://localhost:8083/hearsayapi/subject/";
			url="http://localhost:8082/stratupService/pushList";
			url="http://localhost:8082/stratupService/addPush";
			//url="http://dapaiwei.com/?/question/1041";
		url="http://personal-admin.corp.jingdata.com/dddddd/push/search";
			map.put("taskType", 2);
//			map.put("pushType", 2);
//			map.put("title", "testpush");
//		    map.put("link", "1875");//http://rongtest01.36kr.com/m/#/projectAlbum/pgc/302
//			map.put("content","测试推送项目内容");
//			map.put("runTime", new Date());
//			map.put("effectiveTime", 36);

			List<Map<String, Object>> list=new ArrayList<>();
			for (int i = 0; i < 1; i++) {
				Map<String, Object> params=new HashMap<>();
				params.put("industry",i==0 ? "其他" : "物流");
				//params.put("phase",i==0 ? "种子轮" : "Pre-A轮");
				//params.put("area","北京市");
				//params.put("funding","融资中");
				//SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
				//params.put("startDate", sdf.parse("2010"));
				//params.put("endDate", new Date());
				list.add(params);
			}
			map.put("pushTaskSearch", list);
			headers.put("content-type","application/json");
			headers.put("Cookie","FOCUS_ID=5-9-26482-bs; FOCUS_EMAIL=tclbh@163.com; Hm_lvt_066da718ccf0d5656b81620a125fd3d6=1560084441,1561537680; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%221673f7ead335f7-0bd03f4c4296ee-4313362-1049088-1673f7ead34278%22%2C%22%24device_id%22%3A%221673f7ead335f7-0bd03f4c4296ee-4313362-1049088-1673f7ead34278%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC(%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80)%22%7D%7D; SESSION_AD=e6e811d0-ec7c-48a5-9567-f7b4d520e0ea");
			//headers.put("content-type", "application/x-www-form-urlcoded");
		String sss=JSONObject.toJSONString(map);
		System.err.println(sss);
		httpResult=http.httpPostStringEntity(url, 60000, 60000, 60000, headers, sss, null, null, false);
		System.out.println(httpResult);
		}


	private static void 充值鲸币(Map<String, String> map, Map<String, String> headers) {

		String url="http://admin.corp.36kr.com/dddddd/statistics/addEarnBalance";
		url=url+"?money=0&type=SALE_GAVEN";
		headers=new HashMap<>();
		//headers.put("cookie","kr_stat_uuid=DkFma25711849; _ga=GA1.2.2093164770.1542799148; new_user_guidance=true; device-uid=34202ba0-eef2-11e8-863f-07fb5c5f4b27; download_animation=1; gr_user_id=972c43f8-b5ba-4d13-a621-51076d152944; Hm_lvt_e8ec47088ed7458ec32cde3617b23ee3=1558329102,1559231202; ktu_email=shiwenjuan%40jingdata.com; kr_plus_id=663671872; kr_plus_token=T1TpVbXLbf4dngsFSNCfdVOlKpY58264652_____; krid_user_version=1; uitoken=gav1ruabzgxpq2vgumsnbu1xji30oyc-nrfqtl0jwyta-t3ecynnst2kpy0ve4f9; Hm_lvt_1684191ccae0314c6254306a8333d090=1559893614; Hm_lvt_713123c60a0e86982326bae1a51083e1=1559893614; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22663671872%22%2C%22%24device_id%22%3A%221673f7d57f2753-002a9ebb2060bc-4313362-1049088-1673f7d57f45a%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E8%87%AA%E7%84%B6%E6%90%9C%E7%B4%A2%E6%B5%81%E9%87%8F%22%2C%22%24latest_referrer%22%3A%22https%3A%2F%2Fwww.baidu.com%2Flink%22%2C%22%24latest_referrer_host%22%3A%22www.baidu.com%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC%22%7D%2C%22first_id%22%3A%221673f7d57f2753-002a9ebb2060bc-4313362-1049088-1673f7d57f45a%22%7D; SESSION_AD=3306675f-5c12-42fb-857e-b64b4213df6c");
		//headers.put("Content-Type", "multipart/form-data");
		//map.put("reqUrl", url);
		try {
			Map<String,ContentBody> reqParam = new HashMap<String,ContentBody>();
			String localFileName = "J:/charge.xlsx";
//			reqParam.put("Filename", new StringBody(localFileName, ContentType.MULTIPART_FORM_DATA));
//			reqParam.put("pictitle", new StringBody(localFileName, ContentType.MULTIPART_FORM_DATA));
//			reqParam.put("dir", new StringBody("upload1", ContentType.MULTIPART_FORM_DATA));
//			reqParam.put("fileNameFormat", new StringBody("{time}{rand:6}", ContentType.MULTIPART_FORM_DATA));
//			reqParam.put("fileName", new StringBody(localFileName, ContentType.MULTIPART_FORM_DATA));
//			reqParam.put("file", new StringBody(localFileName, ContentType.MULTIPART_FORM_DATA));
			//reqParam.put("money", new StringBody("0", ContentType.MULTIPART_FORM_DATA));
			//reqParam.put("type", new StringBody("SALE_GAVEN", ContentType.MULTIPART_FORM_DATA));
			//String result = http.postFileMultiPart(url, reqParam,headers);
			Map<String, Object> params=new HashMap<>();
			params.put("money",0);
			params.put("type","SALE_GAVEN");
			File file = new File(localFileName);
			InputStream inputStream = new FileInputStream(file);
			MultipartFile multipartFile = new MockMultipartFile(file.getName(), inputStream);
			List<MultipartFile> multipartFiles=new ArrayList<>();
			multipartFiles.add(multipartFile);
			Map<String, String> stringStringMap = http.httpPostMultipart(url, multipartFiles, localFileName, params, 6000);
			System.out.println("finally......"+stringStringMap.entrySet());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private static void enrollJzService(Map<String, String> map, Map<String, String> headers) {

		map.put("phone", "13261750653");
		map.put("userName", "zxc");
		map.put("companyName","name");
		map.put("productName","产品名称");
		map.put("id","2");
		map.put("code","909471");
		String url="http://localhost:8082/jzService/applyWithSign";
		//url="http://rongtest01.36kr.com/dddddd/mobi-investor/activity/v2/apply?as=investor";
		map.put("reqUrl", url);
	}


	private static void enrollActivity(Map<String, String> map, Map<String, String> headers) {
		map.put("actId","aaaaa");
		map.put("sessionNameStr", "第二场活动,第三场活动,第四场活动");
		map.put("orgName", "华录百纳");
		map.put("name", "超级llllll");
		map.put("card", "https://pic.36krcnd.com/avatar/201803/16063941/arffey71advonrio.jpg");//
		map.put("phone", "23300017847");
		map.put("wechat", "123456");
		map.put("code","180650");
		map.put("investorRole", "OTHERS");
		map.put("email","email123");
		map.put("position","职位123");
		//headers.put("cookie","kr_plus_id=4308; kr_plus_token=eNoVvz6_a8CNZz6BUfsy_Sl5vq3MLtSn39838___; krid_user_version=1");
		String url="http://rongtest01.36kr.com/dddddd/mobi-investor/activity/v2/apply?as=investor&actId=1212&phone=13466338409";
		url="http://localhost:8082/activity/v2/applyWithSign?as=investor";
		//url="http://rongtest01.36kr.com/dddddd/mobi-investor/activity/v2/apply?as=investor";
		map.put("reqUrl", url);
		//headers.put("cookie","kr_plus_id=663671872; kr_plus_token=WNUF_cC1717JAiQ4SQ4DP1Cdd6fqOe5Smxg1____; krid_user_version=1");
		}

	private static void login(Map<String, String> map) {
		String url="http://localhost:8082/v1/ulogin";
		url="http://localhost:8082/user/profile/v2";
		map.put("reqUrl", url);
		map.put("type", "PHONE");
		map.put("phone","86+23300005689");
		map.put("verify_code", "000000");
		//map.put("open_id", "wxe762a2d908d75f88");
		headers.put("User-Agent", "36kr-Tou-Android/5.2.3.beta (MX5; Android 5.1; Scale/1920x1080; device_id/ef36ca6c26d656ea857e72ce61a949c2)");
		headers.put("cookie","kr_plus_id=663671872; kr_plus_token=WNUF_cC1717JAiQ4SQ4DP1Cdd6fqOe5Smxg1____; krid_user_version=1");
	}

	private static void getSystemMsg(Map<String, String> map, Map<String, String> headers) {
		String url="http://rongtest01.36kr.com/dddddd/msg?endpoint=INVESTOR&limit=5&markReaded=true";
		url="http://rongtest01.36kr.com/dddddd/msg?endpoint=INVESTOR&limit=5&markReaded=true";
		//url="http://rongtest01.36kr.com/dddddd/mobi-investor/sixin/v3/systemMsgList";
		//url="http://localhost:8082/sixin/v3/systemMsgList";
		//headers.put("cookie", "sajssdk_2015_cross_new_user=1; kr_stat_uuid=HfDe325965015; Hm_lvt_e8ec47088ed7458ec32cde3617b23ee3=1555474308,1557393556; Hm_lpvt_e8ec47088ed7458ec32cde3617b23ee3=1557900913; download_animation=1; _kr_p_se=ba7ed102-20e7-42d0-8cc1-ea0724fbfe60; krid_user_id=858612505; krid_user_version=15; kr_plus_id=858612505; kr_plus_token=7HINRaIzxlURqGPBe8HEnGD_Ec4do1144232____; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22858612505%22%2C%22%24device_id%22%3A%2216aba2167b3229-0f94d531f45de9-2d604637-250125-16aba2167b4649%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%7D%2C%22first_id%22%3A%2216aba2167b3229-0f94d531f45de9-2d604637-250125-16aba2167b4649%22%7D");
		headers.put("cookie","kr_plus_id=1855133572; kr_plus_token=tTzphYa4LckJY473_FeHzD2GkqYFeOU46339____; krid_user_version=1");
		//httpResult=http.httpPost(url, 0, 0, 0, headers, map, null, null, false, "post");
		headers.put("User-Agent", "36kr-Tou-Android/5.3.3 (MX5; Android 5.1; Scale/1920x1080; device_id/ef36ca6c26d656ea857e72ce61a949c2)");
		httpResult=http.httpGet(url,null,headers);
		System.out.println("result: "+httpResult);

	}

	private static void updatePush(Map<String, String> map, Map<String, String> headers) {
		String url="http://localhost:8082/stratupService/edit";
		map.put("taskId","5");
		map.put("title","test50");
		map.put("taskType","2");
		map.put("link","google.com");
		map.put("content","test5的内容");
		map.put("runTime","2019-09-29 19:35:55");

		try {
			httpResult=http.httpPost(url, 0, 0, 0, headers, map, null, null, false, "post");
			//httpResult=http.httpPostStringEntity(url, 60000, 60000, 60000, headers, textMsg, null, null, false);
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println(httpResult);
		}
	}

	private static void insertPush(Map<String, String> map, Map<String, String> headers) {
		String url="http://localhost:8082/stratupService/push/add";
		map.put("title","test3");
		map.put("taskType","1");
		map.put("link","facebook.com");
		map.put("content","test4的内容");
		map.put("runTime","2019-05-19 13:35:55");

		try {
			httpResult=http.httpPost(url, 0, 0, 0, headers, map, null, null, false, "post");
			//httpResult=http.httpPostStringEntity(url, 60000, 60000, 60000, headers, textMsg, null, null, false);
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println(httpResult);
		}
	}

	private static void insertGroupManager(Map<String, String> map, Map<String, String> headers) {
		String url="http://localhost:8082/stratupService/role/add";

		map.put("uid","1180");
		map.put("role", "GROUP_MANAGER");

		String textMsg = JSONObject.toJSONString(map);
//      String textMsg="{\"id\":328,\"name\":\"鲸制项目——星海计划贰期\",\"type\":\"CHOICE\",\"fetchCode\":\"星海计划——为投资人提供免费的优质项目挖掘服务\",\"body\":\"<p>星海计划，是鲸准19年为投资人提供的免费优质项目挖掘服务，壹期项目单日单项目最高约谈227次，单日约谈总量916次！贰期项目从机器人、人工智能、医疗、高端制造等行业挖掘5大优质项目。</p><p>Ps:项目集周五封闭，马上约谈项目哦~</p>\",\"footer\":\"<p><span style=\\\"color: rgb(25, 31, 37); font-size: 14px; background-color: rgb(255, 255, 255);\\\">如果项目方未回复，请稍作等待~，可能同时在线人数太多。加微信cuiyouxiang,进入鲸准投资人社群</span></p>\",\"imgUrl\":\"https://pic.36krcnd.com/avatar/201904/09120252/c6is8pnum5ct6rv2.png\",\"headPic\":\"https://pic.36krcnd.com/avatar/201904/09120247/cb1vjsms46rbzezv.png\",\"sharePic\":\"https://pic.36krcnd.com/avatar/201904/09120257/ovt4k0zodpbwp80k.png\",\"bgPic\":\"https://pic.36krcnd.com/avatar/201904/09120300/akqtxb2rmb6gyfx6.png\",\"projectList\":[{\"id\":150520,\"name\":\"奥视国际\",\"orderNum\":5,\"enable\":1,\"bright\":1,\"brightDesc\":\"3D智能视觉喷涂机器人 项目亮点： 1.团队来自：日本安川机器人、中科院自动化所博士、微软亚洲研究院博士 2.客户：TATA木门、欧派厨具、联邦家居， 3.远望资本上轮投资\",\"videoUrl\":\"http://video.chuangkr.china.com.cn/201904/08033711/3alw77jj7ni5oiua_fmp4_vb1152.mp4\"},{\"id\":94333,\"name\":\"瑞慈医疗\",\"orderNum\":4,\"enable\":1,\"bright\":1,\"brightDesc\":\"肿瘤患者一站式医学服务平台 项目亮点： 1.3个月已实现盈亏平衡 2.半年服务患者1200 人，GMV逾1500万，19年预计患者超1W人 3.核心成员曾任美MORE Healtch公司高级副总裁\",\"videoUrl\":\"http://video.chuangkr.china.com.cn/201904/08033619/6eb6vtkvcqczrdo2_fmp4_vb1152.mp4\"},{\"id\":63301,\"name\":\"天天美剧\",\"orderNum\":3,\"enable\":1,\"bright\":1,\"brightDesc\":\"IP衍生智能硬件及电竞设备 项目亮点： 1.合作IP：迪士尼、万代、派拉蒙、孩之宝、漫威、育碧等 联想：研发、供应链、渠道及售后，战略ODM：BYD、昆山新鹰\",\"videoUrl\":\"http://video.chuangkr.china.com.cn/201904/08033743/b0c79bucs6mwzfsy_fmp4_vb1152.mp4\"},{\"id\":165020,\"name\":\"医疗点评网\",\"orderNum\":2,\"enable\":1,\"bright\":1,\"brightDesc\":\"3D智能视觉喷涂机器人 项目亮点： 1.团队来自：日本安川机器人、中科院自动化所博士、微软亚洲研究院博士 2.客户：TATA木门、欧派厨具、联邦家居， 3.远望资本上轮投资\",\"videoUrl\":\"\"},{\"id\":119345,\"name\":\"金融八卦女\",\"orderNum\":1,\"enable\":1,\"bright\":1,\"brightDesc\":\"依托知识图图谱的新零售人工智能解决方案 项目亮点：<br> 1.团队来自阿里、携程、英孚教育等算法模型专家 <br>2.客户：优衣库、ZARA、雅戈尔、太平鸟等 <br>3.2018年营收1100万\",\"videoUrl\":\"http://video.chuangkr.china.com.cn/201904/08033838/4zbrgpgtdegg4v9e_fmp4_vb1152.mp4\"}],\"creator\":\"王元元\",\"ad\":false,\"updatorUid\":244,\"createdAt\":1554811802000,\"creatorUid\":244,\"updator\":\"王元元\",\"viewCount\":211,\"status\":\"ONLINE\",\"updatedAt\":1554811811000}";
		headers.put("Content-Type", "application/json");
		System.out.println("heades: "+headers);
		try {
			httpResult=http.httpPostStringEntity(url, 60000, 60000, 60000, headers, textMsg, null, null, false);
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println(httpResult);
		}
	}

	private static void getActInmail(Map<String, String> map, Map<String, String> headers) {
		String url="https://rongtest01.36kr.com/dddddd/mobi-investor/sixin/dialog/act/796090384?lastId=&pageSize=10&flip_type=1&source=0&statsRefer=";
		url="http://localhost:8082/sixin/dialog/act/796090384?lastId=&pageSize=10&flip_type=0&source=0&crmCid=";
		//url="http://rongtest01.36kr.com/dddddd/mobi-investor/activity/v2/sixinClick?actId=测试活动小助手点击前往功能&inmailId=1001342";
		headers.put("cookie", "kr_plus_id=1855133572; kr_plus_token=tTzphYa4LckJY473_FeHzD2GkqYFeOU46339____; krid_user_version=1"); //机构投资人身份23377880000
		httpResult=http.httpGet(url, null, headers);
		System.out.println(httpResult);
		}

	private static void keywordList(Map<String, String> map, Map<String, String> headers) {
		String url="http://localhost:8082/stratupService/getList?startTime=2019-4-11";
		httpResult=http.httpGet(url, null, null);
		System.out.println(httpResult);
		}

	private static void insertKeyword(Map<String, String> map, Map<String, String> headers) {
		String url="http://localhost:8082/stratupService/updateKeyword";

		map.put("id","1");
		map.put("keyword", "互联网京东TT");
		map.put("type", "10");
		map.put("startTime","1554975254000");
		map.put("endTime","1554975264000");

		String textMsg = JSONObject.toJSONString(map);
//      String textMsg="{\"id\":328,\"name\":\"鲸制项目——星海计划贰期\",\"type\":\"CHOICE\",\"fetchCode\":\"星海计划——为投资人提供免费的优质项目挖掘服务\",\"body\":\"<p>星海计划，是鲸准19年为投资人提供的免费优质项目挖掘服务，壹期项目单日单项目最高约谈227次，单日约谈总量916次！贰期项目从机器人、人工智能、医疗、高端制造等行业挖掘5大优质项目。</p><p>Ps:项目集周五封闭，马上约谈项目哦~</p>\",\"footer\":\"<p><span style=\\\"color: rgb(25, 31, 37); font-size: 14px; background-color: rgb(255, 255, 255);\\\">如果项目方未回复，请稍作等待~，可能同时在线人数太多。加微信cuiyouxiang,进入鲸准投资人社群</span></p>\",\"imgUrl\":\"https://pic.36krcnd.com/avatar/201904/09120252/c6is8pnum5ct6rv2.png\",\"headPic\":\"https://pic.36krcnd.com/avatar/201904/09120247/cb1vjsms46rbzezv.png\",\"sharePic\":\"https://pic.36krcnd.com/avatar/201904/09120257/ovt4k0zodpbwp80k.png\",\"bgPic\":\"https://pic.36krcnd.com/avatar/201904/09120300/akqtxb2rmb6gyfx6.png\",\"projectList\":[{\"id\":150520,\"name\":\"奥视国际\",\"orderNum\":5,\"enable\":1,\"bright\":1,\"brightDesc\":\"3D智能视觉喷涂机器人 项目亮点： 1.团队来自：日本安川机器人、中科院自动化所博士、微软亚洲研究院博士 2.客户：TATA木门、欧派厨具、联邦家居， 3.远望资本上轮投资\",\"videoUrl\":\"http://video.chuangkr.china.com.cn/201904/08033711/3alw77jj7ni5oiua_fmp4_vb1152.mp4\"},{\"id\":94333,\"name\":\"瑞慈医疗\",\"orderNum\":4,\"enable\":1,\"bright\":1,\"brightDesc\":\"肿瘤患者一站式医学服务平台 项目亮点： 1.3个月已实现盈亏平衡 2.半年服务患者1200 人，GMV逾1500万，19年预计患者超1W人 3.核心成员曾任美MORE Healtch公司高级副总裁\",\"videoUrl\":\"http://video.chuangkr.china.com.cn/201904/08033619/6eb6vtkvcqczrdo2_fmp4_vb1152.mp4\"},{\"id\":63301,\"name\":\"天天美剧\",\"orderNum\":3,\"enable\":1,\"bright\":1,\"brightDesc\":\"IP衍生智能硬件及电竞设备 项目亮点： 1.合作IP：迪士尼、万代、派拉蒙、孩之宝、漫威、育碧等 联想：研发、供应链、渠道及售后，战略ODM：BYD、昆山新鹰\",\"videoUrl\":\"http://video.chuangkr.china.com.cn/201904/08033743/b0c79bucs6mwzfsy_fmp4_vb1152.mp4\"},{\"id\":165020,\"name\":\"医疗点评网\",\"orderNum\":2,\"enable\":1,\"bright\":1,\"brightDesc\":\"3D智能视觉喷涂机器人 项目亮点： 1.团队来自：日本安川机器人、中科院自动化所博士、微软亚洲研究院博士 2.客户：TATA木门、欧派厨具、联邦家居， 3.远望资本上轮投资\",\"videoUrl\":\"\"},{\"id\":119345,\"name\":\"金融八卦女\",\"orderNum\":1,\"enable\":1,\"bright\":1,\"brightDesc\":\"依托知识图图谱的新零售人工智能解决方案 项目亮点：<br> 1.团队来自阿里、携程、英孚教育等算法模型专家 <br>2.客户：优衣库、ZARA、雅戈尔、太平鸟等 <br>3.2018年营收1100万\",\"videoUrl\":\"http://video.chuangkr.china.com.cn/201904/08033838/4zbrgpgtdegg4v9e_fmp4_vb1152.mp4\"}],\"creator\":\"王元元\",\"ad\":false,\"updatorUid\":244,\"createdAt\":1554811802000,\"creatorUid\":244,\"updator\":\"王元元\",\"viewCount\":211,\"status\":\"ONLINE\",\"updatedAt\":1554811811000}";
			headers.put("Content-Type", "application/json");
			try {
				httpResult=http.httpPostStringEntity(url, 60000, 60000, 60000, headers, textMsg, null, null, false);
			} catch(Exception e) {
				e.printStackTrace();
			}finally {
				System.out.println(httpResult);
			}
		}

	private static void prosetAdd(Map<String, Object> map, Map<String, String> headers) {
//		List<ProSetProjectList> projectList=new ArrayList<>();
//		ProSetProjectList proSetProjectList=new ProSetProjectList();
//		proSetProjectList.setBright(1);
//		proSetProjectList.setBrightDesc("点亮介绍哈哈哈");
//		proSetProjectList.setEnable(1);
//		proSetProjectList.setId(2332);
//		proSetProjectList.setName("积米");
//		proSetProjectList.setVideoUrl("http://www.aaa.com");
//		projectList.add(proSetProjectList);
		String url="http://localhost:8082/demoday/actions/save";
		url="http://localhost:8082/stratupService/new/actions/save";
//		map.put("bgPic", "https://pic.36krcnd.com/avatar/201810/30100008/xcq6a9v3mhc6u0ar.jpg");
//		map.put("status", "ONLINE");
//		map.put("type", "CHOICE");
//		map.put("name","20190406");
//		map.put("headPic", "https://pic.36krcnd.com/avatar/201812/06105340/yqmfgq1zk2ct90qd.jpg");
//		map.put("sharePic", "https://pic.36krcnd.com/avatar/201812/06105348/hsks8xmgsvr3sg4d.jpg");
//		map.put("ad","false");
//		map.put("imgUrl", "https://pic.36krcnd.com/avatar/201812/06092740/ybwfuhv3sk43e21q.jpg");
//		map.put("fetchCode", "这是简介");
//		map.put("footer", "这是页脚的html");
//		map.put("body", "这是一串html");
//		map.put("projectList", projectList);
//		String textMsg = JSONObject.toJSONString(map);
		String startAt="2019-09-07 21:23:23";
		String endAt="2019-11-07 21:23:23";
		String guardTime="2019-10-06 21:23:23";
		String textMsg="{\"guard\": \"0\", \"guardTime\": \"0\", \"startAt\": \"2019-09-07 21:23:23\", \"endAt\": \"2019-11-07 21:23:23\", \"fundraise\":1,\"fundraiseType\":1, \"id\":328,\"name\":\"鲸制项目——星海计划Z期\",\"type\":\"CHOICE\",\"fetchCode\":\"星海计划——为投资人提供免费的优质项目挖掘服务\",\"body\":\"<p>星海计划，是鲸准19年为投资人提供的免费优质项目挖掘服务，壹期项目单日单项目最高约谈227次，单日约谈总量916次！贰期项目从机器人、人工智能、医疗、高端制造等行业挖掘5大优质项目。</p><p>Ps:项目集周五封闭，马上约谈项目哦~</p>\",\"footer\":\"<p><span style=\\\"color: rgb(25, 31, 37); font-size: 14px; background-color: rgb(255, 255, 255);\\\">如果项目方未回复，请稍作等待~，可能同时在线人数太多。加微信cuiyouxiang,进入鲸准投资人社群</span></p>\",\"imgUrl\":\"https://pic.36krcnd.com/avatar/201904/09120252/c6is8pnum5ct6rv2.png\",\"headPic\":\"https://pic.36krcnd.com/avatar/201904/09120247/cb1vjsms46rbzezv.png\",\"sharePic\":\"https://pic.36krcnd.com/avatar/201904/09120257/ovt4k0zodpbwp80k.png\",\"bgPic\":\"https://pic.36krcnd.com/avatar/201904/09120300/akqtxb2rmb6gyfx6.png\",\"projectList\":[{\"id\":150520,\"name\":\"奥视国际\",\"orderNum\":5,\"enable\":1,\"bright\":1,\"brightDesc\":\"3D智能视觉喷涂机器人 项目亮点： 1.团队来自：日本安川机器人、中科院自动化所博士、微软亚洲研究院博士 2.客户：TATA木门、欧派厨具、联邦家居， 3.远望资本上轮投资\",\"videoUrl\":\"http://video.chuangkr.china.com.cn/201904/08033711/3alw77jj7ni5oiua_fmp4_vb1152.mp4\"},{\"id\":94333,\"name\":\"瑞慈医疗\",\"orderNum\":4,\"enable\":1,\"bright\":1,\"brightDesc\":\"肿瘤患者一站式医学服务平台 项目亮点： 1.3个月已实现盈亏平衡 2.半年服务患者1200 人，GMV逾1500万，19年预计患者超1W人 3.核心成员曾任美MORE Healtch公司高级副总裁\",\"videoUrl\":\"http://video.chuangkr.china.com.cn/201904/08033619/6eb6vtkvcqczrdo2_fmp4_vb1152.mp4\"},{\"id\":63301,\"name\":\"天天美剧\",\"orderNum\":3,\"enable\":1,\"bright\":1,\"brightDesc\":\"IP衍生智能硬件及电竞设备 项目亮点： 1.合作IP：迪士尼、万代、派拉蒙、孩之宝、漫威、育碧等 联想：研发、供应链、渠道及售后，战略ODM：BYD、昆山新鹰\",\"videoUrl\":\"http://video.chuangkr.china.com.cn/201904/08033743/b0c79bucs6mwzfsy_fmp4_vb1152.mp4\"},{\"id\":165020,\"name\":\"医疗点评网\",\"orderNum\":2,\"enable\":1,\"bright\":1,\"brightDesc\":\"3D智能视觉喷涂机器人 项目亮点： 1.团队来自：日本安川机器人、中科院自动化所博士、微软亚洲研究院博士 2.客户：TATA木门、欧派厨具、联邦家居， 3.远望资本上轮投资\",\"videoUrl\":\"\"},{\"id\":119345,\"name\":\"金融八卦女\",\"orderNum\":1,\"enable\":1,\"bright\":1,\"brightDesc\":\"依托知识图图谱的新零售人工智能解决方案 项目亮点：<br> 1.团队来自阿里、携程、英孚教育等算法模型专家 <br>2.客户：优衣库、ZARA、雅戈尔、太平鸟等 <br>3.2018年营收1100万\",\"videoUrl\":\"http://video.chuangkr.china.com.cn/201904/08033838/4zbrgpgtdegg4v9e_fmp4_vb1152.mp4\"}],\"creator\":\"王元元\",\"ad\":false,\"updatorUid\":244,\"createdAt\":1554811802000,\"creatorUid\":244,\"updator\":\"王元元\",\"viewCount\":211,\"status\":\"ONLINE\",\"updatedAt\":1554811811000}";
		headers.put("Content-Type", "application/json");
		try {
			httpResult=http.httpPostStringEntity(url, 60000, 60000, 60000, headers, textMsg, null, null, false);
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println(httpResult);
		}

		}

	private static void getCmsCookie(Map<String, String> map, Map<String, String> headers) {
			String url="http://cmstest.corp.36kr.com/login";
			map.put("reqUrl", url);
			map.put("uname","36krjava");
			map.put("password","99eeca376cfa1c6355e3010fd3ec3fc2");
		    httpResult=http.httpPostCookie(map.get("reqUrl"), 0, 0, 0, null, map, null, null, false, "post");

	}

	private static void updateCompany() {
		String url="http://localhost:8082/stratupService/updateCompany";
		Map<String, Object> map=new HashMap<>();
		map.put("ccids","113127,125930,108451,78670468,4661325");
		map.put("id", 4);
		map.put("type", 4);
		String textMsg = JSONObject.toJSONString(map);
		headers.put("Content-Type", "application/json");
		try {
			httpResult=http.httpPostStringEntity(url, 60000, 60000, 60000, headers, textMsg, null, null, false);
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println(httpResult);
		}
	}

	private static void reportSearch() {
		String url="http://localhost:8082/stratupService/search/4?type=4&word=伟";
		map.put("reqUrl", url);
	}

	private static void setPass() {
		String url="http://localhost:8082/stratupService/setPass/4";
		map.put("reqUrl", url);
	}

	private static void setReportPass() {
		String url="http://localhost:8082/stratupService/setPass";
		Map<String, Object> map=new HashMap<>();
		map.put("sendId",4);
		String textMsg = JSONObject.toJSONString(map);
//		JSONObject json=new JSONObject();
//		json.put("id", 4);

		headers.put("Content-Type", "application/json");
		try {
			httpResult=http.httpPostStringEntity(url, 60000, 60000, 60000, headers, textMsg, null, null, false);
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println(httpResult);
		}
	}

	private static void getReportList() {
	String url="http://localhost:8082/stratupService/detail/4";
	map.put("reqUrl", url);

	}

	private static void updateSendTime() {
		String url="http://localhost:8082/stratupService/setSendTime";
		Map<String, Object> map=new HashMap<>();
		Long time=new Long(1565300000000l);
		map.put("sendTime",time);
		String textMsg = JSONObject.toJSONString(map);
		headers.put("Content-Type", "application/json");
		try {
			httpResult=http.httpPostStringEntity(url, 60000, 60000, 60000, headers, textMsg, null, null, false);
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println(httpResult);
		}
	}

	private static void reportAuditList() {
		String url="http://localhost:8082/stratupService/auditList?page=1&pageSize=10";
		url="http://admintest01.corp.36kr.com/dddddd/weeklyAudit/auditList";
		map.put("createTime", "1552924800000");
		map.put("createTimeEnd", "1553924800000");
		map.put("auditStatus","0");
		map.put("tag","人工智能");
		map.put("page", "1");
		map.put("pageSize", "10");

		String textMsg = JSONObject.toJSONString(map);
		headers.put("Content-Type", "application/json");
		headers.put("Cookie","kr_stat_uuid=DkFma25711849; _ga=GA1.2.2093164770.1542799148; new_user_guidance=true; device-uid=34202ba0-eef2-11e8-863f-07fb5c5f4b27; download_animation=1; gr_user_id=972c43f8-b5ba-4d13-a621-51076d152944; ktu_email=shiwenjuan%40jingdata.com; Hm_lvt_e8ec47088ed7458ec32cde3617b23ee3=1551423382,1552964907; Hm_lpvt_e8ec47088ed7458ec32cde3617b23ee3=1552964907; _kr_p_se=a4671a82-fbc6-4472-a1bd-d903744413a3; krid_user_id=1509620272; krid_user_version=12; kr_plus_id=1509620272; kr_plus_token=ReQ2EuM2ZdYeR51bedGwNb24cqrTfBK557_5____; Hm_lvt_1684191ccae0314c6254306a8333d090=1552966113; Hm_lvt_713123c60a0e86982326bae1a51083e1=1552966113; Hm_lpvt_713123c60a0e86982326bae1a51083e1=1552966195; Hm_lpvt_1684191ccae0314c6254306a8333d090=1552966195; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%221509620272%22%2C%22%24device_id%22%3A%221673f7d57f2753-002a9ebb2060bc-4313362-1049088-1673f7d57f45a%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%7D%2C%22first_id%22%3A%221673f7d57f2753-002a9ebb2060bc-4313362-1049088-1673f7d57f45a%22%7D; SESSION_AD=4555c162-ed43-4cda-8b61-d092d8eadb0e");
		try {
			httpResult=http.httpPostStringEntity(url, 60000, 60000, 60000, headers, textMsg, null, null, false);
			//httpResult=http.httpsGet(url, textMsg);
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println(httpResult);
		}
	}

	private static void addAdv() {
		String url="http://localhost:8082/adv/add";
		map.put("startTime", "2019-03-13");
		map.put("endTime", "2019-04-15");
		map.put("title","asasassa");
		map.put("runTime","3");
		map.put("link","www.baidu.com");
		map.put("sort","3");
		map.put("taskType","1");
		map.put("taskName","www.facebook.com");

		String textMsg = JSONObject.toJSONString(map);
		headers.put("Content-Type", "application/json");

		try {
			httpResult=http.httpPostStringEntity(url, 60000, 60000, 60000, headers, textMsg, null, null, false);
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println(httpResult);
		}
	}


	private static void updateAdv() {
		String url="http://localhost:8082/adv/update";
		map.put("id","10");
		map.put("startTime", "2019-03-13");
		map.put("endTime", "2019-04-16");
//		map.put("title","asasassa");
//		map.put("runTime","3");
//		map.put("link","www.baidu.com");
//		map.put("sort","3");
//		map.put("taskType","1");
//		map.put("taskName","www.facebook.com");

		String textMsg = JSONObject.toJSONString(map);
		headers.put("Content-Type", "application/json");

		try {
			httpResult=http.httpPostStringEntity(url, 60000, 60000, 60000, headers, textMsg, null, null, false);
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println(httpResult);
		}
	}


	private static void getAdv(Map<String, String> map, Map<String, String> headers) {
		String url="http://localhost:8082/adv/getAdv";
		map.put("reqUrl", url);
	}



	private static void advList(Map<String, String> map, Map<String, String> headers) {
			map.put("runTime","2019-03-12");
			map.put("status","2");
			String url="http://localhost:8082/stratupService/advList";
			map.put("reqUrl", url);

		}

	private static void enrollResult(Map<String, String> map, Map<String, String> headers) {
		map.put("actId","show");
		map.put("sessionNameStr", "sd");
		map.put("orgName", "华录百纳");
		map.put("name", "超级llllll");
		map.put("card", "https://pic.36krcnd.com/avatar/201803/16063941/arffey71advonrio.jpg");//
		map.put("phone", "13261750653");
		map.put("wechat", "123456");
		map.put("code","898532");
		map.put("investorRole", "OTHERS");
		map.put("email","libohan@jingdata.cn");
		map.put("position","职位123");
		headers.put("cookie","kr_plus_id=663671872; kr_plus_token=JVUp7RcUANDb9hGTb9vCDg9RsdUUc_422324____; krid_user_version=1");
		String url="http://rongtest01.36kr.com/dddddd/mobi-investor/activity/v2/apply?as=investor&actId=1212&phone=13466338409";
		url="http://localhost:8082/activity/v2/apply?as=investor";
		//url="http://rongtest01.36kr.com/dddddd/mobi-investor/activity/v2/apply?as=investor&actId=1212&phone=xxx&ifApp=true";
		String params="actId=show&entityName=%E5%93%88%E5%93%88%E6%9C%BA%E6%9E%84%E4%BD%A0%E7%8C%9C&realName=libohan&wechat=1234567ddgg&phone=13261750653&sessionNameStr=sd&position=%E4%BD%A0%E7%8C%9C%E7%8C%9C%E7%8C%9C&email=libohan%40jingdata.com&code=898532";
		map.put("reqUrl", url);
		//httpResult=http.httpPostStringEntity("http://locahost:8082/activity/v2/apply?as=investor", params);
		System.out.println(httpResult);
	}

	private static void download(Map<String, String> map, Map<String, String> headers) {
		String url="http://localhost:8082/activity/v2/exportExcel?as=investor&actId=qwer";
		map.put("reqUrl", url);
		}

	private static void activityDetail() {
		String url="http://localhost:8082/activity/v2?actId=1";
		map.put("reqUrl", url);
		map.put("actId","190226");
		headers.put("cookie", "kr_plus_id=4308; kr_plus_token=RcsjJXUmacchbzzMNHr_AYPHvf5279922935____; krid_user_version=1"); //机构投资人身份23377880000
		headers.put("cookie","kr_plus_id=663671872; kr_plus_token=JVUp7RcUANDb9hGTb9vCDg9RsdUUc_422324____; krid_user_version=1");

	}

	private static void ifEnroll(Map<String, String> map, Map<String, String> headers) {
		String url="http://localhost:8082/activity/v2/ifEnroll";
		map.put("reqUrl", url);
		map.put("actId","1212");
		//map.put("phone","13466338409");
		headers.put("cookie","kr_plus_id=4308; kr_plus_token=eNoVvz6_a8CNZz6BUfsy_Sl5vq3MLtSn39838___; krid_user_version=1");
	}

	private static void interval(Map<String, String> map, Map<String, String> headers) {
		String url="http://localhost:8082/activity/v2/interval";
		//url="http://rongtest01.36kr.com/dddddd/mobi-investor/activity/v2/interval";
		map.put("reqUrl", url);
		map.put("actStart", "2018-03-01");
		map.put("actEnd", "2019-04-01");


	}

	private static void nowDays(Map<String, String> map, Map<String, String> headers) {
//		map.put("actDate","2019-3-17");
		String url="http://localhost:8082/activity/v2/nowdays?actDate=2019-3-27";
		map.put("reqUrl", url);
	}

	private static void applyList(Map<String, String> map, Map<String, String> headers) {
		map.put("actId","20190226");
		map.put("status","PENDING");
		String url="http://localhost:8082/activity/v2/applyList";
		map.put("reqUrl", url);

	}

	private static void queryActivity(Map<String, String> map, Map<String, String> headers) {
		String url="http://localhost:8082/activity/v2/test?id=5c6f9b513703177ee0de2be6";
		map.put("reqUrl", url);
		}

	private static void updateActivity(Map<String, String> map, Map<String, String> headers) {
	String url="http://localhost:8082/activity/v2/5936628ce4b0ff2f494f0ec2";
	url="http://localhost:8082/activity/v2/5ad8810ce4b0b106f56fa1f8";
	map.put("actName"," 1111111");
	map.put("actId"," 111111");
	map.put("actIntro","人工智能产业价值峰会 * ##### 活动时间和地点 #####");
	map.put("actFlow",null);
		map.put("headImgUrl","https://pic.36krcnd.com/avatar/201803/06113954/0j0cu61ldebolnkv.jpg");
		map.put("contact","111111111");
		map.put("wxShareImgUrl","https://pic.36krcnd.com/avatar/201803/06113958/0prhmxi4nbd11vrm.jpg");
		map.put("wxShareTitle","AI分享12");
		map.put("wxShareDesc","AI描述12");
		map.put("button","222");
		map.put("wechat","122");
		map.put("actStart","2018-08-10 16:00:00");
		map.put("actEnd","2020-01-04 16:00:00");
		map.put("applierType","BOTH");
		map.put("agendaModule","false");
		map.put("projectModule","false");
		map.put("status","OFFLINE");
		map.put("logoStr","https://pic.36krcnd.com/avatar/201803/06113952/qfzo0g6v2xoyxdzw.jpg,https://pic.36krcnd.com/avatar/201902/21081416/ewhidgm90bargnhj.png");
		map.put("sessionStr"," [{\"name\":\"C1\",\"start\":\"2018-04-10 00:00:00\",\"end\":\"2018-04-11 00:00:00\",\"address\":\"北京\"},{\"name\":\"C2\",\"start\":\"2018-05-10 12:00:00\",\"end\":\"2018-05-10 18:00:00\",\"address\":\"上海\"},{\"name\":\"C3\",\"start\":\"2018-05-30 12:00:00\",\"end\":\"2018-05-30 18:00:00\",\"address\":\"深圳\"},{\"name\":\"新增测试\",\"start\":\"2019-02-26 00:01:00\",\"end\":\"2019-03-26 02:00:00\",\"address\":\"123\"}]");
		map.put("qRCode","这是一个二维码333333");//https://pic.36krcnd.com/avatar/201902/25062901/z8a25epkz81lzbs1.png
		map.put("reqUrl", url);
		}

	private static void 后台创建新活动(Map<String, String> map, Map<String, String> headers) {
		String url="http://localhost:8082/activity/v2";
		map.put("actIntro","这是活动介绍");
		map.put("actFlow","flow123");
		map.put("contact","13212345678");
		map.put("sessionStr","[{\"address\":\"北京市海淀区\",\"start\":\"2019-02-25 09:55:00\",\"end\":\"2019-02-25 19:55:00\",\"name\":\"场次1\"}]");
		map.put("logStr","https://pic.36krcnd.com/avatar/201902/22061615/nuiekz5qzwc5u82b.jpg");
		map.put("status","ONLINE");
		map.put("actName","测试二维码微信等");
		map.put("actId","NNNNNN");
		map.put("headImgUrl","https://pic.36krcnd.com/avatar/201902/22061617/saadfkpiw098o6he.jpg");
		map.put("wxShareTitle","wechat111");
		map.put("wxShareDesc","wechatDesc");
		map.put("actStart","2019-02-25 00:00:00");
		map.put("actEnd","2019-02-27 00:00:00");
		map.put("agendaModule","false");
		map.put("projectModule","false");
		map.put("applierType","BOTH");
		map.put("QRCode","二维码地址");
		map.put("button","可编辑的按钮");
		map.put("wechat","微信号");

		map.put("reqUrl", url);
	}

	private static void testLogin(Map<String, String> map, Map<String, String> headers) {
		String url="http://localhost:8082/stratupService/login?uid=663671872&userAgent=36kr-Tou-Android";
		headers.put("cookie","kr_plus_id=663671872; kr_plus_token=Xqo3fTrrFMKZ_9V7xr5wC6jHF7Gy_F6vyH218___; krid_user_version=1");
		headers.put("User-Agent", "36kr-Tou-Android/5.2.3.beta (MX5; Android 5.1; Scale/1920x1080;");
		map.put("reqUrl", url);
	}


	private static void 后台编辑任务(Map<String, String> map, Map<String, String> headers) {
		String url="http://localhost:8082/stratupService/editTask";
		map.put("reqUrl", url);
		map.put("taskId", "633");
		map.put("title", "测试编辑标题");
		map.put("content", "测试编辑内容");
		map.put("ccid","31237550");
		map.put("companyName","知春时代");
		map.put("runTime","2018-12-20 14:40:29");

	}

	private static void 身份验证极验(Map<String, String> map, Map<String, String> headers) {
		String url="http://localhost:8082/v3/geetest/h5/dddddd1?phone=13261750653";
		url="https://rongtest01.36kr.com/dddddd/mobi-investor/v3/geetest/h5/dddddd2";
		map.put("geetest_challenge","b41c3b90e89dbe0c8443463ea3a9b991");
		map.put("geetest_validate","0aee0fec321cbc45ed3e929ddcb2ac54");
		map.put("geetest_seccode","0aee0fec321cbc45ed3e929ddcb2ac54|jordan");
		map.put("phone","13466338409");
		map.put("reqUrl", url);
	}

	private static void 增加精选项目集缩略图(Map<String, String> map, Map<String, String> headers) {
		String url="http://admintest01.corp.36kr.com/dddddd/demoday-pro-set/actions/save";
		map.put("reqUrl", url);
		map.put("bgPic", "https://pic.36krcnd.com/avatar/201810/30100008/xcq6a9v3mhc6u0ar.jpg");
		map.put("status", "ONLINE");
		map.put("type", "HOTSPOT");
		map.put("name","2018120001");
		map.put("headPic", "https://pic.36krcnd.com/avatar/201812/06105340/yqmfgq1zk2ct90qd.jpg");
		map.put("sharePic", "https://pic.36krcnd.com/avatar/201812/06105348/hsks8xmgsvr3sg4d.jpg");
		map.put("ad","false");
		map.put("imgUrl", "https://pic.36krcnd.com/avatar/201812/06092740/ybwfuhv3sk43e21q.jpg");
		headers.put("cookie", "kr_stat_uuid=DkFma25711849; _ga=GA1.2.2093164770.1542799148; Hm_lvt_713123c60a0e86982326bae1a51083e1=1542267718,1542357477,1542360095,1542958438; Hm_lvt_1684191ccae0314c6254306a8333d090=1542267743,1542357560,1542360095,1542958438; new_user_guidance=true; device-uid=34202ba0-eef2-11e8-863f-07fb5c5f4b27; download_animation=1; gr_user_id=972c43f8-b5ba-4d13-a621-51076d152944; ktu_email=shiwenjuan%40jingdata.com; Hm_lvt_e8ec47088ed7458ec32cde3617b23ee3=1543307830,1543483014,1543557175,1543823975; _kr_p_se=e9299294-4807-471a-a423-f75d72c1bb70; krid_user_id=8004546; krid_user_version=2; kr_plus_id=8004546; kr_plus_token=8HeKmhErUURCkQySqcAnHq_e8gn9g158426_3___; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%228004546%22%2C%22%24device_id%22%3A%221673f7d57f2753-002a9ebb2060bc-4313362-1049088-1673f7d57f45a%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%7D%2C%22first_id%22%3A%221673f7d57f2753-002a9ebb2060bc-4313362-1049088-1673f7d57f45a%22%7D; SESSION_AD=8496e82b-dbb7-41bf-bf78-08165a935972");
		}


	private static void 更改精选项目集缩略图(Map<String, String> map, Map<String, String> headers) {
		String url="http://admintest01.corp.36kr.com/dddddd/demoday-pro-set/actions/edit";
		map.put("reqUrl", url);
		map.put("bgPic", "https://pic.36krcnd.com/avatar/201810/30100008/xcq6a9v3mhc6u0ar.jpg");
		map.put("id", "300");
		map.put("status", "ONLINE");
		map.put("type", "HOTSPOT");
		map.put("name","20181205");
		map.put("imgUrl", "https://pic.36krcnd.com/avatar/201812/06092740/ybwfuhv3sk43e21q.jpg");
		headers.put("cookie","kr_stat_uuid=DkFma25711849; _ga=GA1.2.2093164770.1542799148; Hm_lvt_713123c60a0e86982326bae1a51083e1=1542267718,1542357477,1542360095,1542958438; Hm_lvt_1684191ccae0314c6254306a8333d090=1542267743,1542357560,1542360095,1542958438; new_user_guidance=true; device-uid=34202ba0-eef2-11e8-863f-07fb5c5f4b27; download_animation=1; gr_user_id=972c43f8-b5ba-4d13-a621-51076d152944; ktu_email=shiwenjuan%40jingdata.com; Hm_lvt_e8ec47088ed7458ec32cde3617b23ee3=1543307830,1543483014,1543557175,1543823975; _kr_p_se=e9299294-4807-471a-a423-f75d72c1bb70; krid_user_id=8004546; krid_user_version=2; kr_plus_id=8004546; kr_plus_token=8HeKmhErUURCkQySqcAnHq_e8gn9g158426_3___; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%228004546%22%2C%22%24device_id%22%3A%221673f7d57f2753-002a9ebb2060bc-4313362-1049088-1673f7d57f45a%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%7D%2C%22first_id%22%3A%221673f7d57f2753-002a9ebb2060bc-4313362-1049088-1673f7d57f45a%22%7D; SESSION_AD=8496e82b-dbb7-41bf-bf78-08165a935972");
		}

	private static void 测试用户协议(Map<String, String> map, Map<String, String> headers) {
		String url="http://localhost:8082/agreement/click";
		url="https://rongtest01.36kr.com/dddddd/mobi-investor/agreement/click";
		map.put("reqUrl", url);
		headers.put("User-Agent", "36kr-Tou-Android/5.2.3.beta (MX5; Android 5.1; Scale/1920x1080; device_id/ef36ca6c26d656ea857e72ce61a949c2)");
		headers.put("cookie","kr_plus_id=628611853; kr_plus_token=7HTWh6gafGt7Mx8R_SWlaTyR_BwSRvJ611625___; krid_user_version=555");

	}

	private static void 测试灰名单(Map<String, String> map, Map<String, String> headers) {
		String url="http://localhost:8082/kr-investor-mobi-webapp/search/v2/company?keyword=红杉资本&page=1&pageSize=5";
		url="http://10.4.14.246:8082/v1/ulogin";
		map.put("reqUrl", url);
		headers.put("cookie","kr_plus_id=1708129734; kr_plus_token=HZhkENMal6yoaUKtarabrSiK6k3A5859_719____; krid_user_version=555");
		headers.put("User-Agent", "36kr-Tou-Android/5.2.3.beta (Android SDK built for x86; Android 8.1.0; Scale/1794x1080; device_id/123qweasdzxc456)");
	}

	private static void 投资人灰名单(Map<String, String> map, Map<String, String> headers) {

		String url="http://localhost:8082/kr-investor-mobi-webapp/search/v2/company?keyword=红杉资本&page=1&pageSize=5";
		url="http://localhost:8082/investor/support/grayFrame";
		map.put("reqUrl", url);
		headers.put("cookie", "kr_plus_id=1922800629; kr_plus_token=rF9QLMYouX1BHnS3kRM7RsZwBodF4c67794_____; krid_user_version=1");
		headers.put("cookie", "kr_plus_id=1708129734; kr_plus_token=QWEilBEUYcQIjUAKSd5GP8blqsn92217_158____; krid_user_version=1");
		headers.put("User-Agent", "36kr-Tou-Android/5.2.3.beta (Android SDK built for x86; Android 8.1.0; Scale/1794x1080; device_id/qqqqqqqqqqqqqqqq)");

	}

	private static void 创业者服务包(Map<String, String> map, Map<String, String> headers) {
		String url="http://localhost:8082/kr-investor-mobi-webapp/search/v2/company?keyword=红杉资本&page=1&pageSize=5";
		url="http://localhost:8082/stratupService/searchTask";
		map.put("reqUrl", url);
		headers.put("cookie", "kr_plus_id=663671872; kr_plus_token=Xqo3fTrrFMKZ_9V7xr5wC6jHF7Gy_F6vyH218___; krid_user_version=1");
		headers.put("cookie", "kr_plus_id=1922800629; kr_plus_token=uIpFY6lz1yO1ginDvv_d_hHzaRA6SdN935_2_17_; krid_user_version=1");
		headers.put("cookie", "kr_plus_id=663960813; kr_plus_token=FCWrc84KeMKxYhcviFEhIZPc6RTF_6588473____; krid_user_version=1");
	}

	private static void 钉钉机器人(Map<String, String> map, Map<String, String> headers) {
		String url="https://oapi.dingtalk.com/robot/send?access_token=80b40f06c764db17459ea486a5a3afc29b66bfba3761332c8664164ce5637f82";
		map.put("msgtype", "text");
		JSONObject json=new JSONObject();
		json.put("content","我机器人");
		map.put("text",json.toString());
		Map mobile=new HashMap();
		mobile.put("isAtAll","true");
		map.put("at", mobile.toString());
		String textMsg = JSONObject.toJSONString(map);
		headers.put("Content-Type", "application/json; charset=utf-8");

		try {
			httpResult=http.httpPostStringEntity(url, 60000, 60000, 60000, headers, textMsg, null, null, false);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}



	private static void 项目集收藏(Map<String, String> map, Map<String, String> headers) {
		String url="http://localhost:8082/kr-investor-mobi-webapp/search/v2/company?keyword=红杉资本&page=1&pageSize=5";
		url="http://localhost:8082/proSetColumn/v5/favorite?id=110&setType=UGC&flag=0";
		//url="http://localhost:8082/proSetColumn/v5/detail/1?page=1&pageSize=10&setType=UGC";
		//url="https://rongtest01.36kr.com/dddddd/mobi-investor/proSetColumn/v5/favorite?id=2&setType=UGC&flag=1";
		map.put("reqUrl", url);
		headers.put("cookie", "kr_plus_id=663671872; kr_plus_token=Xqo3fTrrFMKZ_9V7xr5wC6jHF7Gy_F6vyH218___; krid_user_version=1");
		headers.put("cookie", "kr_plus_id=1922800629; kr_plus_token=uIpFY6lz1yO1ginDvv_d_hHzaRA6SdN935_2_17_; krid_user_version=1");
		headers.put("cookie", "kr_plus_id=663960813; kr_plus_token=FCWrc84KeMKxYhcviFEhIZPc6RTF_6588473____; krid_user_version=1");

	}

	private static void 项目集点赞分享(Map<String, String> map, Map<String, String> headers) {
		String url="http://localhost:8082/proSetColumn/v5/agree?id=1&setType=UGC";
		//headers.put("cookie", "kr_plus_id=663671872; kr_plus_token=Xqo3fTrrFMKZ_9V7xr5wC6jHF7Gy_F6vyH218___; krid_user_version=1");
		map.put("reqUrl", url);
	}

	private static void 项目集详情页项目列表(Map<String, String> map, Map<String, String> headers) {
		String url="http://localhost:8082/kr-investor-mobi-webapp/search/v2/company?keyword=红杉资本&page=1&pageSize=5";
		url="http://localhost:8082/proSetColumn/v5/detail/130?setType=UGC&page=1&pageSize=5";
		//url="http://localhost:8082/proSetColumn/v5/detail/1?page=1&pageSize=10&setType=UGC";
		map.put("reqUrl", url);
		headers.put("cookie", "kr_plus_id=663671872; kr_plus_token=Xqo3fTrrFMKZ_9V7xr5wC6jHF7Gy_F6vyH218___; krid_user_version=1");
		headers.put("cookie", "kr_plus_id=2990311; kr_plus_token=2qJH9QS2vFCPCDbOZzswWGroOugY9523126_____; krid_user_version=1");

	}

	private static void 项目集详情页辅助(Map<String, String> map, Map<String, String> headers) {
		String url="http://localhost:8082/search/v2/company?keyword=阿拉&page=2&pageSize=10&address=&phases=&subTags=&tags=";
		url="http://localhost:8082/proSetColumn/v5/detail/round/302?setType=PGC";
		//url="https://rongtest01.36kr.com/dddddd/mobi-investor/proSetColumn/v5/detail/round/302?setType=PGC";
		//url="https://rongtest01.36kr.com/dddddd/mobi-investor/search/v2/company?keyword=阿拉&page=2&pageSize=10&address=&phases=&subTags=&tags=";

		map.put("reqUrl", url);
		headers.put("cookie", "kr_plus_id=663671872; kr_plus_token=Xqo3fTrrFMKZ_9V7xr5wC6jHF7Gy_F6vyH218___; krid_user_version=1");
		//headers.put("cookie","kr_plus_id=188051601; kr_plus_token=PL2u1rf4O1p1vypwjhwHif5PUuq33EKH28G_____; krid_user_version=1");
		//headers.put("cookie","kr_plus_id=396212; kr_plus_token=YhThrCrMbsYCYw6zbGTCmLosOs_76852248_6___; krid_user_version=1");
		//headers.put("cookie", null);
	}

	private static void 收藏夹项目集列表(Map<String, String> map, Map<String, String> headers) {
		String url="http://localhost:8082/kr-investor-mobi-webapp/search/v2/company?keyword=红杉资本&page=1&pageSize=5";
		url="http://localhost:8082/proSetColumn/v5/list?setType=PGC";
		url="https://rongtest01.36kr.com/dddddd/mobi-investor/proSetColumn/v5/list?setType=PGC";
		map.put("reqUrl", url);
		headers.put("cookie", "kr_plus_id=1922800629; kr_plus_token=bzO6aLvm7sVxY5CYRXwhovldLipK7221618_____; krid_user_version=1");
		headers.put("cookie", "kr_plus_id=663671872; kr_plus_token=Xqo3fTrrFMKZ_9V7xr5wC6jHF7Gy_F6vyH218___; krid_user_version=1");
		//headers.put("cookie", "kr_plus_id=1808485660; kr_plus_token=x51xj4Xp3vaBJvJBf4mQPaz8WcM4qhz2Q53_____; krid_user_version=1");

	}

	private static void testStringUtil() {
			String str="";
	}

	private static void admin后台修改LP基金(Map<String, String> map, Map<String, String> headers) {
			String url="http://localhost:8082/fundtrade/clueinfo/update/12";
			//map.put("reqUrl", url);
			Map<String, Object> reqMap=new HashMap<>();
			//reqMap.put("id", 4);
			reqMap.put("fundName", "链家中原");//基金名称
			reqMap.put("fundMoney", "1050");//基金规模单位亿
			reqMap.put("fundAttr", 13);//基金属性  13代表其他   9三方财富 12高净值人群
			reqMap.put("fundStructure", "优先LP,劣后LP");//基金结构
			reqMap.put("fundMoneyUnit", 1);//币种
			reqMap.put("fundTerm", 1);//基金期限
			reqMap.put("investField", "核心区");//投资领域
			reqMap.put("registAddr", 2);
//			reqMap.put("directInvest", 1);
//			reqMap.put("registAddrRequire", 0);
//			reqMap.put("backInvest", 1);
//			reqMap.put("backInvestRatio", 16.77);
			reqMap.put("otherAttrName", "lp其他类型333");
			String value=JSONObject.toJSONString(reqMap);
			//System.out.println("TestAiEnroll.admin后台修改LP基金: "+ value);
			headers.put("content-type","");
		try {
			httpResult=http.httpPostStringEntity(url, 60000, 60000, 60000, headers, value, null, null, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void admin基金后台详情页(Map<String, String> map, Map<String, String> headers) {
		String url="http://localhost:8084/fundtrade/contactList/1";
		map.put("reqUrl", url);
		headers.put("cookie", "SESSION_AD=0aa234d7-8b54-4531-b8e1-d502316e8e21");
	}

	private static void 基金详情页(Map<String, String> map, Map<String, String> headers) {
		String url="http://localhost:8082/fundtrade/1";
		map.put("reqUrl", url);
		headers.put("cookie", "kr_plus_id=858612505; kr_plus_token=av9amXVW7KgUSkK7qRi5ICFwO77NJDh_8949____; krid_user_version=1");
		headers.put("cookie", "kr_plus_id=1922800629; kr_plus_token=bzO6aLvm7sVxY5CYRXwhovldLipK7221618_____; krid_user_version=1");

	}

	private static void admin基金后台(Map<String, String> map, Map<String, String> headers) {
		String url="http://localhost:8082/fundtrade/fundtrade/0";
		map.put("reqUrl", url);
	}

//	private static void 创捷接口() {
//		Page<Map<String, Object>> fundList=new Page<>();
//		List <Map<String, Object>> list=new ArrayList<>();
//		Map<String, Object> map=new HashMap<>();
//		map.put("createTime", "1111111111");
//		map.put("orgName","华兴资本");
//		map.put("userName","张先生");
//		map.put("position","职位");
//		map.put("mobile","12121212");
//		map.put("wechat","12121212");
//		map.put("fundName","12121212");
//		list.add(map);
//		fundList.setData(list);
//		System.out.println(JSONObject.toJSONString(JsonResponse.success(fundList)));
//	}

	private static void 上传图片(Map<String, String> map, Map<String, String> headers2) {
			String url="https://rong.36kr.com/dddddd/upload/form-api";
			map.put("reqUrl", url);
			//{"bucket":"krplus-pic","expiration":1566908418,"save-key":"/{year}{mon}/{day}{hour}{min}{sec}/{random}{.suffix}"}
			JSONObject param=new JSONObject();
			param.put("bucket", "krplus-pic");
			param.put("expiration", "1566908418");
			param.put("save-key", "/{year}{mon}/{day}{hour}{min}{sec}/{random}{.suffix}");
			map.put("param", param.toString());
			map.put("type","pic");
		}

		private static void 小程序图片(Map<String, String> map, Map<String, String> headers2) {
			String token="13_y3lhNk5NTD02nSvFIM5Q_mBIg1C-LwzGnTy_4sE4VgSStNwF7mQM_4KFpGFsC2-n0OtybPTNFckF2vZBZsY3eLDNzUI1Ydjzqz_EmUMSZKeZJJy7K64pAxpSlJto8UxdU7JWBq_jvwc09ExFHRNaAGANXT";
			String url="https://dddddd.weixin.qq.com/wxa/getwxacode?access_token="+token;
			map.put("reqUrl", url);
			JSONObject json=new JSONObject();
			json.put("path", "pages/detail/detail?subjectId=1");
			json.put("width", 200);
			json.put("auto_color", true);
			json.put("is_hyaline", true);
			//json.put("page", "pages/index/index");
			//json.put("scene", "pages/index/index");

			try {
				CloseableHttpClient  httpClient = HttpClientBuilder.create().build();
	            HttpPost httpPost = new HttpPost("https://dddddd.weixin.qq.com/wxa/getwxacode?access_token="+token);
	            httpPost.addHeader(HTTP.CONTENT_TYPE, "");
	            String body = json.toString();
	            StringEntity entity;
	            entity = new StringEntity(body);
	            entity.setContentType("image/png");
	            httpPost.setEntity(entity);
	            HttpResponse response;

	            response = httpClient.execute(httpPost);
	            InputStream inputStream = response.getEntity().getContent();

	            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	            int len = 0;
	            byte[] buf = new byte[1024];
	            while ((len = inputStream.read(buf, 0, 1024)) != -1) {
	                outputStream.write(buf, 0, len);
	            }
	            byte[] picByte=outputStream.toByteArray();
	            //System.err.println(new String(picByte));
	            String pic64=com.alibaba.druid.util.Base64.byteArrayToBase64(picByte);
	            //String pic64=Base64.encodeBase64String(picByte);
	            System.err.println(pic64);
	            outputStream.flush();
	            outputStream.close();

	            BufferedOutputStream bout=new BufferedOutputStream(new FileOutputStream("f:/pic67.png"));
	    		byte [] by=com.alibaba.druid.util.Base64.base64ToByteArray(pic64);
	    		bout.write(by);
	    		bout.flush();
	    		bout.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private static void 内幕小程序评论(Map<String, String> map, Map<String, String> headers2) {
			String url="http://localhost:8083/hearsayapi/subject/73/comment?content=qttttt";
			map.put("reqUrl", url);
			map.put("source", "6");
			map.put("subjectId", "73");
			map.put("createCommentReq", "{'content': 'qttttt'}");
			headers.put("cookie", "kr_plus_id=645389868; kr_plus_token=9jbfEcHgaLkRr8vtHBRzztZC3CV7m351835_____; krid_user_version=1");
		}

		private static void 内幕小程序后台更改评论(Map<String, String> map, Map<String, String> headers2) {
			String url="http://localhost:8084/hearsayadminapi/comment/update";
			url="http://admintest01.corp.36kr.com/dddddd/krhearsayadmin/hearsayadminapi/comment/update";
			map.put("reqUrl", url);
			map.put("status", "1");
			map.put("id", "9");
		    headers.put("cookie", "_ga=GA1.2.537989150.1531103684; kr_stat_uuid=cMHmw25524591; gr_user_id=87602bbd-7aac-452c-823f-a095d425d818; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%221647e285e2add8-0fd5baa44c972c-b353461-1049088-1647e285e2bf7%22%2C%22%24device_id%22%3A%221647e285e2add8-0fd5baa44c972c-b353461-1049088-1647e285e2bf7%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%7D%7D; device-uid=da571c70-90c1-11e8-95c1-197005a0540b; Hm_lvt_713123c60a0e86982326bae1a51083e1=1532602291; download_animation=1; ktu_email=wangyuanyuan%4036kr.com; Hm_lvt_e8ec47088ed7458ec32cde3617b23ee3=1533784353,1534821009; Hm_lpvt_e8ec47088ed7458ec32cde3617b23ee3=1534821009; SESSION_AD=e9ef0f51-1a11-431b-9330-35ba1f3fab68");
		    //headers.put("token", "kr_plus_id=645389868; kr_plus_token=9jbfEcHgaLkRr8vtHBRzztZC3CV7m351835_____; krid_user_version=1");
		}


		private static void 内幕小程序评论列表(Map<String, String> map2, Map<String, String> headers2) {
			String url="http://localhost:8084/hearsayadminapi/comment/update";
			url="http://admintest01.corp.36kr.com/dddddd/krhearsayadmin/hearsayadminapi/subject/73/comment/list";
			map.put("reqUrl", url);
			//map.put("status", "1");
			//map.put("id", "9");
			headers.put("cookie", "_ga=GA1.2.537989150.1531103684; kr_stat_uuid=cMHmw25524591; gr_user_id=87602bbd-7aac-452c-823f-a095d425d818; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%221647e285e2add8-0fd5baa44c972c-b353461-1049088-1647e285e2bf7%22%2C%22%24device_id%22%3A%221647e285e2add8-0fd5baa44c972c-b353461-1049088-1647e285e2bf7%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%7D%7D; device-uid=da571c70-90c1-11e8-95c1-197005a0540b; Hm_lvt_713123c60a0e86982326bae1a51083e1=1532602291; download_animation=1; ktu_email=wangyuanyuan%4036kr.com; Hm_lvt_e8ec47088ed7458ec32cde3617b23ee3=1533784353,1534821009; Hm_lpvt_e8ec47088ed7458ec32cde3617b23ee3=1534821009; SESSION_AD=d13c624d-396c-4e6d-80e4-0bcdf461ecc9");
		}

		private static void 新版募资季搜索(Map<String, String> map2, Map<String, String> headers2) {
			String url="http://localhost:8082/kr-investor-mobi-webapp/search/com-and-org?wd=36氪"; //以太资本
			url="http://rongtest01.36kr.com/dddddd/mobi-investor/fundraise/new/v2/org?keyword=红杉";
			url="http://rongtest01.36kr.com/dddddd/mobi-investor/fundraise/new/v2/lp?keyword=公司";
			url="http://rongtest01.36kr.com/dddddd/mobi-investor/fundraise/new/v2/label?keyword=人工智能";
			map.put("reqUrl", url);
		    headers.put("cookie", "kr_plus_id=1922800629; kr_plus_token=bzO6aLvm7sVxY5CYRXwhovldLipK7221618_____; krid_user_version=1");
		}

		private static void 内幕小程序发布(Map<String, Object> map, Map<String, String> headers2) {
			String url="http://localhost:8083/hearsayapi/subject/";
			map.put("reqUrl", url);
			map.put("subjectCategory", "SECRET");
			map.put("titile", "震精！！！");

		    headers.put("token", "9jbfEcHgaLkRr8vtHBRzztZC3CV7m351835_____"); //
		    List<AttachFileVo> attachFileVoList=new ArrayList<>();
		    for (int i = 0; i < 2; i++) {
		    	AttachFileVo attachFileVo=new AttachFileVo();
		    	attachFileVo.setAttachTypeEnum("PIC");
		    	if(0==i){
		    		attachFileVo.setUrl("https://krplus-pic.b0.upaiyun.com/201705/12/7990453a5c792773f899551ca8e3a80c.jpg");
		    	}else{
		    		attachFileVo.setUrl("https://krplus-pic.b0.upaiyun.com/201705/12/41526992c6589851bad991ad52f783b3.png");
		    	}
		    	attachFileVoList.add(attachFileVo);
			}
		    map.put("attachFileVoList", attachFileVoList);


			map.put("content", "投行大秘文,麦子店高盛的故事");
			map.put("publishUsernameTypeEnum", "ANONYMOUS_NAME");

			List<RelateObjectVo> relateObjectVoList=new ArrayList<>();
			for (int i = 0; i < 2; i++) {
		    	RelateObjectVo relateObjectVo=new RelateObjectVo();
		    	relateObjectVo.setRelateObjectTypeEnum("INVEST_ORG");
		    	if(0==i){
		    		relateObjectVo.setRelateId("166");
		    	}else{
		    		relateObjectVo.setRelateId("15");
		    	}
		    	relateObjectVoList.add(relateObjectVo);
			}
			map.put("relateObjectVoList", relateObjectVoList);
			map.put("subjectSourceEnum", "WECHAT_APP");
			headers.put("content-type","");
			//headers.put("content-type", "application/x-www-form-urlcoded");
		}

		private static void 标签搜索(Map<String, String> map, Map<String, String> headers) {
			String url="http://localhost:8082/kr-investor-mobi-webapp/search/com-and-org?wd=36氪"; //以太资本
			url="http://rongtest01.36kr.com/dddddd/mobi-investor/search/v2/label?keyword=人工智能";
			map.put("reqUrl", url);
		    headers.put("cookie", "kr_plus_id=1922800629; kr_plus_token=bzO6aLvm7sVxY5CYRXwhovldLipK7221618_____; krid_user_version=1"); //
		}



		private static void 新版募资季获取报名列表(Map<String, String> map2, Map<String, String> headers2) {
			String url="http://localhost:8082/kr-investor-mobi-webapp/fundraise/new/enrollList";
			//url="http://admintest01.corp.36kr.com/dddddd/fundraise/new/enrollList";
			map.put("reqUrl", url);
			map.put("page", "2");
			map.put("pageSize", "10");
			//map.put("status", "0");
			map.put("labelName", "lp");
			map.put("endTime", "1533801250");
			headers.put("cookie", "_ga=GA1.2.537989150.1531103684; kr_stat_uuid=cMHmw25524591; gr_user_id=87602bbd-7aac-452c-823f-a095d425d818; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%221647e285e2add8-0fd5baa44c972c-b353461-1049088-1647e285e2bf7%22%2C%22%24device_id%22%3A%221647e285e2add8-0fd5baa44c972c-b353461-1049088-1647e285e2bf7%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%7D%7D; device-uid=da571c70-90c1-11e8-95c1-197005a0540b; Hm_lvt_713123c60a0e86982326bae1a51083e1=1532602291; download_animation=1; ktu_email=wangyuanyuan%4036kr.com; Hm_lvt_e8ec47088ed7458ec32cde3617b23ee3=1533784353; Hm_lpvt_e8ec47088ed7458ec32cde3617b23ee3=1533784353; SESSION_AD=cdc1f875-c694-4f32-ab4a-191160fd6f78");
		}


		private static void 新版募资季修改状态(Map<String, String> map2, Map<String, String> headers2) {
			String url="http://localhost:8082/kr-investor-mobi-webapp/fundraise/new/update";
			//url="http://rongtest01.36kr.com/dddddd/mobi-investor/fundraise/new/update";
			map.put("reqUrl", url);
			map.put("id", "327,328,1");
			map.put("status", "2");
			map.put("labelName", "org");
		}


		private static void 新版募资季LP列表(Map<String, String> map2, Map<String, String> headers2) {
			String url="http://localhost:8082/kr-investor-mobi-webapp/fundraise/new/lplist?pageSize=20&page=2";
			map.put("reqUrl", url);
		}


		private static void 新版募资季列表(Map<String, String> map2, Map<String, String> headers2) {
			String url="http://localhost:8082/kr-investor-mobi-webapp/fundraise/new/ranklist?pageSize=20&page=2";
			//url="http://rongtest01.36kr.com/dddddd/mobi-investor/fundraise/new/ranklist";
			map.put("reqUrl", url);
		}


		private static void 新版募资季机构报名(Map<String, String> map2, Map<String, String> headers2) {
			String url="http://localhost:8082/kr-investor-mobi-webapp/fundraise/new/orgEnroll";
			map.put("reqUrl", url);
			map.put("dpi", "dpi");
			map.put("irr", "irr");
			map.put("industry", "120,138");
			map.put("ipoCount", "5344");
			map.put("ipoQuitRadio", "34.5%");
			map.put("phase", "30,100");
			map.put("projectQuitCount", "123");
			map.put("projectQuitRadio", "27%");
			map.put("tvpi", "tvpi");
			map.put("orgId", "15");
			map.put("orgName", "红杉资本");
			map.put("manageCapital", "50000");
			map.put("manageCapitalCurrency", "USD");
			map.put("name", "wangwu");
			map.put("phone", "13145679875");
			map.put("wechat", "aaa");
			map.put("position", "神奇的位aa");
			map.put("card", "www.aaaa");
			map.put("offline", "1");
			map.put("bp", "wwww");
		}


		private static void 新版募资季LP报名(Map<String, String> map2, Map<String, String> headers2) {
			String url="http://localhost:8082/kr-investor-mobi-webapp/fundraise/new/lpEnroll";
			map.put("reqUrl", url);
			map.put("lpId", "92666");
			map.put("lpName", "北京天");
			map.put("name", "wangwu");
			map.put("phone", "13145679875");
			map.put("wechat", "aaa");
			map.put("position", "神奇的位aa");
			map.put("card", "www.aaaa");
			map.put("offline", "1");
			JSONArray investorOrg=new JSONArray();
	    	for (int i = 0; i < 1; i++) {
	    		JSONObject json=new JSONObject();
	    		if(i==0){
	    			json.put("orgId", 15);
					json.put("orgName", "红杉资本");
	    		}else{
	    			json.put("org_id", 0);
					json.put("org_name", "某个机构资本"+i);
	    		}

				investorOrg.add(json);
	    	}
	    	map.put("investorOrg", investorOrg.toString());
		}


		private static void 新版募资季首页(Map<String, String> map2, Map<String, String> headers2) {
			String url="http://localhost:8082/kr-investor-mobi-webapp/fundraise/new/index";
			url="http://rongtest01.36kr.com/dddddd/mobi-investor/fundraise/new/index";
			map.put("reqUrl", url);
		}


		private static void 获取机构详情(Map<String, String> map2, Map<String, String> headers2) {
			String url="http://localhost:8082/kr-investor-mobi-webapp/org_dict/19";
			map.put("reqUrl", url);
		}


		private static void 测试druid监控(Map<String, String> map2, Map<String, String> headers2) {
			String url="http://localhost:8080/dept?departmentName=root";
			map.put("reqUrl", url);
		}


		private static void 修改icon权限(Map<String, String> map2, Map<String, String> headers2) {
			JSONArray array=new JSONArray();
			for (int i = 0; i < 2; i++) {
				JSONObject json=new JSONObject();
					json.put("id", i+1);
					json.put("sort", i+3);
					array.add(json);
			}
			String params="";
			try {
				params = URLEncoder.encode(array.toString(), "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			String url="http://localhost:8082/kr-investor-mobi-webapp/config/updateSort?array="+params;
			//url="http://rongtest01.36kr.com/dddddd/mobi-investor/label_dict/industry/investChart?labelName=人工智能";
			//url="http://localhost:8082/kr-investor-mobi-webapp/config/updateSort?array=123";
			url="http://admintest01.corp.36kr.com/dddddd/icon/updateSort?array="+params;
			map.put("reqUrl", url);
			headers.put("cookie", "_ga=GA1.2.537989150.1531103684; kr_stat_uuid=cMHmw25524591; gr_user_id=87602bbd-7aac-452c-823f-a095d425d818; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%221647e285e2add8-0fd5baa44c972c-b353461-1049088-1647e285e2bf7%22%2C%22%24device_id%22%3A%221647e285e2add8-0fd5baa44c972c-b353461-1049088-1647e285e2bf7%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%7D%7D; device-uid=da571c70-90c1-11e8-95c1-197005a0540b; Hm_lvt_713123c60a0e86982326bae1a51083e1=1532602291; download_animation=1; ktu_email=wangyuanyuan%4036kr.com; SESSION_AD=e7121b93-1c85-4253-8fad-aa53f0bcd07f");
		}



		private static void 获取投资事件(Map<String, String> map2, Map<String, String> headers2) {
			String url="http://localhost:8082/kr-investor-mobi-webapp/label_dict/industry/investChart?labelName=人工智能";
			//url="http://rongtest01.36kr.com/dddddd/mobi-investor/label_dict/industry/investChart?labelName=人工智能";
			map.put("reqUrl", url);
			headers.put("cookie", "kr_plus_id=1922800629; kr_plus_token=bzO6aLvm7sVxY5CYRXwhovldLipK7221618_____; krid_user_version=1");
		}



		private static void 获取榜单状态(Map<String, String> map2, Map<String, String> headers2) {
			String url="http://localhost:8082/kr-investor-mobi-webapp/org_dict_rank/status";
			//url="http://rongtest01.36kr.com/dddddd/mobi-investor/org_dict_rank/status";
			map.put("reqUrl", url);
			headers.put("cookie", "kr_plus_id=2027159589; kr_plus_token=qx1bwpQXNoDYYANPe91yw3VClzOtx737_223____; krid_user_version=1");
			headers.put("cookie", "kr_plus_id=1634384200; kr_plus_token=uhw_cs3aM1m6fmlsv7vt1OVBV5oSGSW8G173____; krid_user_version=1");
			headers.put("cookie", "kr_plus_id=682104162; kr_plus_token=DGFvoZaRPU41FhwAYUlsAl2rQudF62_14439____; krid_user_version=1");
			headers.put("cookie", "kr_plus_id=1922800629; kr_plus_token=bzO6aLvm7sVxY5CYRXwhovldLipK7221618_____; krid_user_version=1");

		}



		private static void 获取所有热度(Map<String, String> map2, Map<String, String> headers2) {
			String url="http://localhost:8082/kr-investor-mobi-webapp/label_dict/industry/all_hot?type=WEEK";
			//url="http://rongtest01.36kr.com/dddddd/mobi-investor/label_dict/industry/all_hot?type=WEEK";
			map.put("reqUrl", url);
			headers.put("cookie", "kr_plus_id=1922800629; kr_plus_token=bzO6aLvm7sVxY5CYRXwhovldLipK7221618_____; krid_user_version=1");
		}

		private static void 获取数据组图标(Map<String, String> map, Map<String, String> headers) {
			String url="http://data-internaltest.corp.36kr.com/hot?cmd=get_hottag_list_by_tags&tags=手机回收,拍卖竞价,二手机械交易&size=7&interval=1";
			map.put("reqUrl", url);
		}

	private static void 修改icon(Map<String, String> map, Map<String, String> headers) {
		String url="http://localhost:8082/config/update";
		//url="http://personaltest01-admin.jingdata.com/dddddd/icon/update";
		map.put("auth", "2,10,50,100,200,300,400");
		map.put("createdAt", "1533715543");
		map.put("iconUrl", "http://www.sogou.com");
		map.put("id", "79");
		map.put("name", "1111");
		map.put("sort", "2");
		map.put("status", "0");
		map.put("updatedAt", "154142189");
		map.put("url", "www.qq.com");
		map.put("reqUrl", url);
		map.put("uid", "244");
		map.put("iconBgUrl","del");
		//headers.put("cookie", "kr_plus_id=1922800629; kr_plus_token=bzO6aLvm7sVxY5CYRXwhovldLipK7221618_____; krid_user_version=1");
		headers.put("cookie", "sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22160a2ebb5ab24c-0d8414d763b5b-b7a103e-1049088-160a2ebb5ac2c4%22%2C%22%24device_id%22%3A%22160a2ebb5ab24c-0d8414d763b5b-b7a103e-1049088-160a2ebb5ac2c4%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC(%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80)%22%7D%7D; Hm_lvt_066da718ccf0d5656b81620a125fd3d6=1535697048; SESSION_AD=f64aa8da-6978-4155-8bb6-9b08f7edb235");
		}

		private static void 插入icon(Map<String, String> map, Map<String, String> headers) {
			String url="http://localhost:8082/kr-investor-mobi-webapp/config/insert";
			map.put("name", "找LP");
			map.put("iconUrl", "http://www.baidu.com");
			map.put("url", "kr://tou");
			map.put("sort", "2");
			map.put("auth", "64,128,256");
			map.put("reqUrl", url);
			headers.put("cookie", "kr_plus_id=1922800629; kr_plus_token=bzO6aLvm7sVxY5CYRXwhovldLipK7221618_____; krid_user_version=1");
		}

		private static void 获取新版榜单(Map<String, String> map, Map<String, String> headers) {
			String url="http://localhost:8082/kr-investor-mobi-webapp/org_dict_rank/companys?labelName=综合&type=4&pageSize=20&page=2";
			//url="http://rongtest01.36kr.com/dddddd/mobi-investor/org_dict_rank/all_org/service?type=7";
			//url="http://rongtest01.36kr.com/dddddd/mobi-investor/org_dict_rank/companys?labelName=综合&type=4&pageSize=40";
			map.put("reqUrl", url);
			headers.put("cookie", "kr_plus_id=1922800629; kr_plus_token=bzO6aLvm7sVxY5CYRXwhovldLipK7221618_____; krid_user_version=1");
		}

		private static void 获取icon图标(Map<String, String> map, Map<String, String> headers) {
			String url="http://localhost:8082/kr-investor-mobi-webapp/config/list";
			//url="http://rongtest01.36kr.com/dddddd/mobi-investor/config/list";
			map.put("reqUrl", url);
			headers.put("cookie", "kr_plus_id=858612505; kr_plus_token=av9amXVW7KgUSkK7qRi5ICFwO77NJDh_8949____; krid_user_version=1");//2331
			//headers.put("cookie", "kr_plus_id=2990311; kr_plus_token=2qJH9QS2vFCPCDbOZzswWGroOugY9523126_____; krid_user_version=1");
		}

		private static void 获取单类图标(Map<String, String> map, Map<String, String> headers) {
			String url="http://localhost:8082/kr-investor-mobi-webapp/label_dict/industry/hot_chart?labelName=人工智能";
			//url="http://rongtest01.36kr.com/dddddd/mobi-investor/label_dict/industry/hot4_chart?type=WEEK&pageSize=48";
			map.put("reqUrl", url);
			headers.put("cookie", "kr_plus_id=1922800629; kr_plus_token=bzO6aLvm7sVxY5CYRXwhovldLipK7221618_____; krid_user_version=1");
		}

		private static void 获取一级领域(Map<String, String> map, Map<String, String> headers) {
			String url="http://localhost:8082/kr-investor-mobi-webapp/label_dict/industry/hot_all";
			//url="http://rongtest01.36kr.com/dddddd/mobi-investor/label_dict/industry/hot_all";
			map.put("reqUrl", url);
			headers.put("cookie", "kr_plus_id=1922800629; kr_plus_token=bzO6aLvm7sVxY5CYRXwhovldLipK7221618_____; krid_user_version=1");
		}

		private static void 获取二级领域(Map<String, String> map, Map<String, String> headers) {
			String url="http://localhost:8082/kr-investor-mobi-webapp/label_dict/industry/mapping?track=yes&labelName=人工智能";
			//url="http://rongtest01.36kr.com/dddddd/mobi-investor/label_dict/industry/mapping?labelName=人工智能&track=yes";
			map.put("reqUrl", url);
			headers.put("cookie", "kr_plus_id=1922800629; kr_plus_token=bzO6aLvm7sVxY5CYRXwhovldLipK7221618_____; krid_user_version=1");
		}

		private static void 获取图标(Map<String, String> map, Map<String, String> headers) {
			String url="http://localhost:8082/kr-investor-mobi-webapp/label_dict/industry/hot4_chart?type=WEEK";
			//url="http://rongtest01.36kr.com/dddddd/mobi-investor/label_dict/industry/hot4_chart?type=WEEK&pageSize=48";
			map.put("reqUrl", url);
			headers.put("cookie", "kr_plus_id=1922800629; kr_plus_token=bzO6aLvm7sVxY5CYRXwhovldLipK7221618_____; krid_user_version=1");
		}

		private static void 获取投资机会列表(Map<String, String> map, Map<String, String> headers) {
			String url="http://localhost:8082/kr-investor-mobi-webapp/investment/chance?page=1&pageSize=6&newData=true&type=1";
			map.put("reqUrl", url);
			headers.put("cookie", "kr_plus_id=1922800629; kr_plus_token=bzO6aLvm7sVxY5CYRXwhovldLipK7221618_____; krid_user_version=1");
		}

		private static void 中投(Map<String, String> map, Map<String, String> headers) {
			String url="http://localhost:8082/kr-investor-mobi-webapp/nrong/company-privilege?excel=export";
			url="http://rongtest01.36kr.com/dddddd/mobi-investor/v3/company/27245?statsRefer=search_list";
			map.put("reqUrl", url);
			headers.put("cookie", "kr_plus_id=1922800629; kr_plus_token=bzO6aLvm7sVxY5CYRXwhovldLipK7221618_____; krid_user_version=1");
			headers.put("User-Agent", "36kr-Tou-Android/4.1.1.beta (MI 6; Android 8.0.0; Scale/1920x1080)");
		}

		private static void 导出excel(Map<String, String> map, Map<String, String> headers) {
			String url="http://localhost:8082/kr-investor-mobi-webapp/nrong/company-privilege?excel=export";
			url="http://localhost:8082/kr-investor-mobi-webapp/nrong/company-privilege?page=1&pageSize=10&startDate=&endDate=&status=PASSED&companyName=&cid=&uid=&userName=&searchType=&searchContent=&companyType=userName&excel=export";
			//url="http://rong.36kr.com/dddddd/mobi-investor/ai/query";
			map.put("reqUrl", url);
		}

		private static void 查询用户数量(Map<String, String> map, Map<String, String> headers) {
			String url="http://localhost:8082/kr-investor-mobi-webapp/ai/query";
			//url="http://rong.36kr.com/dddddd/mobi-investor/ai/query";
			map.put("reqUrl", url);
			map.put("cmd", "querySize");
			map.put("authTypeEnum", "EDIT_VIP_INFO");//EDIT_BASE_INFO   EDIT_VIP_INFO
			map.put("year", "2018");
			map.put("month", "0");
			map.put("day", "1");
			headers.put("User-Agent", "36kr-Tou-Android/4.1.0.beta (MI 6; Android 8.0.0; Scale/1920x1080)");
			headers.put("Cookie", "kr_plus_id=928229358; kr_plus_token=Ir6P5wQ6eCUZfEmpWHMTZsoC8JB43rt88_88____; krid_user_version=1");
		}

		private static void 修改项目基本信息(Map<String, String> map, Map<String, String> headers) {
			String url="http://localhost:8082/kr-investor-mobi-webapp/company/check-edit/55527000/introduce";
			//url="http://rongtest01.36kr.com/dddddd/mobi-investor/company/edit/9093/introduce";
			map.put("reqUrl", url);
			headers.put("cookie", "kr_plus_id=2097219370; kr_plus_token=jA7WCinL5uFYoE4fyDBeHogWWhEC_3412359____; krid_user_version=1"); //23300018587

		}

		private static void 查询用户状态(Map<String, String> map, Map<String, String> headers) {
			String url="http://localhost:8082/kr-investor-mobi-webapp/investor/auth/state";
			//url="http://rongtest01.36kr.com/n/dddddd/investor/auth/state";
			map.put("reqUrl", url);
			headers.put("cookie", "kr_plus_id=2097219370; kr_plus_token=jA7WCinL5uFYoE4fyDBeHogWWhEC_3412359____; krid_user_version=1"); //23300018587
		}

		private static void 修改用户机构(Map<String, String> map, Map<String, String> headers) {
			String url="http://localhost:8082/kr-investor-mobi-webapp/user/profile";
			//url="http://rongtest01.36kr.com/dddddd/mobi-investor/company/edit/9093/introduce";
			map.put("reqUrl", url);
			headers.put("cookie", "kr_plus_id=116802043; kr_plus_token=nfII_aIdPZOnqjMlZ29SB4GykpIAj72432_76___; krid_user_version=1"); //23300011049 有很多项目的创业者
			map.put("investorRole", "0");
			map.put("investorEntityName", "今朝岁起东");
			map.put("investorEntityId", "47199");
			map.put("investorEntityType", "2");
			map.put("businessCardLink", "https://pic.36krcnd.com/avatar/201803/06074944/fgjagpvfdbg5rufg.jpeg");
		}

		private static void 机构搜索(Map<String, String> map, Map<String, String> headers) {
			String url="http://localhost:8082/kr-investor-mobi-webapp/search/com-and-org?wd=36氪"; //以太资本
			//url="http://rongtest01.36kr.com/dddddd/mobi-investor/search/com-and-org?wd=IDG";
			//url="http://localhost:8082/kr-investor-mobi-webapp/search/com-and-org?wd=经纬";
			map.put("reqUrl", url);
		}

		private static void 修改BP(Map<String, String> map, Map<String, String> headers) {
			String url="http://localhost:8082/kr-investor-mobi-webapp/company/edit/9093/bp"; //思必驰
			//url="http://rongtest01.36kr.com/dddddd/mobi-investor/company/edit/9093/introduce";
			map.put("reqUrl", url);
			map.put("url", "https://krplus-normal.b0.upaiyun.com/crm/20160627/[IU宝贝眼]商业计划书.pdf");
			headers.put("cookie", "kr_plus_id=116802043; kr_plus_token=nfII_aIdPZOnqjMlZ29SB4GykpIAj72432_76___; krid_user_version=1"); //23300011049 有很多项目的创业者
		}

		private static void LP搜索(Map<String, String> map, Map<String, String> headers) {
			String url="http://localhost:8082/kr-investor-mobi-webapp/search/v2/lp?keyword=以太资本"; //以太资本
			//url="http://rongtest01.36kr.com/dddddd/mobi-investor/search/v2/lp?keyword=公司";
			map.put("reqUrl", url);
			//headers.put("cookie",null);
		    headers.put("cookie", "kr_plus_id=1922800629; kr_plus_token=bzO6aLvm7sVxY5CYRXwhovldLipK7221618_____; krid_user_version=1"); //
		}


		private static void 服务机构搜索(Map<String, String> map, Map<String, String> headers) {
			String url="http://localhost:8082/kr-investor-mobi-webapp/search/v2/serviceOrg?keyword=以太资本"; //以太资本
			url="http://rongtest01.36kr.com/dddddd/mobi-investor/search/v2/serviceOrg?keyword=以太资本";
			map.put("reqUrl", url);
		}

		private static void 调查问券查询(Map<String, String> map, Map<String, String> headers) {
			headers.put("cookie", "kr_plus_id=797056400; kr_plus_token=PFwdj2KmgLAT5d3_OyT8ONSD6szPIsq65197____; krid_user_version=1"); //机构投资人身份23377880000
		    String url="http://localhost:8082/kr-investor-mobi-webapp/research/get";
		    map.put("reqUrl", url);
	}



		private static void 调查问券(Map<String, String> map, Map<String, String> headers) {
			   	map.put("rootId","0");
			   	map.put("itemId","0");
				headers.put("cookie", "kr_plus_id=797056400; kr_plus_token=PFwdj2KmgLAT5d3_OyT8ONSD6szPIsq65197____; krid_user_version=1"); //机构投资人身份23377880000
			    String url="http://localhost:8082/kr-investor-mobi-webapp/research/vote";
			    map.put("reqUrl", url);
			    map.put("rootId", "1");
			    map.put("itemId", "1");
		}

		private static void 消费生活(Map<String, String> map, Map<String, String> headers) {
			 String url="http://localhost:8082/kr-investor-mobi-webapp/org_consume/service/all_org";
			 map.put("reqUrl", url);
		}

		private static void 测试suggest(Map<String, String> map, Map<String, String> headers) {
			 String url="http://localhost:8082/kr-investor-mobi-webapp/search/v2/suggest?keyword=氪空间&newSearch=1"; //以太资本
			 //url="http://localhost:8082/kr-investor-mobi-webapp/search/com-org?wd=以太资本";
			 map.put("reqUrl", url);
		}

		private static void 初始化票数(Map<String, String> map, Map<String, String> headers) {
			///ticketInit
			 String url="http://localhost:8082/kr-investor-mobi-webapp/ticketInit/companyTicketInit?password=qwer!2340";
			 map.put("reqUrl", url);
		}

		private static void 测试邀请创业者认证(Map<String, String> map, Map<String, String> headers) {
			headers.put("cookie", "kr_plus_id=1686543572; kr_plus_token=sHis3SVUB88hYCLnuimDm5lwb4O9Mt7Y865_____; krid_user_version=1");
			//map.put("ccid", "77");
			/*//map.put("auditStatue","PASS");
			map.put("avatar","https://pic.36krcnd.com/avatar/201805/17091157/xcvja2c8dalqp344.jpg");
			//map.put("compnayPrivilegeId","243045");
			//map.put("createdAt","1526548353626");
			map.put("email","tclbh@163.com");
			//map.put("id","5afd4781e4b01b9ce99947cc");
			//map.put("identity","COMPANY_MEMBERS");
			map.put("name","被邀请认证创业者");
			map.put("os","ANDROID");
			map.put("recommendUid","1922800629");
			//map.put("uid","1686543572");
			//map.put("updateAt","1526548353626");
			map.put("userPosition","123456789");
			map.put("weixin","123456789");
			map.put("has_company", "true");*/
			String url="http://172.16.6.108:8082/kr-investor-mobi-webapp/companyUser/companyUserIdentify?avatar=https%3A%2F%2Fpic.36krcnd.com%2Favatar%2F201805%2F17122856%2Foz2pkazylrol80a1.jpg&name=%E9%99%8C%E9%99%8C&identity=COMPANY_MEMBER&userPosition=9999999&weixin=8888888&email=tclbh%40163.com&hasCompany=true&ccid=77";
			//url="https://rongtest01.36kr.com/dddddd/mobi-investor/companyUser/companyUserIdentify";
			map.put("reqUrl", url);
		}

		private static void 测试发送短息(Map<String, String> map, Map<String, String> headers) {
			map.put("sessionNameStr", "消费生活,区块连+金融");
		    map.put("orgName", "人皇资本");
		    map.put("name", "ccc");
		    //map.put("card", "https://pic.36krcnd.com/avatar/201803/16063941/arffey71advonrio.jpg");//
		    map.put("phone", "23354320000");
		    map.put("orgId", "264");
		     headers.put("cookie", "kr_plus_id=1922800629; kr_plus_token=bzO6aLvm7sVxY5CYRXwhovldLipK7221618_____; krid_user_version=1");// LP 23300000409
		    String url="http://172.16.6.108:8082/kr-investor-mobi-webapp/fundraise/org/signUp";
		    map.put("reqUrl", url);
		    String sql="update edm2.template set title=【鲸准·募资季】,您的机构【】的报名信息及您的投资人身份正在审核，审核结果将于1个工作日内以短信通知到您。详情请咨询客服微信jessicalady1 where id=280";
		    //map.put("card",sql);

		}

		/*{
		    "senderId":2,
		    "ssoId":639713061,
		    "invoker":698832758,
		    "tplCode":"LEGO_SERVICE_ORG_REJECTED",
		    "params":"{"uid":404391,"userName":null,"service_name":"vbh","nrong_domain":"http:\/\/rongtest01.36kr.com\/","krplus_domain":"http:\/\/rongtest01.36kr.com\/"}"
		}*/
		private static void 测试推送(Map<String, String> map, Map<String, String> headers) {
			String url="http://172.16.6.108:8080/kr-dubbo-web/internal/tpl-msg";
			map.put("senderId", "2");
			map.put("invoker", "698832758");
			map.put("tplCode", "FUNDRAISE_SIGNUP_PENDING");
			map.put("ssoId", "663671872"); //2330000409
			JSONObject params=new JSONObject();
			params.put("uid", "663671872");
			params.put("userName", "曹毅");
			params.put("orgName", "源码资本");
			params.put("nrong_domain", "http://rongtest01.36kr.com");
			params.put("krplus_domain", "http://rongtest01.36kr.com");
			map.put("params", params.toString());

		    //url="https://rongtest01.36kr.com/dddddd/mobi-investor/fundraise/lp/111";
			map.put("reqUrl", url);
		}


		private static void LP详情页(Map<String, String> map, Map<String, String> headers) {
			String url="http://172.16.6.108:8082/kr-investor-mobi-webapp/fundraise/lp/111";
		    url="https://rongtest01.36kr.com/dddddd/mobi-investor/fundraise/lp/111";
			map.put("reqUrl", url);
		}


		private static void 获取轮播(Map<String, String> map, Map<String, String> headers) {
			String url="http://172.16.6.108:8082/kr-investor-mobi-webapp/fundraise/rcmd";
		    //url="https://rongtest01.36kr.com/dddddd/mobi-investor/fundraise/rcmd";
			map.put("reqUrl", url);
		}


		private static void 搜索关键字(Map<String, String> map, Map<String, String> headers) {
			headers.put("cookie", "kr_plus_id=56037222; kr_plus_token=3CKhQyghQj_5y2RlAl1D2_5pqW2_DD6KURlp25__; krid_user_version=1"); //23353320000
			String url="http://172.16.6.108:8082/kr-investor-mobi-webapp/search/v2/suggest?keyword=4089&page=1&pageSize=10"; //4037 FA 4089 PARK_INCUBATOR
		    url="https://rongtest01.36kr.com/dddddd/mobi-investor/search/v2/suggest?keyword=4037&page=1&pageSize=10";
			map.put("reqUrl", url);
		}


		private static void 搜索身份(Map<String, String> map, Map<String, String> headers) {
			headers.put("cookie", "kr_plus_id=56037222; kr_plus_token=3CKhQyghQj_5y2RlAl1D2_5pqW2_DD6KURlp25__; krid_user_version=1"); //23353320000
			String url="http://172.16.6.108:8082/kr-investor-mobi-webapp/search/v2/person?keyword=张以蕾&page=1&pageSize=20";
		    //url="https://rongtest01.36kr.com/dddddd/mobi-investor/search/v2/person?keyword=4089&page=1&pageSize=20";
			map.put("reqUrl", url);
		}

		private static void 搜索投资人(Map<String, String> map, Map<String, String> headers) {
			headers.put("cookie", "kr_plus_id=56037222; kr_plus_token=3CKhQyghQj_5y2RlAl1D2_5pqW2_DD6KURlp25__; krid_user_version=1"); //23353320000
			String url="http://172.16.6.108:8082/kr-investor-mobi-webapp/search/v2/investor?keyword=王&page=1&pageSize=20";
		    map.put("reqUrl", url);
		}


		private static void 获取募资季首页(Map<String, String> map, Map<String, String> headers) {
			headers.put("cookie", "kr_plus_id=56037222; kr_plus_token=3CKhQyghQj_5y2RlAl1D2_5pqW2_DD6KURlp25__; krid_user_version=1"); //23353320000
			String url="http://172.16.6.108:8082/kr-investor-mobi-webapp/fundraise/index";
		    url="https://rongtest01.36kr.com/dddddd/mobi-investor/fundraise/index";
			map.put("reqUrl", url);
		}




		private static void 获取拒绝的身份(Map<String, String> map, Map<String, String> headers) {
			headers.put("cookie", "kr_plus_id=56037222; kr_plus_token=3CKhQyghQj_5y2RlAl1D2_5pqW2_DD6KURlp25__; krid_user_version=1"); //23353320000
			String url="http://172.16.6.108:8082/kr-investor-mobi-webapp/user/profile";
		    map.put("reqUrl", url);
		}




		private static void 认证身份加鲸币(Map<String, String> map, Map<String, String> headers) {
			headers.put("cookie", "kr_plus_id=179284098; kr_plus_token=OE3Xay4qCgBYMy7KLZmNsivgL7nG5H45915_____; krid_user_version=1"); //23351110000
			headers.put("cookie", "kr_plus_id=797056400; kr_plus_token=PFwdj2KmgLAT5d3_OyT8ONSD6szPIsq65197____; krid_user_version=1"); //机构投资人身份23377880000
			String url="http://172.16.6.108:8082/kr-investor-mobi-webapp/fundraise/verifyId/ANDROID";
		    //url="https://rongtest01.36kr.com/dddddd/mobi-investor/fundraise/verifyId/ANDROID";
		    map.put("reqUrl", url);
		}




		private static void 获取投资人信息(Map<String, String> map, Map<String, String> headers) {
			headers.put("cookie", "kr_plus_id=797056400; kr_plus_token=PFwdj2KmgLAT5d3_OyT8ONSD6szPIsq65197____; krid_user_version=1"); //机构投资人身份23377880000
		    String url="http://172.16.6.108:8082/kr-investor-mobi-webapp/investor/702";
		    //url="https://rongtest01.36kr.com/dddddd/mobi-investor/investor/702";
		    map.put("reqUrl", url);
		}




		private static void 获取机构基本信息(Map<String, String> map, Map<String, String> headers) {
			headers.put("cookie", "kr_plus_id=797056400; kr_plus_token=PFwdj2KmgLAT5d3_OyT8ONSD6szPIsq65197____; krid_user_version=1"); //机构投资人身份23377880000
		    String url="http://172.16.6.108:8082/kr-investor-mobi-webapp/fundraise//orgDetail/12";
		    url="https://rongtest01.36kr.com/dddddd/mobi-investor/fundraise/orgDetail/6";
		    map.put("reqUrl", url);
		}




		private static void 触发机构工单(Map<String, String> map, Map<String, String> headers) {
			map.put("orgName", "橡树湾");
			map.put("orgFullName", "海淀橡树湾小米有限公司");
			map.put("orgType", "11");
			map.put("orgBrief", "这是一个人才2323323");
			map.put("orgTurn", "90,110");
			map.put("orgTag", "30,40");
			map.put("orgWebsite", "www.qq.com");
			map.put("orgWeibo", "wqwqwqwqwqwqw.com");
			map.put("orgWechat", "weixinaaa");
			map.put("orgAddr", "109");
			map.put("orgPhone", "23300006666");
			//param.put("orgId", "5");
			headers.put("cookie", "kr_plus_id=797056400; kr_plus_token=PFwdj2KmgLAT5d3_OyT8ONSD6szPIsq65197____; krid_user_version=1"); //机构投资人身份23377880000
		    String url="http://172.16.6.108:8082/kr-investor-mobi-webapp/fundraise/neworg";
		    url="http://rongtest01.36kr.com/dddddd/mobi-investor/fundraise/neworg";
		    map.put("reqUrl", url);
	}

		private static void 测试回调(Map<String, String> map, Map<String, String> headers) {
			map.put("result", "asasdsadsadsadsa");
			map.put("org","qwqwq111");
			String url="http://172.16.6.108:8080/springmvc-1/orgCallback";
		    url="http://rongtest01.36kr.com/dddddd/mobi-investor/fundraise/newOrg/callBack?orgId=15&result=1";
			map.put("reqUrl", url);
	}

		private static void 获取字典(Map<String, String> map, Map<String, String> headers) {
			//map.put("mapName", "area");
			headers.put("cookie", "kr_plus_id=797056400; kr_plus_token=PFwdj2KmgLAT5d3_OyT8ONSD6szPIsq65197____; krid_user_version=1"); //机构投资人身份23377880000
		    String url="http://172.16.6.108:8082/kr-investor-mobi-webapp/fundraise/dictMap";
		    url="http://rongtest01.36kr.com/dddddd/mobi-investor/fundraise/dictMap";
		    map.put("reqUrl", url);
	}




		private static void 募资季创业者报名(Map<String, String> map, Map<String, String> headers) {
		    map.put("actId","AIAIAI");
			map.put("sessionNameStr", "人工智能,大健康");
		    map.put("orgName", "华录百纳");
		    map.put("name", "超级llllll");
		    map.put("card", "https://pic.36krcnd.com/avatar/201803/16063941/arffey71advonrio.jpg");//
		    map.put("phone", "23377880000");
		    map.put("wechat", "123456");
		    map.put("investorRole", "START_UP");
			headers.put("cookie", "kr_plus_id=797056400; kr_plus_token=PFwdj2KmgLAT5d3_OyT8ONSD6szPIsq65197____; krid_user_version=1"); //机构投资人身份23377880000
		    String url="http://rongtest01.36kr.com/dddddd/mobi-investor/activity/v2/apply?as=investor";
		    map.put("reqUrl", url);
	}

		private static void adminProfile(Map<String, String> map, Map<String, String> headers) {
		    String url="http://172.16.6.108:8082/kr-investor-mobi-webapp/activity/v2/applyinvestor";
		    map.put("reqUrl", url);
		    map.put("page", "1");
		    map.put("pageSize", "10");
		    map.put("actId","AIAIAI");
		    map.put("status", "PASS");//PENDING,PASS,REJECT,ONLINE,OFFLINE,ALL
		    //map.put("applierType", "INVESTOR");//PERSONAL_INVESTOR, LP, FA, OTHER_SERVICE_ORG, PARK_INCUBATOR, ORG_INVESTOR,
		    map.put("investorRole", "START_UP");
		    //map.put("investorNow", "1");
		}



		/*name: ccc
		  .  orgId: 264
		  .  orgName: DST
		  .  phone: 23354320000
		  .  sessionNameStr: 消费生活,区块连+金融*/
		private static void 募资季华录百纳机构报名(Map<String, String> map, Map<String, String> headers) {
			map.put("sessionNameStr", "消费生活,区块连+金融");
		    map.put("orgName", "DST");
		    map.put("name", "ccc");
		    map.put("card", "https://pic.36krcnd.com/avatar/201803/16063941/arffey71advonrio.jpg");//
		    map.put("phone", "23354320000");
		    map.put("orgId", "264");
			headers.put("cookie", "kr_plus_id=14273744; kr_plus_token=PFwdj2KmgLAT5d3_OyT8ONSD6szPIsq65197____; krid_user_version=1"); //机构投资人身份23377880000
		    String url="http://172.16.6.108:8082/kr-investor-mobi-webapp/fundraise/org/signUp";
		    map.put("reqUrl", url);
	}




		private static void 募资季机构投资人报名(Map<String, String> map, Map<String, String> headers) {
			map.put("sessionNameStr", "人工智能,大健康");
		    map.put("orgName", "华录百纳");
		    map.put("name", "呵呵后");
		    map.put("card", "https://pic.36krcnd.com/avatar/201803/16063941/arffey71advonrio.jpg");
		    map.put("phone", "23377770000");
		    map.put("wechat", "123456");

			headers.put("cookie", "kr_plus_id=131464721; kr_plus_token=Mt5HSFSKTWAiIwUFRoPC8utr8paw38_652_52___; krid_user_version=1"); //机构投资人身份23377770000
		    String url="http://172.16.6.108:8082/kr-investor-mobi-webapp/fundraise/org/signUp";
		    map.put("reqUrl", url);
	}




		private static void 募资季登陆查看机构状态(Map<String, String> map, Map<String, String> headers) {
			    headers.put("cookie", "kr_plus_id=175136230; kr_plus_token=VgL4si1IHRtTzLUVd347Ao1Ja5x_8gzBQP66____; krid_user_version=1"); //机构投资人身份23300014444
			    map.put("orgName", "五岳资本");
			    String url="http://172.16.6.108:8082/kr-investor-mobi-webapp/fundraise/judgeOrgStatus";
			    map.put("reqUrl", url);
	}




		private static void 募资季报名(Map<String, String> map, Map<String, String> headers) {
				map.put("sessionNameStr", "人工智能,大健康");
			    map.put("orgName", "红杉资本");
			    map.put("name", "史诗李四");
			    map.put("card", "https://pic.36krcnd.com/avatar/201803/16063941/arffey71advonrio.jpg");
			    map.put("phone", "23300001111");
			    map.put("wechat", "123456");
		    	headers.put("cookie", "kr_plus_id=2123999737; kr_plus_token=7uiVrp48JT9CWMzSKIhOHLN3ImBpzb62152_____; krid_user_version=1"); //募资季孵化器身份认证  23372220000
			    String url="http://172.16.6.108:8082/kr-investor-mobi-webapp/fundraise/org/signUp";
			    map.put("reqUrl", url);
		}

		private static void 募资季judgeHasEnroll(Map<String, String> map, Map<String, String> headers) {
		    headers.put("cookie", "kr_plus_id=175136230; kr_plus_token=VgL4si1IHRtTzLUVd347Ao1Ja5x_8gzBQP66____; krid_user_version=1"); //机构投资人身份23300014444
		    map.put("orgName", "五岳资本");
		    String url="http://172.16.6.108:8082/kr-investor-mobi-webapp/fundraise/judgeHasEnroll";
		    url=domain+"/fundraise/judgeHasEnroll";
		    map.put("reqUrl", url);
		}

		private static void 募资季judgeInvestor(Map<String, String> map, Map<String, String> headers) {
		    headers.put("cookie", "kr_plus_id=175136230; kr_plus_token=VgL4si1IHRtTzLUVd347Ao1Ja5x_8gzBQP66____; krid_user_version=1"); //机构投资人身份23300014444
	    	headers.put("cookie", "kr_plus_id=2123999737; kr_plus_token=7uiVrp48JT9CWMzSKIhOHLN3ImBpzb62152_____; krid_user_version=1"); //募资季孵化器身份认证  23372220000

		    map.put("orgName", "五岳资本");
			map.put("reqUrl",  "http://localhost:8082/kr-investor-mobi-webapp/fundraise/judgeInvestor");
	}

		private static void 募资季身份认证LP(Map<String, String> map,  Map<String, String> headers) {
			String url="http://localhost:8082/kr-investor-mobi-webapp/investor/auth?action=h5";//获得通过邀请码注册
			map.put("reqUrl", url);
			headers.put("cookie",  "kr_plus_id=2005984813; kr_plus_token=y8yCIfU7kD8SXoNkIDV1Sgv1oW6fukp5318_____; krid_user_version=1"); //23349990000
			封装请求参数(map,"LP");
			map.put("orgName", "琨御府LP");
			map.put("os","ANDROID");
			map.put("recommendUid","931500037");
			//map.put("actId", "fundraise");
	}

		private static void 募资季身份认证孵化器(Map<String, String> map, Map<String, String> headers) {
		    	String url="http://localhost:8082/kr-investor-mobi-webapp/investor/auth?action=h5";//获得通过邀请码注册
				map.put("reqUrl", url);
		    	//headers.put("cookie", "kr_plus_id=2123999737; kr_plus_token=7uiVrp48JT9CWMzSKIhOHLN3ImBpzb62152_____; krid_user_version=1"); //募资季孵化器身份认证  23372220000
				headers.put("cookie",  "kr_plus_id=990927490; kr_plus_token=A_bAByjT3JXFwpyC_ql9Lz8ACyCzAS65_43368__; krid_user_version=1"); //23343330000
				封装请求参数(map,"PARK_INCUBATOR");
				map.put("orgName", "万城华府孵化器");
				map.put("os","ANDROID");
				map.put("recommendUid","931500037");
				//map.put("actId", "fundraise");
		}




		private static void 募资季身份认证投资人(Map<String, String> map, Map<String, String> headers) {
			String url="http://localhost:8082/kr-investor-mobi-webapp/investor/auth?action=h5";//获得通过邀请码注册
			map.put("reqUrl", url);
			//headers.put("cookie", "kr_plus_id=628061187; kr_plus_token=VXt26Q7BG2DrmoJWGAkPOvJDT1dhyt7_9444____; krid_user_version=1"); //募资季投资人身份认证  23370000000
			headers.put("cookie", "kr_plus_id=1335392383; kr_plus_token=uTo_dgETjaItB7xPYv9Up_yO7DQMqR477635_2__; krid_user_version=1"); //募资季投资人身份认证  23347770000
			封装请求参数(map,"ORG_INVESTOR");
			map.put("os","ANDROID");
			map.put("orgName", "涧桥泊屋机构投资人");
			map.put("recommendUid","931500037");
			map.put("actId", "fundraise");
	}

		private static void 募资季身份认证FA等身份(Map<String, String> map, Map<String, String> headers) {
			String url="http://localhost:8082/kr-investor-mobi-webapp/investor/auth?action=h5";//获得通过邀请码注册
			map.put("reqUrl", url);
			//headers.put("cookie", "kr_plus_id=284253; kr_plus_token=zH3CxZ186G7e2Hy15oWbi_lVYS25qGIu8VOi____; krid_user_version=1"); //募资季FA身份认证  23369990000
			//headers.put("cookie", "kr_plus_id=208232245; kr_plus_token=ffIAS7VZ_VpIbFfK_QoA5MilD5FlBj8741958___; krid_user_version=1"); //募资季FA身份认证  23344440000
			//headers.put("cookie", "kr_plus_id=2015959903; kr_plus_token=ITE8BOt9AZKFuNYnVyzKAS3933eerf6191_y____; krid_user_version=1"); //募资季其他服务机构身份认证  23346660000
			headers.put("cookie", "kr_plus_id=179284098; kr_plus_token=OE3Xay4qCgBYMy7KLZmNsivgL7nG5H45915_____; krid_user_version=1");
			封装请求参数(map,"OTHER_SERVICE_ORG");
			map.put("os","ANDROID");
			map.put("orgName", "中海凯旋OTHER_SERVICE_ORG");
			//map.put("recommendUid","931500037");
			//map.put("actId", "fundraise");
	}




		private static void 封装请求参数(Map<String, String> map, String orgType) {
			map.put("investorRoleEnum", orgType);//LP ORG_INVESTOR
			map.put("businessCard", "https://pic.36krcnd.com/avatar/201803/16063941/arffey71advonrio.jpg");
			map.put("realName", "一个"+orgType);//
			map.put("avatar", "https://pic.36krcnd.com/avatar/201803/20065733/e0rueotmr8wih4ht.jpg");
			map.put("weixin", "qwerasd123");
			map.put("orgType", orgType);//LP ORG_INVESTOR
			map.put("email","tclbh@163.com");
			map.put("commonEmail", "tclbh@163.com");
			map.put("position", orgType+"的职位");
		}

		private static void 认证LP(Map<String, String> map) {
			map.put("investorRoleEnum", "LP");//LP ORG_INVESTOR
			map.put("businessCard", "https://pic.36krcnd.com/avatar/201803/16063941/arffey71advonrio.jpg");
			map.put("realName", "另一个被邀请的LP");//
			map.put("avatar", "https://pic.36krcnd.com/avatar/201803/20065733/e0rueotmr8wih4ht.jpg");
			map.put("weixin", "qwerasd123");
			map.put("orgType", "LP");//LP ORG_INVESTOR
			map.put("email","tclbh@163.com");
			map.put("orgName", "LP 公司呵呵后");
			map.put("commonEmail", "tclbh@163.com");
			map.put("investorPosition","这是LP 神奇的职位");
			map.put("position", "这是LP修改为机构职位");
			map.put("recommendUid","931500037");
			map.put("os","ANDROID");
	}


		private static void 认证投资人(Map<String, String> map) {
			封装请求参数(map, "ORG_INVESTOR");
			map.put("recommendUid","1855133572");
			map.put("recommendName","gm");
	}


		private static void buyBP(Map<String, String> map) {
			map.put("ccid", "10657"); // 耳朵树
			map.put("ccid", "1054"); //  新娘说
		}


		private static void 私信列表(Map<String, String> map) {
			map.put("type", "followed");
		}


		private static void token(Map<String, String> map) {
			map.put("phone", "23300000409");
		}


		private static void geeResult(Map<String, String> map) {
			map.put("phone", "13261112222");
	}


		private static void sendCode(Map<String, String> map) {
			map.put("phone", "86+13261750653");
			map.put("isVoice","false");
	}


		private static void 认领项目(Map<String,String> map) {
			map.put("name", "0326LP身份认证");
			map.put("userPosition","LP认领项目职位");
			map.put("action", "CLAIM");
			map.put("weixin", "444444");
			map.put("userBusinessCard", "https://pic.36krcnd.com/avatar/201803/06074944/fgjagpvfdbg5rufg.jpeg");
			map.put("identity", "COMPANY_EXECUTIVES");
	}


		public static void getHot(){
			BigDecimal bg = new BigDecimal(44.6900000000000005);
	        double fixHot = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	        System.err.println(fixHot);
		}

		public static void math(){

			System.out.println(3 << 3);
			System.out.println("right:" + (12 >> 2));
			System.out.println("3 right:" + (12 >>> 2));
		}

	static  final int threadCount = 150;
	static  final ExecutorService executor = Executors.newFixedThreadPool(threadCount);
		private static void 多线程请求() {
			CyclicBarrier barrier = new CyclicBarrier(threadCount); // 100
			// 用来让主线程等待threadCount个子线程执行完毕
			CountDownLatch countDownLatch = new CountDownLatch(threadCount);

			for (int i = 1; i <=threadCount; i++) {
				executor.submit(new Runner(barrier, "test" + i, i, countDownLatch) {
				});
			}
			//executor.shutdown();
			try {
				countDownLatch.await();
				System.err.println(Thread.currentThread().getName()+" fininshed");

			} catch (Exception e) {
				e.printStackTrace();
			}
}



	static class Runner implements Runnable {
		    private CyclicBarrier barrier;
		    private String name;
		    private int id;
		    private CountDownLatch countDownLatch;

		    public Runner(CyclicBarrier barrier, String name, int id, CountDownLatch countDownLatch) {
		        this.barrier = barrier;
		        this.name = name;
		        this.id=id;
		        this.countDownLatch=countDownLatch;
		    }

		    @Override
			public void run() {
		    	try {
					System.out.println(Thread.currentThread().getName()+" "+name + " 准备OK.");
					barrier.await();
					//System.out.println("go go go");
					String url="http://localhost:8088/dept?departmentName="+name;
					url="http://localhost:8088/dept/"+id;
					url="http://rongtest01.36kr.com/dddddd/mobi-investor/ai/index/first";
					//获取项目详情页(map,headers);
					getSystemMsg(map, headers);
					//httpResult = http.httpGet(url, null, headers);
					countDownLatch.countDown();
					System.out.println(Thread.currentThread().getName()+" "+name + " Go!!");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}


		static class AttachFileVo implements Serializable {
		    private String attachTypeEnum;
		    private String url;

		    public String getAttachTypeEnum() {
				return attachTypeEnum;
			}
			public void setAttachTypeEnum(String attachTypeEnum) {
				this.attachTypeEnum = attachTypeEnum;
			}
			public String getUrl() {
		        return url;
		    }
		    public void setUrl(String url) {
		        this.url = url;
		    }
		 }


		static class RelateObjectVo{
			private String relateId;
			private String relateObjectTypeEnum;
			public String getRelateId() {
				return relateId;
			}
			public void setRelateId(String relateId) {
				this.relateId = relateId;
			}
			public String getRelateObjectTypeEnum() {
				return relateObjectTypeEnum;
			}
			public void setRelateObjectTypeEnum(String relateObjectTypeEnum) {
				this.relateObjectTypeEnum = relateObjectTypeEnum;
			}

		}

}


class ReqFactory {

	private static final ReqFactory INSTANCE = new ReqFactory();

	private ReqFactory() {

	}

	public static ReqFactory getInstance() {
		return INSTANCE;
	}

	/**
	 */
	public <DT extends ReqData> Req<DT> createReq(DT data) {
		return createReq(null, data);
	}

	/**
	 * @param head
	 * @param data
	 */
	public <DT extends ReqData> Req<DT> createReq(ReqHead head, DT data) {
		Req<DT> reqIn = new Req<DT>();
		reqIn.setHead(head);
		reqIn.setData(data);
		return reqIn;
	}


	class Req<DT extends ReqData> implements Serializable {

		/**
		 * @Fields serialVersionUID TODO（描述变量的含义）
		 */
		private static final long serialVersionUID = 8876509725575825107L;

		/**
		 * 请求头信息
		 */
		private ReqHead head;

		/**
		 * 请求主体信息
		 */
		private DT data;

		/**
		 * @Title Req
		 * @Description 只能通过 ReqFactory 实例化
		 */
		protected Req() {

		}

		/**
		 * @Description 获取 head 请求头信息
		 */
		public ReqHead getHead() {
			return head;
		}

		/**
		 * @Description 设置 head 请求头信息
		 */
		public void setHead(ReqHead head) {
			this.head = head;
		}

		/**
		 * @return data 请求主体信息
		 */
		public DT getData() {
			return data;
		}

		/**
		 * @Description 设置 data 请求主体信息
		 */
		public void setData(DT data) {
			this.data = data;
		}

		/**
		 * @return
		 * @Title toString
		 */
		@Override
		public String toString() {
			return ReflectionToStringBuilder.toStringExclude(this, Constants.TOSTRINGEXCLUDE);

		}
	}
}


