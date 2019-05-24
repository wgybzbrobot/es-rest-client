package com.jusdt.es.common.indices.template;

import org.junit.Test;

import com.jusdt.es.common.client.config.ElasticSearchVersion;
import com.jusdt.es.common.indices.template.DeleteTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DeleteTemplateTest {

    @Test
    public void testBasicUriGeneration() {
        DeleteTemplate deleteTemplate = new DeleteTemplate.Builder("personal_tweet").build();

        assertEquals("DELETE", deleteTemplate.getRestMethodName());
        assertEquals("_template/personal_tweet", deleteTemplate.getURI(ElasticSearchVersion.UNKNOWN));
    }

    @Test
    public void equalsReturnsTrueForSameTemplate() {
        DeleteTemplate deleteTemplate1 = new DeleteTemplate.Builder("personal_tweet").build();
        DeleteTemplate deleteTemplate1Duplicate = new DeleteTemplate.Builder("personal_tweet").build();

        assertEquals(deleteTemplate1, deleteTemplate1Duplicate);
    }

    @Test
    public void equalsReturnsFalseForDifferentTemplate() {
        DeleteTemplate deleteTemplate1 = new DeleteTemplate.Builder("personal_tweet").build();
        DeleteTemplate deleteTemplate2 = new DeleteTemplate.Builder("company_tweet").build();

        assertNotEquals(deleteTemplate1, deleteTemplate2);
    }

}