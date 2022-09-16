mvn clean install  -Dmaven.test.skip=true
mvn deploy -Dmaven.test.skip=true -DaltDeploymentRepository=rdc-releases::default::https://packages.aliyun.com/maven/repository/2203039-release-8iKGPh/
mvn deploy -Dmaven.test.skip=true -DaltDeploymentRepository=rdc-snapshots::default::https://packages.aliyun.com/maven/repository/2203039-snapshot-w6FApp/
