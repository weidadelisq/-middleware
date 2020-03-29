package com.neo.easyRules;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.mvel.MVELRule;
import org.jeasy.rules.mvel.MVELRuleFactory;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;

public class ShopLauncher {
    public static void main(String[] args) throws Exception {
        //创建一个Person实例(Fact)
        Person tom = new Person("Tom", 14);
        Facts facts = new Facts();
        facts.put("person", tom);

        //创建规则1
        Rule ageRule = new MVELRule()
                .name("age rule")
                .description("Check if person's age is > 18 and marks the person as adult")
                .priority(1)
                .when("person.age > 18")
                .then("person.setAdult(true);");
        //创建规则2
        File file = ResourceUtils.getFile("classpath:alcohol.yml");
        Rule alcoholRule = MVELRuleFactory.createRuleFrom(new FileReader(file));

        Rules rules = new Rules();
        rules.register(ageRule);
        rules.register(alcoholRule);

        //创建规则执行引擎，并执行规则
        RulesEngine rulesEngine = new DefaultRulesEngine();
        System.out.println("Tom: Hi! can I have some Vodka please?");
        rulesEngine.fire(rules, facts);
    }
}