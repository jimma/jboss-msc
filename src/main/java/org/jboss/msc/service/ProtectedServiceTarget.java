/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2010, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.msc.service;

import java.util.Collection;
import org.jboss.msc.value.Value;

/**
 * An "insulated" view of a service target which prevents access to other public methods on the delegate target object.
 *
 * @author <a href="mailto:david.lloyd@redhat.com">David M. Lloyd</a>
 */
public final class ProtectedServiceTarget implements ServiceTarget {
    private final ServiceTarget delegate;

    private ProtectedServiceTarget(final ServiceTarget delegate) {
        this.delegate = delegate;
    }

    /**
     * Construct a new instance.
     *
     * @param delegate the service target to delegate to
     * @return a protected service target
     */
    public static ProtectedServiceTarget create(final ServiceTarget delegate) {
        return delegate instanceof ProtectedServiceTarget ? (ProtectedServiceTarget) delegate : new ProtectedServiceTarget(delegate);
    }

    /** {@inheritDoc} */
    public <T> ServiceBuilder<T> addServiceValue(final ServiceName name, final Value<? extends Service<T>> value) throws IllegalArgumentException {
        return delegate.addServiceValue(name, value);
    }

    /** {@inheritDoc} */
    public <T> ServiceBuilder<T> addService(final ServiceName name, final Service<T> service) throws IllegalArgumentException {
        return delegate.addService(name, service);
    }

    /** {@inheritDoc} */
    public <T> ServiceBuilder<T> addServiceValueIfNotExist(final ServiceName name, final Value<? extends Service<T>> value) throws IllegalArgumentException {
        return delegate.addServiceValueIfNotExist(name, value);
    }

    /** {@inheritDoc} */
    public ServiceTarget addListener(final ServiceListener<Object> listener) {
        return delegate.addListener(listener);
    }

    /** {@inheritDoc} */
    public ServiceTarget addListener(final ServiceListener<Object>... listeners) {
        return delegate.addListener(listeners);
    }

    /** {@inheritDoc} */
    public ServiceTarget addListener(final Collection<ServiceListener<Object>> listeners) {
        return delegate.addListener(listeners);
    }

    /** {@inheritDoc} */
    public ServiceTarget addDependency(final ServiceName dependency) {
        return delegate.addDependency(dependency);
    }

    /** {@inheritDoc} */
    public ServiceTarget addDependency(final ServiceName... dependencies) {
        return delegate.addDependency(dependencies);
    }

    /** {@inheritDoc} */
    public ServiceTarget addDependency(final Collection<ServiceName> dependencies) {
        return delegate.addDependency(dependencies);
    }

    /** {@inheritDoc} */
    public ServiceTarget subTarget() {
        return delegate.subTarget();
    }

    /** {@inheritDoc} */
    public BatchBuilder batchBuilder() {
        return delegate.batchBuilder();
    }
}