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
package org.nabucco.framework.support.scripting.facade.exception;

import org.nabucco.framework.base.facade.exception.service.ServiceException;

/**
 * ScriptExecutionException<p/>Exception for Script Execution errors.<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-01-15
 */
public class ScriptExecutionException extends ServiceException {

    private static final long serialVersionUID = 1L;

    /** Constructs a new ScriptExecutionException instance. */
    public ScriptExecutionException() {
        super();
    }

    /**
     * Constructs a new ScriptExecutionException instance.
     *
     * @param message the String.
     */
    public ScriptExecutionException(String message) {
        super(message);
    }

    /**
     * Constructs a new ScriptExecutionException instance.
     *
     * @param cause the Throwable.
     */
    public ScriptExecutionException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new ScriptExecutionException instance.
     *
     * @param cause the Throwable.
     * @param message the String.
     */
    public ScriptExecutionException(String message, Throwable cause) {
        super(message, cause);
    }
}
