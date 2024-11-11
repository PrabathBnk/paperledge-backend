package edu.icet.service;

import edu.icet.dto.Status;

public interface StatusService {
    Status getByName(String name);
}
