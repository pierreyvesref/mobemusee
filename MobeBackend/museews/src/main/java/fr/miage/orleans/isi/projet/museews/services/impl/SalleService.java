package fr.miage.orleans.isi.projet.museews.services.impl;

import fr.miage.orleans.isi.projet.museews.dao.SalleDAO;
import fr.miage.orleans.isi.projet.museews.dto.OeuvreDTO;
import fr.miage.orleans.isi.projet.museews.dto.SalleDTO;
import fr.miage.orleans.isi.projet.museews.entities.OeuvreEntity;
import fr.miage.orleans.isi.projet.museews.entities.SallesEntity;
import fr.miage.orleans.isi.projet.museews.exceptions.OeuvreNonExistanteException;
import fr.miage.orleans.isi.projet.museews.services.ISalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SalleService implements ISalleService {

    @Autowired
    private SalleDAO salleDAO;

    @Override
    public SallesEntity getSallebyId(Integer id){
        return salleDAO.findSallesEntityById(id);
    }
    @Override
    public SalleDTO getSalleById(Integer id) {
        SallesEntity sallesEntity = salleDAO.findSallesEntityById(id);
        SalleDTO salleDTO = new SalleDTO();
        salleDTO.setId(sallesEntity.getId());
        salleDTO.setValue(sallesEntity.getValue());
        for (OeuvreEntity oeuvreEntity : sallesEntity.getOeuvres()){
            OeuvreDTO oeuvre = new OeuvreDTO(oeuvreEntity.getId(), oeuvreEntity.getNomOeuvre(), oeuvreEntity.getDateAchat(), oeuvreEntity.getPrixAchat());
            salleDTO.addOeuvre(oeuvre);
        }
        return salleDTO;
    }

    @Override
    public List<SalleDTO> getAllSalles() {
        List<SallesEntity> sallesEntities = salleDAO.findAll();
        List<SalleDTO> salleDTOS = new ArrayList<>();
        for(SallesEntity sallesEntity : sallesEntities){
            SalleDTO salleDTO = new SalleDTO();
            salleDTO.setId(sallesEntity.getId());
            salleDTO.setValue(sallesEntity.getValue());
            for (OeuvreEntity oeuvreEntity : sallesEntity.getOeuvres()){
                OeuvreDTO oeuvre = new OeuvreDTO(oeuvreEntity.getId(), oeuvreEntity.getNomOeuvre(), oeuvreEntity.getDateAchat(), oeuvreEntity.getPrixAchat());
                salleDTO.getOeuvres().add(oeuvre);
            }
            salleDTOS.add(salleDTO);
        }
        return salleDTOS;
    }

    @Override
    public List<OeuvreDTO> getOeuvresSalle(int id) {
        SallesEntity sallesEntity= salleDAO.findSallesEntityById(id);
        if(sallesEntity==null){
            return null;
        }else{
            List<OeuvreDTO> list = new ArrayList<>();
            for (OeuvreEntity oe: sallesEntity.getOeuvres()) {
                OeuvreDTO oeuvreDTO = new OeuvreDTO();
                oeuvreDTO.setNomOeuvre(oe.getNomOeuvre());
                oeuvreDTO.setPrixAchat(oe.getPrixAchat());
                oeuvreDTO.setDateAchat(oe.getDateAchat());
                oeuvreDTO.setId(oe.getId());
                list.add(oeuvreDTO);

            }
            return list;
        }
    }

    @Override
    public SalleDTO changeNameSalle(int id, String name) {
        SallesEntity sallesEntity= salleDAO.findSallesEntityById(id);
        if(sallesEntity==null){
            return null;
        }else{
            sallesEntity.setValue(name);
            SalleDTO salleDTO = new SalleDTO();
            salleDTO.setValue(sallesEntity.getValue());
            for (OeuvreEntity oeuvreEntity : sallesEntity.getOeuvres()){
                OeuvreDTO oeuvre = new OeuvreDTO(oeuvreEntity.getId(), oeuvreEntity.getNomOeuvre(), oeuvreEntity.getDateAchat(), oeuvreEntity.getPrixAchat());
                salleDTO.getOeuvres().add(oeuvre);
            }
            salleDAO.save(sallesEntity);
            return salleDTO;
        }
    }

    @Override
    @Transactional
    public SalleDTO createSalle(String value)  {
        SallesEntity test2 = new SallesEntity();
        test2.setValue(value);
        test2.setOeuvres(new ArrayList<OeuvreEntity>());
        salleDAO.save(test2);
        return SallesEntity.createDTOFromEntity(test2);
    }

    @Override
    @Transactional
    public SalleDTO ajouterOeuvreSalle(int id0, OeuvreEntity oeuvre)  {
       SallesEntity sallesEntity = salleDAO.findSallesEntityById(id0);
       List<OeuvreEntity> list = sallesEntity.getOeuvres();
       list.add(oeuvre);
       sallesEntity.setOeuvres(list);
       SalleDTO salleDTO = SallesEntity.createDTOFromEntity(sallesEntity);
       salleDAO.save(sallesEntity);
       return salleDTO;
    }

    @Override
    public SalleDTO getSalleByValue(String value) {
        SallesEntity sallesEntity = salleDAO.findSallesEntityByValue(value);
        if(sallesEntity!=null){
            return SallesEntity.createDTOFromEntity(salleDAO.findSallesEntityByValue(value));
        }else return null;
    }

    @Override
    public void delete(int id) {
        salleDAO.removeById(id);
    }

    @Override
    public OeuvreEntity getOeuvreSalleById(int id, int oeuvre) {
        SallesEntity sallesEntity = salleDAO.findSallesEntityById(id);
        for (OeuvreEntity oe : sallesEntity.getOeuvres()) {
            if(oe.getId()==oeuvre){
                return oe;
            }
        }
        return null;
    }

    @Override
    @Transactional
    public void removeOeuvreSalle(int id, int oeuvre) {
        SallesEntity sallesEntity = salleDAO.findSallesEntityById(id);
        List<OeuvreEntity> oeuvreEntityList = sallesEntity.getOeuvres();
        int ok =0;
        OeuvreEntity oeuvreEntity=null;
        for (OeuvreEntity oe : sallesEntity.getOeuvres()) {
            if(oe.getId()==oeuvre){
                ok++;
                oeuvreEntity=oe;
            }
        }
        if(ok>0) {
            oeuvreEntityList.remove(oeuvreEntity);
        }
        sallesEntity.setOeuvres(oeuvreEntityList);
        salleDAO.save(sallesEntity);
    }

    @Override
    public SallesEntity getSalleEntityByValue(String value) {
        return salleDAO.findSallesEntityByValue(value);
    }

    @Override
    public void supprimerOeuvreTouteSalle(int id) {
        List<SallesEntity> sallesEntities = salleDAO.findAll();
        for (SallesEntity s : sallesEntities) {
            List<OeuvreEntity> aRetirer = new ArrayList<>();
            for(OeuvreEntity oe: s.getOeuvres()){
                if(oe.getId()==id){
                    aRetirer.add(oe);
                }
            }
            if (aRetirer.size()!=0){
                for (OeuvreEntity oes : aRetirer){
                    s.getOeuvres().remove(oes);
                }
            }
        }
        salleDAO.saveAll(sallesEntities);
    }

}
