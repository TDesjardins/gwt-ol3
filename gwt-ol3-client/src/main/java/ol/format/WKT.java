/*
 * Copyright (c) 2001-2016 Territorium Online Srl. All Rights Reserved.
 *
 * This file contains Original Code and/or Modifications of Original Code as
 * defined in and that are subject to the Territorium Online License Version
 * 1.0. You may not use this file except in compliance with the License. Please
 * obtain a copy of the License at http://www.tol.info/license/ and read it
 * before using this file.
 *
 * The Original Code and all software distributed under the License are
 * distributed on an 'AS IS' basis, WITHOUT WARRANTY OF ANY KIND, EITHER EXPRESS
 * OR IMPLIED, AND TERRITORIUM ONLINE HEREBY DISCLAIMS ALL SUCH WARRANTIES,
 * INCLUDING WITHOUT LIMITATION, ANY WARRANTIES OF MERCHANTABILITY, FITNESS FOR
 * A PARTICULAR PURPOSE, QUIET ENJOYMENT OR NON-INFRINGEMENT. Please see the
 * License for the specific language governing rights and limitations under the
 * License.
 */
package ol.format;

import javax.annotation.Nullable;

import jsinterop.annotations.JsType;
import ol.Feature;
import ol.Options;

/**
 * The {@link WKT} class is used
 * 
 * for reading and writing data in the WellKnownText (WKT) format.
 * 
 */
@JsType(isNative = true)
public class WKT extends Feature {

	/**
	 * 
	 * Constructs a(n) {@link WKT} object.
	 *
	 */
	public WKT() {};

	/**
	 * 
	 * Read a feature from a WKT source.
	 *
	 * @param source
	 * @param opt_options
	 * @return
	 */
	public native Feature readFeature(java.lang.Object source, @Nullable Options opt_options);

}