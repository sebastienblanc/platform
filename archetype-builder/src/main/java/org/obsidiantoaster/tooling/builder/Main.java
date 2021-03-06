/**
 * Copyright 2005-2015 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */
package org.obsidiantoaster.tooling.builder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.fabric8.utils.Objects;

public class Main {

	public static Logger LOG = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) throws Exception {
		String outputPath = System.getProperty("outputdir");
		Objects.notNull(outputPath, "System property 'outputdir' was not set");
		File outputDir = new File(outputPath);

		ObsidianArchetypeBuilder builder = new ObsidianArchetypeBuilder();

		List<String> dirs = new ArrayList<>();
		try {
			builder.generateArchetypesFromGithubOrganisation("obsidian-toaster-quickstarts", outputDir, dirs);

		} finally {
			LOG.debug("Completed the generation. Closing!");
		}

		StringBuffer sb = new StringBuffer();
		for (String dir : dirs) {
			sb.append("\n\t<module>").append(dir).append("</module>");
		}
		System.out.println("Done creating archetypes:\n" + sb + "\n");
	}
}
