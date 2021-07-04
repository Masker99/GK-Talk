# GK-Talk
大学校园BBS论坛

### 项目设想
面向本校的BBS校园论坛
* 用户交流平台，用户可以发布个人动态或文章
* 信息聚合平台，后台爬取微信公众号上的校园信息、各社团活动信息在网站进行发布（解决学生需关注众多公众号的麻烦）
* 校园内外商家平台，可以发布优惠信息
* 校园跳蚤市场平台，可以进行二手货物的信息发布

### 目前已实现功能：
* 用户注册登录、找回密码功能
> 要点：
> * 后台发送邀请码到用户注册绑定的邮箱，用户输入邀请码，后台判断是否有效
> * 后台对用户密码进行加密存储
> * 注册时，需要对邮箱、用户名进行唯一确认
> * 用户成功登录后，后台返回Token，前端进行保存，后续用户进行请求时，将此Token保存在Header里，后台获取后验证
* 用户动态、文章发布
> 要点：
> * 进行相关操作时，判断用户是否已经登录


### 项目中使用的技术：
* Spring Boot
* MyBatis
* JWT
* Thymeleaf
* ...

### 运行环境：
* JDK 1.8
* Maven
* MySQL
* ...

### 运行效果：
登陆界面：
![image](https://user-images.githubusercontent.com/49432332/124371811-028fbe00-dcb8-11eb-9925-2db21c95d28b.png)
注册界面：
![image](https://user-images.githubusercontent.com/49432332/124371826-2e12a880-dcb8-11eb-8fa9-fba1e2a93889.png)
找回密码：
![image](https://user-images.githubusercontent.com/49432332/124371830-3e2a8800-dcb8-11eb-882e-0593c9dba48c.png)
首页：
![image](https://user-images.githubusercontent.com/49432332/124371838-500c2b00-dcb8-11eb-8904-30106aa1ec01.png)
文章发布：
![image](https://user-images.githubusercontent.com/49432332/124371864-88136e00-dcb8-11eb-9e81-94d2db1fee2c.png)


> 特别说明：该项目由于.properties文件中涉及私密信息，固没有把该文件push到远程仓库。
