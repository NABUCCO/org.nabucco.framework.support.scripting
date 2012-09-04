/*
 * Copyright 2012 PRODYNA AG
 * 
 * Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/eclipse-1.0.php or
 * http://www.nabucco.org/License.html
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package org.nabucco.framework.support.scripting.facade.exception;

import org.nabucco.framework.base.facade.exception.service.ServiceException;

/**
 * ScriptCompilationException<p/>Exception for Script Compilation errors.<p/>
 *
 * @version 1.0
 * @author Frank Ratschinski, PRODYNA AG, 2010-01-15
 */
public class ScriptCompilationException extends ServiceException {

    private static final long serialVersionUID = 1L;

    /** Constructs a new ScriptCompilationException instance. */
    public ScriptCompilationException() {
        super();
    }

    /**
     * Constructs a new ScriptCompilationException instance.
     *
     * @param message the String.
     */
    public ScriptCompilationException(String message) {
        super(message);
    }

    /**
     * Constructs a new ScriptCompilationException instance.
     *
     * @param cause the Throwable.
     */
    public ScriptCompilationException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new ScriptCompilationException instance.
     *
     * @param cause the Throwable.
     * @param message the String.
     */
    public ScriptCompilationException(String message, Throwable cause) {
        super(message, cause);
    }
}
