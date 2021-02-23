package com.sole.ray.zuul.filter;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//@Component
public class SentinelLimiterFilter extends ZuulFilter implements InitializingBean {

    private static final String RESOURCE_NAME = "HelloWorld";

    @Override
    public void afterPropertiesSet() throws Exception {
        List<FlowRule> rules = new ArrayList<>();

        FlowRule rule = new FlowRule();

        //资源名称
        rule.setResource(RESOURCE_NAME);

        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);

        rule.setCount(2);

        rules.add(rule);

        FlowRuleManager.loadRules(rules);
    }

    @Override
    public String filterType() {
        return null;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        Entry entry = null;
        try {
            entry = SphU.entry(RESOURCE_NAME);
            System.out.println("正常请求");
        } catch (BlockException e) {
            e.printStackTrace();
        } finally {
            entry.exit();
        }

        return null;
    }


}
