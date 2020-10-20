FROM tomcat:9.0.39-jdk11-openjdk-slim
COPY ./target/JspJpaGestor.war /usr/local/tomcat/webapps/Gestor.war
ENV gestor_jdbc_db_server=localhost gestor_jdbc_db_name=gestorDatabase gestor_jdbc_db_user=root gestor_jdbc_db_password=adelfofagia gestor_jdbc_db_port=3306