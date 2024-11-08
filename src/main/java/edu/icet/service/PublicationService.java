package edu.icet.service;

import edu.icet.dto.Publication;

public interface PublicationService {
    Publication getPublicationByName(String name);
    Publication addPublication(Publication publication);
}
