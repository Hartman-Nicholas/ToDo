package utils;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordWrap {

    private final static int WRAP_LENGTH = 80;
    private final static String NEW_LINE_STR = System.lineSeparator();
    private final static boolean WRAP_LONG_WORDS = true;
    private final static String WRAP_ON = " ";


    /**
     * Method that wraps a string in the console to a WRAP_LENGTH of
     * 80 characters. Setup for readability in the console.
     *
     * @param str  the String to be word wrapped, may be null

     */

    public String wrap(final String str) {
        if (str == null) {
            return null;
        }

        final Pattern patternToWrapOn = Pattern.compile(WRAP_ON);
        final int inputLineLength = str.length();
        int offset = 0;
        final StringBuilder wrappedLine = new StringBuilder(inputLineLength + 32);

        while (offset < inputLineLength) {
            int spaceToWrapAt = -1;
            Matcher matcher = patternToWrapOn.matcher(
                    str.substring(offset, Math.min((int) Math.min(Integer.MAX_VALUE, offset + WRAP_LENGTH + 1L), inputLineLength)));
            if (matcher.find()) {
                if (matcher.start() == 0) {
                    offset += matcher.end();
                    continue;
                }
                spaceToWrapAt = matcher.start() + offset;
            }

            // only last line without leading spaces is left
            if (inputLineLength - offset <= WRAP_LENGTH) {
                break;
            }

            while (matcher.find()) {
                spaceToWrapAt = matcher.start() + offset;
            }

            if (spaceToWrapAt >= offset) {
                // normal case
                wrappedLine.append(str, offset, spaceToWrapAt);
                wrappedLine.append(NEW_LINE_STR);
                offset = spaceToWrapAt + 1;

            } else {
                // really long word or URL
                if (WRAP_LONG_WORDS) {
                    // wrap really long word one line at a time
                    wrappedLine.append(str, offset, WRAP_LENGTH + offset);
                    wrappedLine.append(NEW_LINE_STR);
                    offset += WRAP_LENGTH;
                } else {
                    // do not wrap really long word, just extend beyond limit
                    matcher = patternToWrapOn.matcher(str.substring(offset + WRAP_LENGTH));
                    if (matcher.find()) {
                        spaceToWrapAt = matcher.start() + offset + WRAP_LENGTH;
                    }

                    if (spaceToWrapAt >= 0) {
                        wrappedLine.append(str, offset, spaceToWrapAt);
                        wrappedLine.append(NEW_LINE_STR);
                        offset = spaceToWrapAt + 1;
                    } else {
                        wrappedLine.append(str, offset, str.length());
                        offset = inputLineLength;
                    }
                }
            }
        }

        // Whatever is left in line is short enough to just pass through
        wrappedLine.append(str, offset, str.length());

        return wrappedLine.toString();
    }


}
