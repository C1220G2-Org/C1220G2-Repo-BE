package com.codegym.spring_boot_sprint_1.controller;

import com.codegym.spring_boot_sprint_1.model.Property;
import com.codegym.spring_boot_sprint_1.model.PropertyMeetingRoom;
import com.codegym.spring_boot_sprint_1.model.dto.PropertyDto;
import com.codegym.spring_boot_sprint_1.model.dto.PropertyMeetingRoomDto;
import com.codegym.spring_boot_sprint_1.service.IPropertyMeetingRoomService;
import com.codegym.spring_boot_sprint_1.service.IPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/properties")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class PropertyController {

    private static final String UNDEFINED = "undefined";

    @Autowired
    private IPropertyService propertyService;
    @Autowired
    private IPropertyMeetingRoomService propertyMeetingRoomService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<Property>> getAllProperty(Pageable pageable) {
        Page<Property> propertiesList = propertyService.findAll(pageable);

        for (Property c : propertiesList) {
            if (c.getUsingProperty() == 0) {
                c.setUsingProperty(0);
            }
            c.setUsingProperty(this.propertyMeetingRoomService.sumPropertyInRoom(c.getId()));
        }

        if (propertiesList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(propertiesList, HttpStatus.OK);
    }

    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<List<Property>> searchPropertyByNameByAmount(@RequestParam String search) {
        if (UNDEFINED.equals(search) || "".equals(search)) {
            search = null;
        }
        List<Property> propertyListSearch = this.propertyService.searchPropertyByNameAndAmount(search);
        for (Property property : propertyListSearch) {
            if (property.getUsingProperty() == 0) {
                property.setUsingProperty(0);
            }
            property.setUsingProperty(this.propertyMeetingRoomService.sumPropertyInRoom(property.getId()));
            if (property.getMaintenance() == null) {
                property.setMaintenance(0);
            }
            if (property.getUsingProperty() == null) {
                property.setUsingProperty(0);
            }
            property.setAvailability(property.getAmount() - property.getMaintenance() - property.getUsingProperty());
        }
        return new ResponseEntity<>(propertyListSearch, HttpStatus.OK);
    }

    @PostMapping
    public void addNewProperty(@RequestBody PropertyDto propertyDto) {
        Property property = new Property();
        property.setName(propertyDto.getName());
        property.setDetail(propertyDto.getDetail());
        property.setPrice(propertyDto.getPrice());
        property.setAmount(propertyDto.getAmount());
        property.setUsingProperty(0);
        property.setMaintenance(propertyDto.getMaintenance());
        property.setAvailability(propertyDto.getAvailability());
        String str = String.join(",", propertyDto.getImage());
        property.setImage(str);
        propertyService.save(property.getName(), property.getDetail(), property.getPrice(), property.getAmount(), property.getImage(), property.getMaintenance(), property.getAvailability());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyDto> getPropertyById(@PathVariable Long id) {
        Property property = this.propertyService.findPropertyById(id);
        PropertyDto propertyDto = new PropertyDto();
        List<PropertyMeetingRoomDto> roomDtos = new ArrayList<>();
        if (property != null) {
            propertyDto.setId(property.getId());
            propertyDto.setName(property.getName());
            propertyDto.setDetail(property.getDetail());
            propertyDto.setPrice(property.getPrice());
            propertyDto.setAmount(property.getAmount());
            propertyDto.setUsingProperty(this.propertyMeetingRoomService.sumPropertyInRoom(id));
            propertyDto.setMaintenance(property.getMaintenance());
            propertyDto.setAvailability(property.getAvailability());

            List<PropertyMeetingRoom> propertyInRoomList = this.propertyMeetingRoomService.findPropertyInRoom(id);
            for (PropertyMeetingRoom propertyInRoom : propertyInRoomList) {
                PropertyMeetingRoomDto roomDto = new PropertyMeetingRoomDto();
                roomDto.setName(propertyInRoom.getMeetingRoom().getName());
                roomDto.setAmountInRoom(propertyInRoom.getAmountInRoom());
                roomDtos.add(roomDto);
            }
            propertyDto.setPropertyMeetingRoomDto(roomDtos);
            String[] arrImage = property.getImage().split(",");
            propertyDto.setImage(arrImage);
            return new ResponseEntity<>(propertyDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateProperty(@PathVariable Long id, @RequestBody PropertyDto propertyDto) {
        Property property = propertyService.findPropertyById(id);
        if (property == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        property.setId(id);
        property.setName(propertyDto.getName());
        property.setDetail(propertyDto.getDetail());
        property.setPrice(propertyDto.getPrice());
        property.setAmount(propertyDto.getAmount());
        property.setMaintenance(propertyDto.getMaintenance());
        propertyDto.setUsingProperty(this.propertyMeetingRoomService.sumPropertyInRoom(id));
        if (propertyDto.getUsingProperty() == null) {
            propertyDto.setUsingProperty(0);
        }
        property.setAvailability(propertyDto.getAmount() - propertyDto.getMaintenance() - propertyDto.getUsingProperty());
        String str = String.join(",", propertyDto.getImage());
        property.setImage(str);
        propertyService.updateProperty(property.getName(), property.getDetail(), property.getPrice(), property.getAmount(), property.getImage(), property.getMaintenance(), property.getAvailability(), property.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteProperty(@PathVariable Long id) {
        propertyMeetingRoomService.deleteByPropertyId(id);
        propertyService.deletePropertyById(id);
    }
}
