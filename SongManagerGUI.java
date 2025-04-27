import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SongManagerGUI extends JFrame {

    private Playlist playlist;
    private JTextField titleField, artistField, durationField, genreField;
    private JTextArea displayArea;

    public SongManagerGUI(Playlist playlist) {
        this.playlist = playlist;

        setTitle("🎶 Playlist: " + playlist.getName());
        setSize(500, 450);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null); // Center window

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));

        titleField = new JTextField();
        artistField = new JTextField();
        durationField = new JTextField();
        genreField = new JTextField();

        inputPanel.add(new JLabel("Song Title:"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Artist:"));
        inputPanel.add(artistField);
        inputPanel.add(new JLabel("Duration (in seconds):"));
        inputPanel.add(durationField);
        inputPanel.add(new JLabel("Genre:"));
        inputPanel.add(genreField);

        JButton addButton = new JButton("Add Song");
        JButton viewButton = new JButton("View Songs");

        inputPanel.add(addButton);
        inputPanel.add(viewButton);

        add(inputPanel, BorderLayout.NORTH);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // 🔙 Go Back Button
        JButton backButton = new JButton("⬅ Go Back");
        add(backButton, BorderLayout.SOUTH);

        // 🔘 Add Song Action
        addButton.addActionListener(e -> {
            String title = titleField.getText().trim();
            String artist = artistField.getText().trim();
            String genre = genreField.getText().trim();
            int duration;

            if (title.isEmpty() || artist.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter at least title and artist.");
                return;
            }

            try {
                duration = Integer.parseInt(durationField.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number for duration.");
                return;
            }

            Song song = new Song(title, artist, duration, genre);
            playlist.addSong(song);
            clearFields();
            JOptionPane.showMessageDialog(this, "Song added to playlist!");
        });

        // 🔘 View Songs Action
        viewButton.addActionListener(e -> {
            if (playlist.getSongs().isEmpty()) {
                displayArea.setText("This playlist has no songs.");
            } else {
                StringBuilder sb = new StringBuilder("🎵 Songs in " + playlist.getName() + ":\n\n");
                for (Song s : playlist.getSongs()) {
                    sb.append(s.toString()).append("\n");
                }
                displayArea.setText(sb.toString());
            }
        });

        // 🔘 Go Back Action
        backButton.addActionListener(e -> {
            dispose(); // Closes this window
        });

        setVisible(true);
    }

    private void clearFields() {
        titleField.setText("");
        artistField.setText("");
        durationField.setText("");
        genreField.setText("");
    }
}
