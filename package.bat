@echo off
echo "欢迎使用一键打包命令:"
pause

mvn clean && mvn package -Dmaven.test.skip=true -Pdeploy