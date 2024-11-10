package ru.perminov.carpool.service.role;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.perminov.carpool.dto.role.RoleDto;
import ru.perminov.carpool.mapper.RoleMapper;
import ru.perminov.carpool.model.Role;
import ru.perminov.carpool.repository.RoleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Override
    public Role create(RoleDto roleDto) {
        return roleRepository.save(RoleMapper.toEntity(roleDto));
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role getById(Long id) {
        return roleRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Roles not found"));
    }
}
