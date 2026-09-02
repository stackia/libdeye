package io.fogcloud.sdk.fog.api.http;

import com.stub.StubApp;
import io.fogcloud.sdk.fog.bean.AdvertiseInfoBean;
import io.fogcloud.sdk.fog.bean.BannerItemBean;
import io.fogcloud.sdk.fog.bean.BindPhoneReqBean;
import io.fogcloud.sdk.fog.bean.ChannelsBean;
import io.fogcloud.sdk.fog.bean.CheckEmailResult;
import io.fogcloud.sdk.fog.bean.CheckEmailVerCodeResult;
import io.fogcloud.sdk.fog.bean.CheckVerCodeResult;
import io.fogcloud.sdk.fog.bean.DeviceListBean;
import io.fogcloud.sdk.fog.bean.FindItemBean;
import io.fogcloud.sdk.fog.bean.FindPageBean;
import io.fogcloud.sdk.fog.bean.GlobalConfigBean;
import io.fogcloud.sdk.fog.bean.LocationWeather;
import io.fogcloud.sdk.fog.bean.LoginResult;
import io.fogcloud.sdk.fog.bean.MessageBean;
import io.fogcloud.sdk.fog.bean.MessageResultBean;
import io.fogcloud.sdk.fog.bean.PartBean;
import io.fogcloud.sdk.fog.bean.PropertyWrapperBean;
import io.fogcloud.sdk.fog.bean.SchedulerBean;
import io.fogcloud.sdk.fog.bean.SchedulerGroupBean;
import io.fogcloud.sdk.fog.bean.SchedulerHumBean;
import io.fogcloud.sdk.fog.bean.SchedulerV2Bean;
import io.fogcloud.sdk.fog.bean.ShareUserResult;
import io.fogcloud.sdk.fog.bean.SimpleResultBean;
import io.fogcloud.sdk.fog.bean.VercodeResult;
import io.fogcloud.sdk.fog.bean.VerificationResultBean;
import io.fogcloud.sdk.fog.bean.WechatReqBean;
import io.reactivex.rxjava3.core.Flowable;
import java.util.List;
import kotlin.Metadata;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/* compiled from: RetrofitService.kt */
@Metadata(d1 = {"\u0000æ\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J2\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\u00072\b\b\u0001\u0010\t\u001a\u00020\u0007H'J\u001e\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00040\u00032\b\b\u0001\u0010\f\u001a\u00020\rH'J\u001e\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00040\u00032\b\b\u0001\u0010\f\u001a\u00020\u0010H'J\u0014\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00040\u0003H'J\u001e\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00040\u00032\b\b\u0001\u0010\f\u001a\u00020\u0010H'J\u001e\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00040\u00032\b\b\u0001\u0010\u0015\u001a\u00020\u0016H'J\u001e\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00040\u00032\b\b\u0001\u0010\f\u001a\u00020\u0010H'J2\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00040\u00032\b\b\u0001\u0010\u0019\u001a\u00020\u00072\b\b\u0001\u0010\t\u001a\u00020\u00072\b\b\u0001\u0010\u001a\u001a\u00020\u0007H'J\u001e\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u00040\u00032\b\b\u0001\u0010\f\u001a\u00020\u0010H'J\u001e\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u00040\u00032\b\b\u0001\u0010\u001f\u001a\u00020 H'J2\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0\u00040\u00032\b\b\u0001\u0010#\u001a\u00020\u00072\b\b\u0001\u0010\t\u001a\u00020\u00072\b\b\u0001\u0010$\u001a\u00020\u0007H'J(\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00040\u00032\b\b\u0001\u0010\u0019\u001a\u00020\u00072\b\b\u0001\u0010\t\u001a\u00020\u0007H'J(\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00040\u00032\b\b\u0001\u0010'\u001a\u00020\u00072\b\b\u0001\u0010(\u001a\u00020\u0007H'J\u001e\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00040\u00032\b\b\u0001\u0010\f\u001a\u00020\u0010H'J2\u0010*\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00040\u00032\b\b\u0001\u0010+\u001a\u00020\u00072\b\b\u0001\u0010,\u001a\u00020 2\b\b\u0001\u0010-\u001a\u00020\u001eH'J\u001e\u0010.\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00040\u00032\b\b\u0001\u0010/\u001a\u00020\u0007H'J\u001e\u00100\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00040\u00032\b\b\u0001\u00101\u001a\u00020\u0007H'J6\u00102\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\n\b\u0003\u0010\u0019\u001a\u0004\u0018\u00010\u00072\n\b\u0003\u00103\u001a\u0004\u0018\u00010\u0007H'J\u0014\u00104\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002050\u00040\u0003H'J\u001a\u00106\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u000208070\u00040\u0003H'J\u001a\u00109\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020:070\u00040\u0003H'J.\u0010;\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020<070\u00040\u00032\b\b\u0001\u0010=\u001a\u00020\u00072\b\b\u0003\u0010+\u001a\u00020\u0007H'J\u001e\u0010>\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020?0\u00040\u00032\b\b\u0001\u0010\f\u001a\u00020\u0010H'JC\u0010@\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020A0\u00040\u00032\n\b\u0001\u0010B\u001a\u0004\u0018\u00010 2\b\b\u0001\u0010C\u001a\u00020\u00072\b\b\u0001\u0010D\u001a\u00020 2\b\b\u0003\u0010E\u001a\u00020 H'¢\u0006\u0002\u0010FJ\u0014\u0010G\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020H0\u00040\u0003H'J\u001e\u0010I\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020J0\u00040\u00032\b\b\u0001\u0010\b\u001a\u00020\u0007H'J*\u0010K\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020L0\u00040\u00032\b\b\u0001\u0010E\u001a\u00020 2\n\b\u0001\u0010M\u001a\u0004\u0018\u00010\u0007H'J$\u0010N\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u000208070\u00040\u00032\b\b\u0003\u0010O\u001a\u00020 H'J&\u0010P\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020Q070\u00040\u00032\n\b\u0003\u0010R\u001a\u0004\u0018\u00010\u0007H'J&\u0010S\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020Q070\u00040\u00032\n\b\u0003\u0010R\u001a\u0004\u0018\u00010\u0007H'J.\u0010T\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020U070\u00040\u00032\b\b\u0001\u0010+\u001a\u00020\u00072\b\b\u0001\u0010V\u001a\u00020\u0007H'J(\u0010W\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020X0\u00040\u00032\b\b\u0001\u0010+\u001a\u00020\u00072\b\b\u0001\u0010=\u001a\u00020\u0007H'J$\u0010Y\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020Z070\u00040\u00032\b\b\u0001\u00101\u001a\u00020\u0007H'J$\u0010[\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f070\u00040\u00032\b\b\u0001\u00101\u001a\u00020\u0007H'J$\u0010\\\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020]070\u00040\u00032\b\b\u0001\u00101\u001a\u00020\u0007H'J(\u0010^\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020_0\u00040\u00032\b\b\u0001\u00101\u001a\u00020\u00072\b\b\u0001\u0010`\u001a\u00020\u0007H'J\u0014\u0010a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00040\u0003H'J\u0014\u0010b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020 0\u00040\u0003H'J(\u0010c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020?0\u00040\u00032\b\b\u0001\u0010$\u001a\u00020\u00072\b\b\u0001\u0010d\u001a\u00020\u0007H'JF\u0010e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020?0\u00040\u00032\b\b\u0001\u0010$\u001a\u00020\u00072\b\b\u0001\u0010\u0019\u001a\u00020\u00072\b\b\u0001\u0010f\u001a\u00020\u00072\b\b\u0001\u0010g\u001a\u00020\u00072\b\b\u0003\u0010h\u001a\u00020\u0007H'J(\u0010i\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020j0\u00040\u00032\b\b\u0001\u0010k\u001a\u00020\u00072\b\b\u0001\u0010l\u001a\u00020\u0007H'J\u001e\u0010m\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00040\u00032\b\b\u0001\u0010\f\u001a\u00020\u0010H'J\u001e\u0010n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00040\u00032\b\b\u0001\u0010\f\u001a\u00020\u0010H'J<\u0010o\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00040\u00032\b\b\u0001\u0010\t\u001a\u00020\u00072\b\b\u0001\u0010\u0019\u001a\u00020\u00072\b\b\u0001\u0010$\u001a\u00020\u00072\b\b\u0001\u0010p\u001a\u00020\u0007H'J\u001e\u0010q\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00040\u00032\b\b\u0001\u0010\f\u001a\u00020\u0010H'J\u001e\u0010r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00040\u00032\b\b\u0001\u0010\f\u001a\u00020\u0010H'JF\u0010s\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00040\u00032\b\b\u0001\u0010'\u001a\u00020\u00072\b\b\u0001\u0010t\u001a\u00020\u00072\b\b\u0001\u0010$\u001a\u00020\u00072\b\b\u0001\u0010p\u001a\u00020\u00072\b\b\u0003\u0010u\u001a\u00020\u0007H'J\u001e\u0010v\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00040\u00032\b\b\u0001\u0010\u0015\u001a\u00020wH'J\u001e\u0010x\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00040\u00032\b\b\u0001\u0010y\u001a\u00020\u0007H'J<\u0010z\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00040\u00032\b\b\u0001\u0010$\u001a\u00020\u00072\b\b\u0001\u0010{\u001a\u00020\u00072\b\b\u0001\u0010|\u001a\u00020\u00072\b\b\u0001\u0010p\u001a\u00020\u0007H'J\u001e\u0010}\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00040\u00032\b\b\u0001\u0010\f\u001a\u00020\u0010H'J\u001e\u0010~\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020?0\u00040\u00032\b\b\u0001\u0010\f\u001a\u00020\u0010H'J>\u0010\u007f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00040\u00032\b\b\u0001\u0010+\u001a\u00020\u00072\t\b\u0001\u0010\u0080\u0001\u001a\u00020 2\b\b\u0001\u0010V\u001a\u00020\u00072\t\b\u0003\u0010\u0081\u0001\u001a\u00020\u0007H'J+\u0010\u0082\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020?0\u00040\u00032\t\b\u0001\u0010\u0083\u0001\u001a\u00020\u00072\t\b\u0001\u0010\u0084\u0001\u001a\u00020\u0007H'J\u0015\u0010\u0085\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00040\u0003H'J\u001f\u0010\u0086\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00040\u00032\b\b\u0001\u0010\b\u001a\u00020\u0007H'J*\u0010\u0087\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\t\b\u0001\u0010\u0088\u0001\u001a\u00020\u0007H'J\u001f\u0010\u0089\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00040\u00032\b\b\u0001\u0010\f\u001a\u00020\u0010H'J\u001f\u0010\u008a\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00040\u00032\b\b\u0001\u0010\f\u001a\u00020ZH'J*\u0010\u008b\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00040\u00032\t\b\u0001\u0010\u008c\u0001\u001a\u00020 2\b\b\u0001\u0010\f\u001a\u00020\u0010H'J?\u0010\u008d\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00040\u00032\b\b\u0001\u0010+\u001a\u00020\u00072\b\b\u0001\u0010V\u001a\u00020\u00072\t\b\u0001\u0010\u0080\u0001\u001a\u00020 2\t\b\u0001\u0010\u008e\u0001\u001a\u00020\u001eH'J \u0010\u008f\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020j0\u00040\u00032\t\b\u0001\u0010\u0090\u0001\u001a\u00020\u0007H'J \u0010\u0091\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00040\u00032\t\b\u0001\u0010\u0092\u0001\u001a\u00020\u0007H'J\u001f\u0010\u0093\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u00040\u00032\b\b\u0001\u0010\f\u001a\u00020\u0010H'J \u0010\u0094\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00040\u00032\t\b\u0001\u0010\u0095\u0001\u001a\u00020 H'J \u0010\u0096\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u0097\u00010\u00040\u00032\b\b\u0001\u0010\f\u001a\u00020\u0010H'J\u001f\u0010\u0098\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00040\u00032\b\b\u0001\u0010\f\u001a\u00020\u0010H'J)\u0010\u0099\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00040\u00032\b\b\u0001\u0010f\u001a\u00020\u00072\b\b\u0001\u0010g\u001a\u00020\u0007H'J\u0015\u0010\u009a\u0001\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00040\u0003H'¨\u0006\u009b\u0001"}, d2 = {"Lio/fogcloud/sdk/fog/api/http/RetrofitService;", "", "acceptShare", "Lio/reactivex/rxjava3/core/Flowable;", "Lio/fogcloud/sdk/fog/api/http/BaseResult;", "Lio/fogcloud/sdk/fog/bean/ShareUserResult;", "device_id", "", "message_id", "vercode", "addScheduleGroup", "Lio/fogcloud/sdk/fog/bean/SimpleResultBean;", "body", "Lio/fogcloud/sdk/fog/bean/SchedulerV2Bean;", "addScheduleHum", "Lio/fogcloud/sdk/fog/bean/SchedulerHumBean;", "Lokhttp3/RequestBody;", "auth", "bindEmailByToken", "Lio/fogcloud/sdk/fog/bean/LoginResult;", "bindPhoneNum", "req", "Lio/fogcloud/sdk/fog/bean/BindPhoneReqBean;", "changeEmail", "changePhoneNum", "phone_number", "old_phone_number", "checkEmailExists", "Lio/fogcloud/sdk/fog/bean/CheckEmailResult;", "checkNeedShowRate", "", "scene", "", "checkVerCode", "Lio/fogcloud/sdk/fog/bean/CheckVerCodeResult;", "num", "appid", "checkVerCodeForChangePhone", "cleanAccount", "phoneNum", "code", "cleanAccountByEmail", "confirmOptionalComponent", "deviceId", "productComponentId", "optionalComponentInstalled", "deleteScheduleGroup", "id", "deleteScheduleHum", "deviceid", "findShareUser", "email", "getAdImage", "Lio/fogcloud/sdk/fog/bean/AdvertiseInfoBean;", "getBannerList", "", "Lio/fogcloud/sdk/fog/bean/BannerItemBean;", "getChannels", "Lio/fogcloud/sdk/fog/bean/ChannelsBean;", "getDeviceList", "Lio/fogcloud/sdk/fog/bean/DeviceListBean;", "random", "getEmailVerCode", "Lio/fogcloud/sdk/fog/bean/VerificationResultBean;", "getFindItemList", "Lio/fogcloud/sdk/fog/bean/FindPageBean;", "channel_id", "keyword", "page_no", "page_size", "(Ljava/lang/Integer;Ljava/lang/String;II)Lio/reactivex/rxjava3/core/Flowable;", "getGlobalConfig", "Lio/fogcloud/sdk/fog/bean/GlobalConfigBean;", "getMessageInfo", "Lio/fogcloud/sdk/fog/bean/MessageBean;", "getMessageList", "Lio/fogcloud/sdk/fog/bean/MessageResultBean;", "next", "getMinePageBanner", "position", "getMyCollectList", "Lio/fogcloud/sdk/fog/bean/FindItemBean;", "startId", "getMyLikeList", "getPartDetail", "Lio/fogcloud/sdk/fog/bean/PartBean;", "productId", "getProperties", "Lio/fogcloud/sdk/fog/bean/PropertyWrapperBean;", "getScheduleGroupList", "Lio/fogcloud/sdk/fog/bean/SchedulerGroupBean;", "getScheduleHum", "getScheduleTask", "Lio/fogcloud/sdk/fog/bean/SchedulerBean;", "getShareCode", "Lio/fogcloud/sdk/fog/bean/VercodeResult;", "role", "getShareContent", "getUnReadMsgCount", "getVerCode", "loginname", "getWeChatVerCode", "open_id", "access_token", "platform", "getWeatherInfo", "Lio/fogcloud/sdk/fog/bean/LocationWeather;", "lon", "lat", "googleBind", "googleUnBind", "loginByCode", "extend", "loginByEmail", "loginByGoogle", "loginByPwd", "password", "pushtype", "loginByWeChat", "Lio/fogcloud/sdk/fog/bean/WechatReqBean;", "loginOut", "cid", "oneKeyLogin", "token", "appKey", "registerEmail", "resetEmailPassword", "resetPartTime", "componentId", "operation", "resetPassword", "password1", "password2", "setAllMsgRead", "setMsgRead", "shareInvite", "target_user_id", "submitFeedBack", "updateScheduleGroup", "updateScheduleHum", "trigger_id", "updateSubscribeState", "state", "updateUserBirthday", "birthday", "updateUserGender", "gender", "updateUserLocale", "uploadShowRateState", "event_id", "verifyEmailVerCode", "Lio/fogcloud/sdk/fog/bean/CheckEmailVerCodeResult;", "verifyPassword", "weChatBind", "weChatUnBind", "fog_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: /tmp/deye-apk/unidbg-dump/dex/classes7.dex */
public interface RetrofitService {
    @FormUrlEncoded
    @POST("enduser/share/accept/")
    Flowable<BaseResult<ShareUserResult>> acceptShare(@Field("deviceid") String device_id, @Field("message_id") String message_id, @Field("vercode") String vercode);

    @POST("schedule/time/")
    Flowable<BaseResult<SimpleResultBean>> addScheduleGroup(@Body SchedulerV2Bean body);

    @POST("enduser/schedule/humidity/")
    Flowable<BaseResult<SchedulerHumBean>> addScheduleHum(@Body RequestBody body);

    @GET("enduser/auth/")
    Flowable<BaseResult<String>> auth();

    @POST("enduser/intl/bind-email-by-token")
    Flowable<BaseResult<LoginResult>> bindEmailByToken(@Body RequestBody body);

    @POST("enduser/wechat/bind/")
    Flowable<BaseResult<LoginResult>> bindPhoneNum(@Body BindPhoneReqBean req);

    @POST("enduser/changeEmail")
    Flowable<BaseResult<SimpleResultBean>> changeEmail(@Body RequestBody body);

    @FormUrlEncoded
    @POST("enduser/phone/change/")
    Flowable<BaseResult<SimpleResultBean>> changePhoneNum(@Field("phone_number") String phone_number, @Field("vercode") String vercode, @Field("old_phone_number") String old_phone_number);

    @POST("enduser/checkEmail")
    Flowable<BaseResult<CheckEmailResult>> checkEmailExists(@Body RequestBody body);

    @GET("/v3/enduser/feedback/fiveStar")
    Flowable<BaseResult<Boolean>> checkNeedShowRate(@Query("scene") int scene);

    @FormUrlEncoded
    @POST("enduser/checkVerCode/")
    Flowable<BaseResult<CheckVerCodeResult>> checkVerCode(@Field("loginname") String num, @Field("vercode") String vercode, @Field("appid") String appid);

    @FormUrlEncoded
    @POST("enduser/phone/change/checkVercode/")
    Flowable<BaseResult<SimpleResultBean>> checkVerCodeForChangePhone(@Field("phone_number") String phone_number, @Field("vercode") String vercode);

    @FormUrlEncoded
    @POST("enduser/account/deactivate/")
    Flowable<BaseResult<SimpleResultBean>> cleanAccount(@Field("phone_number") String phoneNum, @Field("vercode") String code);

    @POST("enduser/account/deactivate/email")
    Flowable<BaseResult<SimpleResultBean>> cleanAccountByEmail(@Body RequestBody body);

    @FormUrlEncoded
    @POST("/v3/component/optional/confirm")
    Flowable<BaseResult<SimpleResultBean>> confirmOptionalComponent(@Field("device_id") String deviceId, @Field("product_component_id") int productComponentId, @Field("optional_component_installed") boolean optionalComponentInstalled);

    @DELETE("schedule/time/")
    Flowable<BaseResult<SimpleResultBean>> deleteScheduleGroup(@Query("group_id") String id);

    @DELETE("enduser/schedule/humidity/{device_id}/")
    Flowable<BaseResult<SchedulerHumBean>> deleteScheduleHum(@Path("device_id") String deviceid);

    @FormUrlEncoded
    @POST("enduser/share/target/find/")
    Flowable<BaseResult<ShareUserResult>> findShareUser(@Field("device_id") String device_id, @Field("phone_number") String phone_number, @Field("email") String email);

    @GET("app/getAdImage/")
    Flowable<BaseResult<AdvertiseInfoBean>> getAdImage();

    @GET("explore/banner")
    Flowable<BaseResult<List<BannerItemBean>>> getBannerList();

    @GET("explore/channels")
    Flowable<BaseResult<List<ChannelsBean>>> getChannels();

    @GET("enduser/deviceList/")
    Flowable<BaseResult<List<DeviceListBean>>> getDeviceList(@Query("random") String random, @Query("app") String deviceId);

    @POST("enduser/getEmailVerCode")
    Flowable<BaseResult<VerificationResultBean>> getEmailVerCode(@Body RequestBody body);

    @GET("explore/content")
    Flowable<BaseResult<FindPageBean>> getFindItemList(@Query("channel_id") Integer channel_id, @Query("keyword") String keyword, @Query("page_no") int page_no, @Query("page_size") int page_size);

    @GET("/v3/config")
    Flowable<BaseResult<GlobalConfigBean>> getGlobalConfig();

    @GET("message/center/detail/{message_id}/")
    Flowable<BaseResult<MessageBean>> getMessageInfo(@Path("message_id") String message_id);

    @GET("message/center/list/")
    Flowable<BaseResult<MessageResultBean>> getMessageList(@Query("page_size") int page_size, @Query("next") String next);

    @GET("explore/banner")
    Flowable<BaseResult<List<BannerItemBean>>> getMinePageBanner(@Query("position") int position);

    @GET("enduser/my/favorite")
    Flowable<BaseResult<List<FindItemBean>>> getMyCollectList(@Query("start_id") String startId);

    @GET("enduser/my/like")
    Flowable<BaseResult<List<FindItemBean>>> getMyLikeList(@Query("start_id") String startId);

    @GET("component/manage/list")
    Flowable<BaseResult<List<PartBean>>> getPartDetail(@Query("device_id") String deviceId, @Query("product_id") String productId);

    @GET("enduser/get/properties/")
    Flowable<BaseResult<PropertyWrapperBean>> getProperties(@Query("device_id") String deviceId, @Query("random") String random);

    @GET("schedule/time/")
    Flowable<BaseResult<List<SchedulerGroupBean>>> getScheduleGroupList(@Query("device_id") String deviceid);

    @GET("enduser/schedule/humidity/{device_id}/")
    Flowable<BaseResult<List<SchedulerHumBean>>> getScheduleHum(@Path("device_id") String deviceid);

    @GET("schedule/task/?request_type=0&task_type=0")
    Flowable<BaseResult<List<SchedulerBean>>> getScheduleTask(@Query("device_id") String deviceid);

    @FormUrlEncoded
    @POST("enduser/shareCode/")
    Flowable<BaseResult<VercodeResult>> getShareCode(@Field("deviceid") String deviceid, @Field("role") String role);

    @GET("share/content")
    Flowable<BaseResult<String>> getShareContent();

    @POST("message/center/count/unread/")
    Flowable<BaseResult<Integer>> getUnReadMsgCount();

    @FormUrlEncoded
    @POST("enduser/getVerCode/")
    Flowable<BaseResult<VerificationResultBean>> getVerCode(@Field("appid") String appid, @Field("loginname") String loginname);

    @FormUrlEncoded
    @POST("enduser/weChat/verCode/")
    Flowable<BaseResult<VerificationResultBean>> getWeChatVerCode(@Field("app_id") String appid, @Field("phone_number") String phone_number, @Field("open_id") String open_id, @Field("access_token") String access_token, @Field("platform") String platform);

    @GET("enduser/getNowWeather")
    Flowable<BaseResult<LocationWeather>> getWeatherInfo(@Query("lon") String lon, @Query("lat") String lat);

    @POST("enduser/intl/google/bind")
    Flowable<BaseResult<LoginResult>> googleBind(@Body RequestBody body);

    @POST("enduser/intl/google/unbind")
    Flowable<BaseResult<LoginResult>> googleUnBind(@Body RequestBody body);

    @FormUrlEncoded
    @POST("enduser/login/verCode/")
    Flowable<BaseResult<LoginResult>> loginByCode(@Field("vercode") String vercode, @Field("phone_number") String phone_number, @Field("appid") String appid, @Field("extend") String extend);

    @POST("enduser/login/email")
    Flowable<BaseResult<LoginResult>> loginByEmail(@Body RequestBody body);

    @POST("enduser/intl/google/login")
    Flowable<BaseResult<LoginResult>> loginByGoogle(@Body RequestBody body);

    @FormUrlEncoded
    @POST("enduser/login/")
    Flowable<BaseResult<LoginResult>> loginByPwd(@Field("loginname") String phoneNum, @Field("password") String password, @Field("appid") String appid, @Field("extend") String extend, @Field("pushtype") String pushtype);

    @POST("enduser/loginByWeChat/")
    Flowable<BaseResult<LoginResult>> loginByWeChat(@Body WechatReqBean req);

    @FormUrlEncoded
    @PUT("enduser/logout/")
    Flowable<BaseResult<SimpleResultBean>> loginOut(@Field("cid") String cid);

    @FormUrlEncoded
    @POST("enduser/oneClick/")
    Flowable<BaseResult<LoginResult>> oneKeyLogin(@Field("appid") String appid, @Field("token") String token, @Field("umeng_app_key") String appKey, @Field("extend") String extend);

    @POST("enduser/register/email")
    Flowable<BaseResult<LoginResult>> registerEmail(@Body RequestBody body);

    @POST("enduser/resetPassword/email")
    Flowable<BaseResult<VerificationResultBean>> resetEmailPassword(@Body RequestBody body);

    @FormUrlEncoded
    @POST("component/maintenance/reset")
    Flowable<BaseResult<SimpleResultBean>> resetPartTime(@Field("device_id") String deviceId, @Field("component_id") int componentId, @Field("product_id") String productId, @Field("operation") String operation);

    @FormUrlEncoded
    @POST("enduser/resetPassword/")
    Flowable<BaseResult<VerificationResultBean>> resetPassword(@Field("password1") String password1, @Field("password2") String password2);

    @POST("message/center/read/batch/")
    Flowable<BaseResult<SimpleResultBean>> setAllMsgRead();

    @FormUrlEncoded
    @POST("app/devMsgRead/")
    Flowable<BaseResult<SimpleResultBean>> setMsgRead(@Field("message_id") String message_id);

    @FormUrlEncoded
    @POST("enduser/share/invite/")
    Flowable<BaseResult<ShareUserResult>> shareInvite(@Field("deviceid") String device_id, @Field("target_user_id") String target_user_id);

    @POST("enduser/feedback/create/")
    Flowable<BaseResult<SimpleResultBean>> submitFeedBack(@Body RequestBody body);

    @PUT("schedule/time/")
    Flowable<BaseResult<SimpleResultBean>> updateScheduleGroup(@Body SchedulerGroupBean body);

    @PUT("enduser/schedule/humidity/{trigger_id}/")
    Flowable<BaseResult<SchedulerHumBean>> updateScheduleHum(@Path("trigger_id") int trigger_id, @Body RequestBody body);

    @FormUrlEncoded
    @POST("component/msg/subscribe")
    Flowable<BaseResult<SimpleResultBean>> updateSubscribeState(@Field("device_id") String deviceId, @Field("product_id") String productId, @Field("component_id") int componentId, @Field("subscribe_state") boolean state);

    @FormUrlEncoded
    @PUT("enduser/userInfo/")
    Flowable<BaseResult<LocationWeather>> updateUserBirthday(@Field("birthday") String birthday);

    @FormUrlEncoded
    @PUT("enduser/userInfo/")
    Flowable<BaseResult<LoginResult>> updateUserGender(@Field("gender") String gender);

    @POST("/v3/enduser/locale")
    Flowable<BaseResult<Object>> updateUserLocale(@Body RequestBody body);

    @FormUrlEncoded
    @POST("/v3/enduser/feedback/fiveStar")
    Flowable<BaseResult<String>> uploadShowRateState(@Field("event_id") int event_id);

    @POST("enduser/verifyEmailCode")
    Flowable<BaseResult<CheckEmailVerCodeResult>> verifyEmailVerCode(@Body RequestBody body);

    @POST("enduser/verifyPassword")
    Flowable<BaseResult<SimpleResultBean>> verifyPassword(@Body RequestBody body);

    @FormUrlEncoded
    @POST("enduser/bind/wechat/")
    Flowable<BaseResult<SimpleResultBean>> weChatBind(@Field("open_id") String open_id, @Field("access_token") String access_token);

    @POST("enduser/wechat/unbind/")
    Flowable<BaseResult<SimpleResultBean>> weChatUnBind();

    /* compiled from: RetrofitService.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ Flowable getWeChatVerCode$default(RetrofitService retrofitService, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException(StubApp.getString2(44785));
            }
            if ((i & 16) != 0) {
                str5 = StubApp.getString2(35491);
            }
            return retrofitService.getWeChatVerCode(str, str2, str3, str4, str5);
        }

        public static /* synthetic */ Flowable findShareUser$default(RetrofitService retrofitService, String str, String str2, String str3, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException(StubApp.getString2(44779));
            }
            if ((i & 2) != 0) {
                str2 = null;
            }
            if ((i & 4) != 0) {
                str3 = null;
            }
            return retrofitService.findShareUser(str, str2, str3);
        }

        public static /* synthetic */ Flowable loginByPwd$default(RetrofitService retrofitService, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException(StubApp.getString2(44786));
            }
            if ((i & 16) != 0) {
                str5 = StubApp.getString2(13695);
            }
            return retrofitService.loginByPwd(str, str2, str3, str4, str5);
        }

        public static /* synthetic */ Flowable getFindItemList$default(RetrofitService retrofitService, Integer num, String str, int i, int i2, int i3, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException(StubApp.getString2(44781));
            }
            if ((i3 & 8) != 0) {
                i2 = 10;
            }
            return retrofitService.getFindItemList(num, str, i, i2);
        }

        public static /* synthetic */ Flowable getMyCollectList$default(RetrofitService retrofitService, String str, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException(StubApp.getString2(44783));
            }
            if ((i & 1) != 0) {
                str = null;
            }
            return retrofitService.getMyCollectList(str);
        }

        public static /* synthetic */ Flowable getMyLikeList$default(RetrofitService retrofitService, String str, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException(StubApp.getString2(44784));
            }
            if ((i & 1) != 0) {
                str = null;
            }
            return retrofitService.getMyLikeList(str);
        }

        public static /* synthetic */ Flowable getMinePageBanner$default(RetrofitService retrofitService, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException(StubApp.getString2(44782));
            }
            if ((i2 & 1) != 0) {
                i = 1;
            }
            return retrofitService.getMinePageBanner(i);
        }

        public static /* synthetic */ Flowable getDeviceList$default(RetrofitService retrofitService, String str, String str2, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException(StubApp.getString2(44780));
            }
            if ((i & 2) != 0) {
                str2 = StubApp.getString2(244);
            }
            return retrofitService.getDeviceList(str, str2);
        }

        public static /* synthetic */ Flowable resetPartTime$default(RetrofitService retrofitService, String str, int i, String str2, String str3, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException(StubApp.getString2(44787));
            }
            if ((i2 & 8) != 0) {
                str3 = StubApp.getString2(2399);
            }
            return retrofitService.resetPartTime(str, i, str2, str3);
        }
    }
}
