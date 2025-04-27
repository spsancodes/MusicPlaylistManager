import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PlaylistGUI extends JFrame {
//Datatype of playlist manager is PlaylistManager
    private PlaylistManager playlistManager;
    private DefaultListModel<String> playlistListModel;// stores list of playlist names
    //This line declares a private variable named playlistListModel,
    // which is capable of holding a list of strings using the DefaultListModel
    // class, typically for use in a JList component in Swing GUI applications.
    //Jlist is a swing component that helps to dispaly a list of items to user
    private JList<String> playlistList;//visual list displaying all the palylist
    //generic list of strings 

    public PlaylistGUI() {
        playlistManager = new PlaylistManager();

        setTitle("🎵 Playlist Manager");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // --- Create Playlist Panel ---
        JPanel createPanel = new JPanel(new FlowLayout());
        JTextField playlistNameField = new JTextField(20);
        JButton createButton = new JButton("Create Playlist");

        createPanel.add(new JLabel("New Playlist Name:"));
        createPanel.add(playlistNameField);
        createPanel.add(createButton);

        // --- Playlist List Panel ---
        playlistListModel = new DefaultListModel<>();
        playlistList = new JList<>(playlistListModel);
        playlistList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(playlistList);

        add(createPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // --- Actions ---
        createButton.addActionListener(e -> {
            String name = playlistNameField.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Playlist name can't be empty.");
                return;
            }

            if (playlistManager.getPlaylistByName(name) != null) {
                JOptionPane.showMessageDialog(this, "Playlist already exists.");
                return;
            }

            Playlist playlist = new Playlist(name);
            playlistManager.addPlaylist(playlist);
            playlistListModel.addElement(name);
            playlistNameField.setText("");
        });

        playlistList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    String selected = playlistList.getSelectedValue();
                    Playlist selectedPlaylist = playlistManager.getPlaylistByName(selected);
                    if (selectedPlaylist != null) {
                        new SongManagerGUI(selectedPlaylist); // Open new window
                    }
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PlaylistGUI::new);
    }
}
