package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;
/**
 * @ProjectName: p2pNow
 * @Package: com.alipay.config
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/12/25 16:07
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public class AlipayConfig {

    //↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2021000116680686";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCwQyX7zmbxKiVh745O15PiqzrbwSYedxh1GDRS3xiR5gGRVaY0gnGxrsgUDy1a5eAheLIq3vt5VTrwUC+s4RwfXC0WtEzO5wlUKH52kjrMpCDVu5nStVkymnw5lEFPQ9+p3JINrQk1a5Bc9AMxB4Jhdde+YDw0h/hqqcPpqD+NizoE4MjpgjTE+GcIC9/BkGq2GisyUcZX11qi96lMlMk5bsvK5NJuDnPZRHjJMFgS3omU9lskZOBc8uisphL5XwdSdMC6a0opNCEfnfRsiAH93lctmyJgkqZQb60onS53g1JFdsXz2fv+Ys+wEdQeqNgeQZzOIPqQUkqS06M0HVV3AgMBAAECggEAIY6XI6p3BMDuXwfZgv8d45CP4xiRpkksOdbC12gr6FLux3mT6/lNpfNNmiwNiXmsQxMUcH8FR2NHlkrd2L7+uKW229S1rspjh0cyVGaglSdGLQrLTUdDVRP+RIQKLwoQAenBUKM51pbE5D18ghAY7W8fBNqTe7jLPZtWdhyXBN5Eh/g+Xq8VIofWA6wwdl2GtYQjpSvfXVHGw/nJ/rYMcaDIBGL4xz0HtGQCfGaPUnl0cCBwvzBkWm9jbLm4uCycf2ct8lZRMDHQksl8IoubxNUCU6h6M/ApMYMImYxnKTQQiEYoTc1XnZlRIZhbDwuFFDY3Es8uQyE7FJMqTbzLkQKBgQDqKGiEWfvTlUw0Bk2CysDOJGMcIS5k8eFxwBG8myDYQwpn7iOg3D3i2ZK41cBvzmU1tVOdkEnG9eHxAHjLtftw5B9PaPzDsDA75xtXA6M5qRqmZROCGWvOYmn2E2P/oSb6kNcH3aF9PTFhzjsS1EnUk/kBj3DyoWyiT9yG7IoYWQKBgQDAtDf1TVNLTT1E40qEY0ngF532W3Fgwh+ZnF4D/bdrxJIHYqdbv/FLG1Es3wqjKbkuQKqpuZbE5L4pI3bOC+b4MqI7wZOMjlVGCBuOSZv/55gRCfcFEriuU5SeeF123Nw288i0Yfq/p00SejbYxm4cwxIa/OcJoTovpEajx+QiTwKBgQCRxIH8EoBgYQrFQD80cr/AFIrWUhmtoZ40z9q6zN+PgXyS4n6vfyvPcp6SDg70C0qaOr1I0y/6ecsj2qVYO3vB9WFAcB0aPS5pvB8a9hrApHMArt+MGAMywbUxCTUxR8MaLNyP+vqzGksJlfbUa+9iNJPI0uD/Slp0WL7yB4KYUQKBgBWz9l1tMQVZFJ5ujtTsLcTMRCEb2WARXOe7AaenaoH7qIqmZLzjmDRiRjksgZEwwMdhxxbIbR+Nt5EAin/YPVIUucrLGWgKG7aAcUIMLkKa/bhli+mJWz1e8m0Kl1QsuayCtkcChfAR0o9TLlwptPsOSTq64vZlrxzwfngxqhLnAoGBAKjMwIqYajlaHwGFEBgXH0+Uutvp1amiNELGf8Zfb6MIh3tzNPYGSorYpYBUCLNNIV8ATLdKk78WraIam/8wnCOf08HzFMSeEtUk0ZYqLd+1dcgn4tKkHibXRfP00FEIU9IZl+oSzQLY5cgLULN2bty5jcsTomZ6Irqu6A+Mbnz7";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。

    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqt4fPMmawTdMJPeJ43hfUf8grMDJW8km9k43QTrMf1I/JUXWa11OIB35Ud4F1W34iu2pjork6Ryp8pi8v9hbaURRcysW1EwJU8sc+dZPwQae/RFwckI6oKSSvpASvCk5WrAjstki3Vk0kfh6Aq9pc1HH0nTOaB8AxTJ1DpzQqIO58R0l/c8mkWBU6EbFYRVki2uWqY46H7oCpvEZ8xmogY2jqam3X1CjGlvqEjGcqdWjvL1m+L1P0O+voAkfJ7lkynqNR/7y1wyJrTrt+KxjeTptt2jAnpewEPviSQrtsGwIOO3pEzBgYHoRUWdPn9ihE/is4ObC243C0q3y82/9zwIDAQAB";


    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {

        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
