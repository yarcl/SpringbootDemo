@echo off
echo "��ӭʹ��һ���������:"
pause

mvn clean && mvn package -Dmaven.test.skip=true -Pdeploy