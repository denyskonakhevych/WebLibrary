/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.db.common;

/**
 *
 * @author koxa
 */
public abstract class DbTask {
    
    public static enum Status {
        ORDERED("ordered"),
        TAKEN("taken"),
        RETURNED("returned");
        
        private String displayName;

        private Status(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }
    }
}
