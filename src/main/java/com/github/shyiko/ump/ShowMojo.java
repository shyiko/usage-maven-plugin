/*
 * Copyright 2012 Stanley Shyiko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.shyiko.ump;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import java.util.*;

/**
 * @goal show
 * @author <a href="mailto:stanley.shyiko@gmail.com">Stanley Shyiko</a>
 */
public class ShowMojo extends AbstractMojo {

    /**
     * Usage information.
     * @parameter
     * @required
     */
    private String usage;

    /**
     * True indicates that each line should be trimmed before being printed out, false otherwise.
     * @parameter expression="${ump.printTrimmed}" default-value="true"
     */
    private boolean printTrimmed;

    /**
     * Coloring. Default configuration:
     * <pre>
     * &lt;coloring&gt;
     *     &lt;color&gt;
     *         &lt;prefix&gt;#&lt;/prefix&gt;
     *         &lt;number&gt;33&lt;/number&gt;
     *     &lt;/color&gt;
     * &lt;/coloring&gt;
     * </pre>
     * @parameter
     */
    private List<Color> coloring;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        if (coloring == null) {
            coloring = Arrays.asList(new Color("#", "33"));
        }
        Map<String, String> colors = new HashMap<String, String>();
        for (Color color : coloring) {
            colors.put(color.prefix, "\u001B[" + color.number + "m%s\u001B[m");
        }
        for (String line : usage.split("\n")) {
            if (printTrimmed) {
                line = line.trim();
            }
            if (isColoringSupported()) {
                for (Map.Entry<String, String> entry : colors.entrySet()) {
                    if (line.startsWith(entry.getKey())) {
                        line = String.format(entry.getValue(), line);
                        break;
                    }
                }
            }
            getLog().info(line);
        }
    }

    private boolean isColoringSupported() {
        return !System.getProperty("os.name").toLowerCase().contains("win");
    }

    public static class Color {
        private String prefix;
        private String number;
        public Color() {
            // required by maven
        }
        public Color(String prefix, String number) {
            this.prefix = prefix;
            this.number = number;
        }
    }
}
