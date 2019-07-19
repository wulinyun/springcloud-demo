FROM java:8
#FROM 10.128.1.201/system_containers/java:8
VOLUME /tmp
ADD target/spring-cloud-demo.jar app.jar
RUN bash -c 'touch /app.jar'
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
#增加通过使用环境变量的方式对项目配置文件运行时参数进行修改，从而达到一份镜像多份部署的作用
ENV JAVA_OPTS=""
#注册中心环境变量
ENV eureka_host=spring-cloud-discovery
#调用链跟踪
ENV TRACE_HOST=spring-cloud-tracing
#mysql数据库
ENV mysql_host=spring-cloud-mysql
#rabbitmq
ENV rabbitmq_host=spring-cloud-rabbitmq
ENV rabbitmq_port=5672

#下面增加的是tc的APM要求CMD java $JAVA_OPTS -Dpinpoint.agentId=${POD_IP} -jar /app/app.jar
#-javaagent:/tenxcloud/pinpoint-agent/pinpoint-bootstrap-1.7.3.jar -Dpinpoint.applicationName=spring-boot-demo-jar -Dpinpoint.agentId=${POD_IP}
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Dpinpoint.agentId=${POD_IP} -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
#ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
