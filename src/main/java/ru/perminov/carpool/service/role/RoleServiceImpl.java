package ru.perminov.carpool.service.role;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        return getRole(id);
    }

    @Override
    @Transactional
    public Role update(Role role, Long id) {
        Role r = getRole(id);
        if (role.getName() != null) {
            r.setName(role.getName());
        }
        if (role.getDescription() != null) {
            r.setDescription(role.getDescription());
        }
        roleRepository.save(r);
        return r;
    }

    @Override
    public void deleteById(Long id) {
        Role role = getById(id);
        roleRepository.delete(role);
    }

    private Role getRole(long id) {
        return roleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Roles not found"));
    }
}
