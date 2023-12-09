package cn.openjava.mybatis.plus.starter.interceptor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class DBCryptoInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
//        System.out.println(invocation.getMethod().getName());
        return invocation.proceed();
//        String methodName = invocation.getMethod().getName();
//        Object parameter = invocation.getArgs()[1];
//        if (StrUtil.equalsIgnoreCase("update", methodName) || StrUtil.equalsIgnoreCase("insert", methodName)) {
//            CryptoUtil.encryptField(parameter);
//        }
//        Object returnValue = invocation.proceed();
//        if (returnValue != null && returnValue instanceof List) {
//            for (Object o : (List) returnValue) {
//                CryptoUtil.decryptField(o);
//            }
//        }
//        return parameter;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}