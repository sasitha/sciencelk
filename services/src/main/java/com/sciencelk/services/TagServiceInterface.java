package com.sciencelk.services;

import com.sciencelk.dataaccess.exceptions.ItemNotFoundException;
import com.sciencelk.dto.Tag;

import java.util.List;

/**
 * *
 * Created by SasithaG on 6/16/2015.
 */
public interface TagServiceInterface {
    
    List<Tag> getAllTags() throws ItemNotFoundException;
}
