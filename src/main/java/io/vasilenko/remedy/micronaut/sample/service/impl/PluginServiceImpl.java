package io.vasilenko.remedy.micronaut.sample.service.impl;

import io.vasilenko.remedy.micronaut.sample.service.PluginService;

import javax.inject.Singleton;

@Singleton
public class PluginServiceImpl implements PluginService {

    @Override
    public String greeting(String name) {
        return "Hello " + name;
    }
}
