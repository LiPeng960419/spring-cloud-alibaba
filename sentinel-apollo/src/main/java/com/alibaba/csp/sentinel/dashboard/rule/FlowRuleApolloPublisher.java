package com.alibaba.csp.sentinel.dashboard.rule;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.util.AssertUtil;
import com.ctrip.framework.apollo.openapi.client.ApolloOpenApiClient;
import com.ctrip.framework.apollo.openapi.dto.NamespaceReleaseDTO;
import com.ctrip.framework.apollo.openapi.dto.OpenItemDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: lipeng 910138
 * @Date: 2019/10/22 17:19
 */
@Component("flowRuleApolloPublisher")
public class FlowRuleApolloPublisher implements DynamicRulePublisher<List<FlowRuleEntity>> {

    private static final String COMMENT = "release by sentinel-dashboard";
    private static final String FLOW_RULES = "sentinel.flowRules";

    @Autowired
    private ApolloOpenApiClient apolloOpenApiClient;
    @Autowired
    private Converter<List<FlowRuleEntity>, String> converter;

    @Value("${apollo.env:DEV}")
    private String env;

    @Value("${apollo.clusterName:default}")
    private String clusterName;

    @Value("${apollo.nameSpaceName:application}")
    private String nameSpaceName;


    @Override
    public void publish(String app, List<FlowRuleEntity> rules) throws Exception {
        AssertUtil.notEmpty(app, "app name cannot be empty");
        if (rules == null) {
            return;
        }

        OpenItemDTO openItemDTO = new OpenItemDTO();
        openItemDTO.setKey(FLOW_RULES);
        openItemDTO.setValue(converter.convert(rules));
        openItemDTO.setComment(COMMENT);
        openItemDTO.setDataChangeCreatedBy(ApolloConfig.APOLLO);
        apolloOpenApiClient.createOrUpdateItem(app, env, clusterName, nameSpaceName, openItemDTO);

        // Release configuration
        NamespaceReleaseDTO namespaceReleaseDTO = new NamespaceReleaseDTO();
        namespaceReleaseDTO.setEmergencyPublish(true);
        namespaceReleaseDTO.setReleaseComment(COMMENT);
        namespaceReleaseDTO.setReleasedBy(ApolloConfig.APOLLO);
        namespaceReleaseDTO.setReleaseTitle(COMMENT);
        apolloOpenApiClient.publishNamespace(app, env, clusterName, nameSpaceName, namespaceReleaseDTO);
    }
}
