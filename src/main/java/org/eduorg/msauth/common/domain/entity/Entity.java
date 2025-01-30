package org.eduorg.msauth.common.domain.entity;

public class Entity<T> {

        private final T id;

        public Entity(T id) {
            this.id = id;
        }

        public T getId() {
            return id;
        }

        public boolean isEquals( T otherId ){
            return this.id == otherId;
        }
}
