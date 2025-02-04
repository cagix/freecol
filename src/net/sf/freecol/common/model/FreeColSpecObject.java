/**
 *  Copyright (C) 2002-2024   The FreeCol Team
 *
 *  This file is part of FreeCol.
 *
 *  FreeCol is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  FreeCol is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with FreeCol.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.sf.freecol.common.model;


/**
 * A FreeColObject that also contains a Specification.
 */
public abstract class FreeColSpecObject extends FreeColObject {


    /** The {@code Specification} this object uses, which may be null. */
    private Specification specification;


    /**
     * Create a new specification-object.
     *
     * @param specification The {@code Specification} to use.
     */
    public FreeColSpecObject(Specification specification) {
        this.specification = specification;
    }


    /**
     * Get the specification.  It may be null.
     *
     * @return The {@code Specification} used by this object.
     */
    @Override
    public Specification getSpecification() {
        return this.specification;
    }

    /**
     * Sets the specification for this object. 
     *
     * @param specification The {@code Specification} to use.
     */
    @Override
    protected void setSpecification(Specification specification) {
        this.specification = specification;
    }


    // Override FreeColObject

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends FreeColObject> boolean copyIn(T other) {
        FreeColSpecObject o = copyInCast(other, FreeColSpecObject.class);
        if (o == null || !super.copyIn(o)) return false;
        this.specification = o.getSpecification();
        return true;
    }
}
