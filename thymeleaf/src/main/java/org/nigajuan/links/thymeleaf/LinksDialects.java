package org.nigajuan.links.thymeleaf;


import org.nigajuan.links.LinksGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.context.IProcessingContext;
import org.thymeleaf.dialect.IExpressionEnhancingDialect;
import org.thymeleaf.doctype.resolution.IDocTypeResolutionEntry;
import org.thymeleaf.doctype.translation.IDocTypeTranslation;
import org.thymeleaf.processor.IProcessor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by nithril on 20/03/14.
 */
public class LinksDialects implements IExpressionEnhancingDialect {

    public static final String LINKS_EXPRESSION_OBJECT_NAME = "links";

    @Autowired
    private LinksGenerator linksGenerator;


    @Override
    public Map<String, Object> getAdditionalExpressionObjects(final IProcessingContext processingContext) {
        final Map<String, Object> objects = new HashMap<>();
        objects.put(LINKS_EXPRESSION_OBJECT_NAME, linksGenerator);
        return objects;
    }

    @Override
    public String getPrefix() {
        return LINKS_EXPRESSION_OBJECT_NAME;
    }

    @Override
    public Set<IProcessor> getProcessors() {
        return new HashSet<>();
    }

    @Override
    public Map<String, Object> getExecutionAttributes() {
        return new HashMap<>();
    }

    @Override
    public Set<IDocTypeTranslation> getDocTypeTranslations() {
        return new HashSet<>();
    }

    @Override
    public Set<IDocTypeResolutionEntry> getDocTypeResolutionEntries() {
        return new HashSet<>();
    }
}