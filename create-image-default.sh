IMAGE_NAME=baz.kameleoon

pushd .
.mvn spring-boot:build-image -DskipTests=true spring-boot:build-image.imageNAme = $IMAGE_NAME

docker image ls | grep $IMAGE_NAME