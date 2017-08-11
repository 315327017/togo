package com.togo.schedule;

import com.google.common.collect.ImmutableMap;
import com.togo.controller.TogoController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetAddress;

public class ChannelMoniter {

    private static final Logger LOGGER = LoggerFactory.getLogger(TogoController.class);

    @Scheduled(fixedRate = 30 * 1000L)
    public void logAndAlert() {
        channelMap.forEach((String channelName, String hostString) -> {
            try {
                boolean isReachable = InetAddress.getByName(hostString).isReachable(1000);
                String info = String.format(channelName + "%s", "\t\t ping:" + isReachable);
                LOGGER.info(info);
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        });
    }

    /**
     * <p>监控渠道<p/>
     * <p>注释渠道禁止PING命令，无法检测</p>
     */
    private static final ImmutableMap<String,String> channelMap = new ImmutableMap.Builder<String,String>()
            .put("UC九游","sdk.g.uc.cn")
            .put("百度","querysdkapi.91.com")
            .put("360","openapi.360.cn")
            .put("小米","mis.migc.xiaomi.com")
            .put("新浪平台","m.game.weibo.cn")
            .put("178平台","passport.stargame.com")
            .put("棱镜","gameproxy.xinmei365.com")
//            .put("当乐","ngsdk.d.cn")
            .put("豌豆荚","pay.wandoujia.com")
            .put("OPPO","i.open.game.oppomobile.com")
//            .put("优酷","sdk.api.gamex.mobile.youku.com")
            .put("拇指玩","sdk.muzhiwan.com")
            .put("4399","m.4399api.com")
            .put("vivo","usrsys.inner.bbk.com")
            .put("华为","api.vmall.com")
//            .put("联想","passport.lenovo.com")
            .put("金立","id.gionee.com")
//            .put("酷派","openapi.coolyun.com")
            .put("斗鱼","www.douyutv.com")
            .put("今日头条","open.snssdk.com")
            .put("魅族","api.game.meizu.com")
            .put("91wan","www.91wan.com")
            .put("应用宝(YSDK)","ysdk.qq.com")
            .put("虎扑","youxi.hupu.com")
            .put("努比亚","niugamecenter.nubia.com")
            .put("快看","api.kkmh.com")
            .build();
}
