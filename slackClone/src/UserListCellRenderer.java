import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UserListCellRenderer extends JLabel implements ListCellRenderer<User>{
        private static final int ICON_SIZE = 32;

        public UserListCellRenderer() {
            setOpaque(true);
            setIconTextGap(10); // Gap between icon and text
            setBorder(new EmptyBorder(5, 10, 5, 10)); // Padding around each cell
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends User> list, User user, int index, boolean isSelected, boolean cellHasFocus) {
            setText(user.getUsername());
            setIcon(new ImageIcon(user.get_szIconPath()));
            // Scale the icon to a fixed size
            if (getIcon() != null) {
                Image image = ((ImageIcon) getIcon()).getImage();
                Image scaledImage = image.getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH);
                setIcon(new ImageIcon(scaledImage));
            }
            setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
            setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
            return this;
        }
}

