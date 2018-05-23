start servers\jboss-fuse-6.3.0.redhat-187\bin\fuse.bat
start servers\GlassFish_Server\glassfish\bin\startserv.bat
docker pull memoodm/services:service_1_rest
docker pull memoodm/services:service_2_soap
docker pull memoodm/services:apiSelector
docker run -d -p 8081:8080 memoodm/services:service_1_rest
docker run -d -p 8082:8080 memoodm/services:service_2_soap
docker run -d -p 8888:8080 memoodm/services:Routing