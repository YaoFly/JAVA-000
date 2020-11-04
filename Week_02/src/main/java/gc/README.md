GCLogAnalysis 运行1s    

GC算法 | 生成对象数 | Yong GC次数 | Full GC 次数 |
---- | --- | --- | --- |
Serial GC | 13277 | 19 | 5 | 
Parallel GC | 17918 | 12 | 4 | 
CMS GC | 12757 | 46 | 1 | 
G1 GC | 8929 | 11 | 0 | 

GCLogAnalysis 运行5s   

GC算法 | 生成对象数 |
---- | --- | 
Serial GC | 93516 
Parallel GC | 119677 
CMS GC | 79744 
G1 GC | 121487 

执行 sb -u http://localhost:8088/api/hello -c 20 -N 60  

GC算法 | RPS | max | min | avg |
---- | --- | --- | --- | --- |
Serial GC | 5801.8 | 243ms | 0ms | 0.1ms |
Parallel GC | 5857 | 240ms | 0ms | 0.1ms |
CMS GC | 4898.9 | 189ms | 0ms | 0.1ms |
G1 GC | 5699.6 | 206ms | 0ms | 0.1ms |

总结   
吞吐量 Parallel GC > Serial GC > G1 GC > CMS GC  
低延迟 CMS GC ~ G1 GC > Parallel GC ~ Serial GC  

1. G1 在1s单位时间生成对象数最少的原因待研究,但是随着运行时间的增加,G1收集器算法的优势开始凸显，性能持平Parallel GC。
猜测在其他GC算法的Full GC 的频次不高的情况下，G1的Yang GC效率相对劣势，但是随着其他算法Full GC的次数逐渐增多，
G1的Full GC频次少的优势就体现出来了。
2. 从sb压测的数据上来看，G1兼顾高吞吐量和低延迟的优点。
3. 吞吐量上,Parallel GC一直有着稳定的表现，充分利用CPU性能。
