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
package com.github.tdesjardins.ol3.demo.client.example;

import com.github.tdesjardins.ol3.demo.client.utils.DemoUtils;

import jsinterop.base.JsPropertyMap;
import ol.Collection;
import ol.Coordinate;
import ol.Map;
import ol.MapOptions;
import ol.OLFactory;
import ol.PixelColor;
import ol.RasterOperation;
import ol.View;
import ol.event.EventListener;
import ol.layer.Base;
import ol.layer.Image;
import ol.layer.LayerOptions;
import ol.layer.Tile;
import ol.proj.Projection;
import ol.source.Osm;
import ol.source.Raster;
import ol.source.Raster.Event;
import ol.source.RasterOperationType;
import ol.source.RasterOptions;
import ol.source.XyzOptions;

/**
 * Example with a Raster source.
 *
 * @author Tino Desjardins
 */
public class RasterExample implements Example {

    /*
     * (non-Javadoc)
     * @see de.desjardins.ol3.demo.client.example.Example#show()
     */
    @Override
    public void show(String exampleId) {

        XyzOptions osmSourceOptions = OLFactory.createOptions();
        osmSourceOptions.setCrossOrigin("Anonymous");

        Osm osmSource = new Osm(osmSourceOptions);
        LayerOptions osmLayerOptions = OLFactory.createOptions();
        osmLayerOptions.setSource(osmSource);

        // create OSM layer only for background and attribution
        Tile osmLayer = new Tile(osmLayerOptions);

        // wrap OSM with raster source layer
        RasterOptions<Number> rasterOptions = OLFactory.createOptions();

        rasterOptions.setOperationType(RasterOperationType.PIXEL);
        rasterOptions.setSource(osmSource);
        rasterOptions.setThreads(0);

        // define per pixel operation
        rasterOptions.setOperation(new RasterOperation<PixelColor, Number>() {

            @Override
            public PixelColor call(PixelColor[] pixels, JsPropertyMap<Number> data) {

                // get pixel of first source
                PixelColor pix = pixels[0];

                // extract pixel value and apply operation
                int channel = data.get("channel").intValue();
                int value = pix.getChannel(channel);
                int threshold = data.get("threshold").intValue();
                pix.clear();

                if (value > threshold) {
                    pix.setChannel(channel, 255);
                    pix.setAlpha(128);
                }

                return pix;
            }

        });

        Raster<Number> raster = new Raster<>(rasterOptions);

        raster.addBeforeOperationsListener(new EventListener<Raster.Event<Number>>() {

            @Override
            public void onEvent(Event<Number> event) {
                JsPropertyMap<Number> data = event.getData();
                data.set("channel", 0);
                data.set("threshold", 242);
            }

        });

        LayerOptions layerOptions = OLFactory.createOptions();
        layerOptions.setSource(raster);
        Image image = new Image(layerOptions);

        Collection<Base> layers = new Collection<>();
        layers.push(osmLayer);
        layers.push(image);

        View view = new View();

        Coordinate centerCoordinate = new Coordinate(-0.1275, 51.507222);
        Coordinate transformedCenterCoordinate = Projection.transform(centerCoordinate, "EPSG:4326", "EPSG:3857");

        view.setCenter(transformedCenterCoordinate);
        view.setZoom(10);

        MapOptions mapOptions = OLFactory.createOptions();
        mapOptions.setTarget(exampleId);
        mapOptions.setView(view);
        mapOptions.setLayers(layers);

        Map map = new Map(mapOptions);

        // add some controls
        DemoUtils.addDefaultControls(map.getControls());

    }

}
