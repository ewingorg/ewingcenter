package external.mpsdk.core;

import java.util.Arrays;

import org.nutz.lang.Strings;

import external.mpsdk.vo.event.BasicEvent;
import external.mpsdk.vo.event.LocationEvent;
import external.mpsdk.vo.event.MenuEvent;
import external.mpsdk.vo.event.ScanCodeEvent;
import external.mpsdk.vo.event.ScanEvent;
import external.mpsdk.vo.event.SendLocationInfoEvent;
import external.mpsdk.vo.event.SendPhotosEvent;
import external.mpsdk.vo.message.Article;
import external.mpsdk.vo.message.BasicMsg;
import external.mpsdk.vo.message.ImageMsg;
import external.mpsdk.vo.message.LinkMsg;
import external.mpsdk.vo.message.LocationMsg;
import external.mpsdk.vo.message.NewsMsg;
import external.mpsdk.vo.message.TextMsg;
import external.mpsdk.vo.message.VideoMsg;
import external.mpsdk.vo.message.VoiceMsg;
import external.mpsdk.vo.push.SentAllJobEvent;
import external.mpsdk.vo.push.SentTmlJobEvent;

/**
 * 微信消息,事件处理器(实际生产中需要重写)
 * 
 * @author 凡梦星尘(elkan1788@gmail.com)
 * @since 2.0
 */
public class WechatDefHandler implements WechatHandler {

    @Override
    public BasicMsg defMsg(BasicMsg bm) {
        TextMsg tm = new TextMsg(bm);
        tm.setContent(bm.getMsgType());
        return tm;
    }

    @Override
    public BasicMsg defEvent(BasicEvent be) {
        TextMsg tm = new TextMsg(be);
        tm.setContent(Strings.join("\n", be.getEvent(), be.getEventKey()));
        return tm;
    }

    @Override
    public BasicMsg text(TextMsg tm) {
        return tm;
    }

    @Override
    public BasicMsg image(ImageMsg im) {
        return im;
    }

    @Override
    public BasicMsg voice(VoiceMsg vm) {
        TextMsg tm = new TextMsg(vm);
        tm.setContent(Strings.join("\n", vm.getMediaId(), vm.getFormat(), vm.getRecognition()));
        return tm;
    }

    @Override
    public BasicMsg video(VideoMsg vim) {
        TextMsg tm = new TextMsg(vim);
        tm.setContent(Strings.join("\n", vim.getMsgType(), vim.getMediaId(), vim.getThumbMediaId()));
        return tm;
    }

    @Override
    public BasicMsg shortVideo(VideoMsg vim) {
        TextMsg tm = new TextMsg(vim);
        tm.setContent(Strings.join("\n", vim.getMsgType(), vim.getMediaId(), vim.getThumbMediaId()));
        return tm;
    }

    @Override
    public BasicMsg location(LocationMsg lm) {
        TextMsg tm = new TextMsg(lm);
        tm.setContent(Strings.join("\n",
                                   lm.getX(),
                                   lm.getY(),
                                   String.valueOf(lm.getScale()),
                                   lm.getLabel()));
        return tm;
    }

    @Override
    public BasicMsg link(LinkMsg lm) {
        NewsMsg news = new NewsMsg(lm);
        Article art = new Article();
        art.setTitle(lm.getTitle());
        art.setDescription(lm.getDescription());
        art.setPicUrl("http://j2ee.u.qiniudn.com/mpsdk4j-logo.png-aliassmall");
        art.setUrl(lm.getUrl());
        news.setArticles(Arrays.asList(art));
        return news;
    }

    @Override
    public BasicMsg eClick(MenuEvent me) {
        TextMsg tm = new TextMsg(me);
        tm.setContent(me.getEventKey());
        return tm;
    }

    @Override
    public void eView(MenuEvent me) {}

    @Override
    public BasicMsg eSub(BasicEvent be) {
        TextMsg tm = new TextMsg(be);
        tm.setContent("Welcom, wechat develop with use mpsdk4j!");
        return tm;
    }

    @Override
    public void eUnSub(BasicEvent be) {}

    @Override
    public BasicMsg eScan(ScanEvent se) {
        TextMsg tm = new TextMsg(se);
        tm.setContent(se.getEventKey() + se.getTicket());
        return tm;
    }

    @Override
    public void eLocation(LocationEvent le) {}

    @Override
    public BasicMsg eScanCodePush(ScanCodeEvent sce) {
        TextMsg tm = new TextMsg(sce);
        tm.setContent(Strings.join("\n", sce.getEventKey(), sce.getScanType(), sce.getScanResult()));
        return tm;
    }

    @Override
    public BasicMsg eScanCodeWait(ScanCodeEvent sce) {
        return this.eScanCodePush(sce);
    }

    @Override
    public BasicMsg ePicSysPhoto(SendPhotosEvent spe) {
        TextMsg tm = new TextMsg(spe);
        tm.setContent(Strings.join("\n",
                                   spe.getEventKey(),
                                   String.valueOf(spe.getSendPicsInfo().getCount()),
                                   String.valueOf(spe.getSendPicsInfo().getPicList()),
                                   String.valueOf(spe.getSendPicsInfo()
                                                     .getPicList()
                                                     .get(0)
                                                     .getPicMd5Sum())));
        return tm;
    }

    @Override
    public BasicMsg ePicPhotoOrAlbum(SendPhotosEvent spe) {
        return this.ePicSysPhoto(spe);
    }

    @Override
    public BasicMsg ePicWeixin(SendPhotosEvent spe) {
        return this.ePicSysPhoto(spe);
    }

    @Override
    public BasicMsg eLocationSelect(SendLocationInfoEvent slie) {
        TextMsg tm = new TextMsg(slie);
        tm.setContent(Strings.join("\n",
                                   slie.getLocationX(),
                                   slie.getLocationY(),
                                   slie.getLabel(),
                                   String.valueOf(slie.getScale()),
                                   slie.getPoiname()));
        return tm;
    }

    @Override
    public void eSentTmplJobFinish(SentTmlJobEvent stje) {}

    @Override
    public void eSentAllJobFinish(SentAllJobEvent saje) {}

}
