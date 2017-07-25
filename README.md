# Vhall Web SDK for JAVA
[![Software License](https://img.shields.io/badge/license-MIT-brightgreen.svg)](LICENSE)

## 安装
* 下载项目目录下 libs/vhall_api_java_sdk-1.0.jar 文件，引入项目，加载为Libary
* 项目添加了jsonPath包，可以直接按path取值
* 项目开始前，请确保已经申请了app_key 和 secret_key 否则得到一个身份验证失败的异常
* 参数文件统一为HashMap<String, Object> 结构

## 使用方法

### 创建活动 / 上传封面 / 获取活动详情 (http://e.vhall.com/home/vhallapi/active)

```java
HashMap<String, Object> configMap = new HashMap<String, Object>();

// app_key secret_key 请像技服人员申请
configMap.put("app_key", "xxxxxxxxxx");
configMap.put("secret_key", "xxxxxxxxxx");

// API请求域名，如无特殊情况，使用下面配置即可
configMap.put("request_domain", "http://e.vhall.com");

// 按API前缀实例化对象
Webinar webinarObj = new Webinar(configMap);

// 创建活动参数初始化
HashMap<String, Object> paramCreate = new HashMap<String, Object>();

// 上传封面参数初始化
HashMap<String, Object> paramActiveImage = new HashMap<String, Object>();

 // 获取活动详情参数初始化
HashMap<String, Object> paramFetch = new HashMap<String, Object>();

// 时间固定格式为10位长度时间戳
paramCreate.put("start_time", webinarObj.toString(new Date().getTime()).substring(0,10));

// 设定活动主题
paramCreate.put("subject", "take a test again");

try {
	// 获取创建活动返回值，返回值统一使用String类型接收
	String resultCreate = webinarObj.create(paramCreate);

	// 请使用绝对路径获取文件
	paramActiveImage.put("image", new File("").getAbsolutePath() + "/resources/vhall.png");
	paramActiveImage.put("webinar_id", JsonPath.read(resultCreate, "$.data"));

	String resultActiveImage = webinarObj.activeimage(paramActiveImage);

	// 新创建的活动ID直接放入请求参数中
	paramFetch.put("webinar_id", JsonPath.read(resultCreate, "$.data"));
	paramFetch.put("fields", "subject, start_time");

	String resultFetch = webinarObj.fetch(paramFetch);

	// 使用jsonPath 直接获取返回值
	webinarObj.dump(JsonPath.read(resultCreate, "$.data"));
	webinarObj.dump(JsonPath.read(resultActiveImage, "$.data"));
	webinarObj.dump(JsonPath.read(resultFetch, "$.data"));
} catch (Exception e) {
	e.printStackTrace();
}
```


## 常见问题

- 文件不存在，请确保上传的封面文件路径是否正确
- SDK的使用 demo 可以参考 (https://github.com/vhall/web_sdk_java/src/TestSdk)。
- 该SDK请和微吼API配合使用，具体使用参数请参考(https://e.vhall.com/home/vhallapi/)。


## 联系我们

- 如果需要帮助，请提交工单（直接向 yan.gao@vhall.com 发送邮件）
- 更详细的文档，见[官方文档站](http://e.vhall.com/home/vhallapi)
- 如果发现了bug， 欢迎提交 [issue](https://github.com/vhall/web_sdk_java/issues)
- 如果有功能需求，欢迎提交 [issue](https://github.com/vhall/web_sdk_java/issues)

## 代码许可

The MIT License (MIT).详情见 [License文件](https://github.com/vhall/web_sdk_java/blob/master/LICENSE).

[install-packagist]: https://packagist.org/packages/vhall/web_sdk_java
