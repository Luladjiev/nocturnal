namespace nocturnal.DTO;

public record CreateProjectDto(
    string Key,
    string Name,
    string? Description
);