package br.dev.d4rkwav3.a3.projetoa3;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "history")
public class History {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "username")
    private String userName;
    private String artist;
    private String track;
    private String album;
    @Column(name = "dateTime", columnDefinition = "TIMESTAMP")
    private LocalDateTime date;

    public String getUserName() {
        return userName;
    }
    public String getArtist() {
        return artist;
    }
    public String getTrack() {
        return track;
    }
    public String getAlbum() {
        return album;
    }
    public LocalDateTime getDate() {
        return date;
    }
}
