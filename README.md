# sso
spring shiro cas sigle sign on

## sso-server 打包命令
clean package -Dmaven.test.skip=true -X -Dcheckstyle.skip=true -Dlicense.skip=true

## sso-server对cas-server的修改
###deployerConfigContext.xml
  * 添加了数据库校验和 dataSource
  
###cas-servlet.xml 
  * 添加了验证码校验
###cas.properties
  * server.name=http://localhost:8080
  * host.name=sso.server
###修改的页面样式
  * casLoginView.jsp
  * login-webflow.xml
