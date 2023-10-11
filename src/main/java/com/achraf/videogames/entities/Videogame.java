package com.achraf.videogames.entities;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Videogame {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long idVideogame;
    private String nomVideogame ;
    private double prixVideogame ;
    private Date dateCreation ;
    @ManyToOne
    private Genre genre;
	@OneToMany (mappedBy = "videogame")
	@JsonIgnore
	private List<Image> images;

	private String imagePath;

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

public Videogame() {
	  super();
  }

  @Override
public String toString() {
	return "Videogame [idVideogame=" + idVideogame + ", nomVideogame=" + nomVideogame + ", prixVideogame="
			+ prixVideogame + ", dateCreation=" + dateCreation + "]";
}

public Long getIdVideogame() {
	return idVideogame;
}

public void setIdVideogame(Long idVideogame) {
	this.idVideogame = idVideogame;
}

public String getNomVideogame() {
	return nomVideogame;
}

public void setNomVideogame(String nomVideogame) {
	this.nomVideogame = nomVideogame;
}

public double getPrixVideogame() {
	return prixVideogame;
}

public void setPrixVideogame(double prixVideogame) {
	this.prixVideogame = prixVideogame;
}

public Date getDateCreation() {
	return dateCreation;
}

public void setDateCreation(Date dateCreation) {
	this.dateCreation = dateCreation;
}

public Videogame(String nomVideogame,double prixVideogame,Date dateCreation) {
	  this.nomVideogame = nomVideogame ;
	  this.prixVideogame = prixVideogame;
	  this.dateCreation = dateCreation ;
  }

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public void AddImage(Image image){
		this.images.add(image);
	}
}
