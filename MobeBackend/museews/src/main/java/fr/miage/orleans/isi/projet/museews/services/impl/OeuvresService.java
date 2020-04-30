package fr.miage.orleans.isi.projet.museews.services.impl;

import fr.miage.orleans.isi.projet.museews.dao.OeuvreDAO;
import fr.miage.orleans.isi.projet.museews.dto.OeuvreDTO;
import fr.miage.orleans.isi.projet.museews.dto.SalleDTO;
import fr.miage.orleans.isi.projet.museews.entities.OeuvreEntity;
import fr.miage.orleans.isi.projet.museews.services.IOeuvreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OeuvresService implements IOeuvreService {
    @Autowired
    private OeuvreDAO oeuvreDAO;

    @Override
    public OeuvreEntity getOeuvreEntityById(Integer id) {
        return oeuvreDAO.findOeuvreEntityById(id);
    }

    @Override
    public OeuvreDTO getOeuvreById(Integer id) {
        OeuvreEntity oeuvreEntity = oeuvreDAO.findOeuvreEntityById(id);
        if(oeuvreEntity == null){
            return null;
        }else {
            OeuvreDTO oeuvreDTO = new OeuvreDTO();
            oeuvreDTO.setId(oeuvreEntity.getId());
            oeuvreDTO.setNomOeuvre(oeuvreEntity.getNomOeuvre());
            oeuvreDTO.setDateAchat(oeuvreEntity.getDateAchat());
            oeuvreDTO.setPrixAchat(oeuvreEntity.getPrixAchat());
            return oeuvreDTO;
        }
    }

    @Override
    public void deleteOeuvre(Integer id) {
        oeuvreDAO.removeById(id);
    }

    @Override
    public List<OeuvreDTO> getAllOeuvres() {
        List<OeuvreEntity> oeuvreEntities = oeuvreDAO.findAll();
        List<OeuvreDTO> oeuvreDTOS = new ArrayList<>();
        for (OeuvreEntity oeuvreEntity: oeuvreEntities) {
            OeuvreDTO oeuvreDTO = new OeuvreDTO();
            oeuvreDTO.setId(oeuvreEntity.getId());
            oeuvreDTO.setNomOeuvre(oeuvreEntity.getNomOeuvre());
            oeuvreDTO.setDateAchat(oeuvreEntity.getDateAchat());
            oeuvreDTO.setPrixAchat(oeuvreEntity.getPrixAchat());
            oeuvreDTOS.add(oeuvreDTO);
        }
        return oeuvreDTOS;
    }

    @Override
    public OeuvreDTO createOeuvre(String nom, Date date, double prix) {
        OeuvreEntity oeuvreEntity = new OeuvreEntity();
        oeuvreEntity.setNomOeuvre(nom);
        oeuvreEntity.setDateAchat(date);
        oeuvreEntity.setPrixAchat(prix);
        oeuvreDAO.save(oeuvreEntity);
        OeuvreDTO oeuvreDTO = new OeuvreDTO();
        oeuvreDTO.setId(oeuvreEntity.getId());
        oeuvreDTO.setNomOeuvre(oeuvreEntity.getNomOeuvre());
        oeuvreDTO.setDateAchat(oeuvreEntity.getDateAchat());
        oeuvreDTO.setPrixAchat(oeuvreEntity.getPrixAchat());
        return oeuvreDTO;
    }

    @Override
    public void MoveOeuvre(int idOeuvre, SalleDTO salleDTO) {

    }

    @Override
    public void setPrixOeuvre(int id, double prix) {
        OeuvreEntity oeuvreEntity = oeuvreDAO.findOeuvreEntityById(id);
        oeuvreEntity.setPrixAchat(prix);
        oeuvreDAO.save(oeuvreEntity);
    }

    @Override
    public OeuvreDTO getOeuvreByNom(String nom) {
        OeuvreEntity oeuvreEntity = oeuvreDAO.findOeuvreEntitiesByNomOeuvre(nom);
        if(oeuvreEntity == null){
            return null;
        }else {
            OeuvreDTO oeuvreDTO = new OeuvreDTO();
            oeuvreDTO.setId(oeuvreEntity.getId());
            oeuvreDTO.setNomOeuvre(oeuvreEntity.getNomOeuvre());
            oeuvreDTO.setDateAchat(oeuvreEntity.getDateAchat());
            oeuvreDTO.setPrixAchat(oeuvreEntity.getPrixAchat());
            return oeuvreDTO;
        }
    }

    @Override
    public OeuvreEntity getOeuvreEntityByNom(String nom) {
        return oeuvreDAO.findOeuvreEntityByNomOeuvre(nom);
    }

    @Override
    public OeuvreDTO modifierOeuvreById(Integer oeuvreId, OeuvreDTO oeuvreDTO) {
        OeuvreEntity oeuvreEntity = oeuvreDAO.findOeuvreEntityById(oeuvreId);
        oeuvreEntity.setNomOeuvre(oeuvreDTO.getNomOeuvre());
        oeuvreEntity.setPrixAchat(oeuvreDTO.getPrixAchat());
        oeuvreEntity.setDateAchat(oeuvreDTO.getDateAchat());
        oeuvreDTO.setId(oeuvreEntity.getId());
        oeuvreDAO.save(oeuvreEntity);
        return oeuvreDTO;
    }
}
