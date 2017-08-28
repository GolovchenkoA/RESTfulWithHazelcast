# RESTfulWithHazelcast
[![Build Status](https://travis-ci.org/GolovchenkoA/RESTfulWithHazelcast.svg?branch=master)](https://travis-ci.org/GolovchenkoA/RESTfulWithHazelcast)

## Описание
RESTful API с Hazelcast кэш сервером.


## Запуск программы
**вместо _[version]_ необходимо вписать текущую версию файла**
### Cоздание jar-файлов
перейдите в корневую директорию проекта и выполните команду:
*gradlew jar*

### Примеры запуска
**выполнять комманду необходимо из модуля 'root'. Директория: _root_project\root\build\libs_**

*java -Dweb=true -Dcache=true -jar root-all-_[version]_.jar*

[x] web server enabled
[x] cache server enabled
[x] default web-port [80]

*java -Dweb=true -Dwebport=5555 -jar root-all-_[version]_.jar*

[x] web server enabled
[ ] cache server enabled
[ ] default web-port [80]

*java -Dcache=true -jar root-all-_[version]_.jar*

[ ] web server enabled
[x] cache server enabled
[ ] default web-port [80]



## Примеры запуска модулей отдельно
## Модуль: web-api
**выполнять из директории _.\web-api\build\libs_**
*java -jar web-api-all-_[version]_.jar*
[x] default web-port [80]

*java -Dwebport=5555 -jar web-api-all-_[version]_.jar*
[ ] default web-port [80]


## Модуль: cache-server
**выполнять из директории _.\cache-server\build\libs_**
*java -jar cache-server-all-_[version]_.jar*
[x] cache server enabled