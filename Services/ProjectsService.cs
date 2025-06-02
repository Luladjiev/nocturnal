using Microsoft.EntityFrameworkCore;
using nocturnal.Db;
using nocturnal.DTO;
using nocturnal.Models;

namespace nocturnal.Services;

public class ProjectsService(NocturnalDb db)
{
    public async Task<ProjectDto> Create(CreateProjectDto project)
    {
        var entry = db.Projects.Add(new Project
        {
            Key = project.Key,
            Name = project.Name,
            Description = project.Description
        });

        await db.SaveChangesAsync();

        return entry.Entity.ConvertToDto();
    }

    public async Task<ProjectDto?> Get(string key)
    {
        return await db.Projects.FindAsync(key) is { } project ? project.ConvertToDto() : null;
    }

    public async Task<List<ProjectDto>> List()
    {
        return await db.Projects.Select(p => new ProjectDto(
            p.Key,
            p.Name,
            p.Description,
            p.CreatedAt,
            p.UpdatedAt
        )).ToListAsync();
    }
}