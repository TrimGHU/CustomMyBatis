# CustomMyBatis
参考MyBatis的基本原理，仿制定义一个简易MyBatis结构。
只是简易模型，内部很多功能没有定义。

MyBatis的四大对象，后2个在此次没有涉及参数的处理，结果的处理。
1. executor (2个实现，BaseExecutor，CacheExecutor)
2. statementHandler (在这里通过Executor访问数据库操作)
3. parameterHandler
4. resultHandler

## MyBatis简易原理图
![MyBatis简易原理图](https://github.com/TrimGHU/CustomMyBatis/blob/master/custombatis.png?raw=true)

## CustomMyBatis类图
![CustomMyBatis类图](https://github.com/TrimGHU/CustomMyBatis/blob/master/custombatis.cld.jpg)
