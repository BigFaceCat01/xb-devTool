package xb.dev.tools.mongodb.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;
import org.springframework.amqp.rabbit.listener.FatalExceptionStrategy;
import org.springframework.amqp.rabbit.listener.exception.ListenerExecutionFailedException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.util.ErrorHandler;

/**
 * @author Created by huang xiao bao
 * @date 2018-12-17 16:52:09
 */
@Configuration
@Slf4j
public class ListenerExceptionErrorHandler {
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory,ErrorHandler errorHandler) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setErrorHandler(errorHandler);
        return factory;
    }
    @Bean
    public ErrorHandler errorHandler(){
        //若消息监听异常，使用放弃该消息策略
        return new MyFatalExceptionStrategy((throwable) -> true);
    }

    public static final class MyFatalExceptionStrategy extends ConditionalRejectingErrorHandler {

        public MyFatalExceptionStrategy(FatalExceptionStrategy exceptionStrategy) {
            super(exceptionStrategy);
        }

        @Override
        public void handleError(Throwable t) {
            log.error("消息监听异常：{}",t.getStackTrace()[0]);
            super.handleError(t);
        }
    }

}

