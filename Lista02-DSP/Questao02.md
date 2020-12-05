```shell
$md5sum 2014_01-Traffic_Sensors_Fortaleza.csv
75c9e7b5dd6548ebf9d879937905931c  
2014_01-Traffic_Sensors_Fortaleza.csv

$sha1sum 2014_01-Traffic_Sensors_Fortaleza.csv 
aa0cca517cc4261cb28a8b0829e6e75e8ecd589d  
2014_01-Traffic_Sensors_Fortaleza.csv

$time zip 2014_Traffic_Sensors_Fortaleza.zip 2014_01-Traffic_Sensors_Fortaleza.csv 
  adding: 2014_01-Traffic_Sensors_Fortaleza.csv (deflated 65%)
zip 2014_Traffic_Sensors_Fortaleza.zip 2014_01-Traffic_Sensors_Fortaleza.csv  
17,26s user 0,26s system 99% cpu 17,527 total

$time gzip 2014_01-Traffic_Sensors_Fortaleza.csv 
gzip 2014_01-Traffic_Sensors_Fortaleza.csv  13,92s user 0,14s 
system 99% cpu 14,067 total

$time bzip2 2014_01-Traffic_Sensors_Fortaleza.csv 
bzip2 2014_01-Traffic_Sensors_Fortaleza.csv  24,84s user 0,24s 
system 99% cpu 25,125 total

$time p7zip 2014_01-Traffic_Sensors_Fortaleza.csv 
Archive size: 52805800 bytes (51 MiB)                                             
Everything is Ok
p7zip 2014_01-Traffic_Sensors_Fortaleza.csv  344,02s user 3,00s 
system 236% cpu 2:27,03 total


$time rar u 2014_traffic.rar 2014_01-Traffic_Sensors_Fortaleza.csv
Adding    2014_01-Traffic_Sensors_Fortaleza.csv                       OK 
Done
rar u 2014_traffic.rar 2014_01-Traffic_Sensors_Fortaleza.csv  131,73s 
user 0,96s system 318% cpu 41,708 total
```
Nome|Tamanho Inicial(MB)| Tamanho Compactado (MB) | Tipo de Compactação | Tempo (s)
----|-------------------|-------------------------|---------------------|-----------
2014_01-Traffic_Sensors_Fortaleza.zip|233|82,3|zip|17,26
2014_01-Traffic_Sensors_Fortaleza.csv.gz|233|82,3|gzip|13,92
2014_01-Traffic_Sensors_Fortaleza.csv.bz2|233|59,1|bzip2|24,84
2014_01-Traffic_Sensors_Fortaleza.rar|233|62|rar|131,73
2014_01-Traffic_Sensors_Fortaleza.csv.7z|233|52,8|7zip|344,02
