reset
set style line 1 lw 2 pt 1 lc 1

set grid
set title 'Comparação'
set xlabel 'Tamanho do arquivo (MB)'
set ylabel 'Tempo (s)'

plot 'zip.txt' title 'ZIP', 'gzip.txt' title 'GZIP', 'bzip2.txt' title 'BZIP', '7zip.txt' title '7ZIP', 'rar.txt' title 'rar' 
