>### Docker容器安装nginx镜像
>
> 1. ####先拉取nginx镜像
>   ~~~# 
>   docker pull nginx
>   ~~~
>   ![image id](/java/images/image.jpg)
>
> 2. #### 创建nginx挂载目录
>   ~~~#
>   mkdir -p /data/nginx/{conf,conf.d,html,logs}
>   ~~~
>   
> 3. #### 进入容器查看文件目录结构
>   ~~~#
>   docker run -it nginx /bin/bash
>   ~~~
>   ![nginx目录](/java/images/目录结构.jpg)
>
> 4. #### 拷贝配置到nginx挂载目录
>   ~~~#
>   docker cp b36b48f55a6c:/etc/nginx/nginx.conf /data/nginx/conf/nginx.conf
>   docker cp b36b48f55a6c:/etc/nginx/conf.d/default.conf /data/nginx/conf.d/default.conf 
>   ~~~
>   ![挂载目录](/java/images/config.jpg)
> 5. #### 挂载并启动nginx
>   ~~~#
>   docker run --name nginx - p 80:80 --privileged=true -v/data/nginx/html:/usr/share/nginx/html -v /data/nginx/conf/nginx.conf:/etc/nginx/nginx.conf -v /data/nginx/conf.d/default.conf:/etc/nginx/conf.d/default.conf -v /data/nginx/logs:/var/log/nginx -d nginx
>   ~~~
>   
> 
>
>
>
>
>
>
>
>
>
>
>
>
>
>
>
>
>
>
>
>
>
>
>
>
>
>
>
>
>
>
>
>
>
>
>
>
>
>
>
>

