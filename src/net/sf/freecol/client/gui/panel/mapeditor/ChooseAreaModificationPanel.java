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

package net.sf.freecol.client.gui.panel.mapeditor;

import java.awt.Dimension;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import net.miginfocom.swing.MigLayout;
import net.sf.freecol.client.FreeColClient;
import net.sf.freecol.client.gui.DialogHandler;
import net.sf.freecol.client.gui.panel.FreeColPanel;
import net.sf.freecol.client.gui.panel.WrapLayout;
import net.sf.freecol.client.gui.panel.WrapLayout.HorizontalAlignment;
import net.sf.freecol.common.i18n.Messages;
import net.sf.freecol.common.model.Area;

/**
 * A panel for choosing an area that tiles should be toggled in or out from.
 */
public final class ChooseAreaModificationPanel extends FreeColPanel {

    private static final Logger logger = Logger.getLogger(ChooseAreaModificationPanel.class.getName());

    
    private final DialogHandler<Area> dialogHandler;
    
    /**
     * Creates a new game panel.
     *
     * @param freeColClient The {@code FreeColClient} for the game.
     */
    public ChooseAreaModificationPanel(FreeColClient freeColClient, DialogHandler<Area> dialogHandler) {
        super(freeColClient, null, new MigLayout("fill, wrap 1", "[fill, growprio 150]", "[fill, growprio 150]"));
        
        this.dialogHandler = dialogHandler;
        
        final JPanel areasPanel = new JPanel(new WrapLayout()
                .withHorizontalAlignment(HorizontalAlignment.CENTER)
                ) {
            @Override
            public Dimension getMinimumSize() {
                return new Dimension(1, 1);
            }
        };
        areasPanel.setOpaque(false);
        
        final ButtonGroup bg = new ButtonGroup();
        for (Area a : freeColClient.getGame().getAreas()) {
            final String title = (a.getNameKey() != null) ? Messages.message(a.getNameKey()) : a.getName();
            final JToggleButton areaButton = new JToggleButton(title);
            areaButton.addActionListener((e) -> {
                dialogHandler.handle(a);
            });
            bg.add(areaButton);
            areasPanel.add(areaButton);
        }

        add(areasPanel, "grow, shrink");

        setBorder(BorderFactory.createEmptyBorder());
        setSize(getPreferredSize());
    }
    
    @Override
    public String getFrameTitle() {
        return Messages.message("mapEditor.chooseAreaModificationPanel.title");
    }
    
}
