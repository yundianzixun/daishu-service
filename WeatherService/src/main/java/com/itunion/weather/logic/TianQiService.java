package com.itunion.weather.logic;

import com.google.gson.Gson;
import com.itunion.weather.client.TianQiFeignClient;
import com.itunion.weather.client.WeatherD1FeignClient;
import com.itunion.weather.client.WeatherD7FeignClient;
import com.itunion.weather.client.WsFeignClient;
import com.itunion.weather.config.TianQiConfig;
import com.itunion.weather.config.WxConfig;
import com.itunion.weather.handler.TianQiHandler;
import com.itunion.weather.pojo.base.WSBaseResponse;
import com.itunion.weather.pojo.response.WsGeocodeLocationRespDto;
import com.itunion.weather.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class TianQiService {
    @Autowired
    private WsFeignClient wsFeignClient;
    @Autowired
    private WxConfig wxConfig;

    @Autowired
    private TianQiHandler tianQiHandler;


    public Object localDetails(String lat, String lon) {
        return tianQiHandler.getLocalDetailsCacher(lat,lon);
    }

    public Object recently(String cityCode) {
        return tianQiHandler.getRecentlyCacher(cityCode);
    }

    public Object tianqiapi(String version, String cityid) {
        return tianQiHandler.getTianqiapiCacher(version,cityid);
    }

    public Object localInfo(String cityCode) {
        return tianQiHandler.getLocalInfoCacher(cityCode);
    }

    public static void main(String[] args) {
        String a = "var\n" +
                "fc40=[{\"001\":\"01\",\"002\":\"00\",\"003\":\"9\",\"004\":\"4\",\"005\":\"0\",\"006\":\"0\",\"007\":\"7\",\"008\":\"7\",\"009\":\"20211201\",\"010\":\"十月廿七\",\"014\":\"06:34\",\"015\":\"16:51\",\"016\":\"星期三\",\"017\":\"\",\"018\":\"\",\"019\":\"0.0\",\"020\":\"0.0\",\"011\":\"52\",\"012\":\"良\",\"013\":\"\",\"000\":\"202112011100\"},{\"001\":\"00\",\"002\":\"00\",\"003\":\"11\",\"004\":\"5\",\"005\":\"0\",\"006\":\"0\",\"007\":\"6\",\"008\":\"5\",\"009\":\"20211202\",\"010\":\"十月廿八\",\"014\":\"06:35\",\"015\":\"16:51\",\"016\":\"星期四\",\"017\":\"\",\"018\":\"\",\"019\":\"0.0\",\"020\":\"0.0\",\"011\":\"88\",\"012\":\"良\",\"013\":\"\"},{\"001\":\"00\",\"002\":\"01\",\"003\":\"14\",\"004\":\"5\",\"005\":\"0\",\"006\":\"0\",\"007\":\"6\",\"008\":\"5\",\"009\":\"20211203\",\"010\":\"十月廿九\",\"014\":\"06:36\",\"015\":\"16:51\",\"016\":\"星期五\",\"017\":\"\",\"018\":\"\",\"019\":\"0.0\",\"020\":\"0.0\",\"011\":\"65\",\"012\":\"良\",\"013\":\"\"},{\"001\":\"01\",\"002\":\"00\",\"003\":\"14\",\"004\":\"7\",\"005\":\"0\",\"006\":\"0\",\"007\":\"8\",\"008\":\"2\",\"009\":\"20211204\",\"010\":\"十一月初一\",\"014\":\"06:37\",\"015\":\"16:51\",\"016\":\"星期六\",\"017\":\"\",\"018\":\"\",\"019\":\"0.0\",\"020\":\"0.0\",\"011\":\"50\",\"012\":\"优\",\"013\":\"\"},{\"001\":\"00\",\"002\":\"00\",\"003\":\"16\",\"004\":\"8\",\"005\":\"0\",\"006\":\"0\",\"007\":\"2\",\"008\":\"2\",\"009\":\"20211205\",\"010\":\"十一月初二\",\"014\":\"06:38\",\"015\":\"16:51\",\"016\":\"星期日\",\"017\":\"\",\"018\":\"\",\"019\":\"0.0\",\"020\":\"0.0\",\"011\":\"77\",\"012\":\"良\",\"013\":\"\"},{\"001\":\"00\",\"002\":\"00\",\"003\":\"16\",\"004\":\"7\",\"005\":\"0\",\"006\":\"0\",\"007\":\"3\",\"008\":\"0\",\"009\":\"20211206\",\"010\":\"十一月初三\",\"014\":\"06:38\",\"015\":\"16:51\",\"016\":\"星期一\",\"017\":\"\",\"018\":\"\",\"019\":\"0.0\",\"020\":\"0.0\",\"011\":\"\",\"012\":\"\",\"013\":\"\"},{\"001\":\"02\",\"002\":\"02\",\"003\":\"15\",\"004\":\"11\",\"005\":\"0\",\"006\":\"0\",\"007\":\"0\",\"008\":\"0\",\"009\":\"20211207\",\"010\":\"十一月初四\",\"014\":\"06:39\",\"015\":\"16:51\",\"016\":\"星期二\",\"017\":\"\",\"018\":\"大雪\",\"019\":\"0.0\",\"020\":\"0.0\",\"011\":\"\",\"012\":\"\",\"013\":\"\"},{\"001\":\"00\",\"002\":\"00\",\"003\":\"16\",\"004\":\"10\",\"005\":\"0\",\"006\":\"0\",\"007\":\"8\",\"008\":\"8\",\"009\":\"20211208\",\"010\":\"十一月初五\",\"014\":\"06:40\",\"015\":\"16:51\",\"016\":\"星期三\",\"017\":\"\",\"018\":\"\",\"019\":\"0.0\",\"020\":\"0.0\",\"011\":\"\",\"012\":\"\",\"013\":\"\"},{\"001\":\"01\",\"002\":\"00\",\"003\":\"18\",\"004\":\"10\",\"005\":\"0\",\"006\":\"0\",\"007\":\"1\",\"008\":\"1\",\"009\":\"20211209\",\"010\":\"十一月初六\",\"014\":\"06:41\",\"015\":\"16:51\",\"016\":\"星期四\",\"017\":\"\",\"018\":\"\",\"019\":\"0.0\",\"020\":\"0.0\",\"011\":\"\",\"012\":\"\",\"013\":\"\"},{\"001\":\"00\",\"002\":\"01\",\"003\":\"17\",\"004\":\"12\",\"005\":\"0\",\"006\":\"0\",\"007\":\"2\",\"008\":\"2\",\"009\":\"20211210\",\"010\":\"十一月初七\",\"014\":\"06:41\",\"015\":\"16:51\",\"016\":\"星期五\",\"017\":\"\",\"018\":\"\",\"019\":\"0.0\",\"020\":\"0.0\",\"011\":\"\",\"012\":\"\",\"013\":\"\"},{\"001\":\"00\",\"002\":\"02\",\"003\":\"17\",\"004\":\"13\",\"005\":\"0\",\"006\":\"0\",\"007\":\"3\",\"008\":\"7\",\"009\":\"20211211\",\"010\":\"十一月初八\",\"014\":\"06:42\",\"015\":\"16:52\",\"016\":\"星期六\",\"017\":\"\",\"018\":\"\",\"019\":\"0.0\",\"020\":\"0.0\",\"011\":\"\",\"012\":\"\",\"013\":\"\"},{\"001\":\"02\",\"002\":\"02\",\"003\":\"16\",\"004\":\"10\",\"005\":\"0\",\"006\":\"0\",\"007\":\"8\",\"008\":\"8\",\"009\":\"20211212\",\"010\":\"十一月初九\",\"014\":\"06:43\",\"015\":\"16:52\",\"016\":\"星期日\",\"017\":\"\",\"018\":\"\",\"019\":\"0.0\",\"020\":\"0.0\",\"011\":\"\",\"012\":\"\",\"013\":\"\"},{\"001\":\"02\",\"002\":\"02\",\"003\":\"12\",\"004\":\"9\",\"005\":\"1\",\"006\":\"0\",\"007\":\"1\",\"008\":\"1\",\"009\":\"20211213\",\"010\":\"十一月初十\",\"014\":\"06:43\",\"015\":\"16:52\",\"016\":\"星期一\",\"017\":\"\",\"018\":\"\",\"019\":\"0.0\",\"020\":\"0.0\",\"011\":\"\",\"012\":\"\",\"013\":\"\"},{\"001\":\"02\",\"002\":\"301\",\"003\":\"12\",\"004\":\"9\",\"005\":\"0\",\"006\":\"0\",\"007\":\"3\",\"008\":\"7\",\"009\":\"20211214\",\"010\":\"十一月十一\",\"014\":\"06:44\",\"015\":\"16:52\",\"016\":\"星期二\",\"017\":\"\",\"018\":\"\",\"019\":\"0.0\",\"020\":\"0.6\",\"011\":\"\",\"012\":\"\",\"013\":\"\"},{\"001\":\"02\",\"002\":\"02\",\"003\":\"10\",\"004\":\"0\",\"005\":\"2\",\"006\":\"1\",\"007\":\"8\",\"008\":\"7\",\"009\":\"20211215\",\"010\":\"十一月十二\",\"014\":\"06:45\",\"015\":\"16:53\",\"016\":\"星期三\",\"017\":\"\",\"018\":\"\",\"019\":\"0.0\",\"020\":\"0.0\",\"011\":\"\",\"012\":\"\",\"013\":\"\"},{\"001\":\"01\",\"002\":\"01\",\"003\":\"11\",\"004\":\"3\",\"005\":\"\",\"006\":\"\",\"007\":\"\",\"008\":\"\",\"009\":\"20211216\",\"010\":\"十一月十三\",\"014\":\"\",\"015\":\"\",\"016\":\"星期四\",\"017\":\"\",\"018\":\"\",\"019\":\"0.00\",\"020\":\"\",\"011\":\"\",\"012\":\"\",\"013\":\"\"},{\"001\":\"01\",\"002\":\"01\",\"003\":\"11\",\"004\":\"3\",\"005\":\"\",\"006\":\"\",\"007\":\"\",\"008\":\"\",\"009\":\"20211217\",\"010\":\"十一月十四\",\"014\":\"\",\"015\":\"\",\"016\":\"星期五\",\"017\":\"\",\"018\":\"\",\"019\":\"0.00\",\"020\":\"\",\"011\":\"\",\"012\":\"\",\"013\":\"\"},{\"001\":\"01\",\"002\":\"01\",\"003\":\"11\",\"004\":\"3\",\"005\":\"\",\"006\":\"\",\"007\":\"\",\"008\":\"\",\"009\":\"20211218\",\"010\":\"十一月十五\",\"014\":\"\",\"015\":\"\",\"016\":\"星期六\",\"017\":\"\",\"018\":\"\",\"019\":\"0.00\",\"020\":\"\",\"011\":\"\",\"012\":\"\",\"013\":\"\"},{\"001\":\"01\",\"002\":\"01\",\"003\":\"12\",\"004\":\"4\",\"005\":\"\",\"006\":\"\",\"007\":\"\",\"008\":\"\",\"009\":\"20211219\",\"010\":\"十一月十六\",\"014\":\"\",\"015\":\"\",\"016\":\"星期日\",\"017\":\"\",\"018\":\"\",\"019\":\"0.00\",\"020\":\"\",\"011\":\"\",\"012\":\"\",\"013\":\"\"},{\"001\":\"01\",\"002\":\"01\",\"003\":\"12\",\"004\":\"4\",\"005\":\"\",\"006\":\"\",\"007\":\"\",\"008\":\"\",\"009\":\"20211220\",\"010\":\"十一月十七\",\"014\":\"\",\"015\":\"\",\"016\":\"星期一\",\"017\":\"\",\"018\":\"\",\"019\":\"0.00\",\"020\":\"\",\"011\":\"\",\"012\":\"\",\"013\":\"\"},{\"001\":\"01\",\"002\":\"01\",\"003\":\"11\",\"004\":\"4\",\"005\":\"\",\"006\":\"\",\"007\":\"\",\"008\":\"\",\"009\":\"20211221\",\"010\":\"十一月十八\",\"014\":\"\",\"015\":\"\",\"016\":\"星期二\",\"017\":\"\",\"018\":\"冬至\",\"019\":\"0.00\",\"020\":\"\",\"011\":\"\",\"012\":\"\",\"013\":\"\"},{\"001\":\"01\",\"002\":\"01\",\"003\":\"11\",\"004\":\"4\",\"005\":\"\",\"006\":\"\",\"007\":\"\",\"008\":\"\",\"009\":\"20211222\",\"010\":\"十一月十九\",\"014\":\"\",\"015\":\"\",\"016\":\"星期三\",\"017\":\"\",\"018\":\"\",\"019\":\"0.00\",\"020\":\"\",\"011\":\"\",\"012\":\"\",\"013\":\"\"},{\"001\":\"01\",\"002\":\"01\",\"003\":\"12\",\"004\":\"4\",\"005\":\"\",\"006\":\"\",\"007\":\"\",\"008\":\"\",\"009\":\"20211223\",\"010\":\"十一月二十\",\"014\":\"\",\"015\":\"\",\"016\":\"星期四\",\"017\":\"\",\"018\":\"\",\"019\":\"0.00\",\"020\":\"\",\"011\":\"\",\"012\":\"\",\"013\":\"\"},{\"001\":\"01\",\"002\":\"01\",\"003\":\"12\",\"004\":\"4\",\"005\":\"\",\"006\":\"\",\"007\":\"\",\"008\":\"\",\"009\":\"20211224\",\"010\":\"十一月廿一\",\"014\":\"\",\"015\":\"\",\"016\":\"星期五\",\"017\":\"\",\"018\":\"\",\"019\":\"0.00\",\"020\":\"\",\"011\":\"\",\"012\":\"\",\"013\":\"\"},{\"001\":\"01\",\"002\":\"01\",\"003\":\"12\",\"004\":\"4\",\"005\":\"\",\"006\":\"\",\"007\":\"\",\"008\":\"\",\"009\":\"20211225\",\"010\":\"十一月廿二\",\"014\":\"\",\"015\":\"\",\"016\":\"星期六\",\"017\":\"圣诞节\",\"018\":\"\",\"019\":\"0.00\",\"020\":\"\",\"011\":\"\",\"012\":\"\",\"013\":\"\"},{\"001\":\"01\",\"002\":\"01\",\"003\":\"11\",\"004\":\"3\",\"005\":\"\",\"006\":\"\",\"007\":\"\",\"008\":\"\",\"009\":\"20211226\",\"010\":\"十一月廿三\",\"014\":\"\",\"015\":\"\",\"016\":\"星期日\",\"017\":\"\",\"018\":\"\",\"019\":\"0.00\",\"020\":\"\",\"011\":\"\",\"012\":\"\",\"013\":\"\"},{\"001\":\"01\",\"002\":\"01\",\"003\":\"11\",\"004\":\"4\",\"005\":\"\",\"006\":\"\",\"007\":\"\",\"008\":\"\",\"009\":\"20211227\",\"010\":\"十一月廿四\",\"014\":\"\",\"015\":\"\",\"016\":\"星期一\",\"017\":\"\",\"018\":\"\",\"019\":\"0.00\",\"020\":\"\",\"011\":\"\",\"012\":\"\",\"013\":\"\"},{\"001\":\"01\",\"002\":\"01\",\"003\":\"11\",\"004\":\"4\",\"005\":\"\",\"006\":\"\",\"007\":\"\",\"008\":\"\",\"009\":\"20211228\",\"010\":\"十一月廿五\",\"014\":\"\",\"015\":\"\",\"016\":\"星期二\",\"017\":\"\",\"018\":\"\",\"019\":\"0.00\",\"020\":\"\",\"011\":\"\",\"012\":\"\",\"013\":\"\"},{\"001\":\"08\",\"002\":\"08\",\"003\":\"11\",\"004\":\"4\",\"005\":\"\",\"006\":\"\",\"007\":\"\",\"008\":\"\",\"009\":\"20211229\",\"010\":\"十一月廿六\",\"014\":\"\",\"015\":\"\",\"016\":\"星期三\",\"017\":\"\",\"018\":\"\",\"019\":\"24.47\",\"020\":\"\",\"011\":\"\",\"012\":\"\",\"013\":\"\"},{\"001\":\"01\",\"002\":\"01\",\"003\":\"10\",\"004\":\"4\",\"005\":\"\",\"006\":\"\",\"007\":\"\",\"008\":\"\",\"009\":\"20211230\",\"010\":\"十一月廿七\",\"014\":\"\",\"015\":\"\",\"016\":\"星期四\",\"017\":\"\",\"018\":\"\",\"019\":\"0.00\",\"020\":\"\",\"011\":\"\",\"012\":\"\",\"013\":\"\"},{\"001\":\"01\",\"002\":\"01\",\"003\":\"11\",\"004\":\"3\",\"005\":\"\",\"006\":\"\",\"007\":\"\",\"008\":\"\",\"009\":\"20211231\",\"010\":\"十一月廿八\",\"014\":\"\",\"015\":\"\",\"016\":\"星期五\",\"017\":\"\",\"018\":\"\",\"019\":\"0.00\",\"020\":\"\",\"011\":\"\",\"012\":\"\",\"013\":\"\"},{\"001\":\"01\",\"002\":\"01\",\"003\":\"10\",\"004\":\"3\",\"005\":\"\",\"006\":\"\",\"007\":\"\",\"008\":\"\",\"009\":\"20220101\",\"010\":\"十一月廿九\",\"014\":\"\",\"015\":\"\",\"016\":\"星期六\",\"017\":\"元旦\",\"018\":\"\",\"019\":\"0.00\",\"020\":\"\",\"011\":\"\",\"012\":\"\",\"013\":\"\"},{\"001\":\"01\",\"002\":\"01\",\"003\":\"11\",\"004\":\"4\",\"005\":\"\",\"006\":\"\",\"007\":\"\",\"008\":\"\",\"009\":\"20220102\",\"010\":\"十一月三十\",\"014\":\"\",\"015\":\"\",\"016\":\"星期日\",\"017\":\"\",\"018\":\"\",\"019\":\"0.00\",\"020\":\"\",\"011\":\"\",\"012\":\"\",\"013\":\"\"},{\"001\":\"01\",\"002\":\"01\",\"003\":\"10\",\"004\":\"3\",\"005\":\"\",\"006\":\"\",\"007\":\"\",\"008\":\"\",\"009\":\"20220103\",\"010\":\"腊月初一\",\"014\":\"\",\"015\":\"\",\"016\":\"星期一\",\"017\":\"\",\"018\":\"\",\"019\":\"0.00\",\"020\":\"\",\"011\":\"\",\"012\":\"\",\"013\":\"\"},{\"001\":\"01\",\"002\":\"01\",\"003\":\"10\",\"004\":\"3\",\"005\":\"\",\"006\":\"\",\"007\":\"\",\"008\":\"\",\"009\":\"20220104\",\"010\":\"腊月初二\",\"014\":\"\",\"015\":\"\",\"016\":\"星期二\",\"017\":\"\",\"018\":\"\",\"019\":\"0.00\",\"020\":\"\",\"011\":\"\",\"012\":\"\",\"013\":\"\"},{\"001\":\"08\",\"002\":\"08\",\"003\":\"10\",\"004\":\"4\",\"005\":\"\",\"006\":\"\",\"007\":\"\",\"008\":\"\",\"009\":\"20220105\",\"010\":\"腊月初三\",\"014\":\"\",\"015\":\"\",\"016\":\"星期三\",\"017\":\"\",\"018\":\"小寒\",\"019\":\"18.61\",\"020\":\"\",\"011\":\"\",\"012\":\"\",\"013\":\"\"},{\"001\":\"01\",\"002\":\"01\",\"003\":\"11\",\"004\":\"4\",\"005\":\"\",\"006\":\"\",\"007\":\"\",\"008\":\"\",\"009\":\"20220106\",\"010\":\"腊月初四\",\"014\":\"\",\"015\":\"\",\"016\":\"星期四\",\"017\":\"\",\"018\":\"\",\"019\":\"0.00\",\"020\":\"\",\"011\":\"\",\"012\":\"\",\"013\":\"\"},{\"001\":\"01\",\"002\":\"01\",\"003\":\"10\",\"004\":\"3\",\"005\":\"\",\"006\":\"\",\"007\":\"\",\"008\":\"\",\"009\":\"20220107\",\"010\":\"腊月初五\",\"014\":\"\",\"015\":\"\",\"016\":\"星期五\",\"017\":\"\",\"018\":\"\",\"019\":\"0.00\",\"020\":\"\",\"011\":\"\",\"012\":\"\",\"013\":\"\"},{\"001\":\"01\",\"002\":\"01\",\"003\":\"10\",\"004\":\"3\",\"005\":\"\",\"006\":\"\",\"007\":\"\",\"008\":\"\",\"009\":\"20220108\",\"010\":\"腊月初六\",\"014\":\"\",\"015\":\"\",\"016\":\"星期六\",\"017\":\"\",\"018\":\"\",\"019\":\"0.00\",\"020\":\"\",\"011\":\"\",\"012\":\"\",\"013\":\"\"},{\"001\":\"01\",\"002\":\"01\",\"003\":\"10\",\"004\":\"3\",\"005\":\"\",\"006\":\"\",\"007\":\"\",\"008\":\"\",\"009\":\"20220109\",\"010\":\"腊月初七\",\"014\":\"\",\"015\":\"\",\"016\":\"星期日\",\"017\":\"\",\"018\":\"\",\"019\":\"0.00\",\"020\":\"\",\"011\":\"\",\"012\":\"\",\"013\":\"\"}];var\n" +
                "index3d\n" +
                "={\"i0\":\"202112011100\",\"i\":[{\"i1\":\"ac\",\"i2\":\"空调开启指数\",\"i3\":\"\",\"i4\":\"较少开启\",\"i5\":\"您将感到很舒适，一般不需要开启空调。\",\"i6\":\"较少开启\",\"i7\":\"您将感到很舒适，一般不需要开启空调。\",\"i8\":\"较少开启\",\"i9\":\"您将感到很舒适，一般不需要开启空调。\",\"i10\":\"较少开启\",\"i11\":\"您将感到很舒适，一般不需要开启空调。\",\"i12\":\"较少开启\",\"i13\":\"您将感到很舒适，一般不需要开启空调。\",\"i14\":\"较少开启\",\"i15\":\"您将感到很舒适，一般不需要开启空调。\",\"i16\":\"较少开启\",\"i17\":\"您将感到很舒适，一般不需要开启空调。\"},{\"i1\":\"ag\",\"i2\":\"过敏指数\",\"i3\":\"\",\"i4\":\"极不易发\",\"i5\":\"天气条件极不易诱发过敏。\",\"i6\":\"极不易发\",\"i7\":\"天气条件极不易诱发过敏。\",\"i8\":\"极不易发\",\"i9\":\"天气条件极不易诱发过敏。\",\"i10\":\"极不易发\",\"i11\":\"天气条件极不易诱发过敏。\",\"i12\":\"极不易发\",\"i13\":\"天气条件极不易诱发过敏。\",\"i14\":\"极不易发\",\"i15\":\"天气条件极不易诱发过敏。\",\"i16\":\"极不易发\",\"i17\":\"天气条件极不易诱发过敏。\"},{\"i1\":\"cl\",\"i2\":\"晨练指数\",\"i3\":\"\",\"i4\":\"适宜\",\"i5\":\"天气不错，空气清新。\",\"i6\":\"适宜\",\"i7\":\"天气不错，空气清新。\",\"i8\":\"适宜\",\"i9\":\"天气不错，空气清新。\",\"i10\":\"适宜\",\"i11\":\"天气不错，空气清新。\",\"i12\":\"适宜\",\"i13\":\"天气不错，空气清新。\",\"i14\":\"适宜\",\"i15\":\"天气不错，空气清新。\",\"i16\":\"较适宜\",\"i17\":\"早晨气象条件较适宜晨练，但天气阴沉，风力稍大，请选择合适的地点晨练。\"},{\"i1\":\"co\",\"i2\":\"舒适度指数\",\"i3\":\"\",\"i4\":\"较不舒适\",\"i5\":\"白天天气晴好，但仍会使您感觉偏冷，不很舒适，请注意适时添加衣物，以防感冒。\",\"i6\":\"较不舒适\",\"i7\":\"白天天气晴好，但仍会使您感觉偏冷，不很舒适，请注意适时添加衣物，以防感冒。\",\"i8\":\"较不舒适\",\"i9\":\"白天天气晴好，但仍会使您感觉偏冷，不很舒适，请注意适时添加衣物，以防感冒。\",\"i10\":\"较不舒适\",\"i11\":\"白天天气晴好，但仍会使您感觉偏冷，不很舒适，请注意适时添加衣物，以防感冒。\",\"i12\":\"较舒适\",\"i13\":\"白天虽然天气晴好，但早晚会感觉偏凉，午后舒适、宜人。\",\"i14\":\"较舒适\",\"i15\":\"白天虽然天气晴好，但早晚会感觉偏凉，午后舒适、宜人。\",\"i16\":\"较不舒适\",\"i17\":\"白天天气阴沉，您会感觉偏冷，不很舒适，请注意添加衣物，以防感冒。\"},{\"i1\":\"ct\",\"i2\":\"穿衣指数\",\"i3\":\"\",\"i4\":\"较冷\",\"i5\":\"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。\",\"i6\":\"较冷\",\"i7\":\"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。\",\"i8\":\"较冷\",\"i9\":\"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。\",\"i10\":\"较冷\",\"i11\":\"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。\",\"i12\":\"较冷\",\"i13\":\"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。\",\"i14\":\"较冷\",\"i15\":\"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。\",\"i16\":\"较冷\",\"i17\":\"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。\"},{\"i1\":\"dy\",\"i2\":\"钓鱼指数\",\"i3\":\"\",\"i4\":\"较适宜\",\"i5\":\"较适合垂钓，但风力稍大，会对垂钓产生一定的影响。\",\"i6\":\"较适宜\",\"i7\":\"较适合垂钓，但风力稍大，会对垂钓产生一定的影响。\",\"i8\":\"较适宜\",\"i9\":\"较适合垂钓，但风力稍大，会对垂钓产生一定的影响。\",\"i10\":\"较适宜\",\"i11\":\"较适合垂钓，但风力稍大，会对垂钓产生一定的影响。\",\"i12\":\"较适宜\",\"i13\":\"较适合垂钓，但风力稍大，会对垂钓产生一定的影响。\",\"i14\":\"较适宜\",\"i15\":\"较适合垂钓，但风力稍大，会对垂钓产生一定的影响。\",\"i16\":\"不宜\",\"i17\":\"天气不好，有风，不适合垂钓。\"},{\"i1\":\"fs\",\"i2\":\"防晒指数\",\"i3\":\"\",\"i4\":\"较弱\",\"i5\":\"紫外线强度较弱，建议涂擦SPF在12-15之间，PA+的防晒护肤品。\",\"i6\":\"强\",\"i7\":\"属强紫外辐射天气，应加强防护，建议涂擦SPF在15-20之间，PA++的防晒护肤品。\",\"i8\":\"强\",\"i9\":\"属强紫外辐射天气，应加强防护，建议涂擦SPF在15-20之间，PA++的防晒护肤品。\",\"i10\":\"强\",\"i11\":\"属强紫外辐射天气，应加强防护，建议涂擦SPF在15-20之间，PA++的防晒护肤品。\",\"i12\":\"强\",\"i13\":\"属强紫外辐射天气，应加强防护，建议涂擦SPF在15-20之间，PA++的防晒护肤品。\",\"i14\":\"强\",\"i15\":\"属强紫外辐射天气，应加强防护，建议涂擦SPF在15-20之间，PA++的防晒护肤品。\",\"i16\":\"弱\",\"i17\":\"属弱紫外辐射天气，长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。\"},{\"i1\":\"gj\",\"i2\":\"逛街指数\",\"i3\":\"\",\"i4\":\"较适宜\",\"i5\":\"天气较好，较适宜逛街，不过出门前要记得穿暖和一点，千万别着凉了。\",\"i6\":\"适宜\",\"i7\":\"天气较好，在这种天气里去逛街，既可畅快地放松身心，又会有很多意外收获，真是无比惬意。\",\"i8\":\"适宜\",\"i9\":\"天气较好，在这种天气里去逛街，既可畅快地放松身心，又会有很多意外收获，真是无比惬意。\",\"i10\":\"适宜\",\"i11\":\"天气较好，在这种天气里去逛街，既可畅快地放松身心，又会有很多意外收获，真是无比惬意。\",\"i12\":\"适宜\",\"i13\":\"天气较好，在这种天气里去逛街，既可畅快地放松身心，又会有很多意外收获，真是无比惬意。\",\"i14\":\"适宜\",\"i15\":\"天气较好，在这种天气里去逛街，既可畅快地放松身心，又会有很多意外收获，真是无比惬意。\",\"i16\":\"适宜\",\"i17\":\"阴天，在这种天气里去逛街，省去了涂防晒霜，打遮阳伞的麻烦，既可放松身心，又会有很多意外收获。\"},{\"i1\":\"gl\",\"i2\":\"太阳镜指数\",\"i3\":\"\",\"i4\":\"必要\",\"i5\":\"白天太阳辐射较强，建议佩戴透射比1级且标注UV380-UV400的浅色太阳镜\",\"i6\":\"必要\",\"i7\":\"白天天气晴朗，太阳辐射较强，建议佩戴透射比1级且标注UV380-UV400的遮阳镜\",\"i8\":\"必要\",\"i9\":\"白天天气晴朗，太阳辐射较强，建议佩戴透射比1级且标注UV380-UV400的遮阳镜\",\"i10\":\"必要\",\"i11\":\"白天太阳辐射较强，建议佩戴透射比1级且标注UV380-UV400的浅色太阳镜\",\"i12\":\"必要\",\"i13\":\"白天天气晴朗，太阳辐射较强，建议佩戴透射比1级且标注UV380-UV400的遮阳镜\",\"i14\":\"必要\",\"i15\":\"白天天气晴朗，太阳辐射较强，建议佩戴透射比1级且标注UV380-UV400的遮阳镜\",\"i16\":\"必要\",\"i17\":\"白天天空阴沉，但太阳辐射较强，建议佩戴透射比1级且标注UV380-UV400的浅色太阳镜\"},{\"i1\":\"gm\",\"i2\":\"感冒指数\",\"i3\":\"\",\"i4\":\"少发\",\"i5\":\"各项气象条件适宜，无明显降温过程，发生感冒机率较低。\",\"i6\":\"少发\",\"i7\":\"各项气象条件适宜，无明显降温过程，发生感冒机率较低。\",\"i8\":\"少发\",\"i9\":\"各项气象条件适宜，无明显降温过程，发生感冒机率较低。\",\"i10\":\"少发\",\"i11\":\"各项气象条件适宜，无明显降温过程，发生感冒机率较低。\",\"i12\":\"少发\",\"i13\":\"各项气象条件适宜，无明显降温过程，发生感冒机率较低。\",\"i14\":\"少发\",\"i15\":\"各项气象条件适宜，无明显降温过程，发生感冒机率较低。\",\"i16\":\"少发\",\"i17\":\"各项气象条件适宜，无明显降温过程，发生感冒机率较低。\"},{\"i1\":\"gz\",\"i2\":\"干燥指数\",\"i3\":\"\",\"i4\":\"适宜\",\"i5\":\"天气条件较好，保持正常对皮肤的护理即可，可使用冬季滋润保湿型护肤品。\",\"i6\":\"适宜\",\"i7\":\"天气条件较好，保持正常对皮肤的护理即可，可使用冬季滋润保湿型护肤品。\",\"i8\":\"干燥\",\"i9\":\"气温偏高，空气干燥，皮肤水分流失较多，应预防可能出现的皮肤瘙痒等情况，涂抹高保湿补水的护肤品，不要穿紧身衣服。\",\"i10\":\"适宜\",\"i11\":\"天气条件较好，保持正常对皮肤的护理即可，可使用冬季滋润保湿型护肤品。\",\"i12\":\"适宜\",\"i13\":\"天气条件较好，保持正常对皮肤的护理即可，可使用冬季滋润保湿型护肤品。\",\"i14\":\"适宜\",\"i15\":\"天气条件较好，保持正常对皮肤的护理即可，可使用冬季滋润保湿型护肤品。\",\"i16\":\"适宜\",\"i17\":\"天气条件较好，保持正常对皮肤的护理即可，可使用冬季滋润保湿型护肤品。\"},{\"i1\":\"hc\",\"i2\":\"划船指数\",\"i3\":\"\",\"i4\":\"较适宜\",\"i5\":\"白天较适宜划船，但气温稍低，请注意加衣，小心着凉。\",\"i6\":\"较适宜\",\"i7\":\"白天较适宜划船，但气温稍低，请注意加衣，小心着凉。\",\"i8\":\"较适宜\",\"i9\":\"白天较适宜划船，但气温稍低，请注意加衣，小心着凉。\",\"i10\":\"较适宜\",\"i11\":\"白天较适宜划船，但气温稍低，请注意加衣，小心着凉。\",\"i12\":\"适宜\",\"i13\":\"白天天气较好，适宜划船或嬉玩各种水上运动。\",\"i14\":\"适宜\",\"i15\":\"白天天气较好，适宜划船或嬉玩各种水上运动。\",\"i16\":\"较适宜\",\"i17\":\"白天较适宜划船，但阴沉的天气可能会影响您的心情。\"},{\"i1\":\"jt\",\"i2\":\"交通指数\",\"i3\":\"\",\"i4\":\"良好\",\"i5\":\"天气较好，路面干燥，交通气象条件良好，车辆可以正常行驶。\",\"i6\":\"良好\",\"i7\":\"天气较好，路面干燥，交通气象条件良好，车辆可以正常行驶。\",\"i8\":\"良好\",\"i9\":\"天气较好，路面干燥，交通气象条件良好，车辆可以正常行驶。\",\"i10\":\"良好\",\"i11\":\"天气较好，路面干燥，交通气象条件良好，车辆可以正常行驶。\",\"i12\":\"良好\",\"i13\":\"天气较好，路面干燥，交通气象条件良好，车辆可以正常行驶。\",\"i14\":\"良好\",\"i15\":\"天气较好，路面干燥，交通气象条件良好，车辆可以正常行驶。\",\"i16\":\"良好\",\"i17\":\"阴天，路面干燥，交通气象条件良好，车辆可以正常行驶。\"},{\"i1\":\"lk\",\"i2\":\"路况指数\",\"i3\":\"\",\"i4\":\"干燥\",\"i5\":\"天气较好，路面比较干燥，路况较好。\",\"i6\":\"干燥\",\"i7\":\"天气较好，路面比较干燥，路况较好。\",\"i8\":\"干燥\",\"i9\":\"天气较好，路面比较干燥，路况较好。\",\"i10\":\"干燥\",\"i11\":\"天气较好，路面比较干燥，路况较好。\",\"i12\":\"干燥\",\"i13\":\"天气较好，路面比较干燥，路况较好。\",\"i14\":\"干燥\",\"i15\":\"天气较好，路面比较干燥，路况较好。\",\"i16\":\"干燥\",\"i17\":\"阴天，路面比较干燥，路况较好。\"},{\"i1\":\"ls\",\"i2\":\"晾晒指数\",\"i3\":\"\",\"i4\":\"基本适宜\",\"i5\":\"天气不错，午后温暖的阳光仍能满足你驱潮消霉杀菌的晾晒需求。\",\"i6\":\"基本适宜\",\"i7\":\"天气不错，午后温暖的阳光仍能满足你驱潮消霉杀菌的晾晒需求。\",\"i8\":\"基本适宜\",\"i9\":\"天气不错，午后温暖的阳光仍能满足你驱潮消霉杀菌的晾晒需求。\",\"i10\":\"基本适宜\",\"i11\":\"天气不错，午后温暖的阳光仍能满足你驱潮消霉杀菌的晾晒需求。\",\"i12\":\"适宜\",\"i13\":\"天气不错，适宜晾晒。赶紧把久未见阳光的衣物搬出来吸收一下太阳的味道吧！\",\"i14\":\"适宜\",\"i15\":\"天气不错，适宜晾晒。赶紧把久未见阳光的衣物搬出来吸收一下太阳的味道吧！\",\"i16\":\"不太适宜\",\"i17\":\"天气阴沉，不利于水分的迅速蒸发，不太适宜晾晒。若需要晾晒，请尽量选择通风的地点。\"},{\"i1\":\"mf\",\"i2\":\"美发指数\",\"i3\":\"\",\"i4\":\"适宜\",\"i5\":\"洗发后最好用毛巾把头发吸干，若用吹风机，吹风前记得用适合干性发质的护发素，吹风时保持10cm距离，风的温度不要太高。\",\"i6\":\"一般\",\"i7\":\"注意防晒，洗发不宜太勤，建议选用保湿防晒型洗发护发品。出门请戴上遮阳帽或打遮阳伞。\",\"i8\":\"一般\",\"i9\":\"注意防晒，洗发不宜太勤，建议选用保湿防晒型洗发护发品。出门请戴上遮阳帽或打遮阳伞。\",\"i10\":\"一般\",\"i11\":\"注意防晒，洗发不宜太勤，建议选用保湿防晒型洗发护发品。出门请戴上遮阳帽或打遮阳伞。\",\"i12\":\"一般\",\"i13\":\"出门前要在头发上涂上含防晒和滋润成分的护发品，或备好遮阳帽、遮阳伞，以减轻太阳对头发的直接照射。\",\"i14\":\"一般\",\"i15\":\"出门前要在头发上涂上含防晒和滋润成分的护发品，或备好遮阳帽、遮阳伞，以减轻太阳对头发的直接照射。\",\"i16\":\"极适宜\",\"i17\":\"温湿适宜，风力较小，这为您的头发创造一个健康、洁净的生长环境，加上您细心的呵护打理，您的秀发定能飘逸动人。\"},{\"i1\":\"nl\",\"i2\":\"夜生活指数\",\"i3\":\"\",\"i4\":\"较适宜\",\"i5\":\"天气较好，虽然有点风，但仍比较适宜夜生活。\",\"i6\":\"较适宜\",\"i7\":\"天气较好，虽然有点风，比较适宜夜生活。\",\"i8\":\"较适宜\",\"i9\":\"天气较好，虽然有点风，比较适宜夜生活。\",\"i10\":\"较适宜\",\"i11\":\"天气较好，虽然有点风，但仍比较适宜夜生活。\",\"i12\":\"较适宜\",\"i13\":\"天气较好，虽然有点风，比较适宜夜生活。\",\"i14\":\"较适宜\",\"i15\":\"天气较好，虽然有点风，比较适宜夜生活。\",\"i16\":\"较适宜\",\"i17\":\"虽然是阴天，有点风，但仍比较适宜夜生活。\"},{\"i1\":\"pj\",\"i2\":\"啤酒指数\",\"i3\":\"\",\"i4\":\"不适宜\",\"i5\":\"寒冷的天气可能会减弱啤酒对您的诱惑，可少量饮用常温啤酒。\",\"i6\":\"较不适宜\",\"i7\":\"您将会感到有些凉意，建议饮用常温啤酒，并少量饮用为好。\",\"i8\":\"较不适宜\",\"i9\":\"您将会感到有些凉意，建议饮用常温啤酒，并少量饮用为好。\",\"i10\":\"较不适宜\",\"i11\":\"您将会感到有些凉意，建议饮用常温啤酒，并少量饮用为好。\",\"i12\":\"较不适宜\",\"i13\":\"您将会感到有些凉意，建议饮用常温啤酒，并少量饮用为好。\",\"i14\":\"较不适宜\",\"i15\":\"您将会感到有些凉意，建议饮用常温啤酒，并少量饮用为好。\",\"i16\":\"较不适宜\",\"i17\":\"您将会感到有些凉意，建议饮用常温啤酒，并少量饮用为好。\"},{\"i1\":\"pk\",\"i2\":\"放风筝指数\",\"i3\":\"\",\"i4\":\"不宜\",\"i5\":\"天气寒冷，不适宜放风筝。\",\"i6\":\"适宜\",\"i7\":\"天气不错，这种天气去放风筝既可以舒展筋骨，又可放松身心，超然自逸于广袤的天地之间。\",\"i8\":\"适宜\",\"i9\":\"天气不错，这种天气去放风筝既可以舒展筋骨，又可放松身心，超然自逸于广袤的天地之间。\",\"i10\":\"较适宜\",\"i11\":\"温暖舒适，较适宜放风筝。\",\"i12\":\"适宜\",\"i13\":\"天气不错，这种天气去放风筝既可以舒展筋骨，又可放松身心，超然自逸于广袤的天地之间。\",\"i14\":\"适宜\",\"i15\":\"天气不错，这种天气去放风筝既可以舒展筋骨，又可放松身心，超然自逸于广袤的天地之间。\",\"i16\":\"不宜\",\"i17\":\"天气不好，不适宜放风筝。\"},{\"i1\":\"pl\",\"i2\":\"空气污染扩散条件指数\",\"i3\":\"\",\"i4\":\"中\",\"i5\":\"气象条件对空气污染物稀释、扩散和清除无明显影响。\",\"i6\":\"中\",\"i7\":\"气象条件对空气污染物稀释、扩散和清除无明显影响。\",\"i8\":\"中\",\"i9\":\"气象条件对空气污染物稀释、扩散和清除无明显影响。\",\"i10\":\"中\",\"i11\":\"气象条件对空气污染物稀释、扩散和清除无明显影响。\",\"i12\":\"中\",\"i13\":\"气象条件对空气污染物稀释、扩散和清除无明显影响。\",\"i14\":\"中\",\"i15\":\"气象条件对空气污染物稀释、扩散和清除无明显影响。\",\"i16\":\"较差\",\"i17\":\"气象条件较不利于空气污染物稀释、扩散和清除。\"},{\"i1\":\"pp\",\"i2\":\"化妆指数\",\"i3\":\"\",\"i4\":\"保湿\",\"i5\":\"皮肤易缺水，用润唇膏后再抹口红，用保湿型霜类化妆品。\",\"i6\":\"保湿防晒\",\"i7\":\"用SPF15以上防晒霜打底，使用滋润型化妆品。\",\"i8\":\"保湿防晒\",\"i9\":\"用SPF15以上防晒霜打底，使用滋润型化妆品。\",\"i10\":\"保湿防晒\",\"i11\":\"用SPF15以上防晒霜打底，使用滋润型化妆品。\",\"i12\":\"防晒\",\"i13\":\"温湿适宜，但最好使用SPF15以上防晒霜打底，建议使用中性保湿型化妆品。\",\"i14\":\"防晒\",\"i15\":\"温湿适宜，但最好使用SPF15以上防晒霜打底，建议使用中性保湿型化妆品。\",\"i16\":\"保湿\",\"i17\":\"风力不大，建议用中性保湿型霜类化妆品，无需选用防晒化妆品。\"},{\"i1\":\"tr\",\"i2\":\"旅游指数\",\"i3\":\"\",\"i4\":\"适宜\",\"i5\":\"天气较好，同时又有微风伴您一路同行。虽会让人感觉有点凉，但仍适宜旅游，可不要错过机会呦！\",\"i6\":\"适宜\",\"i7\":\"天气较好，温度适宜，是个好天气哦。这样的天气适宜旅游，您可以尽情地享受大自然的风光。\",\"i8\":\"适宜\",\"i9\":\"天气较好，温度适宜，是个好天气哦。这样的天气适宜旅游，您可以尽情地享受大自然的风光。\",\"i10\":\"适宜\",\"i11\":\"天气较好，但丝毫不会影响您出行的心情。温度适宜又有微风相伴，适宜旅游。\",\"i12\":\"适宜\",\"i13\":\"天气较好，温度适宜，是个好天气哦。这样的天气适宜旅游，您可以尽情地享受大自然的风光。\",\"i14\":\"适宜\",\"i15\":\"天气较好，温度适宜，是个好天气哦。这样的天气适宜旅游，您可以尽情地享受大自然的风光。\",\"i16\":\"适宜\",\"i17\":\"天气较好，温度适宜，总体来说还是好天气哦，这样的天气适宜旅游，您可以尽情地享受大自然的风光。\"},{\"i1\":\"uv\",\"i2\":\"紫外线强度指数\",\"i3\":\"\",\"i4\":\"弱\",\"i5\":\"紫外线强度较弱，建议涂擦SPF在12-15之间、PA+的防晒护肤品。\",\"i6\":\"强\",\"i7\":\"紫外线辐射强，建议涂擦SPF20左右、PA++的防晒护肤品。避免在10点至14点暴露于日光下。\",\"i8\":\"强\",\"i9\":\"紫外线辐射强，建议涂擦SPF20左右、PA++的防晒护肤品。避免在10点至14点暴露于日光下。\",\"i10\":\"强\",\"i11\":\"紫外线辐射强，建议涂擦SPF20左右、PA++的防晒护肤品。避免在10点至14点暴露于日光下。\",\"i12\":\"强\",\"i13\":\"紫外线辐射强，建议涂擦SPF20左右、PA++的防晒护肤品。避免在10点至14点暴露于日光下。\",\"i14\":\"强\",\"i15\":\"紫外线辐射强，建议涂擦SPF20左右、PA++的防晒护肤品。避免在10点至14点暴露于日光下。\",\"i16\":\"最弱\",\"i17\":\"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。\"},{\"i1\":\"wc\",\"i2\":\"风寒指数\",\"i3\":\"\",\"i4\":\"冷\",\"i5\":\"感觉有点冷，室外活动要穿厚实一点，年老体弱者要适当注意保暖。\",\"i6\":\"凉\",\"i7\":\"感觉有点凉，室外活动注意适当增减衣物。\",\"i8\":\"无\",\"i9\":\"温度未达到风寒所需的低温，稍作防寒准备即可。\",\"i10\":\"无\",\"i11\":\"温度未达到风寒所需的低温，稍作防寒准备即可。\",\"i12\":\"无\",\"i13\":\"温度未达到风寒所需的低温，稍作防寒准备即可。\",\"i14\":\"无\",\"i15\":\"温度未达到风寒所需的低温，稍作防寒准备即可。\",\"i16\":\"无\",\"i17\":\"温度未达到风寒所需的低温，稍作防寒准备即可。\"},{\"i1\":\"xc\",\"i2\":\"洗车指数\",\"i3\":\"\",\"i4\":\"适宜\",\"i5\":\"适宜洗车，未来持续两天无雨天气较好，适合擦洗汽车，蓝天白云、风和日丽将伴您的车子连日洁净。\",\"i6\":\"适宜\",\"i7\":\"适宜洗车，未来持续两天无雨天气较好，适合擦洗汽车，蓝天白云、风和日丽将伴您的车子连日洁净。\",\"i8\":\"适宜\",\"i9\":\"适宜洗车，未来持续两天无雨天气较好，适合擦洗汽车，蓝天白云、风和日丽将伴您的车子连日洁净。\",\"i10\":\"适宜\",\"i11\":\"适宜洗车，未来持续两天无雨天气较好，适合擦洗汽车，蓝天白云、风和日丽将伴您的车子连日洁净。\",\"i12\":\"适宜\",\"i13\":\"适宜洗车，未来持续两天无雨天气较好，适合擦洗汽车，蓝天白云、风和日丽将伴您的车子连日洁净。\",\"i14\":\"适宜\",\"i15\":\"适宜洗车，未来持续两天无雨天气较好，适合擦洗汽车，蓝天白云、风和日丽将伴您的车子连日洁净。\",\"i16\":\"适宜\",\"i17\":\"适宜洗车，未来持续两天无雨天气较好，适合擦洗汽车，蓝天白云、风和日丽将伴您的车子连日洁净。\"},{\"i1\":\"xq\",\"i2\":\"心情指数\",\"i3\":\"\",\"i4\":\"较差\",\"i5\":\"天气较好，气温较低，会让人觉得有些压抑，不妨与朋友同事沟通交流下，舒缓下心情。\",\"i6\":\"好\",\"i7\":\"天气较好，空气温润，和风飘飘，美好的天气会带来一天接踵而来的好心情。\",\"i8\":\"好\",\"i9\":\"天气较好，空气温润，和风飘飘，美好的天气会带来一天接踵而来的好心情。\",\"i10\":\"较好\",\"i11\":\"天气较好，温度适宜，心情会不错，学习、工作效率较高。\",\"i12\":\"好\",\"i13\":\"天气较好，空气温润，和风飘飘，美好的天气会带来一天接踵而来的好心情。\",\"i14\":\"好\",\"i15\":\"天气较好，空气温润，和风飘飘，美好的天气会带来一天接踵而来的好心情。\",\"i16\":\"较差\",\"i17\":\"天气阴沉，会感觉莫名的压抑，情绪低落，此时将所有的悲喜都静静地沉到心底，在喧嚣的尘世里，感受片刻恬淡的宁静。\"},{\"i1\":\"yd\",\"i2\":\"运动指数\",\"i3\":\"\",\"i4\":\"较适宜\",\"i5\":\"天气较好，无雨水困扰，较适宜进行各种运动，但因气温较低，在户外运动请注意增减衣物。\",\"i6\":\"较适宜\",\"i7\":\"天气较好，无雨水困扰，较适宜进行各种运动，但因气温较低，在户外运动请注意增减衣物。\",\"i8\":\"较适宜\",\"i9\":\"天气较好，无雨水困扰，较适宜进行各种运动，但因气温较低，在户外运动请注意增减衣物。\",\"i10\":\"较适宜\",\"i11\":\"天气较好，无雨水困扰，较适宜进行各种运动，但因气温较低，在户外运动请注意增减衣物。\",\"i12\":\"较适宜\",\"i13\":\"天气较好，无雨水困扰，较适宜进行各种运动，但因天气凉，在户外运动请注意增减衣物。\",\"i14\":\"较适宜\",\"i15\":\"天气较好，无雨水困扰，较适宜进行各种运动，但因天气凉，在户外运动请注意增减衣物。\",\"i16\":\"较适宜\",\"i17\":\"天气较好，无雨水困扰，较适宜进行各种运动，但因天气凉，在户外运动请注意增减衣物。\"},{\"i1\":\"yh\",\"i2\":\"约会指数\",\"i3\":\"\",\"i4\":\"较适宜\",\"i5\":\"虽然有点风，不用担心天气来调皮捣乱而影响了兴致。\",\"i6\":\"较适宜\",\"i7\":\"虽然有点风，不用担心天气来调皮捣乱而影响了兴致。\",\"i8\":\"较适宜\",\"i9\":\"虽然有点风，不用担心天气来调皮捣乱而影响了兴致。\",\"i10\":\"较适宜\",\"i11\":\"虽然有点风，不用担心天气来调皮捣乱而影响了兴致。\",\"i12\":\"较适宜\",\"i13\":\"虽然有点风，不用担心天气来调皮捣乱而影响了兴致。\",\"i14\":\"较适宜\",\"i15\":\"虽然有点风，不用担心天气来调皮捣乱而影响了兴致。\",\"i16\":\"较适宜\",\"i17\":\"虽然天空有些阴沉，不用担心天气来调皮捣乱而影响了兴致。\"},{\"i1\":\"ys\",\"i2\":\"雨伞指数\",\"i3\":\"\",\"i4\":\"不带伞\",\"i5\":\"天气较好，不会降水，因此您可放心出门，无须带雨伞。\",\"i6\":\"不带伞\",\"i7\":\"天气较好，您在出门的时候无须带雨伞。\",\"i8\":\"不带伞\",\"i9\":\"天气较好，您在出门的时候无须带雨伞。\",\"i10\":\"不带伞\",\"i11\":\"天气较好，不会降水，因此您可放心出门，无须带雨伞。\",\"i12\":\"不带伞\",\"i13\":\"天气较好，您在出门的时候无须带雨伞。\",\"i14\":\"不带伞\",\"i15\":\"天气较好，您在出门的时候无须带雨伞。\",\"i16\":\"不带伞\",\"i17\":\"阴天，但降水概率很低，因此您在出门的时候无须带雨伞。\"},{\"i1\":\"zs\",\"i2\":\"中暑指数\",\"i3\":\"\",\"i4\":\"无中暑风险\",\"i5\":\"天气舒适，令人神清气爽的一天，不用担心中暑的困扰。\",\"i6\":\"无中暑风险\",\"i7\":\"天气舒适，令人神清气爽的一天，不用担心中暑的困扰。\",\"i8\":\"无中暑风险\",\"i9\":\"天气舒适，令人神清气爽的一天，不用担心中暑的困扰。\",\"i10\":\"无中暑风险\",\"i11\":\"天气舒适，对易中暑人群来说非常友善。\",\"i12\":\"无中暑风险\",\"i13\":\"天气舒适，令人神清气爽的一天，不用担心中暑的困扰。\",\"i14\":\"无中暑风险\",\"i15\":\"天气舒适，对易中暑人群来说非常友善。\",\"i16\":\"无中暑风险\",\"i17\":\"天气舒适，对易中暑人群来说非常友善。\"},{\"i1\":\"rw\",\"i2\":\"减肥气象指数\",\"i3\":\"\",\"i4\":\"三颗星\",\"i5\":\"天气较舒适，减肥正当时。\",\"i6\":\"三颗星\",\"i7\":\"天气较舒适，减肥正当时。\",\"i8\":\"三颗星\",\"i9\":\"天气较舒适，减肥正当时。\",\"i10\":\"三颗星\",\"i11\":\"天气较舒适，减肥正当时。\",\"i12\":\"三颗星\",\"i13\":\"天气较舒适，减肥正当时。\",\"i14\":\"三颗星\",\"i15\":\"天气较舒适，减肥正当时。\",\"i16\":\"三颗星\",\"i17\":\"天气较舒适，减肥正当时。\"},{\"i1\":\"bl\",\"i2\":\"健臻·血糖指数\",\"i3\":\"\",\"i4\":\"较易波动\",\"i5\":\"血糖较易波动，注意监测。\",\"i6\":\"不易波动\",\"i7\":\"天气条件好，血糖不易波动，可适时进行户外锻炼。\",\"i8\":\"不易波动\",\"i9\":\"天气条件好，血糖不易波动，可适时进行户外锻炼。\",\"i10\":\"不易波动\",\"i11\":\"天气条件好，血糖不易波动，可适时进行户外锻炼。\",\"i12\":\"不易波动\",\"i13\":\"天气条件好，血糖不易波动，可适时进行户外锻炼。\",\"i14\":\"不易波动\",\"i15\":\"天气条件好，血糖不易波动，可适时进行户外锻炼。\",\"i16\":\"不易波动\",\"i17\":\"天气条件好，血糖不易波动，可适时进行户外锻炼。\"}]};var\n" +
                "fc1h_24\n" +
                "={\"jh\":[{\"ja\":\"01\",\"jb\":\"9\",\"jc\":\"0\",\"jd\":\"7\",\"je\":\"47\",\"jf\":\"202112011400\",\"jg\":\"0\"},{\"ja\":\"01\",\"jb\":\"9\",\"jc\":\"0\",\"jd\":\"7\",\"je\":\"49\",\"jf\":\"202112011500\",\"jg\":\"0\"},{\"ja\":\"01\",\"jb\":\"8\",\"jc\":\"0\",\"jd\":\"7\",\"je\":\"50\",\"jf\":\"202112011600\",\"jg\":\"0\"},{\"ja\":\"01\",\"jb\":\"8\",\"jc\":\"0\",\"jd\":\"7\",\"je\":\"52\",\"jf\":\"202112011700\",\"jg\":\"0\"},{\"ja\":\"01\",\"jb\":\"7\",\"jc\":\"0\",\"jd\":\"7\",\"je\":\"53\",\"jf\":\"202112011800\",\"jg\":\"0\"},{\"ja\":\"01\",\"jb\":\"7\",\"jc\":\"0\",\"jd\":\"7\",\"je\":\"55\",\"jf\":\"202112011900\",\"jg\":\"0\"},{\"ja\":\"01\",\"jb\":\"6\",\"jc\":\"0\",\"jd\":\"7\",\"je\":\"56\",\"jf\":\"202112012000\",\"jg\":\"0\"},{\"ja\":\"01\",\"jb\":\"6\",\"jc\":\"0\",\"jd\":\"7\",\"je\":\"58\",\"jf\":\"202112012100\",\"jg\":\"0\"},{\"ja\":\"00\",\"jb\":\"6\",\"jc\":\"0\",\"jd\":\"7\",\"je\":\"60\",\"jf\":\"202112012200\",\"jg\":\"0\"},{\"ja\":\"00\",\"jb\":\"5\",\"jc\":\"0\",\"jd\":\"7\",\"je\":\"62\",\"jf\":\"202112012300\",\"jg\":\"0\"},{\"ja\":\"00\",\"jb\":\"5\",\"jc\":\"0\",\"jd\":\"7\",\"je\":\"64\",\"jf\":\"202112020000\",\"jg\":\"0\"},{\"ja\":\"00\",\"jb\":\"5\",\"jc\":\"0\",\"jd\":\"7\",\"je\":\"67\",\"jf\":\"202112020100\",\"jg\":\"0\"},{\"ja\":\"00\",\"jb\":\"5\",\"jc\":\"0\",\"jd\":\"7\",\"je\":\"69\",\"jf\":\"202112020200\",\"jg\":\"0\"},{\"ja\":\"00\",\"jb\":\"5\",\"jc\":\"0\",\"jd\":\"7\",\"je\":\"68\",\"jf\":\"202112020300\",\"jg\":\"0\"},{\"ja\":\"00\",\"jb\":\"4\",\"jc\":\"0\",\"jd\":\"7\",\"je\":\"68\",\"jf\":\"202112020400\",\"jg\":\"0\"},{\"ja\":\"00\",\"jb\":\"4\",\"jc\":\"0\",\"jd\":\"7\",\"je\":\"67\",\"jf\":\"202112020500\",\"jg\":\"0\"},{\"ja\":\"00\",\"jb\":\"4\",\"jc\":\"0\",\"jd\":\"7\",\"je\":\"66\",\"jf\":\"202112020600\",\"jg\":\"0\"},{\"ja\":\"00\",\"jb\":\"4\",\"jc\":\"0\",\"jd\":\"7\",\"je\":\"66\",\"jf\":\"202112020700\",\"jg\":\"0\"},{\"ja\":\"00\",\"jb\":\"4\",\"jc\":\"0\",\"jd\":\"7\",\"je\":\"65\",\"jf\":\"202112020800\",\"jg\":\"0\"},{\"ja\":\"00\",\"jb\":\"6\",\"jc\":\"0\",\"jd\":\"6\",\"je\":\"53\",\"jf\":\"202112020900\",\"jg\":\"0\"},{\"ja\":\"00\",\"jb\":\"7\",\"jc\":\"0\",\"jd\":\"6\",\"je\":\"42\",\"jf\":\"202112021000\",\"jg\":\"0\"},{\"ja\":\"00\",\"jb\":\"9\",\"jc\":\"0\",\"jd\":\"6\",\"je\":\"30\",\"jf\":\"202112021100\",\"jg\":\"0\"},{\"ja\":\"00\",\"jb\":\"10\",\"jc\":\"0\",\"jd\":\"6\",\"je\":\"33\",\"jf\":\"202112021200\",\"jg\":\"0\"},{\"ja\":\"00\",\"jb\":\"10\",\"jc\":\"0\",\"jd\":\"6\",\"je\":\"36\",\"jf\":\"202112021300\",\"jg\":\"0\"},{\"ja\":\"00\",\"jb\":\"11\",\"jc\":\"0\",\"jd\":\"6\",\"je\":\"39\",\"jf\":\"202112021400\",\"jg\":\"0\"},{\"ja\":\"00\",\"jb\":\"10\",\"jc\":\"0\",\"jd\":\"6\",\"je\":\"40\",\"jf\":\"202112021500\",\"jg\":\"0\"},{\"ja\":\"00\",\"jb\":\"10\",\"jc\":\"0\",\"jd\":\"6\",\"je\":\"41\",\"jf\":\"202112021600\",\"jg\":\"0\"},{\"ja\":\"00\",\"jb\":\"10\",\"jc\":\"0\",\"jd\":\"6\",\"je\":\"42\",\"jf\":\"202112021700\",\"jg\":\"0\"},{\"ja\":\"00\",\"jb\":\"9\",\"jc\":\"0\",\"jd\":\"6\",\"je\":\"45\",\"jf\":\"202112021800\",\"jg\":\"0\"},{\"ja\":\"00\",\"jb\":\"8\",\"jc\":\"0\",\"jd\":\"5\",\"je\":\"48\",\"jf\":\"202112021900\",\"jg\":\"0\"},{\"ja\":\"00\",\"jb\":\"7\",\"jc\":\"0\",\"jd\":\"5\",\"je\":\"51\",\"jf\":\"202112022000\",\"jg\":\"0\"},{\"ja\":\"00\",\"jb\":\"7\",\"jc\":\"0\",\"jd\":\"5\",\"je\":\"52\",\"jf\":\"202112022100\",\"jg\":\"0\"},{\"ja\":\"00\",\"jb\":\"7\",\"jc\":\"0\",\"jd\":\"6\",\"je\":\"53\",\"jf\":\"202112022200\",\"jg\":\"0\"},{\"ja\":\"00\",\"jb\":\"7\",\"jc\":\"0\",\"jd\":\"6\",\"je\":\"55\",\"jf\":\"202112022300\",\"jg\":\"0\"},{\"ja\":\"00\",\"jb\":\"6\",\"jc\":\"0\",\"jd\":\"6\",\"je\":\"59\",\"jf\":\"202112030000\",\"jg\":\"0\"},{\"ja\":\"00\",\"jb\":\"6\",\"jc\":\"0\",\"jd\":\"6\",\"je\":\"63\",\"jf\":\"202112030100\",\"jg\":\"0\"},{\"ja\":\"00\",\"jb\":\"6\",\"jc\":\"0\",\"jd\":\"6\",\"je\":\"66\",\"jf\":\"202112030200\",\"jg\":\"0\"},{\"ja\":\"00\",\"jb\":\"6\",\"jc\":\"0\",\"jd\":\"6\",\"je\":\"67\",\"jf\":\"202112030300\",\"jg\":\"0\"},{\"ja\":\"00\",\"jb\":\"6\",\"jc\":\"0\",\"jd\":\"6\",\"je\":\"68\",\"jf\":\"202112030400\",\"jg\":\"0\"},{\"ja\":\"00\",\"jb\":\"6\",\"jc\":\"0\",\"jd\":\"6\",\"je\":\"68\",\"jf\":\"202112030500\",\"jg\":\"0\"},{\"ja\":\"00\",\"jb\":\"6\",\"jc\":\"0\",\"jd\":\"6\",\"je\":\"68\",\"jf\":\"202112030600\",\"jg\":\"0\"},{\"ja\":\"00\",\"jb\":\"5\",\"jc\":\"0\",\"jd\":\"6\",\"je\":\"68\",\"jf\":\"202112030700\",\"jg\":\"0\"},{\"ja\":\"00\",\"jb\":\"5\",\"jc\":\"0\",\"jd\":\"6\",\"je\":\"67\",\"jf\":\"202112030800\",\"jg\":\"0\"},{\"ja\":\"00\",\"jb\":\"7\",\"jc\":\"0\",\"jd\":\"6\",\"je\":\"53\",\"jf\":\"202112030900\",\"jg\":\"0\"},{\"ja\":\"00\",\"jb\":\"10\",\"jc\":\"0\",\"jd\":\"7\",\"je\":\"38\",\"jf\":\"202112031000\",\"jg\":\"0\"},{\"ja\":\"00\",\"jb\":\"12\",\"jc\":\"0\",\"jd\":\"7\",\"je\":\"24\",\"jf\":\"202112031100\",\"jg\":\"0\"},{\"ja\":\"00\",\"jb\":\"13\",\"jc\":\"0\",\"jd\":\"7\",\"je\":\"27\",\"jf\":\"202112031200\",\"jg\":\"0\"}]};var\n" +
                "cwx={\"i1\":\"适宜\"}";
        String _data = "{" + a.replace("var", "\"").replace(";", ",").replace("=", "\":") + "}";
        System.out.println(replaceBlank(_data));
    }

    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
}