package xb.dev.document.model;

import xb.dev.document.annotation.Head;


public class XuZhouLogisticModel {
    private Integer id;
    @Head(name = "起运港")
    private String startPort;
    @Head(name = "港区")
    private String portArea;
    @Head(name = "目的港")
    private String endPort;
    @Head(name = "目的港挂靠")
    private String endPortStop;
    @Head(name = "船公司")
    private String boatCompany;
    @Head(name = "开航日")
    private String startDateNumber;
    @Head(name = "航程")
    private String totalDays;
    @Head(name = "20GP")
    private String GP20;
    @Head(name = "40GP")
    private String GP40;
    @Head(name = "40HQ")
    private String HQ40;
    @Head(name = "中转/直达")
    private String directOrTransfer;
    @Head(name = "中转港")
    private String transferPort;
    @Head(name = "生效日期")
    private String inDate;
    @Head(name = "失效日期")
    private String expireDate;
    @Head(name = "限重说明")
    private String weightLimitDesc;
    @Head(name = "外部备注")
    private String outRemark;
    @Head(name = "内部备注")
    private String inRemark;
    @Head(name = "截关日")
    private String closingDate;
    @Head(name = "舱位情况")
    private String spaceDesc;
    @Head(name = "共舱方")
    private String shipSharing;
    @Head(name = "提单类型")
    private String billType;
    @Head(name = "航线")
    private String shipRoute;
    @Head(name = "航线代码")
    private String shipRouteCode;
    @Head(name = "特价")
    private String specialPrice;
    @Head(name = "是否电询")
    private String isPhoneAsk;
    @Head(name = "航线状态")
    private String shipRouteStatus;
}
