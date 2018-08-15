package xb.dev.document.model;

import com.zhongfei.data.document.annotation.Document;
import com.zhongfei.data.document.annotation.Head;
import com.zhongfei.data.document.common.DocumentType;

import java.math.BigDecimal;
import java.util.Date;

@Document(documentType = DocumentType.EXCEL,dateFormat = "yyyyMMdd hh:mm:ss")
public class BankStatementEntity {
    @Head(name = "摘要")
    private String digest;
    @Head(name = "付款人开户行名")
    private String payAccountBank;
    @Head(name = "付款人账号")
    private String payAccount;
    @Head(name = "付款人名称")
    private String payName;
    @Head(name = "交易时间")
    private Date transactionTime;
    @Head(name = "交易金额")
    private BigDecimal transactionAmount;
    @Head(name = "用途")
    private String useFor;
    @Head(name = "交易附言")
    private String transactionNote;
    @Head(name = "备注")
    private String remark;
    @Head(name = "交易货币")
    private String transactionCurrency;

}
