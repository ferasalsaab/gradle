/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.internal.snapshot.impl;

import org.gradle.internal.hash.HashCode;
import org.gradle.internal.hash.Hasher;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public class ClassImplementationSnapshot extends ImplementationSnapshot {
    private final HashCode classLoaderHash;

    public ClassImplementationSnapshot(String typeName, HashCode classLoaderHash) {
        super(typeName);
        this.classLoaderHash = classLoaderHash;
    }

    @Override
    public void appendToHasher(Hasher hasher) {
        hasher.putString(ClassImplementationSnapshot.class.getName());
        hasher.putString(typeName);
        hasher.putHash(classLoaderHash);
    }

    @Override
    protected boolean isSameSnapshot(@Nullable Object o) {
        return equals(o);
    }

    @Nonnull
    @Override
    public HashCode getClassLoaderHash() {
        return classLoaderHash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ClassImplementationSnapshot that = (ClassImplementationSnapshot) o;
        return typeName.equals(that.typeName) &&
            classLoaderHash.equals(that.classLoaderHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeName, classLoaderHash);
    }

    @Override
    public String toString() {
        return typeName + "@" + classLoaderHash;
    }
}
