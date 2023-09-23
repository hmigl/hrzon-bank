package br.com.hmigl.hrzonbank.compartilhado;

import java.util.List;

public enum ValidationCondition {
    UNIQUE {
        @Override
        public boolean isValid(List<?> resultList) {
            return resultList.isEmpty();
        }
    },
    EXISTS {
        @Override
        public boolean isValid(List<?> resultList) {
            return !resultList.isEmpty();
        }
    };

    public abstract boolean isValid(List<?> resultList);
}
