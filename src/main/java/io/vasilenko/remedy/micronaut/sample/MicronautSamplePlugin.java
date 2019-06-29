package io.vasilenko.remedy.micronaut.sample;

import com.bmc.arsys.api.Value;
import com.bmc.arsys.pluginsvr.plugins.ARFilterAPIPlugin;
import com.bmc.arsys.pluginsvr.plugins.ARPluginContext;
import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.Micronaut;
import io.vasilenko.remedy.micronaut.sample.service.PluginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class MicronautSamplePlugin extends ARFilterAPIPlugin {

    private static final int INPUT_NAME_VALUE_INDEX = 0;

    private final Logger log = LoggerFactory.getLogger(MicronautSamplePlugin.class);

    private ApplicationContext applicationContext;

    @Inject
    private PluginService service;

    @Override
    public void initialize(ARPluginContext context) {
        applicationContext = Micronaut.run(MicronautSamplePlugin.class);
        applicationContext.registerSingleton(this);
        log.info("initialized");
    }

    @Override
    public List<Value> filterAPICall(ARPluginContext context, List<Value> inputValues) {
        String name = String.valueOf(inputValues.get(INPUT_NAME_VALUE_INDEX));
        log.info("name: {}", name);
        String greeting = service.greeting(name);
        log.info("greeting: {}", greeting);
        List<Value> outputList = new ArrayList<>();
        outputList.add(new Value(greeting));
        return outputList;
    }

    @Override
    public void terminate(ARPluginContext context) {
        if (applicationContext != null && applicationContext.isRunning()) {
            applicationContext.stop();
        }
    }
}
