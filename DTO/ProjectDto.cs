namespace nocturnal.DTO;

public record ProjectDto(
    string Key,
    string Name,
    string? Description,
    DateTime CreatedAt,
    DateTime UpdatedAt
);