/*
 * Copyright 2012 PRODYNA AG
 *
 * Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/eclipse-1.0.php or
 * http://www.nabucco.org/License.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nabucco.framework.support.scripting.impl.service.engine.javascript.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.mozilla.javascript.ScriptableObject;

import org.nabucco.framework.base.facade.datatype.logger.NabuccoLogger;
import org.nabucco.framework.base.facade.datatype.logger.NabuccoLoggingFactory;

/**
 * ScriptLogger
 * 
 * @author Silas Schwarz, PRODYNA AG
 */
public class ScriptLogger extends ScriptableObject {

    private static final long serialVersionUID = 1L;

    private static final String NAME = "ScriptLogger";

    private StringBuilder logTrace;

    private StringBuilder errorTrace;

    /** The NABUCCO Logger. */
    private static NabuccoLogger logger = NabuccoLoggingFactory.getInstance().getLogger(ScriptLogger.class);

    /**
     * Creates a new {@link ScriptLogger} instance.
     */
    public ScriptLogger() {
        this.logTrace = new StringBuilder();
        this.errorTrace = new StringBuilder();
    }

    @Override
    public String getClassName() {
        return NAME;
    }

    /**
     * Fatal log.
     * 
     * @param message
     *            the log message
     */
    public void fatal(String message) {
        logger.debug("FATAL: ", message);

        this.logTrace.append(this.formatLogLine("FATAL", message));
        this.errorTrace.append(this.formatLogLine("FATAL", message));
    }

    /**
     * Error log.
     * 
     * @param message
     *            the log message
     */
    public void error(String message) {
        logger.debug("ERROR: ", message);

        this.logTrace.append(this.formatLogLine("ERROR", message));
        this.errorTrace.append(this.formatLogLine("ERROR", message));
    }

    /**
     * Warning log.
     * 
     * @param message
     *            the log message
     */
    public void warning(String message) {
        logger.debug("WARN: ", message);

        this.logTrace.append(this.formatLogLine("WARNING", message));
    }

    /**
     * Info log.
     * 
     * @param message
     *            the log message
     */
    public void info(String message) {
        logger.debug("INFO: ", message);

        this.logTrace.append(this.formatLogLine("INFO", message));
    }

    /**
     * Debug log.
     * 
     * @param message
     *            the log message
     */
    public void debug(String message) {
        logger.debug("DEBUG: ", message);

        this.logTrace.append(this.formatLogLine("DEBUG", message));
    }

    /**
     * Trace log.
     * 
     * @param message
     *            the log message
     */
    public void trace(String message) {
        logger.debug("TRACE: ", message);

        this.logTrace.append(this.formatLogLine("TRACE", message));
    }

    /**
     * Getter for the log traces.
     * 
     * @return the log trace as string.
     */
    public String getLogTraces() {
        return this.logTrace.toString();
    }

    /**
     * Getter for the error traces.
     * 
     * @return the error trace as string.
     */
    public String getErrorTraces() {
        return this.errorTrace.toString();
    }

    /**
     * Format the log line to an appropriate layout.
     * 
     * @param logLevel
     *            the log level
     * @param logLine
     *            the log line
     * 
     * @return the formattet line
     */
    private String formatLogLine(String logLevel, String logLine) {

        StringBuilder result = new StringBuilder();

        // write current time
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss,SSS");
        result.append(simpleDateFormat.format(date));

        // write loglevel
        result.append(" " + logLevel + " [LoggerUtility] ");

        // write current log line
        result.append(logLine + "\n");

        return result.toString();
    }

}
