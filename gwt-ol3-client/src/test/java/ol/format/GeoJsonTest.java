/*******************************************************************************
 * Copyright 2014, 2018 gwt-ol3
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package ol.format;

import com.google.gwt.core.client.JsonUtils;
import ol.Feature;
import ol.GwtOLBaseTestCase;
import ol.utils.OLTestUtils;

/**
 *
 * @author Tino Desjardins
 *
 */
public class GeoJsonTest extends GwtOLBaseTestCase {

    private GeoJson geoJsonFormat;

    @Override
    protected void gwtSetUp() throws Exception {
        injectUrlAndTest(() -> geoJsonFormat = new GeoJson());
    }

    public void testFeatureToGeoJson() {

        injectUrlAndTest(() -> {

            assertNotNull(geoJsonFormat);

            java.lang.Object geoJSON = geoJsonFormat.writeFeatureObject(OLTestUtils.createLineFeature(), null);
            assertNotNull(geoJSON);

        });
    }

    public void testGeoJsonToFeature() {

        injectUrlAndTest(() -> {
            java.lang.Object geoJson = geoJsonFormat.writeFeatureObject(OLTestUtils.createLineFeature(), null);

            // Convert Features from GeoJSON
            Feature featureGeoJson = geoJsonFormat.readFeature(geoJson, null);
            assertNotNull(featureGeoJson);
        });

    }

    public void testWriteGeoJSON() {

        injectUrlAndTest(() -> {
            String geoJson = geoJsonFormat.writeFeatures(OLTestUtils.createLineFeatures(), null);
            assertNotNull(geoJson);

            java.lang.Object geoJsonObject = JsonUtils.safeEval(geoJson);
            assertNotNull(geoJsonObject);
        });

    }

    public void testReadFeatureFromGeoJson() {

        injectUrlAndTest(() -> {
            String geoJson = geoJsonFormat.writeFeature(OLTestUtils.createLineFeature(), null);
            assertNotNull(geoJson);
            Feature feature = geoJsonFormat.readFeature(geoJson, null);

            assertNotNull(feature);
        });

    }

    public void testReadFeatureCollectionFromGeoJson() {

        injectUrlAndTest(() -> {
            String geoJson = geoJsonFormat.writeFeatures(OLTestUtils.createLineFeatures(), null);
            assertNotNull(geoJson);
            Feature[] features = geoJsonFormat.readFeatures(geoJson, null);

            assertNotNull(features);
            //assertTrue(features.length > 0);
        });

    }

}
