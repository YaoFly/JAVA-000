GCLogAnalysis 运行1s    

GC算法 | 生成对象数 | Yong GC次数 | Full GC 次数 |
---- | --- | --- | --- |
Serial GC | 13277 | 19 | 5 | 
Parallel GC | 17918 | 12 | 4 | 
CMS GC | 12757 | 46 | 1 | 
G1 GC | 8929 | 11 | 0 | 

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

G1 单位时间生成对象数最少的原因待研究，从sb压测的数据上来看，G1兼顾高吞吐量和低延迟的优点。
