package com.codegym.spring_boot_sprint_1.controller;


import com.codegym.spring_boot_sprint_1.model.MeetingRoom;
import com.codegym.spring_boot_sprint_1.model.Property;
import com.codegym.spring_boot_sprint_1.model.PropertyMeetingRoom;
import com.codegym.spring_boot_sprint_1.model.dto.MeetingRoomDto;
import com.codegym.spring_boot_sprint_1.model.dto.PropertyInRoomDto;
import com.codegym.spring_boot_sprint_1.service.IMeetingRoomService;
import com.codegym.spring_boot_sprint_1.service.IPropertyMeetingRoomService;
import com.codegym.spring_boot_sprint_1.service.IPropertyService;
import com.codegym.spring_boot_sprint_1.service.IRoomBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/api/meetingRoom")
//@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
public class MeetingRoomController {

    @Autowired
    IMeetingRoomService meetingRoomService;
    @Autowired
    IPropertyService propertyService;
    @Autowired
    IPropertyMeetingRoomService propertyMeetingRoomService;
    @Autowired
    IRoomBookingService roomBookingService;
    private static final String UNDEFINED = "undefined";

    @GetMapping()
    public ResponseEntity<Page<MeetingRoom>> getListMeetingRoom(Pageable pageable) {

        Page<MeetingRoom> meetingRoomPage = meetingRoomService.findAll(pageable);
        if (meetingRoomPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(meetingRoomPage, HttpStatus.OK);
    }

    @PostMapping("/add-new")
    public void create(@RequestBody MeetingRoomDto meetingRoomDto) {
        MeetingRoom meetingRoom = new MeetingRoom();
        meetingRoom.setName(meetingRoomDto.getName());
        meetingRoom.setColor(meetingRoomDto.getColor());
        meetingRoom.setFloor(meetingRoomDto.getFloor());
        meetingRoom.setCapacity(meetingRoomDto.getCapacity());
        meetingRoom.setStatus(meetingRoomDto.getStatus());
        meetingRoomService.create(meetingRoom.getCapacity(), meetingRoom.getName(), meetingRoom.getColor(),
                meetingRoom.getFloor(), meetingRoom.getStatus());
        Long id = meetingRoomService.getByName(meetingRoomDto.getName()).getId();
        meetingRoomService.deleteImageByIdMeetingRoom(id);
        for (int i = 0; i < meetingRoomDto.getImages().size(); i++) {
            meetingRoomService.saveImage(meetingRoomDto.getImages().get(i), id);
        }
        propertyMeetingRoomService.deleteByIdMeetingRoom(id);
        for (int i = 0; i < meetingRoomDto.getPropertyDtoList().size(); i++) {
            Long propertyId = meetingRoomDto.getPropertyDtoList().get(i).getId();
            Integer amount = meetingRoomDto.getPropertyDtoList().get(i).getAmount();
            propertyMeetingRoomService.save(id, propertyId, amount);
            propertyService.updateAmount(meetingRoomDto.getPropertyDtoList().get(i).getAmountTotal(),
                    propertyId);
        }
    }

    @PutMapping("/propertiesEdit")
    public void editProperties(@RequestBody List<PropertyInRoomDto> properties) {
        for (PropertyInRoomDto property : properties) {
            propertyService.updateAmount(property.getAmountTotal(),
                    property.getId());
        }
    }

    @PutMapping("/{id}")
    public void edit(@RequestBody MeetingRoomDto meetingRoomDto, @PathVariable Long id) {
        MeetingRoom meetingRoom = meetingRoomService.findById(id);
        meetingRoom.setName(meetingRoomDto.getName());
        meetingRoom.setFloor(meetingRoomDto.getFloor());
        meetingRoom.setCapacity(meetingRoomDto.getCapacity());
        meetingRoom.setStatus(meetingRoomDto.getStatus());
        meetingRoomService.save(meetingRoom.getCapacity(), meetingRoom.getName(), meetingRoom.getColor(),
                meetingRoom.getFloor(), meetingRoom.getStatus(), meetingRoom.getId());
        meetingRoomService.deleteImageByIdMeetingRoom(id);
        for (int i = 0; i < meetingRoomDto.getImages().size(); i++) {
            meetingRoomService.saveImage(meetingRoomDto.getImages().get(i), id);
        }
        propertyMeetingRoomService.deleteByIdMeetingRoom(id);
        for (int i = 0; i < meetingRoomDto.getPropertyDtoList().size(); i++) {
            Long propertyId = meetingRoomDto.getPropertyDtoList().get(i).getId();
            Integer amount = meetingRoomDto.getPropertyDtoList().get(i).getAmount();
            propertyMeetingRoomService.save(id, propertyId, amount);
        }

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        roomBookingService.deleteRoomById(id);
        meetingRoomService.deleteImageByIdMeetingRoom(id);
        List<PropertyMeetingRoom> propertyMeetingRoomList = propertyMeetingRoomService.getListByIdMeetingRoom(id);
        for (PropertyMeetingRoom property : propertyMeetingRoomList) {
            propertyService.updateAmount(property.getProperty().getAvailability() + property.getAmountInRoom(),
                    property.getProperty().getId());
        }
        propertyMeetingRoomService.deleteByIdMeetingRoom(id);
        meetingRoomService.deleteById(id);
    }


    @GetMapping("/search")
    public List<MeetingRoom> search(@RequestParam(name = "keyword", required = false) String keyword) {
        if (UNDEFINED.equals(keyword) || "".equals(keyword)) {
            keyword = null;
        }
        return meetingRoomService.listSearchOneField(keyword);
    }

    @GetMapping("/properties")
    public List<PropertyInRoomDto> getListPropertyDto(@RequestParam(name = "name", required = false) String name) {
        if (UNDEFINED.equals(name) || "".equals(name)) {
            name = "";
        }
        List<PropertyInRoomDto> propertyDtoList = new ArrayList<>();
        List<Property> propertyList = propertyService.listSearchWithName(name);
        for (Property element : propertyList) {
            propertyDtoList.add(new PropertyInRoomDto(
                    element.getId(),
                    element.getName(),
                    0, element.getAvailability()));
        }

        return propertyDtoList;
    }

    @GetMapping("/detail/{id}")
    public MeetingRoomDto getMeetingRoomById(@PathVariable Long id) {
        List<PropertyInRoomDto> propertyDtoList = new ArrayList<>();
        MeetingRoom meetingRoom = meetingRoomService.findById(id);
        MeetingRoomDto meetingRoomDto = new MeetingRoomDto();
        meetingRoomDto.setId(meetingRoom.getId());
        meetingRoomDto.setColor(meetingRoom.getColor());
        meetingRoomDto.setFloor(meetingRoom.getFloor());
        meetingRoomDto.setCapacity(meetingRoom.getCapacity());
        meetingRoomDto.setStatus(meetingRoom.getStatus());
        meetingRoomDto.setName(meetingRoom.getName());
        meetingRoomDto.setImages(meetingRoomService.getListImageByIdMeetingRoom(id));
        for (PropertyMeetingRoom element : meetingRoom.getRatings()) {
            propertyDtoList.add(new PropertyInRoomDto(
                    element.getProperty().getId(),
                    element.getProperty().getName(),
                    element.getAmountInRoom()));
        }
        meetingRoomDto.setAmountUse(meetingRoomService.getAmountUse(id));
        meetingRoomDto.setPropertyDtoList(propertyDtoList);
        return meetingRoomDto;
    }


}
