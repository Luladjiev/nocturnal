using System.ComponentModel.DataAnnotations;
using nocturnal.DTO;

namespace nocturnal.Models;

public class Project : Base
{
    [Required] [StringLength(5)] public required string Key { get; set; }

    [Required] [StringLength(100)] public required string Name { get; set; }

    [StringLength(500)] public string? Description { get; set; }

    public ProjectDto ConvertToDto()
    {
        return new ProjectDto(
            Key,
            Name,
            Description,
            CreatedAt,
            UpdatedAt
        );
    }
}