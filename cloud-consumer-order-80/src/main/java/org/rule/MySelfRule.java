package org.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

//@Configuration
public class MySelfRule {

//    @Bean
    public IRule myRule(){
        return new RandomRule();
    }
}
